package com.mahallem.eventSender;

import com.mahallem.dto.Response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfo implements Serializable {

    private static final long serialVersionUID = 7040254474373784730L;

    public String id;

    public Date onlineAt;

    public boolean isAdmin;

    public UserResponse userResponse;
}
