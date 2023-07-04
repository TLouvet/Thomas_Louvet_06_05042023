package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :username OR u.username = :username")
    User findByEmailOrUsername(@Param("username")String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS BIT FROM User u WHERE u.email = :email OR u.username = :username")
    Integer existsByEmailOrUsername(@Param("email")String email, @Param("username") String username);
}
