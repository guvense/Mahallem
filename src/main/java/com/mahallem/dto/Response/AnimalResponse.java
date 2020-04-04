package com.mahallem.dto.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mahallem.constants.AnimalSex;
import com.mahallem.constants.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {

    private AnimalType type;

    private Integer age;

    private AnimalSex sex;

    private String id;

    @JsonIgnore
    private Date birthDate;

    public  void calculateAge() {
        LocalDate now = LocalDate.now();
        LocalDate localDateBirthDate = convertDateToLocalDate(getBirthDate());
        Period diff = Period.between(localDateBirthDate, now);
        setAge(diff.getYears());
    }

    private  LocalDate convertDateToLocalDate(Date birthDate) {
        Instant instant = birthDate.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        return zdt.toLocalDate();
    }

}
