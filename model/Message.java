package model;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/12 10:59
 */
public class Message {
    private int id;
    private Object value;
    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {return id;}
    public Object getValue() {return value;}

    public void setId(int id) {this.id = id;}
    public void setValue(Object value) {this.value = value;}
}
