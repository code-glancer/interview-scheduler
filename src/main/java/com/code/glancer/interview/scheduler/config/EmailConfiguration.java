package com.code.glancer.interview.scheduler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfiguration {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String userName;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.smtp.connectiontimeout}")
    private int connectiontimeout;

    @Value("${spring.mail.smtp.timeout}")
    private int timeout;

    @Value("${spring.mail.smtp.writetimeout}")
    private int writetimeout;

    @Value("${spring.mail.smtp.auth}")
    private boolean auth;

    @Value("${spring.mail.smtp.starttls.enable}")
    private boolean starttlsEnable;

    @Value("${spring.mail.debug}")
    private boolean debug;
    @Value("${spring.mail.smtp.ssl.enable}")
    private boolean sslEnable;

    @Bean
    JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(this.userName);
        javaMailSender.setPassword(this.password);
        javaMailSender.setPort(this.port);
        javaMailSender.setHost(this.host);
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttlsEnable);
        props.put("mail.debug", debug);
        props.put("mail.smtp.ssl.enable", sslEnable);
        return javaMailSender;
    }

}
