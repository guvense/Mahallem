package com.mahallem.Service.Impl;

import com.mahallem.DTO.Request.UserDetailRequest;
import com.mahallem.DTO.Response.UserResponse;
import com.mahallem.Entity.User;
import com.mahallem.Exception.UserNotFoundException;
import com.mahallem.Repository.AuthRepository;
import com.mahallem.Service.IUserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final AuthRepository  authRepository;

    @NotNull
    private final ModelMapper modelMapper;

    @Override
    public UserResponse setUserDetailInformation(String userId, UserDetailRequest userDetailRequest) {

        Optional<User> userOp = authRepository.findBy_id(new ObjectId(userId));
        User user = userOp.orElseThrow(UserNotFoundException::new);

        user.setCellPhone(userDetailRequest.getCellPhone());
        user.setEmail(userDetailRequest.getEmail());
        user.setSex(userDetailRequest.getSex());
        User save = authRepository.save(user);

        return modelMapper.map(save,UserResponse.class);

    }
}
