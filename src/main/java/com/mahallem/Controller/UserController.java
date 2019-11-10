package com.mahallem.Controller;

import com.mahallem.Exception.UserNotFoundException;
import com.mahallem.Service.Impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public void getGame(){


        throw new  UserNotFoundException();

    }
}
