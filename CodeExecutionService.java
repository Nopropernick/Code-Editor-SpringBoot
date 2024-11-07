package com.example.codeeditor.service;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class CodeExecutionService {

    public String executeCode(String code, String language) {

        if (code == null || code.trim().isEmpty()) {
            code = "print('Hello, World!')";
        }

        String[] command = null; // Initialize command as null

        try {
            // Set command based on language
            if (language.equals("python")) {
                command = new String[] { "python", "-c", code };
            } else if (language.equals("java")) {
            } else {
                return "Error: Unsupported language";
            }

            if (command == null) {
                return "Error: Unsupported language";
            }

            Process process = Runtime.getRuntime().exec(command);
            BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = outputReader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Capture any errors
            StringBuilder errorOutput = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }

            return errorOutput.length() > 0 ? errorOutput.toString() : output.toString();
        } catch (Exception e) {
            return "Error executing code: " + e.getMessage();
        }
    }
}
