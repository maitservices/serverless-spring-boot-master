package com.maomkt.product.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
@EnableDynamoDBRepositories(basePackages = "com.maomkt.product.repository")
public class DynamoDBConfig {

    @Value("${spring.profiles.active}")
    private String environment;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
        return amazonDynamoDB;
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
        builder.setTableNameResolver(new DynamoDbTableNameResolver());
        return builder.build();
    }

    private class DynamoDbTableNameResolver implements DynamoDBMapperConfig.TableNameResolver {

        @Override
        public String getTableName(Class<?> clazz, DynamoDBMapperConfig config) {
            return DynamoDBConfig.this.environment + "-" + clazz.getAnnotation(DynamoDBTable.class).tableName();
        }
    }
}
