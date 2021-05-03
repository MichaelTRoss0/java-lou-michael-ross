/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.carlotservice.servicelayer;

import com.mtross.carlotservice.dao.CarLotDAO;
import com.mtross.carlotservice.dto.Car;
import com.mtross.carlotservice.dto.CarKey;
import com.mtross.carlotservice.serviceexception.NoSuchCarException;
import com.mtross.carlotservice.serviceexception.OverpaidPriceException;
import com.mtross.carlotservice.serviceexception.UnderpaidPriceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mike
 */
public class CarLotServiceImpl implements CarLotService {
    
    private CarLotDAO dao;
    
    public CarLotServiceImpl(CarLotDAO dao) {
        this.dao = dao;
    }

    // Given a VIN, it should be able to get a single Car.
    @Override
    public Car getACar(String VIN) {
        return dao.getCar(VIN);
    }

    // It should be able to get all the Cars and return them in a List.
    @Override
    public List<Car> getAllCars() {
        return dao.getCars();
    }

    // Given a color, it should be able to return all the available Cars
      // of that color in a List.
    @Override
    public List<Car> getCarsByColor(String color) {
        List<Car> carsByColor = new ArrayList<>();
        List<Car> allCars = dao.getCars();
        
        for (Car car : allCars) {
            if (car.getColor().equals(color)) {
                carsByColor.add(car);
            }
        }
        
        return carsByColor;
    }

    // Given a max Price, it should be able to return all available Cars
      // at or under that price.
    @Override
    public List<Car> getCarsByPrice(BigDecimal maxPrice) {
        List<Car> carsByPrice = new ArrayList<>();
        List<Car> allCars = dao.getCars();
        
        for (Car car : allCars) {
            if(car.getPrice().compareTo(maxPrice) <= 0) {
                allCars.add(car);
            }
        }
        
        return carsByPrice;
    }

    // Given a VIN and a discount amount (i.e. 15%), this method should
      // discount the car's price (updating the official records of that
      // car), and then return the new final price.
    // If there is no car that matches, it should throw a NoSuchCarException
    @Override
    public BigDecimal discountCar(String VIN, BigDecimal discount)
            throws NoSuchCarException {
        
        BigDecimal discountedPrice;
        Car car = dao.getCar(VIN);
        
        if (car == null) {
            throw new NoSuchCarException("No car matches this VIN");
        }
        
        BigDecimal carPrice = car.getPrice();
        BigDecimal multiplicand = BigDecimal.ONE.subtract(discount);
        discountedPrice = carPrice.multiply(multiplicand);
        
        return discountedPrice;
    }

    // Given a VIN and a cash Amount, it should 'buy' - checking if the
      // price matches, removing the car from the lot, and returning the
      // associated CarKey.
    // If there is no car that matches, it should throw a NoSuchCarException
    // If they gave too much money, it should throw an OverpaidPriceException
    // If they gave too little money, it should throw an UnderpaidPriceException
    @Override
    public CarKey sellCar(String VIN, BigDecimal cashPaid)
            throws NoSuchCarException,
            OverpaidPriceException,
            UnderpaidPriceException {
        
        CarKey key = new CarKey();
        Car car = dao.getCar(VIN);
        
        if (car == null) {
            throw new NoSuchCarException("No car matches this VIN");
        }
        
        BigDecimal price = car.getPrice();
        
        if (cashPaid.equals(price)) {
            key = car.getKey();
            dao.removeCar(VIN);
            
        } else if (cashPaid.compareTo(price) == 1) {
            throw new OverpaidPriceException(
                    "The cash paid is greater than the car's price");
            
        } else if (cashPaid.compareTo(price) == -1) {
            throw new UnderpaidPriceException(
                    "The cash paid is less than the car's price");
        }
        
        return key;
    }
    
}
