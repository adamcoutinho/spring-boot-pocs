package com.main.aws.app;

import com.main.aws.app.services.S3BucketService;
import com.main.aws.app.services.S3ObjectService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PocAwsS3ApplicationTests {

    private S3BucketService s3BucketService;

    private S3ObjectService s3ObjectService;

    @Autowired
    public PocAwsS3ApplicationTests(S3BucketService s3BucketService, S3ObjectService s3ObjectService) {
        this.s3BucketService = s3BucketService;
        this.s3ObjectService = s3ObjectService;
    }


    private String CREATE_BUCKET = "documents-test";

    @Test
    @DisplayName("should create bucket s3 in localstack")
    public void createBucket(){
        this.s3BucketService.createBucket(this.CREATE_BUCKET);
    }

    @Test
    @DisplayName("should store object inside bucket s3 in localstack")
    public void uploadObject(){
//        this.s3ObjectService.updloadFile(this.CREATE_BUCKET)
    }

}
