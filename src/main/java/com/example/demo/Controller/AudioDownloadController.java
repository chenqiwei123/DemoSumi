package com.example.demo.Controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class AudioDownloadController {

    @GetMapping("/download-audio")
    public ResponseEntity<InputStreamResource> downloadAudio(@RequestParam String fileName) throws IOException {
        // 音频文件路径（可以是生成文件的路径）
        File audioFile = new File("path/to/your/audio/files/" + fileName);

        // 检查文件是否存在
        if (!audioFile.exists()) {
            return ResponseEntity.notFound().build();
        }

        // 创建文件输入流
        FileInputStream fileInputStream = new FileInputStream(audioFile);

        // 创建响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        headers.add("Content-Type", "audio/wav");  // 根据音频文件类型调整MIME类型

        // 创建响应实体
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(audioFile.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(fileInputStream));
    }
}

