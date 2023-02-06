package API_TEST_CLASSES.PaymentRequest;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class PaymentInformation {
    private String currency;
    private double Amount;

    public PaymentInformation(String currency, double amount) {
        this.currency = currency;
        Amount = amount;
    }
}
