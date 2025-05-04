package com.spring.restaurant_review.services.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.spring.restaurant_review.domain.GeoLocation;
import com.spring.restaurant_review.domain.entities.Address;
import com.spring.restaurant_review.services.GeoLocationService;

@Service
public class RandomLondonGeLocationService implements GeoLocationService {

    private static final float MIN_LATITUDE = 51.28f;
    private static final float Max_LATITUDE = 51.686f;
    private static final float MIN_LONGITUDE = -0.489f;
    private static final float MAX_LONGITUDE = 0.236f;

    @Override
    public GeoLocation getLocate(Address Address) {
        Random random = new Random();
        double latitude = MIN_LATITUDE + random.nextDouble() * (Max_LATITUDE - MIN_LATITUDE);
        double longitude = MIN_LONGITUDE + random.nextDouble() * (MAX_LONGITUDE - MIN_LONGITUDE);

        return GeoLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();

    }

}
