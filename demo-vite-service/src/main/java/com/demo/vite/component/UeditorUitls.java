package com.demo.vite.component;

import com.alibaba.fastjson.JSONObject;
import com.demo.vite.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhujie
 * @date 2022/6/23
 * @description Ueditor 富文本编辑器工具类，后端完全接管
 */

@Component
@Slf4j
public class UeditorUitls {

    //配置文件路径，默认为resources/ueditor-config.json
    private final String configPath = "ueditor-config.json";

    @Autowired
    private IFileService fileService;

    /**
     * 读取Ueditor配置文件，或者上传文件
     *
     * @param action 读配置文件：config  图片上传：uploadimage 详见 ActionMap.mapping
     * @return
     */
    public Object getConfigOrUploadFile(String action, HttpServletRequest request) {
        int actionCode = ActionMap.getType(action);
        Object result = null;
        switch (actionCode) {
            case ActionMap.CONFIG:
                try {
                    Resource resource = new ClassPathResource(configPath);
                    InputStream is = resource.getInputStream();
                    String configContent = this.readUeditorConfigFile(is);
                    result = JSONObject.parseObject(configContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case ActionMap.UPLOAD_IMAGE:
            case ActionMap.UPLOAD_SCRAWL:
            case ActionMap.UPLOAD_VIDEO:
            case ActionMap.UPLOAD_FILE:
                MultipartHttpServletRequest multipartRequest =
                        WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
                MultipartFile uploadFile = multipartRequest.getFile("upfile");
                result = fileService.upload2Server(uploadFile);
                break;
            case ActionMap.CATCH_IMAGE:
            case ActionMap.LIST_IMAGE:
                result = fileService.getFileList();
                break;
            case ActionMap.LIST_FILE:



                break;
        }
        return result;
    }

    /**
     * 读取配置文件信息
     *
     * @param is
     * @return
     * @throws IOException
     */
    private String readUeditorConfigFile(InputStream is) throws IOException {
        StringBuilder builder = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(is, "UTF-8");
            BufferedReader bfReader = new BufferedReader(reader);
            String tmpContent = null;
            while ((tmpContent = bfReader.readLine()) != null) {
                builder.append(tmpContent);
            }
            bfReader.close();
        } catch (UnsupportedEncodingException e) {
            // 忽略
        }
        return this.filter(builder.toString());
    }

    // 过滤输入字符串, 剔除多行注释以及替换掉反斜杠,官方配置文件中有注释
    private String filter(String input) {
        return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

    }

    /**
     * 请求action映射类
     */
    static class ActionMap {

        public static final Map<String, Integer> mapping;
        // 获取配置请求
        public static final int CONFIG = 0;
        public static final int UPLOAD_IMAGE = 1;
        public static final int UPLOAD_SCRAWL = 2;
        public static final int UPLOAD_VIDEO = 3;
        public static final int UPLOAD_FILE = 4;
        public static final int CATCH_IMAGE = 5;
        public static final int LIST_FILE = 6;
        public static final int LIST_IMAGE = 7;

        static {
            mapping = new HashMap<String, Integer>() {{
                put("config", ActionMap.CONFIG);
                put("uploadimage", ActionMap.UPLOAD_IMAGE);
                put("uploadscrawl", ActionMap.UPLOAD_SCRAWL);
                put("uploadvideo", ActionMap.UPLOAD_VIDEO);
                put("uploadfile", ActionMap.UPLOAD_FILE);
                put("catchimage", ActionMap.CATCH_IMAGE);
                put("listfile", ActionMap.LIST_FILE);
                put("listimage", ActionMap.LIST_IMAGE);
            }};
        }

        public static int getType(String key) {
            return ActionMap.mapping.get(key);
        }
    }
}

