package com.andy.server;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class VelocityServer {

    public static String exportFixedVelocity() {

        VelocityEngine ve = new VelocityEngine();

        ve.setProperty(Velocity.RESOURCE_LOADER, "class");
        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        try {

            ve.init();

            Template t = ve.getTemplate("template/tmp.vm", "UTF-8");

            VelocityContext context = new VelocityContext();
            context.put("name","andy");

            String[] hobbyArray={"h1","h2","h3"};
            context.put("hobby", "hhh");
            context.put("hobbyArray", hobbyArray);


            StringWriter writer = new StringWriter();

            t.merge(context, writer);

            return writer.toString();

        } catch (Exception e) {
            throw new RuntimeException(" velocity error !!!");
        }
    }

    public static void main(String[] args) {
        System.out.println(exportFixedVelocity());;
    }
}