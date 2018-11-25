package com.wkrent.business.bg.appusermanagement.service.impl;

import com.wkrent.business.bg.appusermanagement.dao.BgAppUserDao;
import com.wkrent.business.bg.appusermanagement.service.BgAppUserService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.po.AppUser;
import com.wkrent.common.entity.vo.AppUserVO;
import com.wkrent.common.exception.WkRentException;
import com.wkrent.common.util.BeanUtil;
import com.wkrent.common.util.OperatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class BgAppUserServiceImpl implements BgAppUserService {

    @Autowired
    private BgAppUserDao bgAppUserDao;

    /**
     * 分页查询用户信息
     *
     * @param userVO 查询条件
     * @return 符合条件分页信息
     */
    @Override
    public PageResult<AppUserVO> findByCondition(AppUserVO userVO) {
        PageResult<AppUserVO> pageResult = new PageResult<>();

        int total = bgAppUserDao.countByCondition(userVO);
        if(total > 0){
            List<AppUser> appUserList = bgAppUserDao.findByCondition(userVO);
            pageResult.setRows(BeanUtil.copyList(appUserList, AppUserVO.class));
            pageResult.setTotal(total);
        }
        return pageResult;
    }


    /**
     * 根据用户账号修改用户信息
     *
     * @param userVO 待修改用户信息
     * @param loginAccount 登录账号
     */
    @Override
    public void update(AppUserVO userVO, String loginAccount) {
            //校验用户信息
        if(checkUserInfo(userVO)){
            AppUser appUser = BeanUtil.copyBean(userVO, AppUser.class);
            OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, appUser, loginAccount);
            int result = bgAppUserDao.update(appUser);
            if(result != 1){
                throw new WkRentException("更新用户信息失败，用户信息已变更！");
            }
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
        AppUser user = bgAppUserDao.findById(userId);
        if(user == null){
            return;
        }
        AppUser updateUser = new AppUser();
        updateUser.setAppUserId(userId);
        updateUser.setIsDelete(Constants.STR_TRUE);
        OperatorUtil.setOperatorInfo(OperatorUtil.OperationType.Update, updateUser, loginAccount);
        int result = bgAppUserDao.delete(updateUser);
        if(result != 1){
            throw new WkRentException("删除用户失败，用户信息已被删除！");
        }
    }

    /**
     * 根据userId查询前台用户信息
     *
     * @param userId 前台用户id
     * @return 未被删除user
     */
    @Override
    public BaseAjaxVO findByUserId(String userId) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(StringUtils.isBlank(userId)){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("用户Id不能为空");
            return baseAjaxVO;
        }
        AppUser appUser = bgAppUserDao.findById(userId);
        if(appUser == null){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("用户不存在或已被删除！");
            return baseAjaxVO;
        }
        baseAjaxVO.setResult(BeanUtil.copyBean(appUser, AppUserVO.class));
        return baseAjaxVO;
    }


    /**
     * 校验新增/修改用户信息有效性
     * @param userVO 用户信息
     * @return 校验结果
     */
    private boolean checkUserInfo(AppUserVO userVO){
        //用户姓名
        if(StringUtils.isBlank(userVO.getAppUserName())){
            throw new WkRentException("保存用户信息失败，用户姓名不能为空");
        }
        if(StringUtils.isBlank(userVO.getAppUserSex())){
            throw new WkRentException("保存用户信息失败，用户性别不能为空");
        }
        if(StringUtils.isBlank(userVO.getAppUserPhone())){
            throw new WkRentException("保存用户信息失败，用户手机号码不能为空");
        }
        if(StringUtils.isBlank(userVO.getAppUserEmail())){
            throw new WkRentException("保存用户信息失败，用户邮箱不能为空");
        }
        if(StringUtils.isBlank(userVO.getAppUserBirthday())){
            throw new WkRentException("保存用户信息失败，用户出生日期不能为空");
        }
        return true;
    }

}
