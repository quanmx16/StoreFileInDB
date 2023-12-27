package com.example.uploadfileandstoretodb.controller;

import com.example.uploadfileandstoretodb.model.FileData;
import com.example.uploadfileandstoretodb.service.IFileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class FileDataController {
    private IFileDataService fileDataService;

    @Autowired
    public FileDataController(IFileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    @PostMapping("")
    public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        FileData fileData = fileDataService.save(multipartFile);
        return ResponseEntity.ok("Saved");
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName) {
        FileData fileData = fileDataService.getByFileName(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(fileData.getType()))
                .body(fileData.getData());
    }
}
