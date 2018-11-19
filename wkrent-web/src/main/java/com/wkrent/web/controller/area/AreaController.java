package com.wkrent.web.controller.area;

import com.wkrent.business.bg.area.service.BgAreaService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.vo.BgAreaVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 */
@Api(value = "area", tags = "国家地区信息")
@Controller
@RequestMapping("/area")
public class AreaController extends BaseController{

    @Autowired
    private BgAreaService bgAreaService;

    @ApiOperation(value = "查询国家信息", notes = "查询国家信息")
    @RequestMapping(value = "/queryCountryInfo", method = RequestMethod.GET)
    @ResponseBody
    public BaseAjaxVO queryCountryInfo(){
        return bgAreaService.queryCountryInfo();
    }

    @ApiOperation(value = "根据上级code查询区域信息", notes = "根据上级code查询区域信息")
    @RequestMapping(value = "/findByParentCode", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO queryList(@RequestBody @ApiParam(name = "areaParentCode", value = "传入areaParentCode即可")
                                                         BgAreaVO areaVO){
        return bgAreaService.findByParentCode(areaVO.getAreaParentCode());
    }

}
