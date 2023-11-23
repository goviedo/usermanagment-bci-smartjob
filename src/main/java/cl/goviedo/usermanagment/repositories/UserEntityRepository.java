package cl.goviedo.usermanagment.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.goviedo.usermanagment.entities.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID>{

	UserEntity findUserByEmail(String email);
    
}
