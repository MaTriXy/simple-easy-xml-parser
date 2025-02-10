package com.novoda.jackson.small;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JacksonSmallXmlBenchmark {

    public void parse(String xml) throws Exception {
        XmlMapper mapper = new XmlMapper();
        Employee employee = mapper.readValue(xml, Employee.class);
        System.out.println(getClass().getSimpleName() + " " + employee.name);
    }

    public static class Employee {
        public String name;
    }
}
