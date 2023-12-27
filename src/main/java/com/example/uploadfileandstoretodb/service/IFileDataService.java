package com.example.uploadfileandstoretodb.service;

import com.example.uploadfileandstoretodb.model.FileData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileDataService {
    public FileData save(MultipartFile multipartFile) throws IOException;

    public FileData getByFileName(String fileName);
}
