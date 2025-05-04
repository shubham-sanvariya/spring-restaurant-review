package com.spring.restaurant_review.services;

import com.spring.restaurant_review.domain.entities.Address;

import co.elastic.clients.elasticsearch._types.GeoLocation;

public interface GeoLocationService {
    GeoLocation getLocate(Address Address);
}
