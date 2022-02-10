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
import 'webpack-entry';

import {PluginManifest, PluginStore} from 'graylog-web-plugin/plugin';

import packageJson from '../../package.json';

import KafkaNotificationForm from "./components/KafkaNotificationForm";
import KafkaNotificationSummary from "./components/KafkaNotificationSummary";

const manifest = new PluginManifest(packageJson, {
    /* This is the place where you define which entities you are providing to the web interface.
       Right now you can add routes and navigation elements to it.

       Examples: */

    // Adding a route to /sample, rendering YourReactComponent when called:

    // routes: [
    //  { path: '/sample', component: YourReactComponent, permissions: 'inputs:create' },
    // ],

    // Adding an element to the top navigation pointing to /sample named "Sample":

    // navigation: [
    //  { path: '/sample', description: 'Sample' },
    // ]
    eventNotificationTypes: [
        {
            type: 'ucap-cloud-kafka-notification',
            displayName: '开普云钉钉云采集日志预警kafka插件',
            formComponent: KafkaNotificationForm,
            summaryComponent: KafkaNotificationSummary,
            defaultConfig: KafkaNotificationForm.defaultConfig,
        }
    ]
});

PluginStore.register(manifest);
