package com.ktds.todo1.controller;

import com.ktds.todo1.dto.file.UploadDTO;
import com.ktds.todo1.dto.file.UploadResultDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class UpDownController {

    @Value("${com.ktds.upload.path}")// import 시에 springframework으로 시작하는 Value
    private String uploadPath;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadDTO uploadDTO){

        log.info(uploadDTO);

        List<MultipartFile> fileList = uploadDTO.getFiles();

        List<UploadResultDTO> result = new ArrayList<>();

        if(fileList != null){
            fileList.forEach(multipartFile -> {

                String uuid = UUID.randomUUID().toString();
                String originalName = multipartFile.getOriginalFilename();

                Path savePath = Paths.get(uploadPath, uuid+"_"+ originalName);

                boolean image = false;

                try {
                    multipartFile.transferTo(savePath);

                    // 만약 이미지라면 섬네일 파일을 제작

                    if(Files.probeContentType(savePath).startsWith("image")){

                        image = true;

                        File thumbFile = new File(uploadPath, "s_" + uuid+"_"+ originalName);

                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200,200);
                    }

                    result.add(UploadResultDTO.builder()
                                    .uuid(uuid).
                                    fileName(originalName).
                                    img(image).
                                    build());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }


        return result;
    }

    @ApiOperation(value = "다운로드 파일", notes = "GET방식으로 첨부파일 조회")
    @GetMapping("/down/{fileName}")
    public ResponseEntity<Resource> downFileGET(@PathVariable String fileName){

        Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try{
            // headers.add("Content-Type", Files.probeContentType( resource.getFile().toPath() ));
            headers.add("Content-Type", "application/octet-stream");
        } catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @ApiOperation(value = "view 파일", notes = "GET방식으로 첨부파일 조회")
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName){

        Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try{
            // headers.add("Content-Type", Files.probeContentType( resource.getFile().toPath() ));
            headers.add("Content-Type", Files.probeContentType( resource.getFile().toPath()));
        } catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    
    @ApiOperation(value = "remove 파일", notes = "DELETE 방식으로 파일 삭제")
    @DeleteMapping("/remove/{fileName}")
    public Map<String,Boolean> removeFile(@PathVariable String fileName){

        Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);
        String resourceName = resource.getFilename();

        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;

        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete();

            //섬네일이 존재한다면
            if(contentType.startsWith("image")){
                File thumbnailFile = new File(uploadPath+File.separator +"s_" + fileName);
                thumbnailFile.delete();
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        resultMap.put("result", removed);

        return resultMap;
    }
}