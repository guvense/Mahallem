package com.mahallem.service.Impl;

import com.mahallem.constants.PermissionStatus;
import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Permission;
import com.mahallem.entity.User;
import com.mahallem.exception.PermissionProgressUpdateException;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.mapper.service.UserMapper;
import com.mahallem.repository.PermissionRepository;
import com.mahallem.repository.UserRepository;
import com.mahallem.service.BlobStorageService;
import com.mahallem.service.UserService;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BlobStorageService blobStorageService;
    private final PermissionRepository permissionRepository;

    @NotNull
    final MongoTemplate mongoTemplate;

    @Override
    public UserResponse setUserDetailInformation(String userId, UserDetailRequest userDetailRequest) {

        userRepository.updateUserDetailInfo(userId, userDetailRequest);
        User user = userRepository.findById(new ObjectId(userId))
                .orElseThrow(UserNotFoundException::new);
        return UserMapper.map.userToUserResponse(user);

    }

    @Override
    public UserResponse userInfo(String userId) {

        User user = userRepository.getUserInfo(userId)
                .orElseThrow(UserNotFoundException::new);
        return UserMapper.map.userToUserResponse(user);
    }

    @Override
    public UserResponse getUser(String userId) {
        User user = userRepository.getUserInfo(userId)
                .orElseThrow(UserNotFoundException::new);

        return UserMapper.map.userToUserResponse(user);
    }

    @Override
    public void addHouseIdToUser(String userId, ObjectId houseId) {

        userRepository.addHouseIdToUser(userId, houseId);
    }

    @Override
    public String getHouseId(String userId) {

        return getUser(userId).getHouse().getId();
    }


    public Long countAllUsers() {
        return userRepository.countAllUsers();
    }

    @Override
    public List<UserResponse> getHomemates(String userId) {
        User user = userRepository.getUserInfo(userId)
                .orElseThrow(UserNotFoundException::new);
        List<User> homemates = userRepository.findHomematesByHouseId(new ObjectId(userId), user.getHouseId());
        return UserMapper.map.userListToUserResponseList(homemates);
    }

    @Override
    public ObjectId getUserIdFromUsername(String username) {
        User user = userRepository.getUserInfoFromUsername(username).orElseThrow(UserNotFoundException::new);
        return user.getId();
    }

    @Override
    public void setApproveUserPermission(Permission permission) {
        Boolean success = permissionRepository.setPermissionStatus(permission, PermissionStatus.APPROVE);
        if (!success)
            throw new PermissionProgressUpdateException();
    }

    @Override
    public void setRejectUserPermission(Permission permission) {
        Boolean success = permissionRepository.setPermissionStatus(permission, PermissionStatus.REJECT);
        if (!success)
            throw new PermissionProgressUpdateException();
    }

    @Override
    public String uploadProfilePicture(MultipartFile multipartFile, String userId) throws IOException {
        String uri = blobStorageService.uploadPicture(multipartFile).toURL().toString();
        userRepository.uploadProfilePicture(uri, new ObjectId(userId));
        return uri;
    }
}

