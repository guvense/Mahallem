package com.mahallem.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class House extends BaseEntity{

    private String name;

    private long grade;

    private GeoLocation geoLocation;

    private String houseStatus;

    private List<ObjectId> _animalIds;
}
