package com.wkrent.web.controller.merchant;

import com.wkrent.business.bg.attach.service.BgPicAttachService;
import com.wkrent.business.bg.merchantmanagement.service.BgMerchantService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.enums.UploadFileTypeEnum;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgMerchantVO;
import com.wkrent.common.entity.vo.BgPicAttachVO;
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
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private BgPicAttachService bgPicAttachService;

    @ApiOperation(value = "条件查询商家信息", notes = "条件查询商家信息", httpMethod = "POST", response = PageResult.class)
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgMerchantVO> findByCondition(@RequestBody @ApiParam(name = "merchantVO", value = "查询条件")
                                                                BgMerchantVO merchantVO){
        return bgMerchantService.findByCondition(merchantVO);
    }

    @ApiOperation(value = "根据id查询商家信息", notes = "根据id查询商家信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/findByMerchantId", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO findByMerchantId(@RequestBody @ApiParam(name = "merchantId", value = "商家id")
                                                   String merchantId){
        return bgMerchantService.findByMerchantId(merchantId);
    }

    @ApiOperation(value = "新增商家信息", notes = "新增商家信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody @ApiParam(name = "merchantVO", value = "待新增商家信息")
                                         BgMerchantVO merchantVO, @ApiIgnore HttpSession session){
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

    @ApiOperation(value = "修改商家信息", notes = "修改商家信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody @ApiParam(name = "merchantVO", value = "待修改商家信息")
                                         BgMerchantVO merchantVO, @ApiIgnore HttpSession session){
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

    @ApiOperation(value = "删除商家信息", notes = "删除商家信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO delete(@RequestBody @ApiParam(name = "merchantId", value = "商家id")
                                         String merchantId, @ApiIgnore HttpSession session){
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

    @ApiOperation(value = "禁用商家信息", notes = "禁用商家信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO disable(@RequestBody @ApiParam(name = "merchantId", value = "商家id")
                                          String merchantId, @ApiIgnore HttpSession session){
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

    @ApiOperation(value = "启用商家信息", notes = "启用商家信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO enable(@RequestBody @ApiParam(name = "merchantId", value = "商家id")
                                         String merchantId, @ApiIgnore HttpSession session){
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
    @ApiOperation(value = "根据商家id查询营业执照信息", notes = "根据商家id查询营业执照信息", httpMethod = "POST")
    @RequestMapping(value = "/queryFileById", method = RequestMethod.POST)
    @ResponseBody
    public List<BgPicAttachVO> queryFileById(@RequestBody @ApiParam(name = "merchantId", value = "商家id")
                                                         String merchantId){
        //附件信息
        return bgPicAttachService.selectByOwnerId(merchantId);
    }

    @ApiOperation(value = "查询所有未删除商家信息", notes = "查询所有未删除商家信息", httpMethod = "GET", response = PageResult.class)
    @RequestMapping(value = "/queryAllMerchant", method = RequestMethod.GET)
    @ResponseBody
    public List<BgMerchantVO> queryAllMerchant(){
        return bgMerchantService.queryAllMerchant();
    }

    /**
     * 上传多文件
     * @param request request
     * @param uploadFiles 附件信息
     * @return
     */
    @ApiOperation(value = "商家附件上传", notes = "商家附件上传", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseAjaxVO uploadPictures(@ApiIgnore HttpServletRequest request,
                                     MultipartFile[] uploadFiles) {
        return bgPicAttachService.savePicAttachList(uploadFiles, UploadFileTypeEnum.MERCHANT_FILE.getCode());
    }
}
