package com.airbnb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    @Value("${aws.s3.access-key}")
    private String accessKey;
    @Value("${aws.s3.secret-key}")
    private String secreteKey;

    @Value("${aws.s3.region}")
    private String region;

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secreteKey);

        return S3Client.builder()
                .region(Region.AP_SOUTH_1)  // specify your region
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }
}