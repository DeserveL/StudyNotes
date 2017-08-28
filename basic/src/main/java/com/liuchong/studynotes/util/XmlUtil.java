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
package com.liuchong.studynotes.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liuchong.studynotes.util.A.B;
import com.liuchong.studynotes.util.annotation.XmlNodeName;
import com.liuchong.studynotes.util.annotation.XmlSpecialList;

/**
 * @author DeserveL
 * @date 2017/8/25 15:58
 * @since 1.0.0
 */
public class XmlUtil {
    /**
     * 对象转xml
     *
     * @param obj
     * @return
     */
    public static <T> String toXML(T obj) {
        return toXML(obj, null);
    }

    /**
     * 对象转xml 自定义根节点
     *
     * @param obj
     * @param rootName
     * @return
     */
    public static <T> String toXML(T obj, String rootName) {
        StringBuffer xml = new StringBuffer();
        // 是否为基本类型
        if (isWrapClass(obj.getClass())) {
            if (rootName == null) {
                rootName = "xml";
            }
            xml.append("<").append(rootName).append(">").append(obj).append("</").append(rootName).append(">");
            return xml.toString();
            // list
        } else if (obj instanceof List) {
            List list = (List) obj;
            if (rootName == null) {
                rootName = "xml";
            }
            xml.append("<").append(rootName).append(">");
            for (int i = 0; i < list.size(); i++) {
                Object hm = (Object) list.get(i);
                xml.append(toXML(hm));
            }
            xml.append("</").append(rootName).append(">");
            return xml.toString();
            // map
        } else if (obj instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) obj;
            if (rootName == null) {
                rootName = "xml";
            }
            xml.append("<").append(rootName).append(">");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                xml.append(toXML(entry.getValue(), entry.getKey()));
            }
            xml.append("</").append(rootName).append(">");
            return xml.toString();
            // javabean
        } else {
            return beanToXML(obj, rootName);
        }
    }

    /**
     * javabean转xml
     *
     * @param obj
     * @param rootName
     * @return
     */
    private static <T> String beanToXML(T obj, String rootName) {
        if (rootName == null) {
            rootName = getObjectNodeName(obj);
        }
        StringBuffer xml = new StringBuffer();
        xml.append("<" + rootName + ">");
        // 得到类对象
        Class<? extends Object> userCla = obj.getClass();
        // 追溯其父对象。（不包含 Object.class ）
        for (; userCla != Object.class; userCla = userCla.getSuperclass()) {
            /* 得到类的所有属性集合 */
            Field[] fs = userCla.getDeclaredFields();
            for (Field field : fs) {
                xml.append(fieldToXml(obj, field));
            }

        }
        xml.append("</" + rootName + ">");
        return xml.toString();
    }

    /**
     * 字段转xml
     *
     * @param obj
     * @param field
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static <T> String fieldToXml(T obj, Field field) {

        if (!field.getName().startsWith("this")) {
            StringBuffer xml = new StringBuffer();
            // 属性值
            Object value = getFieldValueByName(field.getName(), obj);
            // 属性节点名
            String nodeName = getFieldtNodeName(field);
            if (null != value) {
                // 是否为基本类型
                if (isWrapClass(field.getType())) {
                    xml.append("<").append(nodeName).append(">").append(value).append("</").append(nodeName)
                            .append(">");
                    return xml.toString();
                    // List
                } else if (field.getType().isAssignableFrom(List.class)) {
                    List list = (List) value;
                    // 是否特殊展示（没有外一层）
                    XmlSpecialList xmlSpecialListAnnotation = field.getAnnotation(XmlSpecialList.class);
                    if (null == xmlSpecialListAnnotation) {
                        xml.append("<").append(nodeName).append(">");
                        for (int i = 0; i < list.size(); i++) {
                            Object hm = (Object) list.get(i);
                            xml.append(toXML(hm));
                        }
                        xml.append("</").append(nodeName).append(">");
                    } else {
                        nodeName = xmlSpecialListAnnotation.value();
                        for (int i = 0; i < list.size(); i++) {
                            Object hm = (Object) list.get(i);
                            xml.append(toXML(hm, nodeName));
                        }
                    }
                    return xml.toString();
                    // Map
                } else if (field.getType().isAssignableFrom(Map.class)) {
                    Map<String, Object> map = (Map<String, Object>) value;
                    xml.append("<").append(nodeName).append(">");
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        xml.append(toXML(entry.getValue(), entry.getKey()));
                    }
                    xml.append("</").append(nodeName).append(">");
                    return xml.toString();
                    // javabean
                } else {
                    return beanToXML(value, nodeName);
                }
            }
        }
        return "";
    }

    /**
     * 获取对象节点名字(没加注解默认类名)
     *
     * @param obj
     * @return
     */
    public static <T> String getObjectNodeName(T obj) {
        XmlNodeName annotation = obj.getClass().getAnnotation(XmlNodeName.class);
        if (null != annotation) {
            return annotation.value();
        } else {
            return obj.getClass().getSimpleName();
        }
    }

    /**
     * 获取属性节点名字(没加注解默认属性名)
     *
     * @param field
     * @return
     */
    public static String getFieldtNodeName(Field field) {
        XmlNodeName annotation = field.getAnnotation(XmlNodeName.class);
        if (null != annotation) {
            return annotation.value();
        } else {
            return field.getName();
        }
    }

    /**
     * 根据属性名获取属性值
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 判断此类型是否为基本类型或者基本类型的包装类型或者String
     *
     * @param clz
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isWrapClass(Class<?> clz) {
        try {
            if (!String.class.equals(clz)) {
                if (!clz.isPrimitive()) {
                    return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] aa) {

        A res = new A();
        res.setName("nihao");
        List<B> list = new ArrayList<B>();
        A.B bo1 = res.new B();
        bo1.setPatientID("123");
        bo1.setPatName("liu");
        B bo2 = res.new B();
        bo2.setPatientID("1231");
        bo2.setPatName("liu1");
        list.add(bo1);
        list.add(bo2);
        res.setList(list);

        res.setOutPatientInfoBo(bo2);

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("map1", "woshi");
        hashMap.put("mapBo", bo2);

        res.setHao(hashMap);

        System.out.println(toXML(hashMap));

    }

}

@XmlNodeName("xml")
class A {
    private String name;

    private Map<String, Object> hao;

    @XmlNodeName("bo")
    private B outPatientInfoBo;

    @XmlSpecialList
    private List<B> list;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the hao
     */
    public Map<String, Object> getHao() {
        return hao;
    }

    /**
     * @param hao the hao to set
     */
    public void setHao(Map<String, Object> hao) {
        this.hao = hao;
    }

    /**
     * @return the outPatientInfoBo
     */
    public B getOutPatientInfoBo() {
        return outPatientInfoBo;
    }

    /**
     * @param outPatientInfoBo the outPatientInfoBo to set
     */
    public void setOutPatientInfoBo(B outPatientInfoBo) {
        this.outPatientInfoBo = outPatientInfoBo;
    }

    /**
     * @return the list
     */
    public List<B> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<B> list) {
        this.list = list;
    }

    public class B {
        private String PatientID;// 病人ID
        private String PatName;// 姓名

        public String getPatientID() {
            return PatientID;
        }

        public void setPatientID(String patientID) {
            PatientID = patientID;
        }

        public String getPatName() {
            return PatName;
        }

        public void setPatName(String patName) {
            PatName = patName;
        }
    }
}
