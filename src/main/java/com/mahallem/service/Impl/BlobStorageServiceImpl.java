package com.mahallem.service.Impl;

import com.mahallem.exception.UploadPictureFailedException;
import com.mahallem.service.BlobStorageService;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlobStorageServiceImpl implements BlobStorageService {

    private final CloudBlobContainer cloudBlobContainer;

    @Override
    public URI uploadPicture(MultipartFile multipartFile) {
        URI uri = null;
        CloudBlockBlob blob = null;
        try {
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String fileName = String.join(".", UUID.randomUUID().toString(), extension);
            blob = cloudBlobContainer.getBlockBlobReference(fileName);
            blob.upload(multipartFile.getInputStream(), -1);
            uri = blob.getUri();
        } catch (URISyntaxException | StorageException | IOException e) {
            throw new UploadPictureFailedException();
        }
        return uri;
    }
}
