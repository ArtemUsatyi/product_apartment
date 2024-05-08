package com.example.product_apartment.email_sendler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailConfig {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.pass}")
    private String password;

    @Value("${mail.smtp.user}")
    private String username;

    @Value("${mail.smtp.port}")
    private Integer port;

    @Value("${mail.smtp.protocol}")
    private String protocol;

    @Value("${mail.smtp.debug}")
    private String debug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host);
        javaMailSender.setPassword(password);
        javaMailSender.setUsername(username);
        javaMailSender.setPort(port);

        Properties properties = javaMailSender.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", protocol);
        properties.setProperty("mail.debug", debug);

        return javaMailSender;
    }
}
