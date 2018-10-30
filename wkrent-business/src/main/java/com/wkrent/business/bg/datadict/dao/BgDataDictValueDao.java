package com.wkrent.business.bg.datadict.dao;

import com.wkrent.common.entity.po.BgDataDictValue;
import com.wkrent.common.entity.vo.BgDataDictValueVO;

import java.util.List;

/**
 * @author Administrator
 */
public interface BgDataDictValueDao {

    /**
     * 根据id删除数据字典枚举值
     * @param dataDictValue 数据字典枚举值id/idList
     * @return 更新条数
     */
    int delete(BgDataDictValueVO dataDictValue);

    /**
     * 新增数据字典枚举值
     * @param record 数据字典枚举值信息
     * @return 新增条数
     *
     */
    int insert(BgDataDictValue record);

    /**
     * 根据id查询未删除数据字典枚举值信息
     * @param bgDataDictValueId 枚举值信息
     * @return 枚举值信息
     */
    BgDataDictValue selectByPrimaryKey(String bgDataDictValueId);

    /**
     * 根据id更新数据字典枚举值信息
     * @param record 枚举值信息
     * @return 更新条数
     */
    int updateByPrimaryKey(BgDataDictValue record);

    /**
     * 停用/启用数据字典枚举信息(批量)
     * @param dataDictValue 待更新数据
     * @return 更新条数
     */
    int updateStatus(BgDataDictValueVO dataDictValue);

    /**
     * 条件查询数据字典
     * @param dataDictValueVO 查询条件（目前是dataDictType）
     * @return 符合条件信息
     */
    List<BgDataDictValueVO> findByCondition(BgDataDictValueVO dataDictValueVO);

    /**
     * 条件查询数据字典总条数
     * @param dataDictValueVO 查询条件（目前是dataDictId）
     * @return 符合条件信息
     */
    int countByCondition(BgDataDictValueVO dataDictValueVO);

    /**
     * 根据枚举类型查询生效未被删除枚举值信息
     * @param dataDictType 枚举类型
     * @return 符合条件信息
     */
    List<BgDataDictValueVO> queryDictValueByType(String dataDictType);
}