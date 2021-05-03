
package com.tsguild.exercises.racer.vehicles;

import com.tsguild.exercises.racer.interfaces.Engine;
import java.math.BigDecimal;

/**
 *
 * @author ahill
 */
public class DragRacer extends Vehicle{
    
    public DragRacer(Engine anEngine) {
        super(anEngine);
        
        this.changeThruput(new BigDecimal(5));
        this.MAX_TANK_SIZE = new BigDecimal("20.00");
        this.DRIVE_EFFICIENCY  = new BigDecimal(".9");
        this.PERCENT_CHANCE_OF_FLAT = 5;
    }
    
    @Override
    public String getShortDescription() {
        return "a fancy "+this.color+" drag racer w/ a " + this.engine.getClass().getSimpleName() + " engine";
    }
    
}
