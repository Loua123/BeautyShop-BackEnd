package com.example.ecommercelouabackend.repositories;


import com.example.ecommercelouabackend.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {

    @Query("""
                    select t from Token  t inner join User u on t.user.id=u.id
                    where u.id = :userID and (t.revoked = false or t.expired=false )
            """)
    List<Token> findAllValidTokenByUser(long userID);

    Optional<Token> findByToken(String token);
}