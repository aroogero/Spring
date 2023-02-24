package kz.bootcamp4.springboot.bootcamp4.springboot.beans;

import kz.bootcamp4.springboot.bootcamp4.springboot.db.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBUtil {
    private Connection connection;

    public DBUtil(String url, String user, String password) {  //кастомный конструктор
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
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
