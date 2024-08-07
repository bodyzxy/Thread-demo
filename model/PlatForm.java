package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/6 12:51
 */
@Data
public class PlatForm {

    private String name;

    public double calcPrice(String name){
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + name.charAt(0);
    }

    // 无参构造函数
    public PlatForm() {
    }

    // 有参构造函数，接受 String 类型参数
    public PlatForm(String name) {
        this.name = name;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
