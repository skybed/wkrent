package com.wkrent.web.controller.menu;

import com.wkrent.business.bg.menumanagement.service.BgMenuService;
import com.wkrent.common.entity.vo.BgMenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Administrator
 */
@Api(value = "bgMenu", tags = "菜单接口")
@Controller
@RequestMapping("/bgMenu")
public class MenuController {

    @Autowired
    private BgMenuService bgMenuService;
    /**
     * 根据父级菜单id查询菜单树形结构
     * @param menuVO 父级菜单id，若为空，则查询所有菜单树形结构
     * @return 菜单树形结构
     */
    @ApiOperation(value = "根据上级菜单id查询菜单树", notes = "根据上级菜单id查询菜单树", httpMethod = "POST", response = BgMenuVO.class)
    @RequestMapping(value = "/queryByParentId", method = RequestMethod.POST)
    @ResponseBody
    public List<BgMenuVO> queryByParentId(@RequestBody @ApiParam(name = "bgMenuParentId", value = "父级菜单id(仅传入bgMenuParentId即可)")
                                                      BgMenuVO menuVO) {
        return bgMenuService.queryMenuList(menuVO.getBgMenuParentId());
    }
}
