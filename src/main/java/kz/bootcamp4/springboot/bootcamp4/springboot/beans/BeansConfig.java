package kz.bootcamp4.springboot.bootcamp4.springboot.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //тоже Бин, автоматом запускается когда перезагружаем весь проект
public class BeansConfig {

    @Value("${dataBase.url}")
    private String dataBaseUrl;

    @Value("${dataBase.user}")
    private String dataBaseUser;

    @Value("${dataBase.password}")
    private String dataBasePassword;

    @Bean
    public DBUtil dbUtil() {  //обычный класс превращаем в Бин
        return new DBUtil(dataBaseUrl, dataBaseUser, dataBasePassword); //Этот подход отличается от Компонента тем, что конструктор по умолчанию не будет вызываться. Здесь будет вызываться то, что ты хочешь вызывался

    }
}
