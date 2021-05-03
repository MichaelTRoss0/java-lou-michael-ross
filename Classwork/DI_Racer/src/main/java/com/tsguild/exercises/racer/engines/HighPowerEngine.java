/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.engines;
import com.tsguild.exercises.racer.engines.exceptions.EngineException;
import com.tsguild.exercises.racer.engines.exceptions.EngineOnFireException;
import com.tsguild.exercises.racer.interfaces.Engine;
import com.tsguild.exercises.racer.vehicles.exceptions.FlatTireException;
import java.math.BigDecimal;
import java.util.Random;

/**
 *
 * @author ahill
 */
public class HighPowerEngine implements Engine{

    private static final BigDecimal KW_PER_GALLON = new BigDecimal("150");
    private static final BigDecimal MIN_KW = new BigDecimal("25");
    private static final int PERCENT_CHANCE_OF_CATCHING_FIRE = 5;
    protected Random r = new Random();
    
    @Override
    public BigDecimal generateKWPower(BigDecimal gallonsOfGas) throws EngineException {
        this.possiblyCatchingFire();
        return gallonsOfGas.multiply(KW_PER_GALLON).max(MIN_KW);
    }
    
    private void possiblyCatchingFire() throws EngineOnFireException{
        int diceRoll = r.nextInt(100);
        if(diceRoll < PERCENT_CHANCE_OF_CATCHING_FIRE){
            throw new EngineOnFireException("EGADS. IT'S ABLAZE!!!!");
        }
    }
    
}
