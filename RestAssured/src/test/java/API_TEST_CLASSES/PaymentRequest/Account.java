package API_TEST_CLASSES.PaymentRequest;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class Account {
    private String iban;
    private String bban;

    public Account(String iban, String bban) {
        this.iban = iban;
        this.bban = bban;
    }
}
