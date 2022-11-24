package PlaneTicketProject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Customer {
    private String customerName;
    private String customerLastName;
    private Integer age;
    private String email;
    public Map<Integer, String> customerTickets = new HashMap<>();
    Set<Map.Entry<Integer, String>> CustomerTicketsSet = customerTickets.entrySet();

    public Customer(String name, String lastName, Integer age, String email) {
        this.customerName = name;
        this.customerLastName = lastName;
        this.age = age;
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
