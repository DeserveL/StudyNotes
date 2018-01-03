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


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author DeserveL
 * @date 2017/8/7 15:39
 * @since 1.0.0
 */
public class myFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        myParameterRequestWrapper myParameterRequestWrapper = new myParameterRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(myParameterRequestWrapper,servletResponse);
        System.out.println("干正事");
    }

    public void destroy() {
        System.out.println("销毁");
    }
}
