/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.engines;
import com.tsguild.exercises.racer.engines.exceptions.EngineException;
import com.tsguild.exercises.racer.interfaces.Engine;
import java.math.BigDecimal;

/**
 *
 * @author ahill
 */
public class EfficientEngine implements Engine{

    private static final BigDecimal KW_PER_GALLON = new BigDecimal("150");
    
    @Override
    public BigDecimal generateKWPower(BigDecimal gallonsOfGas) throws EngineException {
        return gallonsOfGas.multiply(KW_PER_GALLON).setScale(0);
    }
    
}
