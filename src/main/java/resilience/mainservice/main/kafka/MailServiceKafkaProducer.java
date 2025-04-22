package resilience.mainservice.main.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import resilience.mainservice.main.mail.EmailRequest;
import resilience.mainservice.main.mail.MailServiceClient;

@Service
public class MailServiceKafkaProducer implements MailServiceClient {

    private static final Logger log = LoggerFactory.getLogger(MailServiceKafkaProducer.class);
    // KafkaTemplate의 Value 타입을 EmailRequest로 변경
    private final KafkaTemplate<String, EmailRequest> kafkaTemplate;

    // 변경된 타입의 KafkaTemplate 주입
    public MailServiceKafkaProducer(KafkaTemplate<String, EmailRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String sendMail(EmailRequest emailRequest) {
        if (emailRequest == null || emailRequest.getEmail() == null || emailRequest.getEmail().isEmpty()) {
            log.error("EmailRequest 또는 이메일 주소가 null이거나 비어있습니다.");
            return "Failed to send email request: Email address is missing.";
        }

        String key = emailRequest.getEmail();   // EmailRequest의 email 필드를 Kafka 메시지 키로 사용
        EmailRequest value = emailRequest;      // EmailRequest 객체 자체를 Kafka 메시지 값으로 사용

        try {
            // 키(파티셔닝 기준)와 값(EmailRequest 객체)을 함께 전송
            kafkaTemplate.send(KafkaTopicConfig.EMAIL_SEND_REQUEST_TOPIC, key, value);

            log.info("Successfully sent EmailRequest to Kafka topic {} with key {}: {}",
                    KafkaTopicConfig.EMAIL_SEND_REQUEST_TOPIC, key, value);
            return "Email request for " + key + " sent to Kafka.";

        } catch (Exception e) {
            log.error("Failed to send EmailRequest to Kafka topic {} with key {}: {}",
                    KafkaTopicConfig.EMAIL_SEND_REQUEST_TOPIC, key, value, e);

            throw e;
        }
    }
}