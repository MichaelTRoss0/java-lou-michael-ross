/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.carlotservice.dao;

import com.mtross.carlotservice.dto.Car;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mike
 */
public class CarLotDAOImpl implements CarLotDAO {
    
    private Map<String, Car> cars;
    
    @Override
    public Car addCar(String VIN, Car car) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Car getCar(String VIN) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Car> getCars() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void editCar(String VIN, Car car) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Car removeCar(String VIN) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
