package com.main.aws.app;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.main.aws.app.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PocAwsDynamodbApplication implements CommandLineRunner {

    private final AmazonDynamoDB amazonDynamoDB;

    private final  DynamoDBMapper mapper;

    private boolean tableWasCreatedForTest;

    public PocAwsDynamodbApplication(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper mapper) {
        this.amazonDynamoDB = amazonDynamoDB;
        this.mapper = mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(PocAwsDynamodbApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        CreateTableRequest ctr = mapper.generateCreateTableRequest(User.class)
                .withProvisionedThroughput(new ProvisionedThroughput(10L, 10L));
        tableWasCreatedForTest = TableUtils.createTableIfNotExists(amazonDynamoDB, ctr);

        TableUtils.waitUntilActive(amazonDynamoDB, ctr.getTableName());

    }
}
