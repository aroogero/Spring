package kz.bootcamp4.springboot.bootcamp4.springboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity //сущность. сразу поймет что это база данных
@Table(name = "t_items") //как в базе должна называться таблица - говорим в sql-е ты так должна будешь называться
@Getter
@Setter
public class ShopItem {  //а в нашем классе он будет называться ShopItem - а у меня так
    @Id  //одной аннотацией говорим что ты будешь primary_key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(name = "id") //как будто создаешь колонку, "id" - это в базе как будет называться колонка
    private Long id;  //Long - bigint()

    //@Column(name = "item_name") //в базе данных колонка item_name (varchar(255)) -> а у нас name
    //private String name; //

    @Column(name = "item_name", length = 200)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "amount")
    private int amount; //int -> integer

    @Column(name = "price")
    private double price;

    @Column(name = "item_link")
    private String link;

    @ManyToOne  //мы говорим внутри тебя будет объект manufacturer
    private Manufacturer manufacturer; //обязательно должен быть сущностью тэйблом

    @ManyToMany
    private List<ShopMarket> markets; //еще 1 плюс Листа он сохраняет порядок а Сет не соблюдает порядок по названию
}
