package com.wkrent.web.controller.attach;

import com.wkrent.business.bg.attach.service.BgPicAttachService;
import com.wkrent.common.entity.vo.BgPicAttachVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@Api(value = "attach", tags = "附件操作")
@Controller
@RequestMapping("/attach")
public class AttachController {

    @Autowired
    private BgPicAttachService bgPicAttachService;

    /**
     * 获取文件流
     * @param response 返回结果
     * @param picAttachVO 附件id
     * @return 对应附件文件流
     */
    @ApiOperation(value = "获取文件流", notes = "获取文件流", httpMethod = "POST")
    @RequestMapping(value="/getAttachById", method = RequestMethod.POST)
    public void getAttachById(@RequestBody @ApiParam(name = "picAttachId", value = "根据附件id获取文件流(仅传入picAttachId即可)")
                                          BgPicAttachVO picAttachVO, HttpServletResponse response) {
        bgPicAttachService.selectById(picAttachVO.getPicAttachId(), response);
    }

}