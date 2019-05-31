package com.cherkashyn.vitalii.education.mongo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class ExampleFreemarker {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration=new Configuration();
        configuration.setClassForTemplateLoading(ExampleFreemarker.class,"/");

        Template template=configuration.getTemplate("example.tml");
        Map<String, String> dataSource=new HashMap<String, String>();
        dataSource.put("name", "Vitalii");
        template.process(dataSource, new OutputStreamWriter(System.out));
    }

}
