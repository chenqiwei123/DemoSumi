package com.example.demo.Service;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@Service
public class TextToSpeechService {
    private static final String VOICENAME = "kevin16";

    public AudioInputStream generateSpeech(Voice voice,String text, String outputFilePath) {
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICENAME);
        AudioInputStream audioInputStream= null;
        if (voice != null) {
            voice.allocate();
            voice.setRate(120); // 设置语速为120%
            voice.setVolume(10); // 设置音量为10
            voice.speak("Hello, world!");
            try {
                voice.speak(text);

                System.out.println("Audio generated at: " + outputFilePath);
                audioInputStream = AudioSystem.getAudioInputStream(new FileInputStream(outputFilePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Voice not found");
        }
        return audioInputStream;
    }
}
