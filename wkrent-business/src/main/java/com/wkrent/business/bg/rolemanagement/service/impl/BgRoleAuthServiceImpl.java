package com.wkrent.business.bg.rolemanagement.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.rolemanagement.dao.BgRoleAuthDao;
import com.wkrent.business.bg.rolemanagement.service.BgRoleAuthService;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.po.BgRoleAuth;
import com.wkrent.common.util.UUIDUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class BgRoleAuthServiceImpl implements BgRoleAuthService{

    @Autowired
    private BgRoleAuthDao bgRoleAuthDao;
    /**
     * 新增角色权限信息
     *
     * @param bgRoleAuth 角色权限信息
     * @return 新增条数
     */
    @Override
    public int insert(BgRoleAuth bgRoleAuth) {
        if(bgRoleAuth == null){
            return 0;
        }
        if(StringUtils.isBlank(bgRoleAuth.getBgRoleId()) || StringUtils.isBlank(bgRoleAuth.getBgMenuId())){
            return 0;
        }
        bgRoleAuth.setBgRoleAuthId(UUIDUtil.getUUID());
        bgRoleAuth.setIsDelete(Constants.STR_FALSE);
        return bgRoleAuthDao.insert(bgRoleAuth);
    }

    /**
     * 根据角色id删除角色权限信息
     *
     * @param roleId 角色id
     * @return 更新条数
     */
    @Override
    public int deleteByRoleId(String roleId) {
        if(StringUtils.isBlank(roleId)){
            return 0;
        }
        return bgRoleAuthDao.deleteByRoleId(roleId);
    }

    /**
     * 根据角色idList查询权限信息
     *
     * @param roleIdList 角色idList
     * @return 角色权限信息
     */
    @Override
    public List<BgRoleAuth> queryByRoleIdList(List<String> roleIdList) {
        if(CollectionUtils.isEmpty(roleIdList)){
            return Lists.newArrayList();
        }
        return bgRoleAuthDao.queryByRoleIdList(roleIdList);
    }
}
