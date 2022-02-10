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

import CommonNotificationSummary from "./CommonNotificationSummary";

class KafkaNotificationSummary extends React.Component {
    static propTypes = {
        type: PropTypes.string.isRequired,
        notification: PropTypes.object,
        definitionNotification: PropTypes.object.isRequired,
    };

    static defaultProps = {
        notification: {},
    };

    render() {
        const {notification} = this.props;
        return (
            <CommonNotificationSummary {...this.props}>
                <React.Fragment>
                    <tr>
                        <td>Bootstrap servers:</td>
                        <td>{notification.config.bootstrapServers}</td>
                    </tr>
                    <tr>
                        <td>Topic:</td>
                        <td>{notification.config.topic}</td>
                    </tr>
                    <tr>
                        <td>content:</td>
                        <td>{notification.config.content}</td>
                    </tr>
                    <tr>
                        <td>batchSize:</td>
                        <td>{notification.config.batchSize}</td>
                    </tr>
                    <tr>
                        <td>lingerMs:</td>
                        <td>{notification.config.lingerMs}</td>
                    </tr>
                </React.Fragment>
            </CommonNotificationSummary>
        );
    }
}

export default KafkaNotificationSummary;
