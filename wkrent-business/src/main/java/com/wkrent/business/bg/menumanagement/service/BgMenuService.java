package com.wkrent.business.bg.menumanagement.service;

import com.wkrent.common.entity.vo.BgMenuVO;

import java.util.List;

/**
 * @author Administrator
 */
public interface BgMenuService {

    /**
     * 根据父级id查询菜单信息
     * @param parentId 父级菜单id
     * @return 菜单List
     */
    List<BgMenuVO> queryMenuList(String parentId);

    /**
     * 根据菜单idList查询未被删除且启用菜单信息
     * @param menuIdList 菜单IdList
     * @return 符合条件菜单信息
     */
    List<BgMenuVO> findByIdList(List<String> menuIdList);
}
