package kz.bootcamp4.springboot.bootcamp4.springboot.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter   //Автоматом создает геттеры и сеттеры
@Setter
@AllArgsConstructor   //конструктор со всеми параметрами
@NoArgsConstructor   //пустой конструктор по умолчанию

public class Item {
    private Long id;
    private String name;
    private int amount;
    private double price;

}
