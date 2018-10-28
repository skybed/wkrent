package com.wkrent.business.bg.usermanagement.service.impl;

import com.wkrent.business.bg.usermanagement.dao.BgUserRoleDao;
import com.wkrent.business.bg.usermanagement.service.BgUserRoleService;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.po.BgUserRole;
import com.wkrent.common.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class BgUserRoleServiceImpl implements BgUserRoleService {

    @Autowired
    private BgUserRoleDao bgUserRoleDao;

    /**
     * 新增用户角色信息
     *
     * @param bgUserRole 角色信息
     * @return 新增条数
     */
    @Override
    public int insert(BgUserRole bgUserRole) {
        if(bgUserRole == null){
            return 0;
        }
        if(StringUtils.isBlank(bgUserRole.getBgRoleId()) || StringUtils.isBlank(bgUserRole.getBgUserId())){
            return 0;
        }
        bgUserRole.setBgUserRoleId(UUIDUtil.getUUID());
        bgUserRole.setIsDelete(Constants.STR_FALSE);
        return bgUserRoleDao.insert(bgUserRole);
    }

    /**
     * 根据用户id删除用户角色角色信息
     *
     * @param userId 角色id
     * @return 更新条数
     */
    @Override
    public int deleteByUserId(String userId) {
        if(StringUtils.isBlank(userId)){
            return 0;
        }
        BgUserRole bgUserRole = new BgUserRole();
        bgUserRole.setBgUserId(userId);
        bgUserRole.setIsDelete(Constants.STR_FALSE);
        return bgUserRoleDao.deleteByCondition(bgUserRole);
    }
}
