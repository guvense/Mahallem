package com.mahallem.repository;

import com.mahallem.entity.HouseProperty;

public interface PropertyRepository {

    HouseProperty save(HouseProperty houseProperty);

    boolean isPropertyExist(HouseProperty houseProperty);


}
