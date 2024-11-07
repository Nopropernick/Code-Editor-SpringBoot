package com.example.codeeditor.controller;

import com.example.codeeditor.model.CodeRequest;
import com.example.codeeditor.service.CodeExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/code")
@CrossOrigin(origins = "http://localhost:3000")
public class CodeController {

    @Autowired
    private CodeExecutionService codeExecutionService;

    @PostMapping("/execute")
    public String executeCode(@RequestBody CodeRequest codeRequest) {
        return codeExecutionService.executeCode(codeRequest.getCode(), codeRequest.getLanguage());
    }
}
