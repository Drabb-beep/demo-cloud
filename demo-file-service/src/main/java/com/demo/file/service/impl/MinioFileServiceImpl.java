package com.demo.file.service.impl;

import com.demo.file.model.MinioFile;
import com.demo.file.mapper.MinioFileMapper;
import com.demo.file.service.IMinioFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author df
 * @since 2023-06-01
 */
@Service
public class MinioFileServiceImpl extends ServiceImpl<MinioFileMapper, MinioFile> implements IMinioFileService {

}
