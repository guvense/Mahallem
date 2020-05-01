package com.mahallem.resource;

import com.mahallem.constants.Sex;
import com.mahallem.elasticsearch.model.RegisteredUser;

public class ElasticSearchResource {

    public static RegisteredUser registeredUser = RegisteredUser.builder()
                                                                .age(1)
                                                                .sex(Sex.MALE)
                                                                .build();
}
