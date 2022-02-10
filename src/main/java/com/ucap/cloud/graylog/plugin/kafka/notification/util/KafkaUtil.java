/*
 * Copyright (C) 2020 Graylog, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Server Side Public License, version 1,
 * as published by MongoDB, Inc.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Server Side Public License for more details.
 *
 * You should have received a copy of the Server Side Public License
 * along with this program. If not, see
 * <http://www.mongodb.com/licensing/server-side-public-license>.
 */
package com.ucap.cloud.graylog.plugin.kafka.notification.util;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * kafka 极简工具类
 *
 * @author vhs
 * @version 1.0
 * @date 2021-02-25
 */
public class KafkaUtil {

    private static final Logger log = LoggerFactory.getLogger(KafkaUtil.class);

    /**
     * 发送消息
     *
     * @param bootstrapServers 地址
     * @param topic            主题
     * @param content          消息内容
     */
    public static void send(String bootstrapServers, String topic, String content, int batchSize, int lingerMs) {
        Properties props = new Properties();
        // broker地址
        props.put("bootstrap.servers", bootstrapServers);
        // 请求的时候需要验证
        props.put("acks", "all");
        // 请求失败需要重试jps
        props.put("retries", "0");
        // 内存缓存区大小
        props.put("buffer.memory", 33554432);
        props.put("batch.size", batchSize);
        props.put("linger.ms", lingerMs);
        // 指定消息key序列化方式
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        // 指定消息本身的序列化方式
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>(
                topic, content), (metadata, exception) -> {
            log.info("通过kafka发送警告: bootstrapServer: {}, topic: {},exception:{}", bootstrapServers, topic, exception);
        });

        producer.close();
    }
}
