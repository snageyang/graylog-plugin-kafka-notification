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
import React from 'react';
import PropTypes from 'prop-types';
import {ControlLabel} from 'react-bootstrap';
import lodash from 'lodash';

import {Input} from 'components/bootstrap';
import FormsUtils from 'util/FormsUtils';

class KafkaNotificationForm extends React.Component {
    static propTypes = {
        config: PropTypes.object.isRequired,
        validation: PropTypes.object.isRequired,
        onChange: PropTypes.func.isRequired,
    };

    static defaultConfig = {
        bootstrapServers: 'localhost:9092',
        topic: 'topic',
        content: 'kafka-send-content',
        batchSize: '20',
        lingerMs: '30000'
    };

    propagateChange = (key, value) => {
        const {config, onChange} = this.props;
        const nextConfig = lodash.cloneDeep(config);
        nextConfig[key] = value;
        onChange(nextConfig);
    };

    handleChange = event => {
        const {name} = event.target;
        this.propagateChange(name, FormsUtils.getValueFromInput(event.target));
    };

    render() {
        const {config, validation} = this.props;

        return (
            <React.Fragment>
                <ControlLabel>Bootstrap Servers </ControlLabel>
                <Input
                    id="bootstrapServers"
                    type="text"
                    name="bootstrapServers"
                    help="This is servers url which you want to send kafka message"
                    value={config.bootstrapServers ? config.bootstrapServers : kafkaNConfig.bootstrapServers}
                    onChange={this.handleChange}
                />

                <ControlLabel>Topic </ControlLabel>
                <Input
                    id="topic"
                    type="text"
                    name="topic"
                    help="This is topic which you want to send kafka message"
                    value={config.topic ? config.topic : kafkaNConfig.topic}
                    onChange={this.handleChange}
                />

                <ControlLabel>Content </ControlLabel>
                <Input
                    id="content"
                    type="text"
                    name="content"
                    help="This is content which you want to send kafka message"
                    value={config.content ? config.content : kafkaNConfig.content}
                    onChange={this.handleChange}
                />

                <ControlLabel>batch.size </ControlLabel>
                <Input
                    id="batchSize"
                    type="text"
                    name="batchSize"
                    help="生产者一批发送的消息大小,默认值是20"
                    value={config.batchSize ? config.batchSize : kafkaNConfig.batchSize}
                    onChange={this.handleChange}
                />

                <ControlLabel>linger.ms </ControlLabel>
                <Input
                    id="lingerMs"
                    type="text"
                    name="lingerMs"
                    help="生产者一批发送的最长等待时间,默认值是30s"
                    value={config.lingerMs ? config.lingerMs : kafkaNConfig.lingerMs}
                    onChange={this.handleChange}
                />
            </React.Fragment>
        );
    }
}

export default KafkaNotificationForm;