package com.demo.file.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MinioResponseDTO {

private  Long fileId;

private String fileUrl;

private String originalFileName;
}
