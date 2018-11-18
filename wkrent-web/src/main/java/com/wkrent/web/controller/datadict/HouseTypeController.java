package com.wkrent.web.controller.datadict;

import com.wkrent.business.bg.datadict.service.BgDataDictValueService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgDataDictValueVO;
import com.wkrent.common.exception.WkRentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "houseType", tags = "数据字典-房屋类型")
@Controller
@RequestMapping("/houseType")
public class HouseTypeController extends BaseController{

    @Autowired
    private BgDataDictValueService bgDataDictValueService;


    @ApiOperation(value = "条件查询房屋类型", notes = "条件查询房屋类型")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgDataDictValueVO> findByCondition(@RequestBody @ApiParam(name = "dataDictValueVO", value = "查询条件")
                                                                     BgDataDictValueVO dataDictValueVO){
        dataDictValueVO.setBgDataDictType(Constants.DICT_TYPE_HOUSE_TYPE);
        return bgDataDictValueService.findByCondition(dataDictValueVO);
    }

    @ApiOperation(value = "查询房屋类型", notes = "查询房屋类型")
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ResponseBody
    public List<BgDataDictValueVO> queryList(){
        return bgDataDictValueService.queryDictValueList(Constants.DICT_TYPE_HOUSE_TYPE);
    }

    /**
     * 新增房屋类型
     * @param dataDictValueVO 房屋类型信息
     * @param session 用户登录session
     * @return 操作结果
     */
    @ApiOperation(value = "新增房屋类型", notes = "新增房屋类型", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody @ApiParam(name = "dataDictValueVO", value = "新增房屋类型信息")
                                         BgDataDictValueVO dataDictValueVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO;
        try {
            //设置枚举类型为房屋类型
            dataDictValueVO.setBgDataDictType(Constants.DICT_TYPE_HOUSE_TYPE);
            baseAjaxVO = bgDataDictValueService.insert(dataDictValueVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("新增房屋类型失败", e, dataDictValueVO);
        }catch (Exception e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("新增房屋类型失败，系统异常", e, dataDictValueVO);
        }
        return baseAjaxVO;
    }

    /**
     * 修改房屋类型信息
     * @param dataDictValueVO 待更新数据
     * @param session 用户登录session
     * @return 操作结果
     */
    @ApiOperation(value = "修改房屋类型", notes = "修改房屋类型", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody @ApiParam(name = "dataDictValueVO", value = "修改房屋类型信息")
                                         BgDataDictValueVO dataDictValueVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            //设置枚举类型为房屋类型
            dataDictValueVO.setBgDataDictType(Constants.DICT_TYPE_HOUSE_TYPE);
            bgDataDictValueService.update(dataDictValueVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("更新房屋类型失败", e, dataDictValueVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("更新房屋类型失败，系统异常", e, dataDictValueVO);
        }
        return baseAjaxVO;
    }

    /**
     * 删除房屋类型
     * @param valueVO 房屋类型idList
     * @param session 用户登录session
     * @return 操作结果
     */
    @ApiOperation(value = "删除房屋类型", notes = "删除房屋类型", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody @ApiParam(name = "dataDictIdList", value = "房屋类型idList(仅传入dataDictValueIdList即可)")
                                         BgDataDictValueVO valueVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgDataDictValueService.delete(valueVO.getDataDictValueIdList(), getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除房屋类型失败", e, valueVO.getDataDictValueIdList());
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除房屋类型失败，系统异常", e, valueVO.getDataDictValueIdList());
        }
        return baseAjaxVO;
    }
}
