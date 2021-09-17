package com.main.aws.app.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3ObjectService {

    private AmazonS3 s3Client;

    @Autowired
    public S3ObjectService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public Boolean updloadFile(String bucketName, MultipartFile multipartFile) throws IOException {
        File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getName());
        multipartFile.transferTo(tempFile);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,multipartFile.getOriginalFilename(), tempFile);
        this.s3Client.putObject(putObjectRequest);
        tempFile.deleteOnExit();
        return true;
    }

    public void downloadFile(String bucketName, String filePath, File file) throws IOException {
        S3Object s3object = s3Client.getObject(bucketName, filePath);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, file);
    }

    public List<S3ObjectSummary> listingFile(String bucketName) {
        return s3Client.listObjects(bucketName).getObjectSummaries();
    }

    public void copyingRenamingAndMovingAnObject(String bucketNameSourceOrigin, String fileOrigin,  String bucketNameSourceDestiny, String fileDestiny) {
        /**
         1 - source bucket name
         2 - object key in source bucket
         3 - destination bucket name (it can be same as source)
         4 - object key in destination bucket
         **/
        s3Client.copyObject(
                bucketNameSourceOrigin,
                fileOrigin,
                bucketNameSourceDestiny,
                fileDestiny
        );

    }

    public void deletingObject(String bucketName, String keyFileName) {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName,keyFileName);
        this.s3Client.deleteObject(deleteObjectRequest);
    }

    public void deletingObjects(String bucketName, String[] keysFile) {

        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName)
                .withKeys(keysFile);
        s3Client.deleteObjects(deleteObjectsRequest);
    }

}
