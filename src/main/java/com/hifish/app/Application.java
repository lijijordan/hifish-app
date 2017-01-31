package com.hifish.app;

import com.hifish.app.mqtt.MQTTConfig;
import com.hifish.app.mqtt.MQTTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.StringUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.UUID;

/*
 * This is the main Spring Boot application class. It configures Spring Boot, JPA, Swagger
 */

/**
 * The type Application.
 */
@EnableAutoConfiguration  // Sprint Boot Auto Configuration
@ComponentScan(basePackages = "com.hifish.app")
@EnableJpaRepositories("com.hifish.app.dao.jpa")
// To segregate MongoDB and JPA repositories. Otherwise not needed.
@EnableSwagger2 // auto generation of API docs
public class Application extends SpringBootServletInitializer {

    private static final Class<Application> applicationClass = Application.class;
    private static final Logger log = LoggerFactory.getLogger(applicationClass);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        Environment env = context.getEnvironment();
        //================== MQTT ==================
        initMQTT(env);
        MQTTService.initMQTT(context);
    }

    /**
     * INIT MQTT
     *
     * @param env
     */
    private static void initMQTT(Environment env) {
        String uuid = UUID.randomUUID().toString();
        // Configuration mqtt
        MQTTConfig.setBroker(env.getProperty("mqtt.broker"));
        MQTTConfig.setClientId(uuid);
        MQTTConfig.setPassword(env.getProperty("mqtt.password"));
        MQTTConfig.setQos(Integer.valueOf(env.getProperty("mqtt.qos")));
        MQTTConfig.setUserName(env.getProperty("mqtt.userName"));
        MQTTConfig.setTestTopic(env.getProperty("mqtt.testTopic"));
        if (StringUtils.isEmpty(env.getProperty("mqtt.topic"))) {
            MQTTConfig.setTopic(uuid);
        } else {
            MQTTConfig.setTopic(env.getProperty("mqtt.topic"));
        }
        MQTTConfig.setDeviceWillTopic(env.getProperty("mqtt.deviceWillTopic"));
        MQTTConfig.setClientKey(env.getProperty("mqtt.clientKey"));
        MQTTConfig.setKeyStore(env.getProperty("mqtt.keyStore"));
        System.out.println("UserName:" + MQTTConfig.getUserName());
        System.out.println("Topic:" + MQTTConfig.getTopic());
        System.out.println("ClientID:" + MQTTConfig.getClientId());
        env.getProperty("mqtt.password");
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}
