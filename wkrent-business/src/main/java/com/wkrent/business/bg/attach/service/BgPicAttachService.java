package com.wkrent.business.bg.attach.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.vo.BgPicAttachVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface BgPicAttachService {

    /**
     * 根据附件所属id查询附件信息
     * @param ownerId 附件所属id
     * @return 符合条件未删除附件信息
     */
    List<BgPicAttachVO> selectByOwnerId(String ownerId);

    /**
     * 根据附件所属id查询附件Id信息
     * @param ownerId 附件所属id
     * @return 符合条件未删除附件信息
     */
    Map<String, List<String>> selectFileIdByOwnerIdList(List<String> ownerId);

    /**
     * 新增附件信息
     * @param uploadFile 附件
     * @param fileOwnerType 附件所属对象类型
     * @return 已插入附件信息
     */
    BaseAjaxVO savePicAttach(MultipartFile uploadFile, String fileOwnerType);

    /**
     * 新增附件信息
     * @param uploadFiles 附件List
     * @param fileOwnerType 附件所属对象类型
     * @return 已插入附件信息
     */
    BaseAjaxVO savePicAttachList(MultipartFile[] uploadFiles, String fileOwnerType);

    /**
     * 根据附件id获取附件文件流
     * @param picId 附件id
     * @param response response
     * @return 符合条件未删除附件信息
     */
    void selectById(String picId, HttpServletResponse response);

    /**
     * 根据附件id删除附件信息
     * @param picId 附件id
     */
    void deletePicAttach(String picId);

    /**
     * 根据附件id删除附件信息
     * @param picIdList 附件id
     */
    void deletePicAttachList(List<String> picIdList);

    /**
     * 更新附件所属id（不变更已有附件信息）
     * @param picIdList 附件idList
     * @param ownerId 所属id
     */
    void updatePicAttachOwner(List<String> picIdList, String ownerId);

    /**
     * 更新附件所属id信息
     * @param picIdList 附件idList
     * @param ownerId 附件所属id
     */
    void updateAttachOwnerId(List<String> picIdList, String ownerId);

    /**
     * 新增附件信息
     * @param attachVO 附件Base64字符串
     * @param fileOwnerType 附件所属对象类型
     * @return 已插入附件信息
     */
    BaseAjaxVO savePicAttachByBase64(BgPicAttachVO attachVO, String fileOwnerType);

    /**
     * 批量新增附件信息
     * @param attachVOList 附件Base64字符串
     * @param fileOwnerType 附件所属对象类型
     * @return 已插入附件信息
     */
    BaseAjaxVO savePicAttachListByBase64(List<BgPicAttachVO> attachVOList, String fileOwnerType);
}
