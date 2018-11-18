package com.wkrent.web.controller.module;

import com.wkrent.business.bg.attach.service.BgPicAttachService;
import com.wkrent.business.bg.modulemanagement.service.BgModuleService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.enums.ModuleTypeEnum;
import com.wkrent.common.entity.enums.UploadFileTypeEnum;
import com.wkrent.common.entity.vo.BgModuleVO;
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

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */
@Api(value = "moduleSetting", tags = "模块设置")
@Controller
@RequestMapping("/moduleSetting")
public class ModuleSettingController extends BaseController{

    @Autowired
    private BgModuleService bgModuleService;

    @Autowired
    private BgPicAttachService bgPicAttachService;

    @ApiOperation(value = "保存服务协议", notes = "保存服务协议", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/saveAgreement", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO saveAgreement(@RequestBody @ApiParam(name = "moduleVO", value = "moduleVO")
                                       BgModuleVO moduleVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        moduleVO.setBgModuleType(ModuleTypeEnum.AGREEMENT.getCode());
        bgModuleService.save(moduleVO, getLoginAccount(session));
        return baseAjaxVO;
    }

    @ApiOperation(value = "保存租房说明", notes = "保存租房说明", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/saveRentExplain", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO saveRentExplain(@RequestBody @ApiParam(name = "moduleVO", value = "moduleVO")
                                       BgModuleVO moduleVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        moduleVO.setBgModuleType(ModuleTypeEnum.RENT_EXPLAIN.getCode());
        bgModuleService.save(moduleVO, getLoginAccount(session));
        return baseAjaxVO;
    }

    @ApiOperation(value = "查看服务协议", notes = "查看服务协议", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/queryAgreement", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO queryAgreement(){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        BgModuleVO moduleVO = bgModuleService.findByType(ModuleTypeEnum.AGREEMENT.getCode());
        baseAjaxVO.setResult(moduleVO);
        return baseAjaxVO;
    }

    @ApiOperation(value = "查看租房说明", notes = "查看租房说明", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/queryRentExplain", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO queryRentExplain(){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        BgModuleVO moduleVO = bgModuleService.findByType(ModuleTypeEnum.RENT_EXPLAIN.getCode());
        baseAjaxVO.setResult(moduleVO);
        return baseAjaxVO;
    }

    /**
     * 上传客服微信
     * @param uploadFile 附件信息
     * @return
     */
    @ApiOperation(value = "客服微信上传", notes = "客服微信上传", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/serviceUpload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseAjaxVO uploadPictures(@ApiParam(name = "uploadFile", value = "附件信息")
                                             MultipartFile uploadFile) {
        return bgPicAttachService.savePicAttach(uploadFile, UploadFileTypeEnum.CUSTOMER_FILE.getCode());
    }

    /**
     * 上传平台微信
     * @param uploadFile 附件信息
     * @return
     */
    @ApiOperation(value = "平台微信上传", notes = "平台微信上传", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/platformUpload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseAjaxVO platformUpload( @ApiParam(name = "uploadFile", value = "附件信息")
                                              MultipartFile uploadFile) {
        return bgPicAttachService.savePicAttach(uploadFile, UploadFileTypeEnum.PLATFORM_FILE.getCode());
    }

}
