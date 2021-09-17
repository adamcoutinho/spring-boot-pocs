package com.main.aws.app.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class S3BucketService {

    private final AmazonS3 s3Client;

    @Autowired
    public S3BucketService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public boolean createBucket(String bucketName) {
        this.s3Client.createBucket(bucketName);
        return true;

    }

    public boolean deleteBucket(String bucketName) {
        try {
            this.s3Client.deleteBucket(bucketName);
            return true;
        } catch (AmazonServiceException e) {
            System.err.println("e.getErrorMessage()");
            return false;
        }
    }

    public List<Bucket> listBuckets() {
        return this.s3Client.listBuckets();
    }

    public Boolean existBucket(String bucketName) {
        return this.s3Client.doesBucketExistV2(bucketName);
    }


}
