package com.example.demo.Controller;

import com.example.demo.Service.SubtitleService;
import com.example.demo.Service.TextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AudioController {

    @Autowired
    private SubtitleService subtitleService;

    @Autowired
    private TextToSpeechService textToSpeechService;

//    @PostMapping("/convert-and-download")
//    public ResponseEntity<InputStreamResource> convertToAudioAndDownload(@RequestParam("file") MultipartFile file) throws IOException {
//        String fileName = file.getOriginalFilename();
//        File tempFile = File.createTempFile("temp", ".srt");
//        file.transferTo(tempFile);
//
//        List<String> lines = subtitleService.parseSRT(tempFile.getPath());
//        StringBuilder text = new StringBuilder();
//        for (String line : lines) {
//            text.append(line).append(" ");
//        }
//
//        // 生成音频文件
//        String outputFilePath = "output_local.wav";
//        textToSpeechService.generateSpeech(text.toString(), outputFilePath);
//
//        // 创建文件对象
//        File audioFile = new File(outputFilePath);
//
//        // 创建文件输入流
//        FileInputStream fileInputStream = new FileInputStream(audioFile);
//
//        // 创建响应头
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=" + outputFilePath);
//        headers.add("Content-Type", "audio/wav");
//
//        // 返回文件流
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(audioFile.length())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(new InputStreamResource(fileInputStream));
//    }
}

