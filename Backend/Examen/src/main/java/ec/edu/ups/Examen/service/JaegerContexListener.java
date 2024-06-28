package ec.edu.ups.Examen.service;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class JaegerContexListener implements ServletContextListener{

	@Override
    public void contextInitialized(ServletContextEvent sce) {
        JaegerConfig.initTracer();
    }
	
	 @Override
     public void contextDestroyed(ServletContextEvent sce) {
	 }
	
}
