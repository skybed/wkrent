package com.wkrent.business.bg.attach.service.impl;

import com.google.common.collect.Lists;
import com.wkrent.business.bg.attach.dao.BgPicAttachDao;
import com.wkrent.business.bg.attach.service.BgPicAttachService;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.entity.po.BgPicAttach;
import com.wkrent.common.entity.vo.BgPicAttachVO;
import com.wkrent.common.exception.WkRentException;
import com.wkrent.common.util.BeanUtil;
import com.wkrent.common.util.PropertiesUtils;
import com.wkrent.common.util.UUIDUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
@Service
public class BgPicAttachServiceImpl implements BgPicAttachService{

    /**
     * 图片存放地址
     */
    private static final String UPLOAD_DIRECTORY = PropertiesUtils.getProperty("attach.upload.directory", "/wkrent/bg/upload/image");

    @Autowired
    private BgPicAttachDao bgPicAttachDao;

    @Override
    public List<BgPicAttachVO> selectByOwnerId(String ownerId) {
        List<BgPicAttach> attachList =   bgPicAttachDao.selectByOwnerId(ownerId);
        if(CollectionUtils.isEmpty(attachList)){
            return Lists.newArrayList();
        }
        return BeanUtil.copyList(attachList, BgPicAttachVO.class);
    }

    @Override
    public BaseAjaxVO savePicAttach(MultipartFile uploadFile, String fileOwnerType) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        try {
            BgPicAttachVO picAttach = uploadAttachInfo(uploadFile, fileOwnerType);
            baseAjaxVO.setResult(picAttach);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
        }
        return baseAjaxVO;
    }

    /**
     * 上传附件并保存附件信息
     * @param uploadFile 附件
     * @param fileOwnerType 附件所属单据类型
     * @return 上传附件信息
     */
    private BgPicAttachVO uploadAttachInfo(MultipartFile uploadFile, String fileOwnerType) {
        // 判断文件是否为空
        if (!uploadFile.isEmpty()) {
            try {
                //判断文件目录是否存在，否则自动生成
                File directory = new File(UPLOAD_DIRECTORY);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // 获取上传的文件的名称    
                String filename = uploadFile.getOriginalFilename();

                //修改文件名称 uuid
                String fileUUID = UUIDUtil.getUUID();

                //获取后缀
                String prefix = filename.substring(filename.lastIndexOf(".") + 1);

                //修改后完整的文件名称
                String nFileName = fileUUID + "." + prefix;

                // 文件保存路径
                String filePath = FilenameUtils.concat(UPLOAD_DIRECTORY, nFileName);

                // 转存文件
                uploadFile.transferTo(new File(filePath));

                //写附件表
                String fileURL = UPLOAD_DIRECTORY + File.separator + nFileName;
                BgPicAttach picAttach = new BgPicAttach();
                picAttach.setPicAttachId(fileUUID);
                picAttach.setPicAttachName(filename);
                picAttach.setPicAttachUrl(fileURL);
                picAttach.setPicAttachFileType(prefix);
                picAttach.setPicAttachType(fileOwnerType);
                picAttach.setPicAttachFileVolume(String.valueOf(uploadFile.getSize()));
                bgPicAttachDao.insertSelective(picAttach);
                return BeanUtil.copyBean(picAttach, BgPicAttachVO.class);

            } catch (Exception e) {
                Logger.getRootLogger().error("上传图片抛异常，异常信息为：" + e.getMessage(), e);
                throw new WkRentException("上传图片异常，系统出错！");
            }
        }
        return null;
    }

    /**
     * 新增附件信息
     *
     * @param uploadFiles   附件List
     * @param fileOwnerType 附件所属对象类型
     * @return 已插入附件信息
     */
    @Override
    public BaseAjaxVO savePicAttachList(MultipartFile[] uploadFiles, String fileOwnerType) {
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        if(uploadFiles == null || uploadFiles.length == 0){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText("上传附件失败，附件信息为空！");
            return baseAjaxVO;
        }
        List<BgPicAttachVO> bgPicAttachVOList = Lists.newArrayList();
        try {
            for(MultipartFile multipartFile : uploadFiles){
                BgPicAttachVO bgPicAttachVO = uploadAttachInfo(multipartFile, fileOwnerType);
                bgPicAttachVOList.add(bgPicAttachVO);
            }
            baseAjaxVO.setResult(bgPicAttachVOList);
        }catch (Exception e){
            baseAjaxVO.setCode(Constants.FAILED_CODE);
            baseAjaxVO.setText(e.getMessage());
        }
        return baseAjaxVO;
    }

    @Override
    public void selectById(String picId, HttpServletResponse response) {
        BgPicAttach bgPicAttach =  bgPicAttachDao.selectByPrimaryKey(picId);
        if(bgPicAttach == null){
            throw new WkRentException("获取附件信息失败，附件已被删除！");
        }
        String path = bgPicAttach.getPicAttachUrl();
        File file = new File(path);

        //如果文件存在
        if(file.exists()) {
            FileInputStream inputStream = null;
            OutputStream stream = null;
            try {
                inputStream = new FileInputStream(file);
                byte[] data = new byte[(int)file.length()];
                inputStream.read(data);

                //设置返回内容格式
                response.setContentType("image/" + bgPicAttach.getPicAttachFileType());

                stream = response.getOutputStream();
                stream.write(data);
                stream.flush();
            } catch (Exception e) {
                Logger.getRootLogger().error("读图片异常，异常信息为：" + e.getMessage(), e);
            } finally {
                try {
                    if(stream != null) {
                        stream.close();
                    }
                } catch (IOException e) {
                    Logger.getRootLogger().error("读图片关闭OutputStream抛异常，异常信息为：" + e.getMessage(), e);
                }

                try {
                    if(inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    Logger.getRootLogger().error("读图片关闭InputStream抛异常，异常信息为：" + e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void deletePicAttach(String picId) {
        bgPicAttachDao.deletePicAttach(picId);
    }

    /**
     * 根据附件id删除附件信息
     *
     * @param picIdList 附件id
     */
    @Override
    public void deletePicAttachList(List<String> picIdList) {
        bgPicAttachDao.deletePicAttachList(picIdList);
    }

    private void updatePicAttachOwner(List<String> picIdList, String ownerId) {
        if(CollectionUtils.isNotEmpty(picIdList) && StringUtils.isBlank(ownerId)){
            bgPicAttachDao.updatePicAttachOwner(picIdList, ownerId);
        }
    }

    @Override
    public void updateAttachOwnerId(List<String> attachIdList, String ownerId){
        if(CollectionUtils.isEmpty(attachIdList)){
            return;
        }
        //获取当前单据对应的附件信息
        List<BgPicAttachVO> picAttachVOList =  selectByOwnerId(ownerId);
        //如果当前单据下已有附件信息，则对比附件id，删除此次未保存附件信息
        List<String> deleteIdList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(picAttachVOList)){
            Set<String> attachIdSet = new HashSet<>(attachIdList);
            for(BgPicAttachVO bgPicAttachVO : picAttachVOList){
                if(!attachIdSet.contains(bgPicAttachVO.getPicAttachId())){
                    deleteIdList.add(bgPicAttachVO.getPicAttachId());
                }
            }
        }
        if(CollectionUtils.isNotEmpty(deleteIdList)){
            deletePicAttachList(deleteIdList);
        }
        updatePicAttachOwner(attachIdList, ownerId);
    }
}
