package com.wkrent.business.app.image.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.wkrent.business.app.picture.service.AppImageService;
import com.wkrent.business.app.util.UUIDUtil;
import com.wkrent.common.constants.Constant;
import com.wkrent.common.obj.ResultData;
import com.wkrent.common.util.PropertiesUtils;

@Controller
@RequestMapping("/app/api")
public class AppImageController {
	
	//图片存放地址
	private static final String UPLOAD_DIRECTORY = PropertiesUtils.getProperty("fileupload.directory", "/wkrent/app/upload/image");
	
	@Autowired
	private AppImageService appImageService;
	
	/**
	 * 上传文件
	 * @param request
	 * @param uploadfile
	 * @return
	 */
	@RequestMapping(value = "/uploadPicture.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String uploadPicture(HttpServletRequest request, MultipartFile uploadfile) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileId", "");
		
		// 判断文件是否为空
		if (!uploadfile.isEmpty()) {
			try {
				//判断文件目录是否存在，否则自动生成
                File directory = new File(UPLOAD_DIRECTORY);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                
                // 获取上传的文件的名称    
                String filename = uploadfile.getOriginalFilename();
                
                //修改文件名称 uuid
                String fileUUID = UUIDUtil.getUUIDString();
                
                //获取后缀
                String prefix = filename.substring(filename.lastIndexOf(".") + 1);
                
                //修改后完整的文件名称
                String nFileName = fileUUID + "." + prefix;
                
                // 文件保存路径
                String filePath = FilenameUtils.concat(UPLOAD_DIRECTORY, nFileName);
                
                // 转存文件
                uploadfile.transferTo(new File(filePath));
                
                //写附件表
                
                map.put("fileId", fileUUID);

            } catch (Exception e) {
            	Logger.getRootLogger().error("上传图片抛异常，异常信息为：" + e.getMessage(), e);
            }
        }
        resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}


	@RequestMapping(value = "/uploadPictures.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String uploadPictures(HttpServletRequest request, MultipartFile[] uploadfiles) {
		ResultData resultData = new ResultData();
		resultData.setCode(Constant.RESULT_SUCCESS_CODE);
		resultData.setMsg(Constant.RESULT_SUCCESS_MSG);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileId", "");
		
		List<String> idsList = new ArrayList<String>();
		//判断file数组不能为空并且长度大于0
        if (uploadfiles != null && uploadfiles.length > 0) {
        	File directory = new File(UPLOAD_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            //循环获取file数组中得文件
            for (int i = 0; i < uploadfiles.length; i++) {
                MultipartFile file = uploadfiles[i];
                
                // 获取上传的文件的名称    
                String filename = file.getOriginalFilename();
                
                //修改文件名称 uuid
                String fileUUID = UUIDUtil.getUUIDString();
                
                //获取后缀
                String prefix = filename.substring(filename.lastIndexOf(".") + 1);
                
                //修改后完整的文件名称
                String nFileName = fileUUID + "." + prefix;
                
                // 文件保存路径
                String filePath = FilenameUtils.concat(UPLOAD_DIRECTORY, nFileName);
                
                try {
                	// 转存文件
                	file.transferTo(new File(filePath));
                	idsList.add(fileUUID);
                } catch (IOException e) {
                	Logger.getRootLogger().error("批量上传图片抛异常，异常信息为：" + e.getMessage(), e);
                }
            }
            map.put("fileId", idsList);
        }
        resultData.setData(JSON.toJSONString(map));
		return JSON.toJSONString(resultData);
	}
	
}