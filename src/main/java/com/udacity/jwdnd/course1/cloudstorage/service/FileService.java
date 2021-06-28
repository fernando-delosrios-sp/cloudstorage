package com.udacity.jwdnd.course1.cloudstorage.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

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

    public void save(MultipartFile filedata, Integer userId) {
        try {
            Blob blob = new SerialBlob(filedata.getBytes());
            byte bytes [] = blob.getBytes(1, (int) blob.length());
            File file = new File(filedata.getOriginalFilename(), filedata.getContentType(), String.valueOf(filedata.getSize()), userId, bytes);
            fileMapper.save(file);
        } catch (SerialException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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