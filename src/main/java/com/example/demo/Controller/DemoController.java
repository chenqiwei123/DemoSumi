package com.example.demo.Controller;

import com.example.demo.annotation.RunWshLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DemoController {

    @GetMapping("/methodAspectLog")
    @RunWshLog
    public Map<String, List<String>> methodAspectLog(@RequestParam(value = "name", defaultValue = "run") String name, @RequestParam(value = "age", defaultValue = "18") int age){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("sunMi");
        map.put("runWsh",list);
        return map;
    }
}
