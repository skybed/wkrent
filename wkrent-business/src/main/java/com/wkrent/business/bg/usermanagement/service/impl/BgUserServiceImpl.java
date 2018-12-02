package com.wkrent.business.bg.usermanagement.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.menumanagement.service.BgMenuService;
import com.wkrent.business.bg.rolemanagement.service.BgRoleAuthService;
import com.wkrent.business.bg.usermanagement.dao.BgUserDao;
import com.wkrent.business.bg.usermanagement.service.BgUserRoleService;
import com.wkrent.business.bg.usermanagement.service.BgUserService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.enums.SexEnum;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.BgRoleAuth;
import com.wkrent.common.entity.po.BgUser;
import com.wkrent.common.entity.po.BgUserRole;
import com.wkrent.common.entity.vo.BgMenuVO;
import com.wkrent.common.entity.vo.BgUserRoleVO;
import com.wkrent.common.entity.vo.BgUserVO;
import com.wkrent.common.exception.WkRentException;
import com.wkrent.common.util.BeanUtil;
import com.wkrent.common.util.Md5Utils;
import com.wkrent.common.util.OperatorUtil;
import com.wkrent.common.util.UUIDUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
@Service
public class BgUserServiceImpl implements BgUserService {

    @Autowired
    private BgUserDao bgUserDao;

    @Autowired
    private BgUserRoleService bgUserRoleService;

    @Autowired
    private BgRoleAuthService bgRoleAuthService;

    @Autowired
    private BgMenuService bgMenuService;

    /**
     * 查询所有用户信息
     * @return 用户信息
     */
    @Override
    public List<BgUser> getAllUser() {
        return bgUserDao.getAllUserList();
    }


    /**
     * 根据用户名查询用户信息
     * @param userAccount 用户名
     * @return 符合条件用户信息
     */
    @Override
    public BgUserVO findByUserAccount(String userAccount) {
        if(StringUtils.isBlank(userAccount)){
            return null;
        }
        //获取到当前角色下所有菜单信息
        BgUser bgUser =  bgUserDao.findByUserAccount(userAccount);
        if(bgUser == null){
            return null;
        }
        return BeanUtil.copyBean(bgUser, BgUserVO.class);
    }

    /**
     * 根据用户id获取当前用户下所有菜单权限
     * @param userId id
     * @return 用户菜单权限
     */
    @Override
    public List<BgMenuVO> queryMenuListByUser(String userId){
        List<BgMenuVO> menuVOList = Lists.newArrayList();
        //根据角色id查询到当前用户菜单信息
        BgUserRole bgUserRole = new BgUserRole();
        bgUserRole.setBgUserId(userId);
        List<BgUserRoleVO> bgUserRoleVOList = bgUserRoleService.findByCondition(bgUserRole);
        if(CollectionUtils.isEmpty(bgUserRoleVOList)){
            return menuVOList;
        }
        //获取当前用户角色idList
        List<String> roleIdList = Lists.newArrayList();
        bgUserRoleVOList.forEach(roleInfo -> roleIdList.add(roleInfo.getBgRoleId()));

        List<BgRoleAuth> bgRoleAuthList =  bgRoleAuthService.queryByRoleIdList(roleIdList);
        if(CollectionUtils.isEmpty(bgRoleAuthList)){
            return menuVOList;
        }
        //获取菜单idList
        Set<String> menuIdSet = new HashSet<>();
        bgRoleAuthList.forEach(bgRoleAuth -> menuIdSet.add(bgRoleAuth.getBgMenuId()));
        if(CollectionUtils.isEmpty(menuIdSet)){
            return menuVOList;
        }
        //当前角色对应menuId为空，则返回
        List<BgMenuVO> menuList = bgMenuService.findByIdList(Lists.newArrayList(menuIdSet));
        if(CollectionUtils.isEmpty(menuList)){
            return menuVOList;
        }
        //筛选当前用户一级菜单
        for(BgMenuVO menuVO : menuList){
            if(StringUtils.isBlank(menuVO.getBgMenuParentId())){
                //获取到当前菜单下所有子菜单
                List<BgMenuVO> childrenList = bgMenuService.queryMenuList(menuVO.getBgMenuId());
                menuVO.setChildMenuList(childrenList);
                menuVOList.add(menuVO);
            }
        }
        return menuVOList;
    }

    /**
     * 分页查询用户信息
     *
     * @param userVO 查询条件
     * @return 符合条件分页信息
     */
    @Override
    public PageResult<BgUserVO> findByCondition(BgUserVO userVO) {
        PageResult<BgUserVO> pageResult = new PageResult<>();

        int total = bgUserDao.countByCondition(userVO);
        if(total > 0){
            List<BgUserVO> bgUserList = bgUserDao.findByCondition(userVO);
            for(BgUserVO bgUserVO : bgUserList){
                if(SexEnum.getByCode(bgUserVO.getBgUserSex()) != null){
                    bgUserVO.setBgUserSexStr(SexEnum.getByCode(bgUserVO.getBgUserSex()).getDesc());
                }
            }
            pageResult.setRows(bgUserList);
            pageResult.setTotal(total);
        }
        return pageResult;
    }

    /**
     * 新增用户信息
     *
     * @param bgUserVO 用户信息
     * @return 新增条数
     */
    @Override
    public  BaseAjaxVO insert(BgUserVO bgUserVO, String loginAccount) {
        BaseAjaxVO ajaxVO = new BaseAjaxVO();
        //校验用户信息
        if(checkUserInfo(bgUserVO)){
            //校验用户账号是否存在
            BgUser existUser = bgUserDao.findByUserAccount(bgUserVO.getBgUserAccount());
            if(existUser != null){
                throw new WkRentException("新增用户失败，用户账号已存在！");
            }
            BgUser bgUser = BeanUtil.copyBean(bgUserVO, BgUser.class);
            //加密用户密码
            bgUser.setBgUserPwd(Md5Utils.encryptPassword(bgUser.getBgUserAccount(), bgUser.getBgUserPwd(), Md5Utils.SALT));
            bgUser.setBgUserId(UUIDUtil.getUUID());
            bgUser.setIsActive(Constants.STR_TRUE);
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Add, bgUser, loginAccount);
            int result = bgUserDao.insertUser(bgUser);
            if(result != 1){
                throw new WkRentException("新增用户失败，服务器异常！");
            }
            insertUserRoleInfo(bgUserVO.getRoleId(), bgUser.getBgUserId());
            bgUserVO.setBgUserId(bgUser.getBgUserId());
            bgUserVO.setIsActive(bgUser.getIsActive());
            ajaxVO.setResult(bgUserVO);
        }
        return ajaxVO;
    }

    /**
     * 新增用户角色信息
     * @param roleId 角色id
     * @param userId 用户id
     */
    private void insertUserRoleInfo(String roleId, String userId) {
        BgUserRole bgUserRole = new BgUserRole();
        bgUserRole.setBgRoleId(roleId);
        bgUserRole.setBgUserId(userId);
        bgUserRoleService.insert(bgUserRole);
    }

    /**
     * 根据用户账号修改用户信息
     *
     * @param bgUserVO 待修改用户信息
     * @param loginAccount 登录账号
     */
    @Override
    public void update(BgUserVO bgUserVO, String loginAccount) {
            //校验用户信息
        if(checkUserInfo(bgUserVO)){
            BgUser user = bgUserDao.findById(bgUserVO.getBgUserId());
            if(user == null){
                throw new WkRentException("更新用户信息失败，用户信息已被删除！");
            }
            BgUser bgUser = BeanUtil.copyBean(bgUserVO, BgUser.class);
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, bgUser, loginAccount);
            int result = bgUserDao.update(bgUser);
            if(result != 1){
                throw new WkRentException("更新用户信息失败，用户信息已变更！");
            }
            //删除已有账户角色信息后新增账户角色信息
            bgUserRoleService.deleteByUserId(bgUser.getBgUserId());
            insertUserRoleInfo(bgUserVO.getRoleId(), bgUser.getBgUserId());
        }
    }

    /**
     * 根据用户账号修改用户密码
     *
     * @param bgUserVO     待修改用户信息
     * @param loginAccount 当前登录账号
     * @return 修改条数
     */
    @Override
    public void updatePassWord(BgUserVO bgUserVO, String loginAccount) {
        if(StringUtils.isBlank(bgUserVO.getBgUserId())){
            throw new WkRentException("重置密码失败，用户id不能为空");
        }
        if(StringUtils.isBlank(bgUserVO.getBgUserPwd())){
            throw new WkRentException("重置密码失败，新密码不能为空");
        }
        BgUser user = bgUserDao.findById(bgUserVO.getBgUserId());
        if(user == null){
            throw new WkRentException("重置密码失败，用户信息已被删除！");
        }
        BgUser updateUser = new BgUser();
        //设置密码（加密后）
        updateUser.setBgUserPwd(Md5Utils.encryptPassword(user.getBgUserAccount(), bgUserVO.getBgUserPwd(), Md5Utils.SALT));
        updateUser.setBgUserId(bgUserVO.getBgUserId());
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateUser, loginAccount);
        bgUserDao.updatePassWord(updateUser);
    }

    /**
     * 根据id删除用户
     *
     * @param userId 用户id
     */
    @Override
    public void delete(String userId, String loginAccount) {
        if(StringUtils.isBlank(userId)){
            throw new WkRentException("删除用户失败，请传入用户id");
        }
        BgUser bgUser = bgUserDao.findById(userId);
        if(bgUser == null){
            return;
        }
        BgUser updateUser = new BgUser();
        updateUser.setBgUserId(userId);
        updateUser.setIsDelete(Constants.STR_TRUE);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateUser, loginAccount);
        int result = bgUserDao.delete(updateUser);
        if(result != 1){
            throw new WkRentException("删除用户失败，用户信息已被删除！");
        }
    }

    /**
     * 根据id锁定用户
     *
     * @param userId 用户Id
     * @param loginAccount 登录用户账号
     */
    @Override
    public void lockAccount(String userId, String loginAccount) {
        if(StringUtils.isBlank(userId)){
            throw new WkRentException("锁定用户失败，请传入用户id");
        }
        BgUser bgUser = bgUserDao.findById(userId);
        if(bgUser == null){
            throw new WkRentException("锁定用户失败，用户信息已被删除");
        }
        //用户已被锁定，不能再次锁定
        if (Constants.STR_FALSE.equals(bgUser.getIsActive())) {
            throw new WkRentException("锁定用户失败，用户信息已被锁定");
        }
        BgUser updateUser = new BgUser();
        updateUser.setBgUserId(userId);
        updateUser.setIsActive(Constants.STR_FALSE);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateUser, loginAccount);
        int result = bgUserDao.updateUserStatus(updateUser);
        if(result != 1){
            throw new WkRentException("锁定用户信息失败，用户信息已变更！");
        }
    }

    /**
     * 根据id解锁用户
     *
     * @param userId 用户Id
     * @param loginAccount 登录账号
     */
    @Override
    public void unlockAccount(String userId, String loginAccount) {
        if(StringUtils.isBlank(userId)){
            throw new WkRentException("解锁用户失败，请传入用户id");
        }
        BgUser bgUser = bgUserDao.findById(userId);
        if(bgUser == null){
            throw new WkRentException("解锁用户失败，用户信息已被删除");
        }
        //用户已被锁定，不能再次锁定
        if (Constants.STR_TRUE.equals(bgUser.getIsActive())) {
            throw new WkRentException("解锁用户失败，用户信息已被解锁");
        }
        BgUser updateUser = new BgUser();
        updateUser.setBgUserId(userId);
        updateUser.setIsActive(Constants.STR_TRUE);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateUser, loginAccount);
        int result = bgUserDao.updateUserStatus(updateUser);
        if(result != 1){
            throw new WkRentException("解锁用户信息失败，用户信息已变更！");
        }
    }

    /**
     * 校验新增/修改用户信息有效性
     * @param bgUserVO 用户信息
     * @return 校验结果
     */
    private boolean checkUserInfo(BgUserVO bgUserVO){
        //用户姓名
        if(StringUtils.isBlank(bgUserVO.getBgUserName())){
            throw new WkRentException("保存用户信息失败，用户姓名不能为空");
        }
        if(StringUtils.isBlank(bgUserVO.getBgUserSex())){
            throw new WkRentException("保存用户信息失败，用户性别不能为空");
        }
        if(StringUtils.isBlank(bgUserVO.getBgUserPhone())){
            throw new WkRentException("保存用户信息失败，用户手机号码不能为空");
        }
        if(StringUtils.isBlank(bgUserVO.getBgUserEmail())){
            throw new WkRentException("保存用户信息失败，用户邮箱不能为空");
        }
        if(StringUtils.isBlank(bgUserVO.getBgUserEmpId())){
            throw new WkRentException("保存用户信息失败，用户员工工号不能为空");
        }
        if (StringUtils.isBlank(bgUserVO.getRoleId())) {
            throw new WkRentException("保存用户信息失败，用户角色信息不能为空");
        }
        if(StringUtils.isBlank(bgUserVO.getBgUserDept())){
            throw new WkRentException("保存用户信息失败，用户所属部门不能为空");
        }
        if(StringUtils.isBlank(bgUserVO.getBgUserAccount())){
            throw new WkRentException("保存用户信息失败，用户登录账号不能为空");
        }
        if(StringUtils.isBlank(bgUserVO.getBgUserPwd())){
            throw new WkRentException("保存用户信息失败，用户登录密码不能为空");
        }
        return true;
    }

}
