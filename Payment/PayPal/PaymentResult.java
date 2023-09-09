package PayPal;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Transaction;

public class PaymentResult {
    private PayerInfo payerInfo;
    private Transaction transaction;

    public PaymentResult() {
    }

    public PaymentResult(PayerInfo payerInfo, Transaction transaction) {
        this.payerInfo = payerInfo;
        this.transaction = transaction;
    }

    public PayerInfo getPayerInfo() {
        return payerInfo;
    }

    public void setPayerInfo(PayerInfo payerInfo) {
        this.payerInfo = payerInfo;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
