package com.kalyanathagavalthalam.repo;


import com.fasterxml.jackson.core.type.TypeReference;
import com.kalyanathagavalthalam.bean.DBIDHOLDER;
import com.kalyanathagavalthalam.bean.Profile;
import com.kalyanathagavalthalam.bean.ProfileCard;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.changestream.UpdateDescription;
import org.bson.BsonDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.Map;
import java.util.Optional;

@Repository
public abstract class IDFetcher implements MongoOperations {

  public int fetch() {
    DBIDHOLDER  currentID =  findOne(new Query(), DBIDHOLDER.class);;
    if(currentID == null) {
      currentID = new DBIDHOLDER();
    }
    int id = currentID.getTodayId();
    currentID.setTodayId(id+1);
    findAndReplace(new Query(), currentID);
    return id;
  }

  public void reset() {
    findAndReplace(new Query(), new DBIDHOLDER());
  }

}
