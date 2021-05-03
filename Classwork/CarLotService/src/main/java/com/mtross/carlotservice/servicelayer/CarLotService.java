/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.carlotservice.servicelayer;

import com.mtross.carlotservice.serviceexception.OverpaidPriceException;
import com.mtross.carlotservice.serviceexception.UnderpaidPriceException;
import com.mtross.carlotservice.serviceexception.NoSuchCarException;
import com.mtross.carlotservice.dto.Car;
import com.mtross.carlotservice.dto.CarKey;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mike
 */
public interface CarLotService {
    public Car getACar(String VIN);    
    public List<Car> getAllCars();
    public List<Car> getCarsByColor(String color);
    public List<Car> getCarsByPrice(BigDecimal maxPrice);    

    public BigDecimal discountCar(String VIN, BigDecimal discount)
        throws NoSuchCarException;

    public CarKey sellCar(String VIN, BigDecimal cashPaid)
        throws NoSuchCarException,
        OverpaidPriceException, 
        UnderpaidPriceException;
}
