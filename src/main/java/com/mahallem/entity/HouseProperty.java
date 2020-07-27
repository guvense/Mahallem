package com.mahallem.entity;

import com.mahallem.constants.HousePropertyType;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseProperty extends BaseEntity {

    @Field("property_type")
    private HousePropertyType propertyType;

    private ObjectId _houseId;
}
