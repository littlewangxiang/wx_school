package com.wx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUpload {

    @RequestMapping("/vidio")
    public String vidio(MultipartFile multipartFile) {
        try {

            multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }
}
