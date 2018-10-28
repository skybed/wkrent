package com.wkrent.business.bg.menumanagement.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.menumanagement.dao.BgMenuDao;
import com.wkrent.business.bg.menumanagement.service.BgMenuService;
import com.wkrent.common.entity.po.BgMenu;
import com.wkrent.common.entity.vo.BgMenuVO;
import com.wkrent.common.util.BeanUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class BgMenuServiceImpl implements BgMenuService{

    @Autowired
    private BgMenuDao bgMenuDao;

    private final String ROOT_KEY = "root_key";

    /**
     * 根据父级id查询菜单信息
     *
     * @param parentId 父级菜单id
     * @return 菜单List
     */
    @Override
    public List<BgMenuVO> queryMenuList(String parentId) {

        //查询所有菜单信息
        List<BgMenu> menuList = bgMenuDao.findAllMenu();
        List<BgMenuVO> menuVOList = BeanUtil.copyList(menuList, BgMenuVO.class);
        return getMenuTree(menuVOList, parentId);
    }

    /**
     * 根据菜单idList查询未被删除且启用菜单信息
     *
     * @param menuIdList 菜单IdList
     * @return 符合条件菜单信息
     */
    @Override
    public List<BgMenuVO> findByIdList(List<String> menuIdList) {
        if (CollectionUtils.isNotEmpty(menuIdList)) {
            return Lists.newArrayList();
        }
        List<BgMenu> menuList = bgMenuDao.findByIdList(menuIdList);
        return BeanUtil.copyList(menuList, BgMenuVO.class);
    }

    /**
     * 根据父级id查询 &分组菜单树
     * @param menuVOList 全量未删除菜单List
     * @param parentId 父级菜单id，若需查询全量菜单树，传空即可
     * @return 符合条件菜单树
     */
    private List<BgMenuVO> getMenuTree(List<BgMenuVO> menuVOList, String parentId){
        List<BgMenuVO> bgMenuVOList = Lists.newArrayList();
        if(CollectionUtils.isEmpty(menuVOList)){
            return bgMenuVOList;
        }
        Map<String, List<BgMenuVO>> treeMap = new HashedMap<>();
        for(BgMenuVO bgMenuVO : menuVOList){

            String parentKey = bgMenuVO.getBgMenuParentId();
            //若父级id为空，则设置父级key 为 root
            if(StringUtils.isBlank(parentKey)){
                parentKey = ROOT_KEY;
            }
            //不含当前父级id，则新增
            if(!treeMap.containsKey(bgMenuVO.getBgMenuParentId())){
                treeMap.put(bgMenuVO.getBgMenuParentId(), Lists.newArrayList(bgMenuVO));
            }else {
                treeMap.get(parentKey).add(bgMenuVO);
            }
        }
        //循环菜单List，设置子菜单
        for(BgMenuVO bgMenuVO : menuVOList){
            List<BgMenuVO> childrenList = treeMap.get(bgMenuVO.getBgMenuId());
            if(CollectionUtils.isNotEmpty(childrenList)){
                //jdk8 排序，根据menuSort排序
                childrenList.sort(Comparator.comparing(BgMenuVO :: getBgMenuSort));
            }
            bgMenuVO.setChildMenuList(childrenList);
        }
        if(StringUtils.isBlank(parentId)){
            return treeMap.get(parentId);
        }
        return treeMap.get(parentId);
    }
}
