package com.wkrent.business.bg.modulemanagement.service;

import com.wkrent.common.entity.vo.BgModuleVO;
import com.wkrent.common.entity.vo.BgPicAttachVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Administrator
 */
public interface BgModuleService {

    /**
     * 保存模块设置
     * @param moduleVO 模块设置信息
     * @param loginAccount 登录账号
     */
    void save(BgModuleVO moduleVO, String loginAccount);

    /**
     * 根据类型查询模块设置信息
     * @param moduleType 模块类型
     * @return 符合条件模块设置
     */
    BgModuleVO findByType(String moduleType);

    /**
     * 保存租房说明微信二维码
     * @param uploadFile 图片
     * @param fileOwnerType 上传类型
     */
    BgPicAttachVO saveModuleFile(MultipartFile uploadFile, String fileOwnerType);

}
