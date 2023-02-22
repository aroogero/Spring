package kz.bootcamp4.springboot.bootcamp4.springboot.beans;

import kz.bootcamp4.springboot.bootcamp4.springboot.db.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class DBConnector {

    @Value("${dataBase.url}")
    private String dataBaseUrl;

    @Value("${dataBase.user}")
    private String dataBaseUser;

    @Value("${dataBase.password}")
    private String dataBasePassword;
//Устанавливаем соединение с базой данных
    private Connection connection;

    public DBConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Этот метод не получился, так как сначала создается конструктор и потом подтягиваются значения с application properties
            //connection = DriverManager.getConnection(dataBaseUrl, dataBaseUser, dataBasePassword);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp4spring", "root", ""); //это временно
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Item> getItems() {
        //Создаем локальный ArrayList
        ArrayList<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM item ORDER BY price DESC");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getLong("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setAmount(resultSet.getInt("amount"));  //мы упаковали
                items.add(item);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}
