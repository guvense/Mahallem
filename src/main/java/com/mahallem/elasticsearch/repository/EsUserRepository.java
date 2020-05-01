package com.mahallem.elasticsearch.repository;

import com.mahallem.elasticsearch.model.RegisteredUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface EsUserRepository extends ElasticsearchRepository<RegisteredUser, String> {

}
