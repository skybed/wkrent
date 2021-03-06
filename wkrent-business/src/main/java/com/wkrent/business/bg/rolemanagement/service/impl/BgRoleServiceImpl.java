package com.wkrent.business.bg.rolemanagement.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.rolemanagement.dao.BgRoleDao;
import com.wkrent.business.bg.rolemanagement.service.BgRoleAuthService;
import com.wkrent.business.bg.rolemanagement.service.BgRoleService;
import com.wkrent.business.bg.usermanagement.dao.BgUserRoleDao;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.BgRole;
import com.wkrent.common.entity.po.BgRoleAuth;
import com.wkrent.common.entity.po.BgUserRole;
import com.wkrent.common.entity.vo.BgRoleVO;
import com.wkrent.common.entity.vo.BgUserRoleVO;
import com.wkrent.common.exception.WkRentException;
import com.wkrent.common.util.BeanUtil;
import com.wkrent.common.util.OperatorUtil;
import com.wkrent.common.util.UUIDUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
@Service
public class BgRoleServiceImpl implements BgRoleService {

    @Autowired
    private BgRoleDao bgRoleDao;

    @Autowired
    private BgRoleAuthService bgRoleAuthService;

    @Autowired
    private BgUserRoleDao bgUserRoleDao;



    /**
     * 分页查询角色信息
     * @param roleVO 查询条件
     * @return 符合条件分页信息
     */
    @Override
    public PageResult<BgRoleVO> findByCondition(BgRoleVO roleVO) {
        PageResult<BgRoleVO> pageResult = new PageResult<>();

        int total = bgRoleDao.countByCondition(roleVO);
        if(total > 0){
            List<BgRole> bgRoleList = bgRoleDao.findByCondition(roleVO);
            List<BgRoleVO> bgRoleVOList = BeanUtil.copyList(bgRoleList, BgRoleVO.class);
            //设置权限信息
            setAuthInfoByRole(bgRoleVOList);
            pageResult.setRows(bgRoleVOList);
            pageResult.setTotal(total);
        }
        return pageResult;
    }

    private void setAuthInfoByRole(List<BgRoleVO> bgRoleVOList){
        if(CollectionUtils.isEmpty(bgRoleVOList)){
            return;
        }
        Set<String> roleIdSet = new HashSet<>();
        bgRoleVOList.forEach((roleVO )-> roleIdSet.add(roleVO.getBgRoleId()));
        //根据roleIdList 查询 权限信息
        List<BgRoleAuth> bgRoleAuthList = bgRoleAuthService.queryByRoleIdList(Lists.newArrayList(roleIdSet));
        if(CollectionUtils.isEmpty(bgRoleAuthList)){
            return;
        }
        Map<String, List<String>> roleAuthIdMap = new HashMap<>();
        bgRoleAuthList.forEach((roleAuth )-> transferRoleAuthMap(roleAuthIdMap, roleAuth));
        bgRoleVOList.forEach((roleVO )-> roleVO.setAuthList(roleAuthIdMap.get(roleVO.getBgRoleId())));
    }

    /**
     * 角色权限信息转换为mao
     * key：roleId  value：authIdList
     * @param roleAuthIdMap 角色权限map
     * @param roleAuth 角色权限信息
     */
    private void transferRoleAuthMap(Map<String, List<String>> roleAuthIdMap, BgRoleAuth roleAuth){
        String key = roleAuth.getBgRoleId();
        if(roleAuthIdMap.containsKey(key)){
            roleAuthIdMap.get(key).add(roleAuth.getBgMenuId());
        }else {
            roleAuthIdMap.put(key, Lists.newArrayList(roleAuth.getBgMenuId()));
        }
    }

    /**
     * 查询所有启用角色信息
     *
     * @return 符合条件角色信息
     */
    @Override
    public BaseAjaxVO queryRoleInfo() {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        BgRole bgRole = new BgRole();
        bgRole.setIsActive(Constants.STR_TRUE);
        List<BgRole> bgRoleList = bgRoleDao.queryRoleInfo(bgRole);
        if(CollectionUtils.isNotEmpty(bgRoleList)){
            baseAjaxVO.setResult(BeanUtil.copyList(bgRoleList, BgRoleVO.class));
        }
        return baseAjaxVO;
    }

    /**
     * 新增角色信息
     * @param roleVO 角色信息
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseAjaxVO insert(BgRoleVO roleVO, String loginAccount) {
        BaseAjaxVO ajaxVO = new BaseAjaxVO();
        //校验角色信息
        if(checkRoleInfo(roleVO)){
            //校验角色是否存在
            BgRole existUser = bgRoleDao.findRoleByName(roleVO.getBgRoleName());
            if(existUser != null){
                throw new WkRentException("新增角色失败，角色已存在！");
            }
            BgRole bgRole = BeanUtil.copyBean(roleVO, BgRole.class);
            bgRole.setBgRoleId(UUIDUtil.getUUID());
            bgRole.setIsActive(Constants.STR_TRUE);
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Add, bgRole, loginAccount);
            int result = bgRoleDao.insertRole(bgRole);
            if(result != 1){
                throw new WkRentException("新增角色失败，服务器异常！");
            }
            //新增角色权限信息
            insertRoleAuthInfo(roleVO.getAuthList(), bgRole.getBgRoleId());

            roleVO.setBgRoleId(bgRole.getBgRoleId());
            ajaxVO.setResult(roleVO);
        }
        return ajaxVO;
    }

    /**
     * 新增角色权限
     * @param authIdList 权限idList
     * @param roleId 角色id
     */
    private void insertRoleAuthInfo(List<String> authIdList, String roleId) {
        //新增角色权限信息
        for(String authId : authIdList){
            if(StringUtils.isBlank(authId)){
                continue;
            }
            BgRoleAuth bgRoleAuth = new BgRoleAuth();
            bgRoleAuth.setBgRoleId(roleId);
            bgRoleAuth.setBgMenuId(authId);
            bgRoleAuthService.insert(bgRoleAuth);
        }
    }

    /**
     * 根据角色账号修改角色信息
     * @param roleVO 待修改角色信息
     * @param loginAccount 当前登录账号
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BgRoleVO roleVO, String loginAccount) {
            //校验角色信息
        if(checkRoleInfo(roleVO)){
            //校验当前角色是否已被删除
            BgRole roleInfo = bgRoleDao.selectByPrimaryKey(roleVO.getBgRoleId());
            if(roleInfo == null){
                throw new WkRentException("修改角色失败，角色已被删除！");
            }
            //校验角色名是否存在
            BgRole existUser = bgRoleDao.findRoleByName(roleVO.getBgRoleName());
            if(existUser != null && !StringUtils.equals(existUser.getBgRoleId(), roleInfo.getBgRoleId())){
                throw new WkRentException("修改角色失败，角色名已存在！");
            }
            BgRole bgRole = BeanUtil.copyBean(roleVO, BgRole.class);
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, bgRole, loginAccount);
            int result = bgRoleDao.update(bgRole);
            if(result != 1){
                throw new WkRentException("更新角色信息失败，角色信息已变更！");
            }
            //根据角色id删除权限信息
            bgRoleAuthService.deleteByRoleId(roleVO.getBgRoleId());
            insertRoleAuthInfo(roleVO.getAuthList(), roleVO.getBgRoleId());
        }
    }

    /**
     * 根据id删除角色
     * @param roleId 角色id
     * @param loginAccount 当前登录账号
     */
    @Override
    public void delete(String roleId, String loginAccount) {
        if(StringUtils.isBlank(roleId)){
            throw new WkRentException("删除角色失败，请传入角色id");
        }
        BgRole bgRole = bgRoleDao.selectByPrimaryKey(roleId);
        if(bgRole == null){
            return;
        }
        BgRole updateRole = new BgRole();
        updateRole.setBgRoleId(roleId);
        updateRole.setIsDelete(Constants.STR_TRUE);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateRole, loginAccount);
        int result = bgRoleDao.delete(updateRole);
        if(result != 1){
            throw new WkRentException("删除角色失败，角色信息已被删除！");
        }
        //删除角色权限信息
        bgRoleAuthService.deleteByRoleId(roleId);
    }

    /**
     * 根据角色id查询平台用户信息
     *
     * @param roleId 角色id
     * @return 符合条件用户信息
     */
    @Override
    public BaseAjaxVO findUserByRoleId(String roleId) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(roleId)){
            return baseAjaxVO;
        }
        BgUserRole bgUserRole = new BgUserRole();
        bgUserRole.setBgRoleId(roleId);
        List<BgUserRoleVO> bgUserRoleVOList = bgUserRoleDao.findByCondition(bgUserRole);
        if(CollectionUtils.isNotEmpty(bgUserRoleVOList)){
            baseAjaxVO.setResult(bgUserRoleVOList);
        }
        return baseAjaxVO;
    }

    /**
     * 根据id禁用角色
     * @param roleId 角色id
     * @param loginAccount 当前登录账号
     */
    @Override
    public void disable(String roleId, String loginAccount) {
        if(StringUtils.isBlank(roleId)){
            throw new WkRentException("禁用角色失败，请传入角色id");
        }
        BgRole bgRole = bgRoleDao.selectByPrimaryKey(roleId);
        if(bgRole == null){
            throw new WkRentException("禁用角色失败，角色信息已被删除");
        }
        //角色已被禁用，不能再次禁用
        if (Constants.STR_FALSE.equals(bgRole.getIsActive())) {
            throw new WkRentException("禁用角色失败，角色信息已被禁用");
        }
        BgRole updateRole = new BgRole();
        updateRole.setBgRoleId(roleId);
        updateRole.setIsActive(Constants.STR_FALSE);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateRole, loginAccount);
        int result = bgRoleDao.updateStatus(updateRole);
        if(result != 1){
            throw new WkRentException("禁用角色失败，角色信息已变更！");
        }
    }

    /**
     * 根据id启用角色
     * @param roleId 角色id
     * @param loginAccount 当前登录账号
     */
    @Override
    public void enable(String roleId, String loginAccount) {
        if(StringUtils.isBlank(roleId)){
            throw new WkRentException("启用角色失败，请传入角色id");
        }
        BgRole bgRole = bgRoleDao.selectByPrimaryKey(roleId);
        if(bgRole == null){
            throw new WkRentException("启用角色失败，角色信息已被删除");
        }
        //角色已被启用，不能再次启用
        if (Constants.STR_TRUE.equals(bgRole.getIsActive())) {
            throw new WkRentException("启用角色失败，角色信息已被启用");
        }
        BgRole updateRole = new BgRole();
        updateRole.setBgRoleId(roleId);
        updateRole.setIsActive(Constants.STR_TRUE);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateRole, loginAccount);
        int result = bgRoleDao.updateStatus(updateRole);
        if(result != 1){
            throw new WkRentException("启用角色失败，角色信息已变更！");
        }
    }

    /**
     * 校验新增/修改角色信息有效性
     * @param roleVO 角色信息
     * @return 校验结果
     */
    private boolean checkRoleInfo(BgRoleVO roleVO){
        //角色姓名
        if(StringUtils.isBlank(roleVO.getBgRoleName())){
            throw new WkRentException("保存角色信息失败，角色姓名不能为空");
        }
        if(CollectionUtils.isEmpty(roleVO.getAuthList())){
            throw new WkRentException("保存角色信息失败，请选择权限");
        }
        return true;
    }

}
