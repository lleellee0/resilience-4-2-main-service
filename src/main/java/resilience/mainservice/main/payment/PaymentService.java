package resilience.mainservice.main.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import resilience.mainservice.main.mail.MailService;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentServiceClient paymentServiceClient;
    private final MailService mailService;

    public PaymentService(PaymentServiceClient paymentServiceClient, MailService mailService) {
        this.paymentServiceClient = paymentServiceClient;
        this.mailService = mailService;
    }

    public PaymentResponse processPaymentMvc(PaymentRequest request) {
        // 1. 결제 요청
        PaymentResponse paymentResponse = paymentServiceClient.processPaymentMvc(request);

        // 2. 결제 DB 갱신 (TODO)
        // 만약 DB 갱신도 별도의 MVC 방식으로 처리한다면 해당 로직 추가

        // 3. 메일 발송
        String mailResponse = mailService.sendMail(request.getEmail(), paymentResponse);
        logger.info("메일 발송 완료: {}", mailResponse);

        return paymentResponse;
    }
}
