package com.cherkashyn.vitalii.education.mongo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ExampleSparkFreemarker {

    /** execute main logic */
    public static void main(String[] args) throws IOException, TemplateException {

        Spark.get(new Route("/echo"){
            private final static String PARAM_NAME="message";
            @Override
            public Object handle(Request request, Response response) {
                String messageParameter=request.queryParams(PARAM_NAME);
                if(messageParameter==null){
                    halt(500, "necessary parameter is not found: "+PARAM_NAME);
                }
                Map<String, String> dataSource=new HashMap<String, String>();
                dataSource.put("message", messageParameter);

                try {
                    StringWriter writer=new StringWriter();
                    getTemplateForRequest(configureFreemarker())
                            .process(dataSource, writer);
                    return writer.toString();
                } catch (TemplateException e) {
                    halt(501, "template was not found");
                    return null;
                } catch (IOException e) {
                    halt(501, "output exception");
                    return null;
                }
            };
        });
    }

    private final static String TEMPLATE_ECHO="echo.tml";

    private static Template getTemplateForRequest(Configuration configuration) throws IOException {
        return configuration.getTemplate(TEMPLATE_ECHO);
    }

    /** create Spark configuration */
    private static Configuration configureFreemarker(){
        Configuration configuration=new Configuration();
        configuration.setClassForTemplateLoading(ExampleSparkFreemarker.class,"/");
        return configuration;
    }

}
