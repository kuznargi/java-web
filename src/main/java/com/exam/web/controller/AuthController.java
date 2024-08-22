package com.exam.web.controller;


import com.exam.web.dto.LoginDto;
import com.exam.web.dto.RegistrationDto;
import com.exam.web.model.UserEntity;
import com.exam.web.repository.RoleRepository;
import com.exam.web.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder, RoleRepository repository) {
        this.userService = userService;
        this.repository=repository;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;
    }

    @GetMapping("/")
    public String homePage(){ return "home";}
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user= new RegistrationDto();
        model.addAttribute("user",user);
        return "register";
    }

//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute("user") LoginDto loginDto,
//                        BindingResult result, Model model) {
//        System.out.println(0);
//        if (result.hasErrors()) {
//            return "login";
//        }
//
//        try {
//            System.out.println(1);
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginDto.getName(), loginDto.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            System.out.println(2);
//            return "redirect:/diaries"; // Redirect to the target page after successful login
//        } catch (AuthenticationException e) {
//            model.addAttribute("error", "Invalid username or password");
//            return "login"; // Redirect back to login page with error message
//        }
//    }

    @PostMapping ("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user,
                           BindingResult result, Model model) {
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            //return "redirect:/diaries?success";
            return "redirect:/register?fail";

        }
        UserEntity existingUserUsername = userService.findByName(user.getName());
        if(existingUserUsername != null && existingUserUsername.getName() != null && !existingUserUsername.getName().isEmpty()){
            //return "redirect:/diaries?success";
           return "redirect:/register?fail";

        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/diaries?success";
    }

}
