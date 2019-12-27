package com.mahallem.entity;

import com.mahallem.constants.HousePropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseProperty extends BaseEntity {

    private HousePropertyType propertyType;

    private ObjectId _houseId;
}
