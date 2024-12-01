package com.example.demo.Controller;


import com.example.demo.Service.GoogleTTSService;
import com.example.demo.Service.SubtitleService;
import com.example.demo.Service.TextToSpeechService;
import com.sun.speech.freetts.Voice;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class UploadController {

    private final SubtitleService subtitleService;
    private final TextToSpeechService textToSpeechService;
    private final GoogleTTSService googleTTSService;

    public UploadController(SubtitleService subtitleService,
                            TextToSpeechService textToSpeechService,
                            GoogleTTSService googleTTSService) {
        this.subtitleService = subtitleService;
        this.textToSpeechService = textToSpeechService;
        this.googleTTSService = googleTTSService;
    }

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @RequestMapping(value = "/api/convert-to-audio", method = RequestMethod.POST)
    public void convertToAudio(@RequestParam("file") MultipartFile file) throws Exception {
        Voice voice=null;
        String fileName = file.getOriginalFilename();
        File tempFile = File.createTempFile("temp", ".srt");
        file.transferTo(tempFile);

        List<String> lines = subtitleService.parseSRT(tempFile.getPath());
        StringBuilder text = new StringBuilder();
        for (String line : lines) {
            text.append(line).append(" ");
        }
        googleTTSService.generateSpeech(String.valueOf(text),"D:\\AI\\audio");

    }

}
