package com.wkrent.business.bg.menumanagement.dao;

import com.wkrent.common.entity.po.BgMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface BgMenuDao {

    /**
     * 新增菜单信息
     * @param record 待新增菜单
     * @return 新增条数
     *
     */
    int insert(BgMenu record);

    /**
     * 根据菜单idList查询菜单信息
     * @param menuIdList 菜单idList
     * @return 符合条件未被删除且启用菜单信息
     */
    List<BgMenu> findByIdList(@Param("menuIdList") List<String> menuIdList);

    /**
     * 根据父级id查询菜单信息
     * @param parentId 父级id
     * @return 符合条件的未删除且启用的菜单信息
     */
    List<BgMenu> findByParentId(String parentId);

    /**
     * 查询所有未删除且启用的菜单
     * @return 符合条件菜单信息
     */
    List<BgMenu> findAllMenu();

}