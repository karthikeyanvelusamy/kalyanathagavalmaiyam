package com.kalyanathagavalthalam.configuration;

import com.kalyanathagavalthalam.repo.IDFetcher;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClientFactory;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDatabaseUtils;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AppConfig {


  @Bean
  public MongoClient mongoClient() {
    ConnectionString connectionString =
      new ConnectionString("mongodb+srv://admin:admin@cluster0.d93ii.mongodb.net/kalyana_thagaval_thalam?retryWrites=true&w=majority");

    MongoClientSettings settings = MongoClientSettings.builder()
      .applyConnectionString(connectionString)
      .build();
    MongoClient mongoClient = MongoClients.create(settings);
    return  mongoClient;

  }

  @Bean
  public MongoOperations mongoOperations(MongoClient client){
    return new MongoTemplate(client,"kalyana_thagaval_thalam");
  }



}
