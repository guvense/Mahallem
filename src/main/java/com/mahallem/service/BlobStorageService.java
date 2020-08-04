package com.mahallem.service;

import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

public interface BlobStorageService {

    public URI uploadPicture(MultipartFile multipartFile);
}
