/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.interfaces;

import com.tsguild.exercises.racer.interfaces.Driveable;
import com.tsguild.exercises.racer.vehicles.exceptions.VehicleException;
import java.math.BigDecimal;

/**
 *
 * @author ahill
 */
public interface RaceAnnouncer {
    
    public void announceNonRace();

    public void announceEmptyRace();
    
    public void announceSubstituteRacer();
    
    public void announceBreakdown(VehicleException boom, Driveable aRacer);

    public void announceDriverPlacement(int place, Driveable aRacer);

    public void announceNoMechanic(Driveable aRacer);

    public void announceRace();

    public void announceRacerBeginRound(Driveable aRacer);

    public void announceRacerJoin(Driveable aRacer);

    public void announceRacerOdometer(Driveable aRacer);

    public void announceRacerProgress(Driveable aRacer, BigDecimal milesTraveled);

    public void announceRoundEnd(int round);

    public void announceRoundStart(int round);

    public void announceWinner(Driveable aRacer);
    
    public void askToContinue();
    
    public void loadTheStartingLine();
}
