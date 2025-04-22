package resilience.mainservice.main.payment;

// JSON 직렬화/역직렬화를 위해 getter와 setter가 필요
public class PaymentRequest {
    private String orderId;
    private Double amount;
    private String email; // 결제자 이메일 주소 필드 추가

    // Getters & Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}