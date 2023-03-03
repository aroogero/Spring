package kz.bootcamp4.springboot.bootcamp4.springboot.repository;

import jakarta.transaction.Transactional;
import kz.bootcamp4.springboot.bootcamp4.springboot.model.Application_L6t1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Application_L6t1, Long> {
}
