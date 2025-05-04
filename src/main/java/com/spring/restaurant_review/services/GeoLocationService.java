package com.spring.restaurant_review.services;

import com.spring.restaurant_review.domain.GeoLocation;
import com.spring.restaurant_review.domain.entities.Address;

public interface GeoLocationService {
    GeoLocation getLocate(Address Address);
}
