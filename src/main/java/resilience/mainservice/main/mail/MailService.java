package resilience.mainservice.main.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import resilience.mainservice.main.payment.PaymentResponse;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class); // 로깅 추가 권장
    private final MailServiceClient mailServiceClient;

    public MailService(MailServiceClient mailServiceClient) {
        this.mailServiceClient = mailServiceClient;
    }

    public String sendMail(String email, PaymentResponse paymentResponse) {
        logger.info("메일 발송 요청: {}, {}", email, paymentResponse); // 요청 로그

        EmailRequest emailRequest = new EmailRequest(email, paymentResponse.getMessage());

        return mailServiceClient.sendMail(emailRequest);
    }
}
