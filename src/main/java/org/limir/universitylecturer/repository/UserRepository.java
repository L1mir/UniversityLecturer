package org.limir.universitylecturer.repository;

import org.limir.universitylecturer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> deleteUserByName(String name);
    @Query("SELECT DISTINCT u FROM User u " +
            "WHERE (:name IS NULL OR u.name = :name) " +
            "AND (:email IS NULL OR u.email = :email) " +
            "AND (:roleId IS NULL OR u.role.id = :roleId)")
    List<User> findUsersByNameEmailRole(
            @Param("name") String name,
            @Param("email") String email,
            @Param("roleId") Long roleId
    );
}
