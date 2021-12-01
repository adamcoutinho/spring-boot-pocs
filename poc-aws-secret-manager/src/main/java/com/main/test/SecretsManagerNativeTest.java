package com.main.test;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.amazonaws.services.secretsmanager.model.InvalidParameterException;
import com.amazonaws.services.secretsmanager.model.InvalidRequestException;
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class SecretsManagerNativeTest {

    public static void main(String[] args) throws IOException {
        // Call the program with maven as mvn clean compile exec:java -Dexec.mainClass=com.amazonaws.samples.KMSEncryptionSample -Dexec.args="arg1 arg2"
        try {
            System.out.println("Retreiving Secrets based on the secret id and region entered as command line arguements: \n");
//            SecretsManagerSample.getSecret(args[0], args[1]);
            SecretsManagerNativeTest.getSecret();
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon AWS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with AWS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    public static void getSecret() {
        String endpoint = System.getenv("AWS_URL");
        String region = System.getenv("AWS_REGION");
        String secretName = System.getenv("AWS_SECRET_NAME_COCKPIT");
//        String secretName  = "/cockpit/tests/GM4/gestaodeleads/Credentials";

        AwsClientBuilder.EndpointConfiguration config = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
        AWSSecretsManagerClientBuilder clientBuilder = AWSSecretsManagerClientBuilder.standard();
        clientBuilder.setEndpointConfiguration(config);
        clientBuilder.setCredentials(new EnvironmentVariableCredentialsProvider());
        AWSSecretsManager client = clientBuilder.build();
        String secret = null;
        ByteBuffer binarySecretData;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
        GetSecretValueResult getSecretValueResponse = null;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);

        } catch (ResourceNotFoundException e) {
            System.out.println("The requested secret " + secretName + " was not found");
        } catch (InvalidRequestException e) {
            System.out.println("The request was invalid due to: " + e.getMessage());
        } catch (InvalidParameterException e) {
            System.out.println("The request had invalid params: " + e.getMessage());
        }

        if (getSecretValueResponse == null) {
            return;
        }

        if (getSecretValueResponse.getSecretString() != null) {
            secret = getSecretValueResponse.getSecretString();
        } else {
            binarySecretData = getSecretValueResponse.getSecretBinary();
        }

//        System.out.println("Secret Name : " + secretName + "\t Secret Value : " + secret + "\n");
//        System.out.println(JsonUtils.getUserCredential(secret).getUserName());
//        System.out.println(JsonUtils.getUserCredential(secret).getPassword());
    }

}