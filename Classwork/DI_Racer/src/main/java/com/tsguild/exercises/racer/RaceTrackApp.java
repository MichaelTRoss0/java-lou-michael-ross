/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer;

import com.tsguild.exercises.racer.helpers.RealAnnouncer;
import com.tsguild.exercises.racer.vehicles.DigitalPorsche;
import com.tsguild.exercises.racer.vehicles.PixelTank;
import com.tsguild.exercises.racer.vehicles.JWBeetle;
import com.tsguild.exercises.racer.vehicles.Vehicle;
import com.tsguild.exercises.racer.vehicles.DragRacer;
import com.tsguild.exercises.racer.vehicles.HamsterBall;
import com.tsguild.exercises.racer.interfaces.Driveable;
import com.tsguild.exercises.racer.tracks.Race;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ahill
 */
public class RaceTrackApp {
    
    public static void main(String[] args) {

        ApplicationContext carFactory = new ClassPathXmlApplicationContext("car-factory.xml");
        
        Race theRace = carFactory.getBean(Race.class);
        
        theRace.runRace();

    }

}
