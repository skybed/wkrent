package com.wkrent.web.controller.datadict;

import com.wkrent.business.bg.datadict.service.BgDataDictValueService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgDataDictValueVO;
import com.wkrent.common.exception.WkRentException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Administrator
 */
@Api(value = "houseProperty", tags = "数据字典-房屋性质")
@Controller
@RequestMapping("/houseProperty")
public class HousePropertyController extends BaseController{

    @Autowired
    private BgDataDictValueService bgDataDictValueService;

//    @ApiOperation(value = "条件查询房屋性质", notes = "条件查询房屋性质")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgDataDictValueVO> findByCondition(@RequestBody BgDataDictValueVO dataDictValueVO){
        dataDictValueVO.setBgDataDictType(Constants.DICT_TYPE_HOUSE_PROPERTY);
        return bgDataDictValueService.findByCondition(dataDictValueVO);
    }

//    @ApiOperation(value = "查询房屋性质", notes = "查询房屋性质")
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ResponseBody
    public List<BgDataDictValueVO> queryList(){
        return bgDataDictValueService.queryDictValueList(Constants.DICT_TYPE_HOUSE_PROPERTY);
    }

    /**
     * 新增房屋性质
     * @param dataDictValueVO 房屋性质信息
     * @param session 用户登录session
     * @return 操作结果
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody BgDataDictValueVO dataDictValueVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO;
        try {
            //设置枚举类型为房屋性质
            dataDictValueVO.setBgDataDictType(Constants.DICT_TYPE_HOUSE_PROPERTY);
            baseAjaxVO = bgDataDictValueService.insert(dataDictValueVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("新增房屋性质失败", e, dataDictValueVO);
        }catch (Exception e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("新增房屋性质失败，系统异常", e, dataDictValueVO);
        }
        return baseAjaxVO;
    }

    /**
     * 修改房屋性质信息
     * @param dataDictValueVO 待更新数据
     * @param session 用户登录session
     * @return 操作结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody BgDataDictValueVO dataDictValueVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            //设置枚举类型为房屋性质
            dataDictValueVO.setBgDataDictType(Constants.DICT_TYPE_HOUSE_PROPERTY);
            bgDataDictValueService.update(dataDictValueVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("更新房屋性质失败", e, dataDictValueVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("更新房屋性质失败，系统异常", e, dataDictValueVO);
        }
        return baseAjaxVO;
    }

    /**
     * 删除房屋性质
     * @param dataDictIdList 房屋性质idList
     * @param session 用户登录session
     * @return 操作结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody List<String> dataDictIdList, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgDataDictValueService.delete(dataDictIdList, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除房屋性质失败", e, dataDictIdList);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除房屋性质失败，系统异常", e, dataDictIdList);
        }
        return baseAjaxVO;
    }
}
