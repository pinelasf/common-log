package com.example.commonlog;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class CommonLogApplication {

    private static final Logger log = LoggerFactory.getLogger(CommonLogApplication.class);

    public static void main(String[] args) {
        //SpringApplication.run(CommonLogApplication.class, args);
        log.error(IdType.CASE_ID, "1", "test {}", 10 );
    }
}
