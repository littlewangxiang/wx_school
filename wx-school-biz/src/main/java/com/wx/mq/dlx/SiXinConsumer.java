package com.wx.mq.dlx;

import com.rabbitmq.client.*;
import com.wx.mq.MQConstant;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class SiXinConsumer {

    private static String dxlExchangeName = "dlx.exchange";
    private static String dxlQueueName = "dlx.queue";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost(MQConstant.VIRTUAL_HOST);

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            String exchangeName = "test_dlx_exchange";
            String exchangeType = "topic";
            String routingKey = "dlx.#";

            //1.声明exchagne
            channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
            //2.声明队列
            String queueName = "test_dlx_queue";
            Map<String, Object> agruments = new HashMap<>();
            agruments.put("x-dead-letter-exchange", dxlExchangeName);
            channel.queueDeclare(queueName, true, false, false, agruments);
            //3.绑定
            channel.queueBind(queueName, exchangeName, routingKey);

            //4.对死信队列进行声明
            channel.exchangeDeclare(dxlExchangeName, exchangeType, true, false, false, null);
            //4.1声明死信队列
            channel.queueDeclare(dxlQueueName, true, false, false, null);
            channel.queueBind(dxlQueueName, dxlExchangeName, "#");

            //消费
            QueueingConsumer consumer = new QueueingConsumer(channel);
//            channel.basicConsume(queueName, true, consumer);

//            boolean hasNext = true;
//            while (hasNext) {
//                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//                String str = new String(delivery.getBody());
//                if (StringUtils.isNotBlank(str)) {
//                    hasNext = false;
//                    continue;
//                }
//                System.out.println("get:" + str);
//            }

            channel.basicConsume(dxlQueueName, true, consumer);

            boolean dlxHasNext = true;
            while (dlxHasNext) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String str = new String(delivery.getBody());
                System.out.println("dxl get:" + str);
                if (StringUtils.isNotBlank(str)) {
                    dlxHasNext = false;
                }
            }

            channel.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
