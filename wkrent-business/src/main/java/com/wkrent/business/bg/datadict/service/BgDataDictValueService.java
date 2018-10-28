package com.wkrent.business.bg.datadict.service;

import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgDataDictValueVO;

import java.util.List;

/**
 * 数据字典
 * @author Administrator
 */
public interface BgDataDictValueService {

    /**
     * 分页查询枚举信息
     * @param dataDictValueVO 查询条件
     * @return 符合条件分页信息
     */
    PageResult<BgDataDictValueVO> findByCondition(BgDataDictValueVO dataDictValueVO);

    /**
     * 查询枚举值List
     * @param dictType 查询条件
     * @return 符合条件枚举值信息
     */
    List<BgDataDictValueVO> queryDictValueList(String dictType);

    /**
     * 新增枚举信息
     * @param dataDictValueVO 数据字典
     * @param loginAccount 当前登录账号
     * @return 新增后数据信息
     */
    BaseAjaxVO insert(BgDataDictValueVO dataDictValueVO, String loginAccount);

    /**
     * 修改数据字典值
     * @param dataDictValueVO 待数据字典信息
     * @param loginAccount 当前登录账号
     * @return 修改条数
     */
    void update(BgDataDictValueVO dataDictValueVO, String loginAccount);

    /**
     * 根据idList删除数据字典信息
     * @param dictValueIdList 枚举值idList
     * @param loginAccount 当前登录账号
     */
    void delete(List<String> dictValueIdList, String loginAccount);

    /**
     * 根据idList禁用数据字典值
     * @param dictValueIdList 数据字典值idList
     * @param loginAccount 当前登录账号
     */
    void disable(List<String> dictValueIdList, String loginAccount);

    /**
     * 根据idList启用数据字典值
     * @param dictValueIdList 数据字典值idList
     * @param loginAccount 当前登录账号
     */
    void enable(List<String> dictValueIdList, String loginAccount);
}
