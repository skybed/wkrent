package com.wkrent.business.bg.attach.dao;

import com.wkrent.common.entity.po.BgPicAttach;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface BgPicAttachDao {

    /**
     * 根据福建所属id查询附件信息
     * @param ownerId 附件所属id
     * @return 符合条件未删除附件信息
     */
    List<BgPicAttach> selectByOwnerId(String ownerId);

    /**
     * 根据附件所属id查询附件信息
     * @param ownerIdList 附件所属id
     * @return 符合条件未删除附件信息
     */
    List<BgPicAttach> selectByOwnerIdList(List<String> ownerIdList);

    /**
     * 新增附件信息
     * @param bgPicAttach 附件
     */
    void insertSelective(BgPicAttach bgPicAttach);

    /**
     * 根据附件id查询附件信息
     * @param picAttachId 附件id
     * @return 符合条件未删除附件信息
     */
    BgPicAttach selectByPrimaryKey(String picAttachId);

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
     * 更新附件所属id信息
     * @param picIdList 附件id
     * @param ownerId 附件所属id
     */
    void updatePicAttachOwner(@Param("picIdList") List<String> picIdList, @Param("ownerId") String ownerId);
}
