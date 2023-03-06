package kz.bootcamp4.springboot.bootcamp4.springboot.repository;

import jakarta.transaction.Transactional;
import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional //работает так, что каждая транзакция не будет скрещиваться с другой транзакцией
public interface ItemRepository extends JpaRepository<ShopItem, Long> {
    List<ShopItem> findAllByNameContaining(String name); //по названию метода он делает поиск Select where like
    List<ShopItem> findAllByNameContainingAndPriceBetweenAndAmountBetweenOrderByPriceDesc(
            String name, double fromPrice, double toPrice, int fromAmount, int toAmount
    ); //очень крутая технология - максимальное абстрагирование. Спринг Дата сам за нас сделает
//наверху - вот этот чувак делает то же, самое что и этот - внизу
    //у вверхнего имплементация быстрая. А у нижнего вручную вмешиваешься
    @Query(value = "" +    //гибрид SQL-a и ORM-a - пишем запрос средний между этими двумя - указываем сущность
            "SELECT it FROM ShopItem it " +
            "WHERE it.name LIKE :nazvanie " +
            "AND it.price BETWEEN :otCena AND :doCena " +
            "AND it.amount BETWEEN :otKolvo AND :doKolvo " +
            "ORDER BY it.price DESC")
    List<ShopItem> poisk(
            @Param("nazvanie") String name, //как RequestParam
            @Param("otCena") double fromPrice,
            @Param("doCena") double toPrice,
            @Param("otKolvo") int fromAmount,
            @Param("doKolvo") int toAmount
    );
}

