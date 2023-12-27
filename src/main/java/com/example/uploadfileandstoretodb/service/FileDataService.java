package com.example.uploadfileandstoretodb.service;

import com.example.uploadfileandstoretodb.exception.NotFoundException;
import com.example.uploadfileandstoretodb.model.FileData;
import com.example.uploadfileandstoretodb.repository.IFileDataRepository;
import com.example.uploadfileandstoretodb.utils.FileCompression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileDataService implements IFileDataService {
    private IFileDataRepository fileDataRepository;
    private FileCompression fileCompression;

    @Autowired
    public FileDataService(IFileDataRepository fileDataRepository, FileCompression fileCompression) {
        this.fileDataRepository = fileDataRepository;
        this.fileCompression = fileCompression;
    }

    @Override
    public FileData save(MultipartFile multipartFile) throws IOException {

        FileData fileData = FileData.builder()
                .name(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmssSSS")) + multipartFile.getOriginalFilename())
                .data(fileCompression.compressImage(multipartFile.getBytes()))
                .type(multipartFile.getContentType())
                .build();
        return fileDataRepository.save(fileData);
    }

    @Override
    public FileData getByFileName(String fileName) {
        FileData fileData = fileDataRepository.findByName(fileName)
                .orElse(null);
        if (fileData != null) {
            fileData.setData(fileCompression.decompressImage(fileData.getData()));
        } else {
            throw new NotFoundException("File Not Found");
        }
        return fileData;
    }
}
