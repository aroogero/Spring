package kz.bootcamp4.springboot.bootcamp4.springboot.db;

import lombok.*;

@Getter   //Автоматом создает геттеры и сеттеры
@Setter
@AllArgsConstructor   //конструктор со всеми параметрами
@NoArgsConstructor   //пустой конструктор по умолчанию
@Builder //для создания нового объекта
public class Item {

    private Long id;
    private String name;
    private int amount;
    private double price;
    private String link;
}
