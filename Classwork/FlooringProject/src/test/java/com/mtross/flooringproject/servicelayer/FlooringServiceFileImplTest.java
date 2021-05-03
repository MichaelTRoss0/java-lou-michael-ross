/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.servicelayer;

import com.mtross.flooringproject.dto.Order;
import com.mtross.flooringproject.dto.Product;
import com.mtross.flooringproject.servicelayerexceptions.FlooringInvalidInputException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mike
 */
public class FlooringServiceFileImplTest {
    
    private FlooringService testService;
    
    public FlooringServiceFileImplTest() {
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationContext.xml");
        testService = 
            ctx.getBean("serviceLayer", FlooringService.class);
    }

    @Test
    public void testLoadAllFiles() throws Exception {
        // Assert that just calling this method will not cause an error
        testService.loadAllFiles();
    }

    @Test
    public void testSaveAllChanges() throws Exception {
        // Assert that just calling this method will not cause an error
        testService.saveAllChanges();
    }

    @Test
    public void testGetEveryOrder() {
        // ARRANGE
        List<Order> listOfOneOrder;
        
        // ACT
        listOfOneOrder = testService.getEveryOrder();
        
        // ASSERT
        Assertions.assertEquals(1, listOfOneOrder.size(),
                "There should be exactly one order in the list.");
    }

    @Test
    public void testGetOrdersByDate() throws Exception {
        // ARRANGE
        List<Order> listOfTodaysOrder = new ArrayList<>();
        List<Order> emptyList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate epoch = LocalDate.EPOCH;
        
        // ACT &  ASSERT
        try {
            listOfTodaysOrder = testService.getOrdersByDate(today);
        } catch (FlooringInvalidInputException e) {
            Assertions.fail(
                    "An error should not have been thrown, "
                  + "as there should be exactly one order on this date.");
        }
        
        try {
            emptyList = testService.getOrdersByDate(epoch);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as there should be no orders on this date.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
        
        // ASSERT
        Assertions.assertEquals(1, listOfTodaysOrder.size(),
                "There should be exactly one order in the list.");
        Assertions.assertEquals(0, emptyList.size(),
                "The list should still be empty.");
    }

    @Test
    public void testGetOneOrder() {
        // ARRANGE
        int rightNum = 1;
        int wrongNum = 666;
        
        // ACT
        Order shouldNotBeNull = testService.getOneOrder(rightNum);
        Order shouldBeNull = testService.getOneOrder(wrongNum);
        
        // ASSERT
        Assertions.assertNotNull(shouldNotBeNull,
                "The order with the correct order number should not be null.");
        Assertions.assertNull(shouldBeNull,
                "The order with the incorrect order number should be null.");
    }

    @Test
    public void testGetEveryProduct() {
        // ARRANGE
        List<Product> listOfOneProduct;
        
        // ACT
        listOfOneProduct = testService.getEveryProduct();
        
        // ASSERT
        Assertions.assertEquals(1, listOfOneProduct.size(),
                "There should be exactly one product in the list.");
    }

    @Test
    public void testAddOrder() {
        // ARRANGE
        Order newOrder = new Order(1);
        newOrder.setCustomerName("Michael Ross");
        newOrder.setState("KY");
        newOrder.setTaxRate(new BigDecimal("6.00"));
        newOrder.setProductType("Wood");
        newOrder.setArea(new BigDecimal("1000.00"));
        newOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        newOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        newOrder.setMaterialCost(new BigDecimal("5150.00"));
        newOrder.setLaborCost(new BigDecimal("4750.00"));
        newOrder.setTax(new BigDecimal("594.00"));
        newOrder.setTotal(new BigDecimal("10494.00"));
        
        LocalDate today = LocalDate.now();
        
        // ACT
        Order putIn = testService.addOrder(newOrder, today);
        
        // ASSERT
        Assertions.assertEquals(putIn, newOrder,
                "The order put in should be the same as the order put out.");
    }

    @Test
    public void testEditOrder() {
        // ARRANGE
        Order newOrder = new Order(1);
        newOrder.setCustomerName("Somebody Else");
        newOrder.setState("KY");
        newOrder.setTaxRate(new BigDecimal("6.00"));
        newOrder.setProductType("Wood");
        newOrder.setArea(new BigDecimal("1000.00"));
        newOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        newOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        newOrder.setMaterialCost(new BigDecimal("5150.00"));
        newOrder.setLaborCost(new BigDecimal("4750.00"));
        newOrder.setTax(new BigDecimal("594.00"));
        newOrder.setTotal(new BigDecimal("10494.00"));
        
        // ACT
        Order originalOrder = testService.editOrder(1, newOrder);
        
        // ASSERT
        Assertions.assertNotEquals(originalOrder, newOrder,
                "The new order should not be the same as the original order.");
    }

    @Test
    public void testRemoveOrder() {
        // ARRANGE
        int rightOrderNum = 1;
        int wrongOrderNum = 666;
        
        // ACT
        Order shouldNotBeNull = testService.removeOrder(rightOrderNum);
        Order shouldBeNull = testService.removeOrder(wrongOrderNum);
        
        // ASSERT
        Assertions.assertNotNull(shouldNotBeNull,
                "The order with the correct order number should not be null.");
        Assertions.assertNull(shouldBeNull,
                "The order with the incorrect order number should be null.");
    }

    @Test
    public void testVerifyDate() throws Exception {
        // ARRANGE
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate tomorrow = today.plusDays(1);
        
        // ACT & ASSERT
        try {
            testService.verifyDate(today);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as this date is not in the future.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
        
        try {
            testService.verifyDate(yesterday);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as this date is not in the future.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
        
        try {
            testService.verifyDate(tomorrow);
        } catch (FlooringInvalidInputException e) {
            Assertions.fail(
                    "An error should not have been thrown, "
                  + "as this date is in the future.");
        }
    }

    @Test
    public void testVerifyName() throws Exception {
        // ARRANGE
        String goodName = "Jebediah Kerman";
        String badName1 = "Bond, James Bond";
        String blankName = "";
        
        // ACT & ASSERT
        try {
            testService.verifyName(goodName);
        } catch (FlooringInvalidInputException e) {
            Assertions.fail(
                    "An error should not have been thrown, "
                  + "as this name meets all requirements.");
        }
        
        try {
            testService.verifyName(badName1);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as this name has a comma in it.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
        
        try {
            testService.verifyName(blankName);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as this name is blank.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
    }

    @Test
    public void testVerifyState() throws Exception {
        // ARRANGE
        String goodStateName = "Kentucky";
        String goodStateAbbr = "KY";
        String badStateName = "Japan";
        String badStateAbbr = "JP";
        
        // ACT & ASSERT
        String verifiedStateName = "n/a";
        String verifiedStateAbbr = "n/a";
        
        try {
            verifiedStateName = testService.verifyState(goodStateName);
        } catch (FlooringInvalidInputException e) {
            Assertions.fail(
                    "An error should not have been thrown, "
                  + "as this name is on the list of taxes.");
        }
        try {
            verifiedStateAbbr = testService.verifyState(goodStateAbbr);
        } catch (FlooringInvalidInputException e) {
            Assertions.fail(
                    "An error should not have been thrown, "
                  + "as this abbreviation is on the list of taxes.");
        }
        
        try {
            testService.verifyState(badStateName);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as this name is not on the list of taxes.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
        try {
            testService.verifyState(badStateAbbr);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as this abbreviation is not on the list of taxes.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
        
        // ASSERT
        Assertions.assertEquals("KY", verifiedStateName,
                "The State name should have been changed to its abbreviation.");
        Assertions.assertEquals("KY", verifiedStateAbbr,
                "The State abbreviation should have stayed an abbreviation.");
    }

    @Test
    public void testVerifyProduct() throws Exception {
        // ARRANGE
        String goodProductType = "Wood";
        String badProductType = "Shag";
        
        List<Product> listOfOneProduct = testService.getEveryProduct();
        
        // ACT & ASSERT
        try {
            testService.verifyProduct(goodProductType, listOfOneProduct);
        } catch (FlooringInvalidInputException e) {
            Assertions.fail(
                    "An error should not have been thrown, "
                  + "as this product was on the list of product types.");
        }
        
        try {
            testService.verifyProduct(badProductType, listOfOneProduct);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as this product was not on the list of product types.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
    }

    @Test
    public void testVerifyArea() throws Exception {
        // ARRANGE
        BigDecimal goodArea1 = new BigDecimal("100");
        BigDecimal goodArea2 = new BigDecimal("101");
        BigDecimal badArea = new BigDecimal("99");
        
        // ACT & ASSERT
        try {
            testService.verifyArea(goodArea1);
        } catch (FlooringInvalidInputException e) {
            Assertions.fail(
                    "An error should not have been thrown, "
                  + "as this area is exactly 100 square feet.");
        }
        
        try {
            testService.verifyArea(goodArea2);
        } catch (FlooringInvalidInputException e) {
            Assertions.fail(
                    "An error should not have been thrown, "
                  + "as this area is more than 100 square feet.");
        }
        
        try {
            testService.verifyArea(badArea);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as this area is less than 100 square feet.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
    }

    @Test
    public void testVerifyDateAndOrderNum() throws Exception {
        // ARRANGE
        LocalDate goodDate = LocalDate.now();
        LocalDate badDate = LocalDate.EPOCH;
                
        int goodOrderNum = 1;
        int badOrderNum = 666;
        
        // ACT & ASSERT
        try {
            testService.verifyDateAndOrderNum(goodDate, goodOrderNum);
        } catch (FlooringInvalidInputException e) {
            Assertions.fail(
                    "An error should not have been thrown, "
                  + "as the date and order number match up.");
        }
        
        try {
            testService.verifyDateAndOrderNum(badDate, goodOrderNum);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as there are no orders for this date.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
        
        try {
            testService.verifyDateAndOrderNum(goodDate, badOrderNum);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as there is no order with this order number.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
        
        try {
            testService.verifyDateAndOrderNum(badDate, badOrderNum);
            Assertions.fail(
                    "An error should have been thrown, "
                  + "as there are no orders for this date "
                  + "AND there is no order with this order number.");
        } catch (FlooringInvalidInputException e) {
            // This catch statement has been intentionally left blank.
        }
    }
    
    @Test
    public void testMakeNewOrder() {
        // ARRANGE
        String name = "Michael Ross";
        String stateAbbr = "KY";
        String productType = "Wood";
        BigDecimal area = new BigDecimal("1000.000");
        
        // ACT
        Order orderMade = testService.makeNewOrder(name, stateAbbr, productType, area);
        Order orderGot = testService.getOneOrder(1);
        
        // ASSERT
        Assertions.assertEquals(orderMade.getCustomerName(), orderGot.getCustomerName(),
                "The customer names should be the same.");
        Assertions.assertEquals(orderMade.getState(), orderGot.getState(),
                "The states should be the same.");
        Assertions.assertEquals(orderMade.getTaxRate(), orderGot.getTaxRate(),
                "The tax rates should be the same.");
        Assertions.assertEquals(orderMade.getProductType(), orderGot.getProductType(),
                "The poduct types should be the same.");
        Assertions.assertEquals(orderMade.getArea(), orderGot.getArea(),
                "The areas should be the same.");
        Assertions.assertEquals(orderMade.getCostPerSquareFoot(), orderGot.getCostPerSquareFoot(),
                "The costs per square foot should be the same.");
        Assertions.assertEquals(orderMade.getLaborCostPerSquareFoot(), orderGot.getLaborCostPerSquareFoot(),
                "The labor costs per square foot should be the same.");
        Assertions.assertEquals(orderMade.getMaterialCost(), orderGot.getMaterialCost(),
                "The material costs should be the same.");
        Assertions.assertEquals(orderMade.getLaborCost(), orderGot.getLaborCost(),
                "The labor costs should be the same.");
        Assertions.assertEquals(orderMade.getTax(), orderGot.getTax(),
                "The taxes should be the same.");
        Assertions.assertEquals(orderMade.getTotal(), orderGot.getTotal(),
                "The totals should be the same.");
    }
    
}
