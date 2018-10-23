package com.wkrent.business.bgrole.manager.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bgrole.manager.dao.BgRoleDao;
import com.wkrent.business.bgrole.manager.service.BgRoleAuthService;
import com.wkrent.business.bgrole.manager.service.BgRoleService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.BgRole;
import com.wkrent.common.entity.po.BgRoleAuth;
import com.wkrent.common.entity.vo.BgRoleVO;
import com.wkrent.common.exception.WkRentException;
import com.wkrent.common.util.BeanUtil;
import com.wkrent.common.util.OperatorUtil;
import com.wkrent.common.util.UUIDUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class BgRoleServiceImpl implements BgRoleService {

    @Autowired
    private BgRoleDao bgRoleDao;

    @Autowired
    private BgRoleAuthService bgRoleAuthService;



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
            pageResult.setRows(BeanUtil.copyList(bgRoleList, BgRoleVO.class));
            pageResult.setTotal(total);
        }
        return pageResult;
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
        updateRole.setIsDelete(Constants.STR_FALSE);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateRole, loginAccount);
        int result = bgRoleDao.delete(updateRole);
        if(result != 1){
            throw new WkRentException("删除角色失败，角色信息已被删除！");
        }
        //删除角色权限信息
        bgRoleAuthService.deleteByRoleId(roleId);
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
