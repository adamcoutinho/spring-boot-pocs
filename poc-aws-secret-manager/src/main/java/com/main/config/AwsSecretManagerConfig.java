package com.main.config;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UncheckedIOException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSecretManagerConfig {

    @Value("${amazon.aws-secret-manager.access-key}")
    private String AWS_ACCESS_KEY;
    @Value("${amazon.aws-secret-manager.secret-key}")
    private String AWS_SECRET_KEY;
    @Value("${amazon.aws-secret-manager.aws-secret}")
    private String AWS_SECRET;
    @Value("${amazon.aws-secret-manager.endpoint}")
    private String URL_ENDPOINT;
    @Value("${amazon.region}")
    private String REGION;

    @Autowired
    private ObjectMapper mapper;

    @Bean
    public UserAdminTest loadAwsSecretManager() {
        try {
            System.out.println(this.AWS_ACCESS_KEY);
            System.out.println(this.AWS_SECRET_KEY);
            System.out.println(this.AWS_SECRET);
            System.out.println(this.REGION);
            System.out.println(this.URL_ENDPOINT);
            return mapper.readValue(extractedSecretvalue(), UserAdminTest.class);
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String extractedSecretvalue() {
        var secretName = Objects.nonNull(System.getenv("AWS_SECRET_NAME")) ? System.getenv("AWS_SECRET_NAME") : this.AWS_SECRET_KEY;
        AWSSecretsManagerClientBuilder clientBuilder = AWSSecretsManagerClientBuilder.standard();
        clientBuilder.setEndpointConfiguration(getEndpointConfiguration());
        clientBuilder.setCredentials(new EnvironmentVariableCredentialsProvider());
        return clientBuilder.build().getSecretValue(new GetSecretValueRequest().withSecretId(secretName)).getSecretString();
    }

    private AwsClientBuilder.EndpointConfiguration getEndpointConfiguration() {
        var awsUrl = System.getenv("AWS_URL");
        var awsRegion = System.getenv("AWS_REGION");
        AwsClientBuilder.EndpointConfiguration config = new AwsClientBuilder.EndpointConfiguration(awsUrl, awsRegion);
        return config;
    }


}
