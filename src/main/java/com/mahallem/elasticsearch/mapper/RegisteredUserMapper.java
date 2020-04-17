package com.mahallem.elasticsearch.mapper;

import com.mahallem.elasticsearch.model.RegisteredUser;
import com.mahallem.entity.User;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ObjectIdMapper.class)
public interface RegisteredUserMapper {

    RegisteredUserMapper map = Mappers.getMapper(RegisteredUserMapper.class);

    RegisteredUser userToRegisteredUser(User user);
}
