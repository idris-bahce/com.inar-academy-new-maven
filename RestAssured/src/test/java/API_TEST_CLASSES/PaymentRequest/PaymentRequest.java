package API_TEST_CLASSES.PaymentRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    private String paymentDateStamp;
    private Debtor debtor;
    private Creditor creditor;
    private PaymentInformation paymentInformation;
}
