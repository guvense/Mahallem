package com.mahallem.dto.Request;

import com.mahallem.constants.HousePropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HousePropertyRequest {

    private HousePropertyType propertyType;
}
