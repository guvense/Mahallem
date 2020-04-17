package com.mahallem.elasticsearch.model;


import com.mahallem.constants.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "registered_user",type = "user")
public class RegisteredUser extends BaseModel {

    @Id
    private String id;

    private Integer age;

    @Override
    public String toString() {
        return "RegisteredUser {" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", userName='" + userName + '\'' +
                ", createdDate=" + getCreatedDate() +
                '}';
    }

    private Sex sex;

    private String userName;

}
