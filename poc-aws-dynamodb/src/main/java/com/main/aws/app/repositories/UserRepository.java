package com.main.aws.app.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.main.aws.app.entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final DynamoDBMapper dynamoDBMapper;

    private final AmazonDynamoDB amazonDynamoDB;


    public UserRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB amazonDynamoDB) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void save(User user) {
        this.dynamoDBMapper.save(user);
    }


    public User findById(String id,String login) {
        return this.dynamoDBMapper.load(User.class, id,login);
    }

    public PaginatedQueryList<User> findByIdUser(String id) {

        Map<String,String> expressionAttributesNames = new HashMap<>();

        expressionAttributesNames.put("id","id");

        Map<String,AttributeValue> expressionAttributeValues = new HashMap<>();

        expressionAttributeValues.put(":id",new AttributeValue().withS(id));


        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withKeyConditionExpression("#id = :id")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(expressionAttributeValues);

        return dynamoDBMapper.query(User.class,queryExpression);

    }

    public String delete(String id) {
        User user = this.dynamoDBMapper.load(User.class, id);
        this.dynamoDBMapper.delete(user);
        return id;
    }

//    public String update(User user, String id) {
//        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression()
//
//                .withExpectedEntry("id", new ExpectedAttributeValue(new AttributeValue().withS(id)));
//        dynamoDBMapper.save(user, dynamoDBSaveExpression);
//        return id;
//    }

    public String update(User user,String id) {


        this.dynamoDBMapper.save(user,buildDynamoDBSaveExpression(user,id));

        return user.getId();
    }

    public void find() {

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>();
        queryExpression.withHashKeyValues(new User("asda6as5das4d"));

        User user = this.dynamoDBMapper.load(User.class, queryExpression);

        System.out.println(user.toString());
    }

    public List<Map<String, AttributeValue>> findAll() {

        ScanRequest scanRequest = new ScanRequest()
                .withTableName("user");

        ScanResult result = this.amazonDynamoDB.scan(scanRequest);
        for (Map<String, AttributeValue> item : result.getItems()) {
            System.out.println(item.get("id").getS());
        }

        return result.getItems();
    }
    public DynamoDBSaveExpression buildDynamoDBSaveExpression(User user,String id) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expected = new HashMap<>();
        expected.put("id", new ExpectedAttributeValue(new AttributeValue(id))
                .withComparisonOperator(ComparisonOperator.EQ));
        saveExpression.setExpected(expected);
        return saveExpression;
    }
}
