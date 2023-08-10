package be.telenet.iss.poc.postcardservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailPostcardRequest {

    private String sender;
    private String recipient;
    private String cardText;
}
