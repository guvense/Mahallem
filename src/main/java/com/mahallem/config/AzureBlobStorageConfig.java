package com.mahallem.config;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@RequiredArgsConstructor
@Configuration
@Profile("dev")
public class AzureBlobStorageConfig {

    @Value("${spring.azure.blob.store.connectionString}")
    private String connectionString;

    @Value("${spring.azure.blob.store.containerName}")
    private String containerName;

    @Bean
    public CloudBlobClient cloudBlobClient() throws URISyntaxException, InvalidKeyException {
        CloudStorageAccount storageAccount = CloudStorageAccount.parse(connectionString);
        return storageAccount.createCloudBlobClient();
    }

    @Bean
    public CloudBlobContainer testBlobContainer() throws URISyntaxException, StorageException, InvalidKeyException {
        return cloudBlobClient().getContainerReference(containerName);
    }
}
