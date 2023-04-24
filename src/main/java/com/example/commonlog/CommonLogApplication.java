package com.example.commonlog;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.commonlog.CaseRef.CASE;

//@SpringBootApplication
public class CommonLogApplication {

    private static final Logger log = LoggerFactory.getLogger(CommonLogApplication.class);

    public static void main(String[] args) {
        //SpringApplication.run(CommonLogApplication.class, args);
        log.info(CASE(2), "test {}", 10 );
    }
}
