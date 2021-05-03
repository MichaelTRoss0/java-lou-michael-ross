/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.servicelayer;

import java.math.RoundingMode;
import static java.math.RoundingMode.HALF_UP;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mike
 */
public class FlooringServiceFileImplTest {
    
    private final RoundingMode ROUNDING_MODE = HALF_UP;
    private final int SCALE = 2;
    
//    private OrderDao serviceOrderDao;
//    private TaxesDao serviceTaxesDao;
//    private ProductDao serviceProductDao;
    
    private FlooringService testService;
    
    public FlooringServiceFileImplTest() {
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationContext.xml");
        testService = 
            ctx.getBean("serviceLayer", FlooringService.class);
    }

    @Test
    public void testSomeMethod() {
        fail("The test case is a prototype.");
    }
    
}
