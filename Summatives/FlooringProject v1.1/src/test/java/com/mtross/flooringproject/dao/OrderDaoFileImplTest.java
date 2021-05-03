/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.dao;

import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mike
 */
public class OrderDaoFileImplTest {
    
    OrderDao testDao;
    
    public OrderDaoFileImplTest() {
        testDao = new OrderDaoFileImpl("Test/TestOrders_MMddyyyy.txt");
    }

    @Test
    public void testAddGetAnOrder() {
        // ARRANGE
        Order firstOrder = new Order(0);
        firstOrder.setCustomerName("Ada Lovelace");
        firstOrder.setState("CA");
        firstOrder.setTaxRate(new BigDecimal("25.00"));
        firstOrder.setProductType("Tile");
        firstOrder.setArea(new BigDecimal("249.00"));
        firstOrder.setCostPerSquareFoot(new BigDecimal("3.50"));
        firstOrder.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        firstOrder.setMaterialCost(new BigDecimal("871.50"));
        firstOrder.setLaborCost(new BigDecimal("1033.35"));
        firstOrder.setTax(new BigDecimal("476.21"));
        firstOrder.setTotal(new BigDecimal("2381.06"));
        
        Order secondOrder = new Order(0);
        secondOrder.setCustomerName("Boris Janson");
        secondOrder.setState("TX");
        secondOrder.setTaxRate(new BigDecimal("2.50"));
        secondOrder.setProductType("Wood");
        secondOrder.setArea(new BigDecimal("2490.00"));
        secondOrder.setCostPerSquareFoot(new BigDecimal("30.50"));
        secondOrder.setLaborCostPerSquareFoot(new BigDecimal("400.15"));
        secondOrder.setMaterialCost(new BigDecimal("8710.50"));
        secondOrder.setLaborCost(new BigDecimal("10330.35"));
        secondOrder.setTax(new BigDecimal("4760.21"));
        secondOrder.setTotal(new BigDecimal("23810.06"));
        
        LocalDate today = LocalDate.now();
        
        // ACT
        Order firstOrderOut = testDao.addAnOrder(firstOrder, today);
        Order secondOrderOut = testDao.addAnOrder(secondOrder, today);
        Order firstTakenOut = testDao.getAnOrder(1);
        Order secondTakenOut = testDao.getAnOrder(2);
        
        // ASSERT
        Assertions.assertEquals(1, firstTakenOut.getOrderNumber(),
                "Order Number should become 1 when put into an empty dao");
        Assertions.assertEquals(2, secondTakenOut.getOrderNumber(),
                "Order Number should become 2 when put into the dao next");
        Assertions.assertEquals(firstTakenOut.getCustomerName(), firstOrder.getCustomerName(),
                "CustomerNames should match");
        Assertions.assertEquals(firstTakenOut.getState(), firstOrder.getState(),
                "States should match");
        Assertions.assertEquals(firstTakenOut.getTaxRate(), firstOrder.getTaxRate(),
                "Tax Rates should match");
        Assertions.assertEquals(firstTakenOut.getProductType(), firstOrder.getProductType(),
                "Product Types should match");
        Assertions.assertEquals(firstTakenOut.getArea(), firstOrder.getArea(),
                "Areas should match");
        Assertions.assertEquals(firstTakenOut.getCostPerSquareFoot(), firstOrder.getCostPerSquareFoot(),
                "Costs Per Square Foot should match");
        Assertions.assertEquals(firstTakenOut.getLaborCostPerSquareFoot(), firstOrder.getLaborCostPerSquareFoot(),
                "Labor Costs Per Square Foot should match");
        Assertions.assertEquals(firstTakenOut.getMaterialCost(), firstOrder.getMaterialCost(),
                "Material Costs should match");
        Assertions.assertEquals(firstTakenOut.getLaborCost(), firstOrder.getLaborCost(),
                "LaborCosts should match");
        Assertions.assertEquals(firstTakenOut.getTax(), firstOrder.getTax(),
                "Taxes should match");
        Assertions.assertEquals(firstTakenOut.getTotal(), firstOrder.getTotal(),
                "Totals should match");
        
        Assertions.assertNull(firstOrderOut, "There should not have been an order here");
        Assertions.assertNull(secondOrderOut, "There should not have been an order here");
    }

    @Test
    public void testAddGetAllOrders() {
        // ARRANGE
        Order orderIn1 = new Order(1);
        orderIn1.setCustomerName("Ada Lovelace");
        orderIn1.setState("CA");
        orderIn1.setTaxRate(new BigDecimal("25.00"));
        orderIn1.setProductType("Tile");
        orderIn1.setArea(new BigDecimal("249.00"));
        orderIn1.setCostPerSquareFoot(new BigDecimal("3.50"));
        orderIn1.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        orderIn1.setMaterialCost(new BigDecimal("871.50"));
        orderIn1.setLaborCost(new BigDecimal("1033.35"));
        orderIn1.setTax(new BigDecimal("476.21"));
        orderIn1.setTotal(new BigDecimal("2381.06"));
        
        Order orderIn2 = new Order(2);
        orderIn2.setCustomerName("Boris Janson");
        orderIn2.setState("TX");
        orderIn2.setTaxRate(new BigDecimal("2.50"));
        orderIn2.setProductType("Wood");
        orderIn2.setArea(new BigDecimal("2490.00"));
        orderIn2.setCostPerSquareFoot(new BigDecimal("30.50"));
        orderIn2.setLaborCostPerSquareFoot(new BigDecimal("400.15"));
        orderIn2.setMaterialCost(new BigDecimal("8710.50"));
        orderIn2.setLaborCost(new BigDecimal("10330.35"));
        orderIn2.setTax(new BigDecimal("4760.21"));
        orderIn2.setTotal(new BigDecimal("23810.06"));
        
        LocalDate today = LocalDate.now();
        
        // ACT
        Order firstOrderOut = testDao.addAnOrder(orderIn1, today);
        Order secondOrderOut = testDao.addAnOrder(orderIn2, today);
        
        List<Order> orderList = testDao.getAllOrders();
        
        // ASSERT
        Assertions.assertNotNull(orderList,
                "The list of orders should not be null");
        Assertions.assertEquals(2, orderList.size(),
                "There should be 2 orders in the list");
        Assertions.assertTrue(orderList.contains(orderIn1),
                "Order list should have the first order stored");
        Assertions.assertTrue(orderList.contains(orderIn2),
                "Order list should have the second order stored");
        
        Assertions.assertNull(firstOrderOut,
                "There shouldn't be an order returned when storing in an empty dao");
        Assertions.assertNull(secondOrderOut,
                "There shouldn't be an order returned when storing in the dao");
    }

    @Test
    public void testAddUpdateAnOrder() {
        // ARRANGE
        Order firstOrder = new Order(1);
        firstOrder.setCustomerName("Ada Lovelace");
        firstOrder.setState("CA");
        firstOrder.setTaxRate(new BigDecimal("25.00"));
        firstOrder.setProductType("Tile");
        firstOrder.setArea(new BigDecimal("249.00"));
        firstOrder.setCostPerSquareFoot(new BigDecimal("3.50"));
        firstOrder.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        firstOrder.setMaterialCost(new BigDecimal("871.50"));
        firstOrder.setLaborCost(new BigDecimal("1033.35"));
        firstOrder.setTax(new BigDecimal("476.21"));
        firstOrder.setTotal(new BigDecimal("2381.06"));
        
        Order secondOrder = new Order(1);
        secondOrder.setCustomerName("Boris Janson");
        secondOrder.setState("TX");
        secondOrder.setTaxRate(new BigDecimal("2.50"));
        secondOrder.setProductType("Wood");
        secondOrder.setArea(new BigDecimal("2490.00"));
        secondOrder.setCostPerSquareFoot(new BigDecimal("30.50"));
        secondOrder.setLaborCostPerSquareFoot(new BigDecimal("400.15"));
        secondOrder.setMaterialCost(new BigDecimal("8710.50"));
        secondOrder.setLaborCost(new BigDecimal("10330.35"));
        secondOrder.setTax(new BigDecimal("4760.21"));
        secondOrder.setTotal(new BigDecimal("23810.06"));
        
        LocalDate today = LocalDate.now();
        
        // ACT
        testDao.addAnOrder(firstOrder, today);
        testDao.updateAnOrder(1, secondOrder);
        
        Order overwrittenOrder = testDao.getAnOrder(1);
        List<Order> orderList = testDao.getAllOrders();
        
        // ASSERT
        Assertions.assertFalse(orderList.contains(firstOrder),
                "First order should not be in list");
        Assertions.assertEquals(secondOrder, overwrittenOrder,
                "Order should have been replaced by second");
        Assertions.assertEquals(1, orderList.size(),
                "There should be only one order");
        Assertions.assertTrue(orderList.contains(secondOrder),
                "Only order left should be the second one");
    }

    @Test
    public void testAddRemoveAnOrder() {
        // ARRANGE
        Order orderIn = new Order(1);
        orderIn.setCustomerName("Ada Lovelace");
        orderIn.setState("CA");
        orderIn.setTaxRate(new BigDecimal("25.00"));
        orderIn.setProductType("Tile");
        orderIn.setArea(new BigDecimal("249.00"));
        orderIn.setCostPerSquareFoot(new BigDecimal("3.50"));
        orderIn.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        orderIn.setMaterialCost(new BigDecimal("871.50"));
        orderIn.setLaborCost(new BigDecimal("1033.35"));
        orderIn.setTax(new BigDecimal("476.21"));
        orderIn.setTotal(new BigDecimal("2381.06"));
        
        LocalDate today = LocalDate.now();
        
        // ACT
        testDao.addAnOrder(orderIn, today);
        Order removedOrder = testDao.removeAnOrder(1);
        Order shouldBeNull = testDao.getAnOrder(1);
        
        List<Order> orderList = testDao.getAllOrders();
        
        // ASSERT
        Assertions.assertEquals(orderIn, removedOrder,
                "Stored order and removed order should match");
        Assertions.assertNull(shouldBeNull,
                "Item should no longer be in DAO");
        Assertions.assertEquals(0, orderList.size(),
                "List should have 0 items in it");
    }

    @Test
    public void testEmptyDao() {
        // ARRANGE
        
        // ACT
        List<Order> emptyList = testDao.getAllOrders();
        
        // ASSERT
        Assertions.assertNotNull(emptyList, "Should be an empty list, not null");
        Assertions.assertEquals(0, emptyList.size(), "Should be an empty list");
    }
    
    // In case something happens, this is  what the two test files are supposed to look like:
    
    /*
        TestOrders_06012013.txt:
    
    OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
    1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06
    
    
        TestOrders_06022013.txt:
    
    OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total
    2,Doctor Who,WA,9.25,Wood,243.00,5.15,4.75,1251.45,1154.25,216.51,2622.21
    3,Albert Einstein,KY,6.00,Carpet,217.00,2.25,2.10,488.25,455.70,56.64,1000.59
    
    */

    @Test
    public void testLoadFromFilesAndGetOrder() throws FlooringPersistenceException {
        // ARRANGE
        Order order1 = new Order(1, "Ada Lovelace" ,"CA",
                new BigDecimal("25.00"), "Tile" , new BigDecimal("249.00"),
                new BigDecimal("3.50"), new BigDecimal("4.15"), new BigDecimal("871.50"),
                new BigDecimal("1033.35"), new BigDecimal("476.21"), new BigDecimal("2381.06"));
        
        Order order2 = new Order(2, "Doctor Who" ,"WA" ,
                new BigDecimal("9.25"), "Wood" , new BigDecimal("243.00"),
                new BigDecimal("5.15") , new BigDecimal("4.75") , new BigDecimal("1251.45"),
                new BigDecimal("1154.25"), new BigDecimal("216.51"), new BigDecimal("2622.21"));
        
        Order order3 = new Order(3, "Albert Einstein", "KY",
                new BigDecimal("6.00"), "Carpet" , new BigDecimal("217.00"),
                new BigDecimal("2.25"), new BigDecimal("2.10"), new BigDecimal("488.25"),
                new BigDecimal("455.70"), new BigDecimal("56.64"), new BigDecimal("1000.59"));
        
        // ACT
        testDao.loadFromFiles();
        
        List<Order> loadedList = testDao.getAllOrders();
        
        // ASSERT
        Assertions.assertEquals(3, loadedList.size(),
                "Loaded list should have 3 items");
        Assertions.assertTrue(loadedList.contains(order1),
                "First order should be in the loaded list");
        Assertions.assertTrue(loadedList.contains(order2),
                "Second order should be in the loaded list");
        Assertions.assertTrue(loadedList.contains(order3),
                "Third order should be in the loaded list");
    }

    @Test
    public void testAddSaveLoadAndGet() throws FlooringPersistenceException {
        // ARRANGE
        Order order1 = new Order(1, "Ada Lovelace" ,"CA",
                new BigDecimal("25.00"), "Tile" , new BigDecimal("249.00"),
                new BigDecimal("3.50"), new BigDecimal("4.15"), new BigDecimal("871.50"),
                new BigDecimal("1033.35"), new BigDecimal("476.21"), new BigDecimal("2381.06"));
        
        Order order2 = new Order(2, "Doctor Who" ,"WA" ,
                new BigDecimal("9.25"), "Wood" , new BigDecimal("243.00"),
                new BigDecimal("5.15") , new BigDecimal("4.75") , new BigDecimal("1251.45"),
                new BigDecimal("1154.25"), new BigDecimal("216.51"), new BigDecimal("2622.21"));
        
        Order order3 = new Order(3, "Albert Einstein", "KY",
                new BigDecimal("6.00"), "Carpet" , new BigDecimal("217.00"),
                new BigDecimal("2.25"), new BigDecimal("2.10"), new BigDecimal("488.25"),
                new BigDecimal("455.70"), new BigDecimal("56.64"), new BigDecimal("1000.59"));
        
        LocalDate fileDate1 = LocalDate.parse("2013-06-01");
        LocalDate fileDate2 = LocalDate.parse("2013-06-02");
        
        // ACT
        testDao.addAnOrder(order1, fileDate1);
        testDao.addAnOrder(order2, fileDate2);
        testDao.addAnOrder(order3, fileDate2);
        
        testDao.saveToAllFiles();
        testDao.loadFromFiles();
        
        List<Order> afterSAL = testDao.getAllOrders();
        
        // ASSERT
        Assertions.assertEquals(3, afterSAL.size(),
                "List after save & load should have 3 orders");
        Assertions.assertTrue(afterSAL.contains(order1),
                "First order should be in the list after save & load");
        Assertions.assertTrue(afterSAL.contains(order2),
                "Second order should be in the list after save & load");
        Assertions.assertTrue(afterSAL.contains(order3),
                "Third order should be in the list after save & load");
    }
    
}
