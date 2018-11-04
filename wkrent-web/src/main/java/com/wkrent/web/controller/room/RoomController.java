package com.wkrent.web.controller.room;

import com.wkrent.business.bg.roommanagement.service.BgRoomService;
import com.wkrent.common.base.BaseController;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.paging.PageResult;
import com.wkrent.common.entity.vo.BgRoomVO;
import com.wkrent.common.exception.WkRentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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

    @ApiOperation(value = "条件查询房源信息", notes = "条件查询房源信息", httpMethod = "POST", response = BgRoomVO.class)
    @RequestMapping(value = "/findByCondition", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BgRoomVO> findByCondition(@RequestBody BgRoomVO roomVO){
        return bgRoomService.findByCondition(roomVO);
    }

    @ApiOperation(value = "根据id查询房源信息", notes = "根据id查询房源信息", httpMethod = "POST", response = BaseAjaxVO.class)
    @RequestMapping(value = "/findByRoomId", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO findByRoomId(@RequestBody String roomId){
        return bgRoomService.findByRoomId(roomId);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO insert(@RequestBody BgRoomVO roomVO, HttpSession session){
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO update(@RequestBody BgRoomVO roomVO, HttpSession session){
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

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxVO manage(@RequestBody BgRoomVO roomVO, HttpSession session){
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            bgRoomService.updateRoomStatusById(roomVO, getLoginAccount(session));
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
}
