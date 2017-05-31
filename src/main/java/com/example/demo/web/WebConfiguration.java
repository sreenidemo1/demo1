package com.example.demo.web;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Sreenivasulu on 5/20/2017.
 */
@Profile("mem")
@Configuration
public class WebConfiguration {

   @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/h2console*//*");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }
}
