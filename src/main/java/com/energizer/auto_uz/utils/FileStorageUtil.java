package com.energizer.auto_uz.utils;

import com.energizer.auto_uz.exceptions.FileNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStorageUtil {
    public String saveFile(MultipartFile file) {
        try {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + fileName));
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> saveFiles(List<MultipartFile> files) {
        List<String> filenames = new ArrayList<>();
        for(var file : files) {
            filenames.add(saveFile(file));
        }
        return filenames;
    }
    public Resource loadFile(String filename) {
        try {
            Path filePath = Paths.get(uploadPath).toAbsolutePath().normalize().resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotExistException("File not found on server");
            }
        } catch (MalformedURLException e) {
            throw new FileNotExistException("File not found on server");
        }
    }
    private final String uploadPath;
    @Autowired
    public FileStorageUtil(@Value("${file.upload-path}") String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
