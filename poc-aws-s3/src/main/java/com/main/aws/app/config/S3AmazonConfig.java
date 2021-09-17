package com.main.aws.app.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3AmazonConfig {

    @Value("${amazon.s3.bucket-name}")   private String BUCKET;
    @Value("${amazon.s3.access-key}")    private String AWS_ACCESS_KEY;
    @Value("${amazon.s3.secret-key}")    private String AWS_SECRET_KEY;
    @Value("${amazon.s3.endpoint}")      private String URL_AMAZON_S3;
    @Value("${amazon.region}")           private String REGION;

    @Bean
    public AmazonS3 amazonS3Configure() {
        System.out.println(toCredentials());
        return AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(getEndpointConfiguration())
                .withCredentials(new AWSStaticCredentialsProvider(getBasicAWSCredentials()))
                .withPathStyleAccessEnabled(true)
                .build();
    }

    private String toCredentials() {
        return "{ \nbucket-name:"+this.BUCKET+",\naccess-key:"+this.AWS_ACCESS_KEY+",\nsecret-key:"+this.AWS_SECRET_KEY+",\nurl-amazon:"+this.URL_AMAZON_S3+",\nregion:"+this.REGION+"\n}";
    }


    private EndpointConfiguration getEndpointConfiguration() {

        return new EndpointConfiguration(this.URL_AMAZON_S3, this.REGION);
    }

    private AWSStaticCredentialsProvider getCredentialsProvider() {

        return new AWSStaticCredentialsProvider(getBasicAWSCredentials());
    }

    private AWSCredentials getBasicAWSCredentials() {

        return new BasicAWSCredentials(this.AWS_ACCESS_KEY, this.AWS_SECRET_KEY);
    }


}
