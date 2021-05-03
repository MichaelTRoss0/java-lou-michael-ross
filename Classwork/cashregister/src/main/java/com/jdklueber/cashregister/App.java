package com.jdklueber.cashregister;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdklueber.cashregister.controller.MainController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    	MainController controller = ctx.getBean("controller", MainController.class);
    	controller.run();
    }
}
