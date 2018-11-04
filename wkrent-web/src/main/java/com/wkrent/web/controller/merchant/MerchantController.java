package com.wkrent.web.controller.merchant;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.merchantmanagement.service.BgMerchantService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgMerchantVO;
import com.wkrent.common.entity.vo.BgPicAttachVO;
import com.wkrent.common.exception.WkRentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 商家信息
 * @author Administrator
 */
@Api(value = "merchant", tags = "商家接口")
@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController{

    @Autowired
    private BgMerchantService bgMerchantService;

    @ApiOperation(value = "条件查询商家信息", notes = "条件查询商家信息", httpMethod = "POST", response = BgMerchantVO.class)
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgMerchantVO> findByCondition(@RequestBody BgMerchantVO merchantVO){
        return bgMerchantService.findByCondition(merchantVO);
    }

    @ApiOperation(value = "根据id查询商家信息", notes = "根据id查询商家信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/findByMerchantId", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO findByMerchantId(@RequestBody String merchantId){
        return bgMerchantService.findByMerchantId(merchantId);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody BgMerchantVO merchantVO, HttpSession session){
        BaseAjaxVO baseAjaxVO;
        try {
            baseAjaxVO = bgMerchantService.insert(merchantVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("新增商家失败", e, merchantVO);
        }catch (Exception e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("新增商家失败，系统异常", e, merchantVO);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody BgMerchantVO merchantVO, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgMerchantService.update(merchantVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("更新商家信息失败", e, merchantVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("更新商家信息失败，系统异常", e, merchantVO);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody String merchantId, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgMerchantService.delete(merchantId, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("删除商家失败", e, merchantId);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("删除商家失败，系统异常", e, merchantId);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO disable(@RequestBody String merchantId, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgMerchantService.disable(merchantId, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("禁用商家失败", e, merchantId);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("禁用商家失败，系统异常", e, merchantId);
        }
        return baseAjaxVO;
    }

    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO enable(@RequestBody String merchantId, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgMerchantService.enable(merchantId, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("启用商家失败", e, merchantId);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("启用商家失败，系统异常", e, merchantId);
        }
        return baseAjaxVO;
    }

    /**
     * 根据商家id查询营业执照信息
     * @param merchantId 商家id
     * @return 未被删除营业执照附件信息
     */
    @RequestMapping(value = "/queryFileById", method = RequestMethod.POST)
    @ResponseBody
    public List<BgPicAttachVO> queryFileById(@RequestBody String merchantId){
        //TODO 附件信息待处理
        return Lists.newArrayList();
    }
}
