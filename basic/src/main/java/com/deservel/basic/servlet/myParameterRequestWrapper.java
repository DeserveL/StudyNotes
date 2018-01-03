/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.deservel.basic.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DeserveL
 * @date 2017/8/7 16:47
 * @since 1.0.0
 */
public class myParameterRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest myRequest;

    public myParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        this.myRequest = request;
    }

    @Override
    public String getParameter(String name) {
        String parameter = myRequest.getParameter(name);
        return parameter + "0.0";
    }

    @Override
    public Map getParameterMap() {
        Map<String, String[]> parameterMap = myRequest.getParameterMap();
        Map<String, String[]> rsMap = new HashMap<String, String[]>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String[] value = entry.getValue();
            for (int i = 0; i < value.length; i++) {
                value[i] = value[i] + "1.1";
            }
            rsMap.put(entry.getKey(), value);
        }
        return rsMap;
    }
}
