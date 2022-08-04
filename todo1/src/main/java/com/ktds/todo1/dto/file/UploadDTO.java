package com.ktds.todo1.dto.file;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadDTO {
    
    List<MultipartFile> files;


}
