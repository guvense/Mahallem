package com.mahallem.storage;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("blob")
@RequiredArgsConstructor
public class BlobController {

    private final CloudBlobContainer cloudBlobContainer;

    @PostMapping
    public String writeBlobFile(@RequestParam(value = "image", required = false) MultipartFile file) throws IOException{

        return upload(file).toURL().toString();
    }

    private URI upload(MultipartFile multipartFile){
        URI uri = null;
        CloudBlockBlob blob = null;
        try {
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String fileName = String.join(".", UUID.randomUUID().toString(), extension);
            blob = cloudBlobContainer.getBlockBlobReference(fileName);
            blob.upload(multipartFile.getInputStream(), -1);
            uri = blob.getUri();
        } catch (URISyntaxException | StorageException | IOException e) {
            // TODO custom exception fırlatılacak
            e.printStackTrace();
        }
        return uri;
    }

}
