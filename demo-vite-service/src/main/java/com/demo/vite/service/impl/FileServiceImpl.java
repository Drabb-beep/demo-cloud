package com.demo.vite.service.impl;

import com.demo.vite.service.IFileService;
import com.demo.vite.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements IFileService {


    @Value("${web.upload-path}")
    private String path;
    @Value("${web.image-path}")
    private String imagePath;

    @Override
    public Object upload2Server(MultipartFile uploadFile) {
        Map<String, Object> rs = new HashMap<String, Object>();
        String originalFileName = "";
        if (!uploadFile.isEmpty()) {
            originalFileName = uploadFile.getOriginalFilename();
            String fileName = FileUtil.upload(uploadFile, path, originalFileName);;
            System.out.println(fileName);
            rs.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
            rs.put("url", imagePath+fileName); // 能访问到你现在图片的路径
            rs.put("title", originalFileName);
            rs.put("original", originalFileName);
        }
        return rs;
    }

    @Override
    public Object getFileList() {
        Map<String, Object> rs = new HashMap<String, Object>();
        List<String> fileNames = getFileNames(path);
        List<Map<String,String>> fileList = new ArrayList<>();
        fileNames.stream().forEach(e->{
            Map<String,String> map = new HashMap<>();
            map.put("url",imagePath+e);
            fileList.add(map);
        });
        rs.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
        rs.put("list", fileList); // 能访问到你现在图片的路径
        rs.put("start", 20);
        rs.put("total", fileNames.size());
        return rs;
    }


    /**
     * 得到文件名称
     *
     * @param path 路径
     * @return {@link List}<{@link String}>
     */
    private List<String> getFileNames(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        List<String> fileNames = new ArrayList<>();
        return getFileNames(file, fileNames);
    }

    /**
     * 得到文件名称
     *
     * @param file      文件
     * @param fileNames 文件名
     * @return {@link List}<{@link String}>
     */
    private List<String> getFileNames(File file, List<String> fileNames) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getFileNames(f, fileNames);
            } else {
                fileNames.add(f.getName());
            }
        }
        return fileNames;
    }


}
