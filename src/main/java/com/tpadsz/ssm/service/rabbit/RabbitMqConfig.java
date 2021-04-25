package com.tpadsz.ssm.service.rabbit;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by hongjian.chen on 2019/1/23.
 */
@Configuration
@ComponentScan
@PropertySource("classpath:common.properties")
public class RabbitMqConfig {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private MessageConsumer messageConsumer;

    private String message = "topic.demo.message";
    private String messages = "topic.messages";

    @Value("${rabbitmq.queue}")
    private String blt_queue;
    @Value("${rabbitmq.host}")
    private String host;
    @Value("${rabbitmq.port}")
    private Integer port;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        factory.setVirtualHost("/");
        return factory;
    }

    @Bean
    public AmqpTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public TopicExchange directExchange() {
        return new TopicExchange("demoExchange");
    }

    @Bean
    public RabbitAdmin rabbitmqAdmin() throws Exception {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.declareExchange(directExchange());
        return rabbitAdmin;
    }

    @Bean
    public Queue helloQueue() {
        return new Queue("hello-queue");
    }

    @Bean
    public Queue tpadQueue() {
        return new Queue(blt_queue, true, false, false);
    }

    @Bean
    public Queue queueMessage() {
        return new Queue(message);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(messages);
    }

    @Bean
    public Binding bindingExchangeMessage(Queue queueMessage, TopicExchange directExchange) {
        return BindingBuilder.bind(queueMessage).to(directExchange).with("topic.*.message");
    }

    @Bean
    public Binding bindingExchangeMessages(Queue queueMessages, TopicExchange directExchange) {
        return BindingBuilder.bind(queueMessages).to(directExchange).with("topic.#");
    }

    @Bean
    public MessageListenerContainer messageListenerContainer() throws Exception {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setAmqpAdmin(rabbitmqAdmin());
        container.setQueues(helloQueue(), queueMessages());
        container.setPrefetchCount(20);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMessageListener(messageConsumer);
        return container;
    }
}