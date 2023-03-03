package kz.bootcamp4.springboot.bootcamp4.springboot.repository;

import jakarta.transaction.Transactional;
import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional //работает так, что каждая транзакция не будет скрещиваться с другой транзакцией
public interface ItemRepository extends JpaRepository<ShopItem, Long> {
    List<ShopItem> findAllByNameContaining(String name); //по названию метода он делает поиск Select where like
    List<ShopItem> findAllByNameContainingAndPriceBetweenAndAmountBetweenOrderByPriceDesc(
            String name, double fromPrice, double toPrice, int fromAmount, int toAmount
    ); //очень крутая технология - максимальное абстрагирование. Спринг Дата сам за нас сделает
}
