package com.demo.file.controller;

import cn.hutool.core.io.FileUtil;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.demo.file.config.MinioConfig;
import com.demo.file.dto.response.MinioResponseDTO;
import com.demo.file.model.MinioFile;
import com.demo.file.service.IMinioFileService;
import com.demo.file.utils.MinioClientUtils;
import demo.common.model.ResultVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author df
 * @since 2023-06-01
 */
@RestController
@RequestMapping("/minio-file")
public class MinioFileController {
    private static final Logger logger = LoggerFactory.getLogger(MinioFileController.class);
    @Autowired
    private MinioClientUtils minioClientUtils;
    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private IMinioFileService minioFileService;

    @PostMapping(value = {"/admin/uploadFile","/web/uploadFile"})
    public ResultVo uploadFile(@RequestParam("files") List<MultipartFile> files) {
        logger.info(files.toString());
        if (CollectionUtils.isEmpty(files)){
            return ResultVo.error("未选择文件！");
        }

        List<MinioResponseDTO> MinioResponseDTOList=new ArrayList<>();
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
//            获取文件拓展名
            String extName = FileUtil.extName(originalFilename);
            logger.info("文件拓展名:"+extName);
//            生成新的文件名，存入到minio
            long millSeconds = Instant.now().toEpochMilli();
            String minioFileName=millSeconds+ RandomStringUtils.randomNumeric(12)+"."+extName;
            String contentType = file.getContentType();
            logger.info("文件mime:{}",contentType);
//            返回文件大小,单位字节
            long size = file.getSize();
            logger.info("文件大小："+size);
            try {
                String bucketName = minioConfig.getBucketName();
                boolean flag = minioClientUtils.bucketExists(bucketName);
                if (!flag){
                    minioClientUtils.makeBucket(bucketName);
                }
                minioClientUtils.putObject(bucketName,file,minioFileName);
                String fileUrl = minioClientUtils.getObjectUrl(bucketName, minioFileName);
                MinioFile minioFile = new MinioFile();
                minioFile.setOriginalFileName(originalFilename);
                minioFile.setFileExtName(extName);
                minioFile.setFileName(minioFileName);
                minioFile.setFileSize(size);
                minioFile.setMime(contentType);
                minioFile.setIsDelete(NumberUtils.INTEGER_ZERO);
                boolean insert = minioFileService.save(minioFile);
                if (insert) {
                    MinioResponseDTO minioResponseDTO = new MinioResponseDTO();
                    minioResponseDTO.setFileId(minioFile.getId());
                    minioResponseDTO.setOriginalFileName(originalFilename);
                    minioResponseDTO.setFileUrl(fileUrl);
                    MinioResponseDTOList.add(minioResponseDTO);
                }


            } catch (Exception e) {
                logger.error("上传文件出错:{}",e);
                return ResultVo.error("上传文件出错");

            }
        }

        return ResultVo.success(MinioResponseDTOList);
    }


    /**
     * 仅仅用于测试，是否可以正常上传文件
     * @return
     * @throws Exception
     */
    @GetMapping("/test")
    public ResultVo testPutObject() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\MSI\\Desktop\\新建文本文档.txt");
        boolean bs = minioClientUtils.putObject("fsp-dev", "新建文本文档.txt", fileInputStream, "image/jpg");
        logger.info("上传成功?"+bs);
        return ResultVo.success("上传成功");
    }

}
