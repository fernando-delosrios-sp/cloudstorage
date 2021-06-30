package com.udacity.jwdnd.course1.cloudstorage.service;

import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public void save(MultipartFile fileData, Integer userId) throws Exception {
        if (fileData.getSize() == 0) {
            throw new Exception("Please select a valid file");
        }
        if (fileMapper.exists(fileData.getOriginalFilename())) {
            throw new Exception("File already exists");
        } else {
            Blob blob = new SerialBlob(fileData.getBytes());
            byte bytes [] = blob.getBytes(1, (int) blob.length());
            File file = new File(fileData.getOriginalFilename(), fileData.getContentType(), String.valueOf(fileData.getSize()), userId, bytes);
            fileMapper.save(file);
        }
    }

    public File[] list(Integer userId) {
        return fileMapper.list(userId);
    }

    public File get(Integer fileId) {
        return fileMapper.get(fileId);
    }

    public void delete(Integer fileId) {
        fileMapper.delete(fileId);
    }
}