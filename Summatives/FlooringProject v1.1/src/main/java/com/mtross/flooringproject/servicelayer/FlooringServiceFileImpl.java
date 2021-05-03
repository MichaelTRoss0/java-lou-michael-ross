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
    public List<Order> getOrdersByDate(LocalDate date) throws FlooringInvalidInputException {
        List<Order> datedOrders = orderDao.getDatedOrders(date);
        
        if (datedOrders.isEmpty()) {
            throw new FlooringInvalidInputException();
        }
        
        return datedOrders;
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
    public Order addOrder(Order newOrder, LocalDate date) {
        return orderDao.addAnOrder(newOrder, date);
    }

    @Override
    public Order editOrder(int orderNum, Order newOrder) {
        return orderDao.updateAnOrder(orderNum, newOrder);
    }

    @Override
    public Order removeOrder(int orderNum) {
        return orderDao.removeAnOrder(orderNum);
    }

    @Override
    public void saveAllChanges() throws FlooringPersistenceException {
        orderDao.saveToAllFiles();
    }

    @Override
    public void verifyDate(LocalDate date) throws FlooringInvalidInputException {
        // Ensures that the given date is in the future.
        // Otherwise it throws an exception.
        if (date.compareTo(LocalDate.now()) <= 0) {
            throw new FlooringInvalidInputException(
                    "The date must be in the future.");
        }
    }

    @Override
    public void verifyName(String name) throws FlooringInvalidInputException {
        // Ensures that the given name is not blank, and that there are no commas in it.
        // Otherwise it throws an exception.
        if (name.isBlank() || name.contains(",")) {
            throw new FlooringInvalidInputException(
                    "The name must not be blank, can only contain [a-z], [0-9], periods and spaces.");
        }
    }

    @Override
    public String verifyState(String state) throws FlooringInvalidInputException {
        // Ensures that the given String is either a State's abbreviation or name, then returns the abbreviation.
        // Otherwise it throws an exception.
        List<Taxes> allTaxes = getEveryStateTax();
        String currentStateAbbr;
        String currentStateName;
        
        for (Taxes currentTaxes : allTaxes) {
            currentStateAbbr = currentTaxes.getStateAbbreviation();
            currentStateName = currentTaxes.getStateName();
            
            if (state.equals(currentStateAbbr) || state.equals(currentStateName)) {
                return currentStateAbbr;
            }
        }
        
        throw new FlooringInvalidInputException(
                "There was no match between the input given and the States on file.");
    }

    @Override
    public void verifyProduct(String productType, List<Product> products) throws FlooringInvalidInputException {
        // Ensures that the given product is on the product list.
        // Otherwise it throws an exception.
        boolean noMatches = true;
        for (Product currentProduct : products) {
            if (productType.equals(currentProduct.getProductType())) {
                noMatches = false;
            }
        }
        
        if (noMatches) {
            throw new FlooringInvalidInputException(
                    "There was no match between the input given and the products on file.");
        }
    }

    @Override
    public void verifyArea(BigDecimal area) throws FlooringInvalidInputException {
        // Ensures that the given area is at least 100 sq ft.
        // Otherwise it throws an exception.
        BigDecimal oneHundred = new BigDecimal("100");
        if (area.compareTo(oneHundred) == -1) {
            throw new FlooringInvalidInputException(
                    "The area given was too small.");
        }
    }

    @Override
    public void verifyDateAndOrderNum(LocalDate date, int orderNum) throws FlooringInvalidInputException {
        // Ensures that the given order exists for the given date.
        // Otherwise it throws an exception.
        List<Order> datedOrders = getOrdersByDate(date);
        
        boolean noMatches = true;
        for (Order currentOrder : datedOrders) {
            if (currentOrder.getOrderNumber() == orderNum) {
                noMatches = false;
            }
        }
        
        if (noMatches) {
            throw new FlooringInvalidInputException(
                    "The order number given does not match with any orders on " + date + ".");
        }
    }
    
    private List<Taxes> getEveryStateTax() {
        return taxesDao.getAllTaxes();
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
    
}
