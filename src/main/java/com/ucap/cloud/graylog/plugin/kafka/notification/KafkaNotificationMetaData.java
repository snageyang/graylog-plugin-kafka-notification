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
package com.ucap.cloud.graylog.plugin.kafka.notification;

import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.ServerStatus;
import org.graylog2.plugin.Version;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

/**
 * Implement the PluginMetaData interface here.
 */
public class KafkaNotificationMetaData implements PluginMetaData {
    private static final String PLUGIN_PROPERTIES = "com.ucap.cloud.graylog.plugin.ucap-cloud-graylog-plugin-kafka-notification/graylog-plugin.properties";

    @Override
    public String getUniqueId() {
        return "com.ucap.cloud.graylog.plugin.kafka.notification.KafkaNotificationPlugin";
    }

    @Override
    public String getName() {
        return "开普云云采集日志预警kafka插件";
    }

    @Override
    public String getAuthor() {
        return "杨振亮 <yangzl@ucap.com.cn>";
    }

    @Override
    public URI getURL() {
        return URI.create("https://www.kaipuyun.cn");
    }

    @Override
    public Version getVersion() {
        return Version.fromPluginProperties(getClass(), PLUGIN_PROPERTIES, "version", Version.from(0, 0, 0, "unknown"));
    }

    @Override
    public String getDescription() {
        return "云采集实时推送采集日志，graylog通过聚合计算统计固定时间超过阈值的站点实现kafka预警";
    }

    @Override
    public Version getRequiredVersion() {
        return Version.fromPluginProperties(getClass(), PLUGIN_PROPERTIES, "graylog.version", Version.from(0, 0, 0, "unknown"));
    }

    @Override
    public Set<ServerStatus.Capability> getRequiredCapabilities() {
        return Collections.emptySet();
    }
}
