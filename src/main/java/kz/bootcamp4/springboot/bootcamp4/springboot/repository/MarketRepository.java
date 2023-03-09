package kz.bootcamp4.springboot.bootcamp4.springboot.repository;

import jakarta.transaction.Transactional;
import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MarketRepository extends JpaRepository<ShopMarket, Long> {

}
