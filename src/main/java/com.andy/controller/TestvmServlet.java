package com.andy.controller;

/**
 * Created by andy on 15-7-22.
 */
import com.andy.server.VelocityServer;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

public class TestvmServlet extends HttpServlet {
    VelocityServer vs = new VelocityServer();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VelocityEngine ve = new VelocityEngine();
//        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Properties p = new Properties();
        p.load(this.getClass().getResourceAsStream("/velocity.properties"));
        p.setProperty("file.resource.loader.path", this.getServletContext().getRealPath("/WEB-INF/template"));
        ve.init(p);

        Template t = ve.getTemplate("/tmp.vm");
        VelocityContext ctx = new VelocityContext();

        // put  data
        Map map = new HashMap();
        map.put("title","testVM");
        map.put("body","i'm body body body ~~~");
        ctx.put("map",map);


        // put data done ;
        StringWriter sw = new StringWriter();

        t.merge(ctx, sw);

        PrintWriter outer = resp.getWriter();

        outer.write(sw.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
