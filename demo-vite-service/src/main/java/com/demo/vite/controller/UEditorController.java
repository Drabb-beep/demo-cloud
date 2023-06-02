package com.demo.vite.controller;

import com.baidu.ueditor.ActionEnter;
import com.demo.vite.component.UeditorUitls;
import demo.common.model.ResultVo;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/ueditor")
public class UEditorController {

    @Autowired
    private UeditorUitls ueditorUitls;

//    Ueditor富文本编辑器服务端统一请求接口
    @GetMapping("/config/upload/file")
    public Object getConfigUploadFile(String action, HttpServletRequest request){
        Object result = ueditorUitls.getConfigOrUploadFile(action, request);
        return result;
    }

    //    Ueditor富文本编辑器服务端统一请求接口
    @PostMapping("/config/upload/file")
    public Object postConfigUploadFile(String action, HttpServletRequest request){
        Object result = ueditorUitls.getConfigOrUploadFile(action, request);
        return result;
    }

}