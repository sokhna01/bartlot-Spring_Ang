// package com.bartlot.Server.service;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.core.io.Resource;
// import org.springframework.core.io.UrlResource;
// import org.springframework.stereotype.Service;

// import java.net.MalformedURLException;
// import java.nio.file.Path;
// import java.nio.file.Paths;

// @Service
// public class FileStorageService {

// @Value("${file.upload-dir}")
// private String fileUploadDir;

// public Resource loadFileAsResource(String fileName) throws Exception {
// try {
// Path filePath = Paths.get(fileUploadDir).resolve(fileName).normalize();
// Resource resource = new UrlResource(filePath.toUri());
// if (resource.exists()) {
// return resource;
// } else {
// throw new Exception("File not found " + fileName);
// }
// } catch (MalformedURLException ex) {
// throw new Exception("File not found " + fileName, ex);
// }
// }
// }
