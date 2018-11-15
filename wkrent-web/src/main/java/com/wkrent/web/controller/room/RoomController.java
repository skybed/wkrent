package com.wkrent.web.controller.room;

import com.wkrent.business.bg.attach.service.BgPicAttachService;
import com.wkrent.business.bg.roommanagement.service.BgRoomService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.enums.UploadFileTypeEnum;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgPicAttachVO;
import com.wkrent.common.entity.vo.BgRoomVO;
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
 * 房源信息
 * @author Administrator
 */
@Api(value = "room", tags = "房源接口")
@Controller
@RequestMapping("/room")
public class RoomController extends BaseController{

    @Autowired
    private BgRoomService bgRoomService;

    @Autowired
    private BgPicAttachService bgPicAttachService;

    @ApiOperation(value = "条件查询房源信息", notes = "条件查询房源信息", httpMethod = "POST", response = BgRoomVO.class)
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgRoomVO> findByCondition(@RequestBody BgRoomVO roomVO){
        return bgRoomService.findByCondition(roomVO);
    }

    @ApiOperation(value = "根据id查询房源信息", notes = "根据id查询房源信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/findByRoomId", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO findByRoomId(@RequestBody @ApiParam(name = "roomId", value = "房源id")
                                               String roomId){
        return bgRoomService.findByRoomId(roomId);
    }

    @ApiOperation(value = "新增房源信息", notes = "新增房源信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody @ApiParam(name = "roomVO", value = "待新增房源信息")
                                         BgRoomVO roomVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO;
        try {
            baseAjaxVO = bgRoomService.insert(roomVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("新增房源失败", e, roomVO);
        }catch (Exception e){
            baseAjaxVO = new BaseAjaxVO();
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("新增房源失败，系统异常", e, roomVO);
        }
        return baseAjaxVO;
    }

    @ApiOperation(value = "修改房源信息", notes = "修改房源信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody @ApiParam(name = "roomVO", value = "待修改房源信息")
                                         BgRoomVO roomVO, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgRoomService.update(roomVO, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("更新房源信息失败", e, roomVO);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("更新房源信息失败，系统异常", e, roomVO);
        }
        return baseAjaxVO;
    }

    @ApiOperation(value = "房源下架", notes = "房源下架", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/soldOut", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO soldOut(@RequestBody @ApiParam(name = "roomId", value = "房源Id")
                                         String roomId, @ApiIgnore HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgRoomService.soldOut(roomId, getLoginAccount(session));
        }catch (WkRentException e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
            log.warn("下架房源失败", e, roomId);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(Constants.FAILED_TEXT);
            log.error("下架房源失败，系统异常", e, roomId);
        }
        return baseAjaxVO;
    }

    /**
     * 上传多文件
     * @param attachVOList 附件信息
     * @return
     */
    @ApiOperation(value = "房源附件上传", notes = "房源附件上传", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO uploadPictures(@RequestBody @ApiParam(name = "attachVOList", value = "附件List")
                                                 List<BgPicAttachVO> attachVOList) {
        return bgPicAttachService.savePicAttachListByBase64(attachVOList, UploadFileTypeEnum.MERCHANT_FILE.getCode());
    }
}
