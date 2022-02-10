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

/**
 * 常量类
 *
 * @author 杨振亮
 * @date 2022/2/8 15:08
 */
public interface Constants {
    String FIELD_BOOTSTRAP_SERVERS = "bootstrapServers";
    String FIELD_TOPIC = "topic";
    String FIELD_CONTENT = "content";
    String FIELD_BATCH_SIZE = "batchSize";
    String FIELD_LINGER_MS = "lingerMs";
}