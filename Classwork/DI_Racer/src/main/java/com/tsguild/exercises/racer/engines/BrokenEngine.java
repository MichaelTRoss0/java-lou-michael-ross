/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.engines;
import com.tsguild.exercises.racer.engines.exceptions.EngineException;
import com.tsguild.exercises.racer.engines.exceptions.WonkyPistonException;
import com.tsguild.exercises.racer.interfaces.Engine;
import java.math.BigDecimal;

/**
 *
 * @author ahill
 */
public class BrokenEngine implements Engine{

    @Override
    public BigDecimal generateKWPower(BigDecimal gallonsOfGas) throws EngineException {
        throw new WonkyPistonException("Engine has a wonky piston! IT CANNOT GO!");
    }
    
}
