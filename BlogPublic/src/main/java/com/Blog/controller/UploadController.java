package com.Blog.controller;

import com.Blog.domain.entity.ResponseResult;
import com.Blog.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(tags = "操作",description = "文件上传相关接口")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @ApiOperation(value = "操作结果",notes = "上传用户上传的图片")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
