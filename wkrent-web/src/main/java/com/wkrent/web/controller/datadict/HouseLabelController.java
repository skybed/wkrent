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
@Api(value = "houseLabel", tags = "数据字典-房源标签")
@Controller
@RequestMapping("/houseLabel")
public class HouseLabelController extends BaseController{

    @Autowired
    private BgDataDictValueService bgDataDictValueService;

//    @ApiOperation(value = "条件查询房源标签", notes = "条件查询房源标签")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgDataDictValueVO> findByCondition(@RequestBody BgDataDictValueVO dataDictValueVO){
        dataDictValueVO.setBgDataDictType(Constants.DICT_TYPE_HOUSE_LABEL);
        return bgDataDictValueService.findByCondition(dataDictValueVO);
    }

//    @ApiOperation(value = "查询房源标签", notes = "查询房源标签")
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ResponseBody
    public List<BgDataDictValueVO> queryList(){
        return bgDataDictValueService.queryDictValueList(Constants.DICT_TYPE_HOUSE_LABEL);
    }

    /**
     * 新增房源标签
     * @param dataDictValueVO 房源标签信息
     * @param session 用户登录session
     * @return 操作结果
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody BgDataDictValueVO dataDictValueVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO;
        try {
            //设置枚举类型为房源标签
            dataDictValueVO.setBgDataDictType(Constants.DICT_TYPE_HOUSE_LABEL);
            baseAjaxVO = bgDataDictValueService.insert(dataDictValueVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("新增房源标签失败", e, dataDictValueVO);
        }catch (Exception e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("新增房源标签失败，系统异常", e, dataDictValueVO);
        }
        return baseAjaxVO;
    }

    /**
     * 修改房源标签信息
     * @param dataDictValueVO 待更新数据
     * @param session 用户登录session
     * @return 操作结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody BgDataDictValueVO dataDictValueVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            //设置枚举类型为房源标签
            dataDictValueVO.setBgDataDictType(Constants.DICT_TYPE_HOUSE_LABEL);
            bgDataDictValueService.update(dataDictValueVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("更新房源标签失败", e, dataDictValueVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("更新房源标签失败，系统异常", e, dataDictValueVO);
        }
        return baseAjaxVO;
    }

    /**
     * 删除房源标签
     * @param dataDictIdList 房源标签idList
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
            log.warn("删除房源标签失败", e, dataDictIdList);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除房源标签失败，系统异常", e, dataDictIdList);
        }
        return baseAjaxVO;
    }
}
