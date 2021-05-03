/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.servicelayer;

import com.mtross.flooringproject.dao.OrderDao;
import com.mtross.flooringproject.dao.ProductDao;
import com.mtross.flooringproject.dao.TaxesDao;
import com.mtross.flooringproject.daoexceptions.FlooringPersistenceException;
import com.mtross.flooringproject.dto.Order;
import com.mtross.flooringproject.dto.Product;
import com.mtross.flooringproject.dto.Taxes;
import com.mtross.flooringproject.servicelayerexceptions.FlooringInvalidInputException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.math.RoundingMode.HALF_UP;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mike
 */
public class FlooringServiceFileImpl implements FlooringService {
    
    private final RoundingMode ROUNDING_MODE = HALF_UP;
    private final int SCALE = 2;
    
    private OrderDao orderDao;
    private TaxesDao taxesDao;
    private ProductDao productDao;

    public FlooringServiceFileImpl() {
    }

    public FlooringServiceFileImpl(OrderDao orderDao, TaxesDao taxesDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.taxesDao = taxesDao;
        this.productDao = productDao;
    }

    @Override
    public void loadAllFiles() throws FlooringPersistenceException {
        orderDao.loadFromFiles();
        taxesDao.loadFromFile();
        productDao.loadFromFile();
    }

    @Override
    public List<Order> getEveryOrder() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) {
        return orderDao.getDatedOrders(date);
    }

    @Override
    public Order getOneOrder(int orderNum) {
        return orderDao.getAnOrder(orderNum);
    }
    
    @Override
    public List<Product> getEveryProduct() {
        return productDao.getAllProducts();
    }

    @Override
    public void addOrder(Order newOrder, LocalDate date) {
        orderDao.addAnOrder(newOrder, date);
    }

    @Override
    public void editOrder(int orderNum, Order newOrder) {
        orderDao.updateAnOrder(orderNum, newOrder);
    }

    @Override
    public void removeOrder(int orderNum) {
        orderDao.removeAnOrder(orderNum);
    }

    @Override
    public void saveAllChanges() throws FlooringPersistenceException {
        orderDao.saveToAllFiles();
    }

    @Override
    public void verifyDate() throws FlooringInvalidInputException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void verifyNewOrderDate(LocalDate date) throws FlooringInvalidInputException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void verifyDateAndOrderNum(LocalDate date, int orderNum) throws FlooringInvalidInputException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void verifyName(String name) throws FlooringInvalidInputException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String verifyState(String state) throws FlooringInvalidInputException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void verifyProduct(String productType) throws FlooringInvalidInputException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void verifyArea(BigDecimal area) throws FlooringInvalidInputException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Order makeNewOrder(String name, String stateAbbr, String productType, BigDecimal area) {
        Order newOrder = new Order(0);
        Taxes taxInfo = taxesDao.getATax(stateAbbr);
        Product productInfo = productDao.getAProduct(productType);
        
        BigDecimal taxRate = taxInfo.getTaxRate();
        taxRate.setScale(SCALE, ROUNDING_MODE); 
        BigDecimal costPerSquareFoot = productInfo.getCostPerSquareFoot();
        costPerSquareFoot.setScale(SCALE, ROUNDING_MODE);
        BigDecimal laborCostPerSquareFoot = productInfo.getLaborCostPerSquareFoot();
        laborCostPerSquareFoot.setScale(SCALE, ROUNDING_MODE);
        
        BigDecimal materialCost = area.multiply(costPerSquareFoot);
        materialCost.setScale(SCALE, ROUNDING_MODE);
        
        BigDecimal laborCost = area.multiply(laborCostPerSquareFoot);
        laborCost.setScale(SCALE, ROUNDING_MODE);
        
        BigDecimal beforeTax = materialCost.add(laborCost);
        beforeTax.setScale(SCALE, ROUNDING_MODE);
        BigDecimal oneHundred = new BigDecimal("100");
        BigDecimal taxPercent = taxRate.divide(oneHundred, 4, ROUNDING_MODE);
        BigDecimal tax = beforeTax.multiply(taxPercent);
        tax.setScale(SCALE, ROUNDING_MODE);
        
        BigDecimal total = beforeTax.add(tax);
        
        newOrder.setCustomerName(name);
        newOrder.setState(stateAbbr);
        newOrder.setTaxRate(taxRate);
        newOrder.setProductType(productType);
        newOrder.setArea(area);
        newOrder.setCostPerSquareFoot(costPerSquareFoot);
        newOrder.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        newOrder.setMaterialCost(materialCost);
        newOrder.setLaborCost(laborCost);
        newOrder.setTax(tax);
        newOrder.setTotal(total);
        
        return newOrder;
    }
    
    private String changeNameToAbbr(String stateName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private Taxes getStateTax(String stateAbbr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private List<Taxes> getEveryStateTax() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private Product getProduct(String productType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
