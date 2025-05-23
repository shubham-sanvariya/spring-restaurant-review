package com.spring.restaurant_review.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.spring.restaurant_review.domain.entities.Restaurant;

public interface RestaurantRepository extends ElasticsearchRepository<Restaurant,String >{
    
}
