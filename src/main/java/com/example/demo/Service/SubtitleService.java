package com.example.demo.Service;



import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubtitleService {

    public List<String> parseSRT(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.matches("\\d+") && !line.contains("-->")) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }
}
