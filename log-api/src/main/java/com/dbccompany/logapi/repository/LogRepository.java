package com.dbccompany.logapi.repository;

import com.dbccompany.logapi.entity.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<LogEntity, String> {
}
