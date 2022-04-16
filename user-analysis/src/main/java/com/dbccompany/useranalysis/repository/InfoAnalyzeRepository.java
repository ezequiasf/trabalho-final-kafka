package com.dbccompany.useranalysis.repository;

import com.dbccompany.useranalysis.entity.InfoAnalyzeEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoAnalyzeRepository extends MongoRepository<InfoAnalyzeEntity, String> {

    @Aggregation("{ $group: { _id : $productName, productName: { $first: $productName },  soldNumber : { $sum : $soldNumber } } }")
    List<InfoAnalyzeEntity> groupByProductNameAndSoldNumber();

    @Aggregation("{ $group: { _id : $soldDay, soldDay: { $first: $soldDay },  soldNumber : { $count : {} } } }")
    List<InfoAnalyzeEntity> groupBySoldDay();

    @Aggregation("{ $group: { _id : $productName, productName: { $first: $productName },  totalAmount : { $sum : $totalAmount } } }")
    List<InfoAnalyzeEntity> groupByTotalAmount();
}
