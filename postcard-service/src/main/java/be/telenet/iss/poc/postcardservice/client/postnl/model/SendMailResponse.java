package be.telenet.iss.poc.postcardservice.client.postnl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMailResponse {
    private int queuePosition;
}
