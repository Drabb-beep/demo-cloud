package com.demo.vite.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    Object upload2Server(MultipartFile uploadFile);

    Object getFileList();
}
