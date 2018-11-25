package com.wkrent.business.bg.usermanagement.service.impl;

import com.wkrent.business.bg.usermanagement.dao.BgUserDao;
import com.wkrent.business.bg.usermanagement.service.BgUserService;
import com.wkrent.business.bg.usermanagement.service.BgUserRoleService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.BgUser;
import com.wkrent.common.entity.po.BgUserRole;
import com.wkrent.common.entity.vo.BgUserVO;
import com.wkrent.common.exception.WkRentException;
import com.wkrent.common.util.BeanUtil;
import com.wkrent.common.util.OperatorUtil;
import com.wkrent.common.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class BgUserServiceImpl implements BgUserService {

    @Autowired
    private BgUserDao bgUserDao;

    @Autowired
    private BgUserRoleService bgUserRoleService;

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
    public BgUser findByUserAccount(String userAccount) {
        if(StringUtils.isBlank(userAccount)){
            return null;
        }
        return bgUserDao.findByUserAccount(userAccount);
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
            List<BgUser> bgUserList = bgUserDao.findByCondition(userVO);
            pageResult.setRows(BeanUtil.copyList(bgUserList, BgUserVO.class));
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
            BgUser existUser = findByUserAccount(bgUserVO.getBgUserAccount());
            if(existUser != null){
                throw new WkRentException("新增用户失败，用户账号已存在！");
            }
            BgUser bgUser = BeanUtil.copyBean(bgUserVO, BgUser.class);
            bgUser.setBgUserId(UUIDUtil.getUUID());
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Add, bgUser, loginAccount);
            int result = bgUserDao.insertUser(bgUser);
            if(result != 1){
                throw new WkRentException("新增用户失败，服务器异常！");
            }
            insertUserRoleInfo(bgUserVO.getRoleId(), bgUser.getBgUserId());
            bgUserVO.setBgUserId(bgUser.getBgUserId());
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
        updateUser.setIsDelete(Constants.STR_FALSE);
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
