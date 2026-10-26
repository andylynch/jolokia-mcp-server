/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jolokia.mcp;

import java.util.List;
import java.util.Optional;

import org.jolokia.json.JSONObject;

public interface JolokiaService {

    /**
     * Resolve the service to use for the given Jolokia agent URL.
     *
     * @param url Jolokia agent URL, or {@code null}/blank for the default target
     * @return the service for the given URL
     * @throws UnsupportedOperationException if the implementation cannot target other agents
     */
    default JolokiaService forUrl(String url) {
        if (url == null || url.isBlank()) {
            return this;
        }
        throw new UnsupportedOperationException("Targeting a Jolokia URL is not supported by this MCP server");
    }

    List<String> listMBeans() throws Exception;

    JSONObject listOperations(String mbean) throws Exception;

    JSONObject listAttributes(String mbean) throws Exception;

    Optional<Object> read(String mbean, String attr) throws Exception;

    Optional<Object> write(String mbean, String attr, Object value) throws Exception;

    Optional<Object> exec(String mbean, String op, Object... args) throws Exception;
}

