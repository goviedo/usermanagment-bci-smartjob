package cl.goviedo.usermanagment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.goviedo.usermanagment.entities.PhoneEntity;

@Repository
public interface PhoneEntityRepository extends JpaRepository<PhoneEntity, Long> {

}
