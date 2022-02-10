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
package com.ucap.cloud.graylog.plugin.kafka.notification.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import org.graylog.events.contentpack.entities.EventNotificationConfigEntity;
import org.graylog.events.notifications.EventNotificationConfig;
import org.graylog2.contentpacks.model.entities.EntityDescriptor;
import org.graylog2.contentpacks.model.entities.references.ValueReference;

import java.util.Map;

import static com.ucap.cloud.graylog.plugin.kafka.notification.util.Constants.*;


/**
 * implements EventNotificationConfigEntity
 *
 * @author vhs
 * @version 1.0
 * @date 2021-02-25
 */
@AutoValue
@JsonTypeName(KafkaEventNotificationConfig.TYPE_NAME)
@JsonDeserialize(builder = KafkaEventNotificationConfigEntity.Builder.class)
public abstract class KafkaEventNotificationConfigEntity implements EventNotificationConfigEntity {


    @JsonProperty(FIELD_BOOTSTRAP_SERVERS)
    public abstract ValueReference bootstrapServers();

    @JsonProperty(FIELD_TOPIC)
    public abstract ValueReference topic();

    @JsonProperty(FIELD_CONTENT)
    public abstract ValueReference content();

    @JsonProperty(FIELD_BATCH_SIZE)
    public abstract ValueReference batchSize();

    @JsonProperty(FIELD_LINGER_MS)
    public abstract ValueReference lingerMs();

    public static Builder builder() {
        return Builder.create();
    }

    @AutoValue.Builder
    public static abstract class Builder implements EventNotificationConfigEntity.Builder<Builder> {

        @JsonCreator
        public static Builder create() {
            return new AutoValue_KafkaEventNotificationConfigEntity.Builder()
                    .type(KafkaEventNotificationConfig.TYPE_NAME);
        }

        @JsonProperty(FIELD_BOOTSTRAP_SERVERS)
        public abstract Builder bootstrapServers(ValueReference bootstrapServers);

        @JsonProperty(FIELD_TOPIC)
        public abstract Builder topic(ValueReference topic);

        @JsonProperty(FIELD_CONTENT)
        public abstract Builder content(ValueReference content);

        @JsonProperty(FIELD_BATCH_SIZE)
        public abstract Builder batchSize(ValueReference content);

        @JsonProperty(FIELD_LINGER_MS)
        public abstract Builder lingerMs(ValueReference content);

        public abstract KafkaEventNotificationConfigEntity build();
    }

    @Override
    public EventNotificationConfig toNativeEntity(Map<String, ValueReference> parameters, Map<EntityDescriptor, Object> nativeEntities) {
        return KafkaEventNotificationConfig.builder()
                .bootstrapServers(bootstrapServers().asString(parameters))
                .topic(topic().asString(parameters))
                .content(content().asString(parameters))
                .batchSize(batchSize().asInteger(parameters))
                .lingerMs(lingerMs().asInteger(parameters))
                .build();
    }
}
