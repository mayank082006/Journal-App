 package com.project.journalApp.repository;

import com.project.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


 public class UserRepositoryImpl {

@Autowired
private MongoTemplate mongoTemplate;
  public List<UserEntity>getUserSA(){

    Query query=new Query();
    query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"));
    query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
    List<UserEntity>userEntities=mongoTemplate.find(query,UserEntity.class);

    return userEntities;

  }

}
