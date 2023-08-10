package be.telenet.iss.poc.postcardservice.client.stamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBalance {
    private String accountOwner;
    private int balance;

    public void reduceBalance() {
        this.balance--;
    }
}
