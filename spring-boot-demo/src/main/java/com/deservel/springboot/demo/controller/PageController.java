/*
 * Copyright 2002-2018 the original author or authors.
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
package com.deservel.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 欢迎你
 *
 * @author DeserveL
 * @date 2018-12-18 14:38
 * @since 1.0.0
 */
@Controller
public class PageController {

    @Value("${name}")
    private String name;

    @RequestMapping({"/", "/page"})
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("name", name);
        map.addAttribute("welcome", "欢迎");
        // return模板文件的名称，对应src/main/resources/templates/welcome.html
        return "welcome";
    }

    @RequestMapping("/page/{pageName}")
    public String webSocket(@PathVariable String pageName) {
        return pageName;
    }
}
