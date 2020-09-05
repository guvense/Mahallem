package com.mahallem.service;


import com.mahallem.blobstorage.BlobStorageService;
import com.mahallem.blobstorage.BlobStorageServiceImpl;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RestClientTest(BlobStorageServiceTest.class)
@RunWith(MockitoJUnitRunner.class)
public class BlobStorageServiceTest {

    // private CloudBlobContainer cloudBlobContainer;

    @Mock
    private BlobStorageService blobStorageService;

    private URI uri;
    private String filename;

    @Before
    public void init(){
        uri = URI.create("http://test");
        filename = "filename.txt";

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "data",
                "filename.txt",
                "text/plain",
                "some xml".getBytes()
        );
    }

    @Test
    public void uploadPicture_withAllParameters() {

    }

}
