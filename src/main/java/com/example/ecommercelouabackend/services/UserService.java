package com.example.ecommercelouabackend.services;

import com.example.ecommercelouabackend.entities.Role;
import com.example.ecommercelouabackend.entities.User;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.UserRepo;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements ICurd<User> {

    private final UserRepo userRepo;

    @Override
    public User add(User user) throws NotFoundEntityException {

        if (user != null)
            return this.userRepo.save(user);
        throw new NotFoundEntityException("no User found , user is null");
    }

    @Override
    public void delete(long id) {
        this.userRepo.findById(id)
                .ifPresent(this.userRepo::delete);
    }

    @Override
    public void update(User user) {
        if (user != null && user.getId() != null) {
            userRepo.save(user);
        }
    }


    @Override
    public User get(long id) throws NotFoundEntityException {
        return this.userRepo.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("could not found user with id " + id));
    }

    @Override
    public List<User> getAll() {
        return this.userRepo.findAll();
    }

    public boolean ifEmailExists(String email) {return userRepo.existsByUsername(email);}
    public Optional<User> getuserbyemail(String email) {return userRepo.findByUsername(email);}

    public User changeRoleToSeller(String idUser) {
        User user = this.userRepo.findByUsername(idUser)
                .orElseThrow(() -> new RuntimeException("could not find user with id: " + idUser));
        user.setRole(Role.SELLER);
        return this.userRepo.save(user);

    }

    public List<User> getVendeurlist() {
        return this.userRepo.findAllByRole(Role.SELLER);
    }
}
