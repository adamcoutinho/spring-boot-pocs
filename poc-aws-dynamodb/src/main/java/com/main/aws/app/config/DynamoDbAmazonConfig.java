package com.main.aws.app.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbAmazonConfig {



    @Value("${amazon.dynamodb.access-key}")    private String AWS_ACCESS_KEY;
    @Value("${amazon.dynamodb.secret-key}")    private String AWS_SECRET_KEY;
    @Value("${amazon.dynamodb.endpoint}")      private String URL_AMAZON_S3;
    @Value("${amazon.region}")                 private String REGION;



    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    private String toCredentials() {
        return "{ \naccess-key:"+this.AWS_ACCESS_KEY+",\nsecret-key:"+this.AWS_SECRET_KEY+",\nurl-amazon:"+this.URL_AMAZON_S3+",\nregion:"+this.REGION+"\n}";
    }

    private EndpointConfiguration getEndpointConfiguration() {

        return new EndpointConfiguration(this.URL_AMAZON_S3, this.REGION);
    }
    @Bean
    public AmazonDynamoDB amazonS3Configure() {
        System.out.println(toCredentials());
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(getEndpointConfiguration())
                .withCredentials(new AWSStaticCredentialsProvider(getBasicAWSCredentials()))
                .build();
    }
    private AWSStaticCredentialsProvider getCredentialsProvider() {

        return new AWSStaticCredentialsProvider(getBasicAWSCredentials());
    }

    private AWSCredentials getBasicAWSCredentials() {

        return new BasicAWSCredentials(this.AWS_ACCESS_KEY, this.AWS_SECRET_KEY);
    }
}
