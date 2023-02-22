package kz.bootcamp4.springboot.bootcamp4.springboot.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
@Getter
@Setter
public class FirstBean {
    private String name;
    private int value;

    public FirstBean() {
        this.name = "Iliyas";
        this.value = 7;
        System.out.println("I am creating new object of FirstBean");
    }
    public String getData() {
        return "The data is : " + this.name + " - " + this.value;
    }
}
