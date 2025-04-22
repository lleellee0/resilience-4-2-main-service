package resilience.mainservice.main.mail;

public class EmailRequest {
    private String email;
    private String emailBody; // 이메일 본문 필드 추가

    // 기본 생성자
    public EmailRequest() {
    }

    // 이메일 주소만 받는 생성자 (기존)
    public EmailRequest(String email) {
        this.email = email;
    }

    // 이메일 주소와 본문을 모두 받는 생성자 (추가)
    public EmailRequest(String email, String emailBody) {
        this.email = email;
        this.emailBody = emailBody;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // emailBody 필드의 Getter와 Setter (추가)
    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }
}