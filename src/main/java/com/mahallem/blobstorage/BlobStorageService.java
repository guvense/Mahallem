package com.mahallem.blobstorage;

import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

public interface BlobStorageService {

    public URI uploadPicture(MultipartFile multipartFile);
}
