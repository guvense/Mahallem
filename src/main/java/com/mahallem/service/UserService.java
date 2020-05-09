package com.mahallem.service;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Permission;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {

    UserResponse setUserDetailInformation(String userId, UserDetailRequest userDetailRequest);

    UserResponse userInfo(String userId);

    UserResponse getUser(String userId);

    void addHouseIdToUser(String userId, ObjectId houseId);

    String getHouseId(String userId);

    Long countAllUsers();

    List<UserResponse> getHomemates(String userId);

    ObjectId getUserIdFromUsername(String username);

    void setApproveUserPermission(Permission permission);

    void setRejectUserPermission(Permission permission);

}
