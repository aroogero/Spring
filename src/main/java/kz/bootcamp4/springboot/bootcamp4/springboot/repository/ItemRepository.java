package kz.bootcamp4.springboot.bootcamp4.springboot.repository;

import jakarta.transaction.Transactional;
import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<ShopItem, Long> {
    List<ShopItem> findAllByNameContaining(String name);
    List<ShopItem> findAllByNameContainingAndPriceBetweenAndAmountBetweenOrderByPriceDesc(
            String name, double fromPrice, double toPrice, int fromAmount, int toAmount
    );

    List<ShopItem> findAllByNameContainingAndPriceBetweenAndAmountBetweenAndManufacturerIdOrderByPriceDesc(
            String name, double fromPrice, double toPrice, int fromAmount, int toAmount, Long manufacturerId
    );

    @Query(value = "" +
            "SELECT it FROM ShopItem it " +
            "WHERE it.name LIKE :nazvanie " +
            "AND it.price >= :otCena AND it.price <= :doCena " +
            "AND it.amount >= :otKolvo AND it.amount <= :doKolvo " +
            "ORDER BY it.price DESC")
    List<ShopItem> poisk(
            @Param("nazvanie") String name,
            @Param("otCena") double fromPrice,
            @Param("doCena") double toPrice,
            @Param("otKolvo") int fromAmount,
            @Param("doKolvo") int toAmount
    );

    @Query(value = "" +
            "SELECT it FROM ShopItem it " +
            "WHERE it.name LIKE :nazvanie " +
            "AND it.price BETWEEN :otCena AND :doCena " +
            "AND it.amount BETWEEN :otKolvo AND :doKolvo " +
            "AND it.manufacturer.id = :manufacturerId " +
            "ORDER BY it.price DESC")
    List<ShopItem> poiskWithManufacturer(
            @Param("nazvanie") String name,
            @Param("otCena") double fromPrice,
            @Param("doCena") double toPrice,
            @Param("otKolvo") int fromAmount,
            @Param("doKolvo") int toAmount,
            @Param("manufacturerId") Long manufacturerId
    );

    @Query(value = "SELECT SUM(it.price) FROM ShopItem it")
    double sumOfPrices();
}

