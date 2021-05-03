/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.helpers;

import com.tsguild.exercises.racer.interfaces.RaceAnnouncer;
import com.tsguild.exercises.racer.interfaces.Driveable;
import com.tsguild.exercises.racer.vehicles.exceptions.VehicleException;
import java.math.BigDecimal;

/**
 *
 * @author ahill
 */
public class MumbleBot implements RaceAnnouncer{

    @Override
    public void announceNonRace() {
        System.out.println("Mmm. MMmmf.");
    }

    @Override
    public void announceEmptyRace() {
        System.out.println("Mmmmfmfmf....");
    }
    
    @Override
    public void announceBreakdown(VehicleException boom, Driveable aRacer) {
        System.out.println("Mummmblemumblemumble");
    }

    @Override
    public void announceDriverPlacement(int place, Driveable aRacer) {
        System.out.println("mmmmmmmMmmmmMMmMmMMM");
    }

    @Override
    public void announceNoMechanic(Driveable aRacer) {
        System.out.println("...");
    }

    @Override
    public void announceRace() {
        System.out.println("MUMMBLEMUMBLE");
    }

    @Override
    public void announceRacerBeginRound(Driveable aRacer) {
        System.out.println("mmmmmmmumblem...");
    }

    @Override
    public void announceRacerJoin(Driveable aRacer) {
        System.out.println("...");
    }

    @Override
    public void announceRacerOdometer(Driveable aRacer) {
        System.out.println("mmmm");
    }

    @Override
    public void announceRacerProgress(Driveable aRacer, BigDecimal milesTraveled) {
        System.out.println("Mummumblem");
    }

    @Override
    public void announceRoundEnd(int round) {
        System.out.println("hrmrmrmmrmrhfff");
    }

    @Override
    public void announceRoundStart(int round) {
        System.out.println("Mummmblemumblemumble");
    }

    @Override
    public void announceWinner(Driveable aRacer) {
        System.out.println("mmmmmmmMmmmmMMmMmMMM");
    }

    @Override
    public void announceSubstituteRacer() {
        System.out.println("Mmmf. MmmmMmmmmmmm.");
        System.out.println("Mmf. Mmf. Mmf.");
    }
    
    @Override
    public void askToContinue(){
        System.out.println("MMf?");
    }
    
    @Override
    public void loadTheStartingLine(){
        System.out.println("");
        System.out.println("MF! MMMMFMmMFMmMFM!! MMF!");
    }
}
