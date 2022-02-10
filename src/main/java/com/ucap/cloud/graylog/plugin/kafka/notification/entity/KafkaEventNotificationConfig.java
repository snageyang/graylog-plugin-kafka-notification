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
import org.apache.commons.lang3.StringUtils;
import org.graylog.events.contentpack.entities.EventNotificationConfigEntity;
import org.graylog.events.event.EventDto;
import org.graylog.events.notifications.EventNotificationConfig;
import org.graylog.events.notifications.EventNotificationExecutionJob;
import org.graylog.scheduler.JobTriggerData;
import org.graylog2.contentpacks.EntityDescriptorIds;
import org.graylog2.contentpacks.model.entities.references.ValueReference;
import org.graylog2.plugin.rest.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.ucap.cloud.graylog.plugin.kafka.notification.util.Constants.*;

/**
 * implements EventNotificationConfig
 *
 * @author vhs
 * @version 1.0
 * @date 2021-02-25
 */
@AutoValue
@JsonTypeName(KafkaEventNotificationConfig.TYPE_NAME)
@JsonDeserialize(builder = KafkaEventNotificationConfig.Builder.class)
public abstract class KafkaEventNotificationConfig implements EventNotificationConfig {

    private static final Logger log = LoggerFactory.getLogger(KafkaEventNotificationConfig.class);

    public static final String TYPE_NAME = "ucap-cloud-kafka-notification";



    @JsonProperty(FIELD_BOOTSTRAP_SERVERS)
    public abstract String bootstrapServers();

    @JsonProperty(FIELD_TOPIC)
    public abstract String topic();

    @JsonProperty(FIELD_CONTENT)
    public abstract String content();

    @JsonProperty(FIELD_BATCH_SIZE)
    public abstract Integer batchSize();

    @JsonProperty(FIELD_LINGER_MS)
    public abstract Integer lingerMs();

    public static Builder builder() {
        return Builder.create();
    }

    @Override
    public JobTriggerData toJobTriggerData(EventDto dto) {
        return EventNotificationExecutionJob.Data.builder().eventDto(dto).build();
    }

    @Override
    public ValidationResult validate() {
        final ValidationResult validation = new ValidationResult();
        String errorMessage;
        if (StringUtils.isEmpty(bootstrapServers())) {
            errorMessage = "bootstrap_servers cannot be empty";
            log.error(errorMessage);
            validation.addError(FIELD_BOOTSTRAP_SERVERS, errorMessage);
        }
        if (StringUtils.isEmpty(topic())) {
            errorMessage = "topic cannot be empty";
            log.error(errorMessage);
            validation.addError(FIELD_TOPIC, errorMessage);
        }
        if (StringUtils.isEmpty(content())) {
            errorMessage = "content cannot be empty";
            log.error(errorMessage);
            validation.addError(FIELD_CONTENT, errorMessage);
        }
        return validation;
    }

    @Override
    public EventNotificationConfigEntity toContentPackEntity(EntityDescriptorIds entityDescriptorIds) {
        return KafkaEventNotificationConfigEntity.builder()
                .bootstrapServers(ValueReference.of(bootstrapServers()))
                .topic(ValueReference.of(topic()))
                .content(ValueReference.of(content()))
                .batchSize(ValueReference.of(batchSize()))
                .lingerMs(ValueReference.of(lingerMs()))
                .build();
    }

    @AutoValue.Builder
    public static abstract class Builder implements EventNotificationConfig.Builder<Builder> {
        @JsonCreator
        public static Builder create() {
            return new AutoValue_KafkaEventNotificationConfig.Builder()
                    .type(TYPE_NAME)
                    .bootstrapServers("localhost:9092")
                    .topic("topic")
                    .content("kafka-send-message")
                    .batchSize(20)
                    .lingerMs(30000);
        }

        @JsonProperty(FIELD_BOOTSTRAP_SERVERS)
        public abstract Builder bootstrapServers(String bootstrapServers);

        @JsonProperty(FIELD_TOPIC)
        public abstract Builder topic(String topic);

        @JsonProperty(FIELD_CONTENT)
        public abstract Builder content(String content);

        @JsonProperty(FIELD_BATCH_SIZE)
        public abstract Builder batchSize(Integer batchSize);

        @JsonProperty(FIELD_LINGER_MS)
        public abstract Builder lingerMs(Integer lingerMs);

        public abstract KafkaEventNotificationConfig build();
    }
}
