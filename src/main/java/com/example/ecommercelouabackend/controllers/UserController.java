package com.example.ecommercelouabackend.controllers;


import com.example.ecommercelouabackend.dto.*;
import com.example.ecommercelouabackend.entities.*;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.services.UserService;
import com.example.ecommercelouabackend.services.interfaces.EmailService;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import com.example.ecommercelouabackend.utils.UserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final ICurd<User> userICurd;
    private final UserService userService;
    private final EmailService emailService ;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) throws NotFoundEntityException {
        return ResponseEntity.ok(this.userICurd.add(user));
    }
    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(@RequestParam long idUser) throws NotFoundEntityException {
        return ResponseEntity.ok(this.userICurd.get(idUser));
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAll());
    }

    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        System.out.println(user);
        this.userService.update(user);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam long idUser) {
        this.userICurd.delete(idUser);
        return ResponseEntity.ok("user with id " + idUser + " is deleted");
    }
    @PostMapping("/checkaccountbyemail")
    public AccountResponse resetPasswordEmail(@RequestBody ResetPassword resetPassword) throws NotFoundEntityException {
            boolean result = userService.ifEmailExists(resetPassword.getEmail());
            AccountResponse accountResponse = new AccountResponse();
            if (result) {
                String resetCode = UserCode.getCode();
                String email = resetPassword.getEmail();
                Optional<User> user ;
                user = userService.getuserbyemail(email);
                Mail mail =new Mail(resetPassword.getEmail(), resetCode);
                User userObj = user.get();
                userObj.setReset_code(resetCode);
                userService.add(userObj);
                emailService.sendCodeByMail(mail);
                accountResponse.setResult(1);
            }else
            {
                accountResponse.setResult(0);
            }
            return accountResponse;
    }
    @PostMapping("/verifycodereset")
    public int verifyCodeReset(@RequestBody ResetCodeEmailRequest resetCodeEmailRequest) {
        String email = resetCodeEmailRequest.getEmail().replaceAll("^\"|\"$", "");
        Optional<User> user = userService.getuserbyemail(email);
        if (user.isPresent()) {
            User userObj = user.get();
            String code = userObj.getReset_code();
            if (resetCodeEmailRequest.getResetCode().equals(code)) {
                return 1;
            }
        }
        return 0;
    }
    @PostMapping("/resetpassword")
    public int resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws NotFoundEntityException {
        String email = resetPasswordRequest.getEmail().replaceAll("^\"|\"$", "");
        Optional<User> user = userService.getuserbyemail(email);
        if (user.isPresent()) {
            User userObj = user.get();
            System.out.println(resetPasswordRequest.getNewPassword());
            userObj.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            userService.add(userObj);
            return 1;
        }
        return 0;
    }
    @PostMapping("/accountActivation")
    public int accountActivation(@RequestBody AccountActivationRequest request) throws NotFoundEntityException
    {
        String code = request.getCode();
        String email = request.getEmail();
        Optional<User> user = userService.getuserbyemail(email);
        if (user.isPresent()) {
            User userObj = user.get();
                if (userObj.getActive_code().equals(code)) {
                    userObj.setIsActive(true);
                    userService.add(userObj);
                    return 1;
                }
        }
return 0;
    }
    @PostMapping("/resendcodeActivation")
    public int resendcodeActivation(@RequestBody ResendActivationCode request) throws NotFoundEntityException
    {
        Optional<User> user = userService.getuserbyemail(request.getEmail());
        if (user.isPresent()) {
            User userObj = user.get();
            String code = userObj.getActive_code();
            Mail mail =new Mail(request.getEmail(), code);
            emailService.ResendActivationCodeByMail(mail);
            return 1;
        }
        return 0;
    }

    @PostMapping("/updateprofilepicture")
    public int updatePhoto(@RequestBody UpdatePhotoRequest request) throws NotFoundEntityException {
        System.out.println(request);
        Optional<User> user = userService.getuserbyemail(request.getEmail());
        if (user.isPresent()) {
            User userObj = user.get();
            userObj.setImage(request.getFileName());
            userService.add(userObj);
            return 1;
        }
        return 0;
    }
    @PostMapping("/updatepassword")
    public int updatePassword(@RequestBody UpdatePasswordRequest request) {
        String email = request.getEmail().replaceAll("^\"|\"$", "");
        Optional<User> user = userService.getuserbyemail(email);

        if (user.isPresent()) {
            User userObj = user.get();
            String hashedOldPassword = passwordEncoder.encode(request.getOldpassword());
            if (passwordEncoder.matches(request.getOldpassword(), userObj.getPassword())) {
                userObj.setPassword(passwordEncoder.encode(request.getNewpassword()));
                userService.update(userObj);
                return 1;
            }
        }
        return 0;
    }
    @PatchMapping("changeRole")
    public ResponseEntity<User> changeRoleToSELLER(@RequestParam String idUser){
     return  ResponseEntity.ok(this.userService.changeRoleToSeller(idUser));
    }
}
