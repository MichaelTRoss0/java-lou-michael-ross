/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.interfaces;
import com.tsguild.exercises.racer.engines.exceptions.EngineException;
import java.math.BigDecimal;

/**
 *
 * @author ahill
 */
public interface Engine {
    
    public BigDecimal generateKWPower(BigDecimal gallonsOfGas) throws EngineException;;
    
}
