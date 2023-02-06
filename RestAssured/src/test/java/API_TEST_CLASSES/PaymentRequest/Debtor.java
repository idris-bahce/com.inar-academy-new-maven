package API_TEST_CLASSES.PaymentRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Debtor {
    private Bank bank;
    private Account account;
    private String countryCode;
}
