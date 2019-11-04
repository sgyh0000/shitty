package club.itguys.shitty.mvc;

import club.itguys.shitty.beans.BeanContext;
import club.itguys.shitty.beans.DefaultInitializer;
import club.itguys.shitty.beans.Initializer;
import club.itguys.shitty.mvc.anno.Controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author sgyh
 */
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String basePackage = servletContext.getInitParameter("basePackage");
        String charset = servletContext.getInitParameter("charset");
        if (charset == null) {
            charset = "utf-8";
        }
        System.setProperty("base.package", basePackage);
        System.setProperty("charset", charset);
        Initializer initializer = new DefaultInitializer();
        BeanContext.setBeanInitializer(Controller.class, new ControllerFactory());
        initializer.init(basePackage);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
