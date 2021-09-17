package com.main.aws.app.endpoints;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.gson.Gson;
import com.main.aws.app.services.S3BucketService;
import com.main.aws.app.services.S3ObjectService;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
public class S3AmazonEndPoint {

    private final S3BucketService s3BucketService;

    private final S3ObjectService s3ObjectService;

    @Autowired
    public S3AmazonEndPoint(S3BucketService s3BucketService, S3ObjectService s3ObjectService) {
        this.s3BucketService = s3BucketService;
        this.s3ObjectService = s3ObjectService;
    }


    /**
     * EndPoints for operation buckets s3
     **/
    @GetMapping("/bucket/listing")
    public ResponseEntity<List<Bucket>> list() {

        return ResponseEntity.status(HttpStatus.OK).body(this.s3BucketService.listBuckets());
    }

    @PostMapping("/bucket/create")
    public ResponseEntity<Boolean> create(@RequestParam("bucketName") String bucketName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.s3BucketService.createBucket(bucketName));
    }

    @DeleteMapping("/bucket/delete")
    public ResponseEntity<Boolean> delete(@RequestParam("bucketName") String bucketName) {

        return ResponseEntity.status(HttpStatus.OK).body(this.s3BucketService.deleteBucket(bucketName));
    }

    /**
     * EndPoints for operation Objects(pdf,images,xls)
     **/

    @PostMapping("/object/create")
    public void create(@RequestParam("bucketName") String bucketName, MultipartFile multipartFile) throws IOException {

        this.s3ObjectService.updloadFile(bucketName, multipartFile);
    }

    @PostMapping("/object/transfer")
    public void transfer(
            @RequestParam("bucketNameSourceOrigin") String bucketNameSourceOrigin,
            @RequestParam("fileOrigin") String fileOrigin,
            @RequestParam("bucketNameSourceDestiny") String bucketNameSourceDestiny,
            @RequestParam("fileDestiny") String fileDestiny
    ) {
        this.s3ObjectService.copyingRenamingAndMovingAnObject(bucketNameSourceOrigin, fileDestiny, bucketNameSourceDestiny, fileDestiny);
    }

    @DeleteMapping("/objects/delete")
    public ResponseEntity<Boolean> deleteMultiples(@RequestParam("bucketName") String bucketName, @RequestBody String keys) {

        final HashMap<String, List<String>> map = new Gson().fromJson(keys, HashMap.class);

        List<String> files = map.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        String[] values = new String[files.size()];
        files.toArray(values);
        this.s3ObjectService.deletingObjects(bucketName, values);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @DeleteMapping("/object/delete")
    public ResponseEntity<Boolean> delete(@RequestParam("bucketName") String bucketName, @RequestParam("keyNameFile") String keyNameFile) {
        this.s3ObjectService.deletingObject(bucketName, keyNameFile);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping("/object/listing")
    public ResponseEntity<List<S3ObjectSummary>> listFiles(@RequestParam("bucketName") String bucketName) {
        return ResponseEntity.status(HttpStatus.OK).body(this.s3ObjectService.listingFile(bucketName));
    }

}
