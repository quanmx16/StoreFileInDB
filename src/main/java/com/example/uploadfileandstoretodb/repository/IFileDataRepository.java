package com.example.uploadfileandstoretodb.repository;

import com.example.uploadfileandstoretodb.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IFileDataRepository extends JpaRepository<FileData, Long> {

    public FileData save(FileData fileData);
    public Optional<FileData> findByName(String name);
}
