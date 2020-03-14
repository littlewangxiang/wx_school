package com.wx.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer2 {

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("test001");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //1.创建出消息队列
            //第一个参数 消息队列的名称
            //第二个参数  消息是否持久化
            //第三个参数  消息队列是否被channel独占
            //第四个参数  是否自动删除 。当消息队列没有绑定交换机
            //第五个参数  扩展参数
            //1.1 声明队列
            channel.queueDeclare(MQConstant.QUEUE_2, true, false, false, null);

            //1.2声明叫交换机
            channel.exchangeDeclare(MQConstant.DIRECT_EXCHANGE_NAME, MQConstant.EXCHANGE_TYPE_DIRECT, true, false,
                    false, null);
            //1.3交换机和队列绑定。通知绑定routeKey
            channel.queueBind(MQConstant.QUEUE_2, MQConstant.DIRECT_EXCHANGE_NAME, MQConstant.DIRECT_ROUTE_KEY_2, null);

            //2.创建消费者
            QueueingConsumer consumer = new QueueingConsumer(channel);
            //3.消费者和消息队列关联
            //第一个参数 消息队列名称
            //第二个参数 是否自动签收
            //第三个参数  消费者
            channel.basicConsume(MQConstant.QUEUE_2, true, consumer);
            //4.获取消息
            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String str = new String(delivery.getBody());
                System.out.println("2号get:" + str);
                if (str.equals("5")) {
                    break;
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
