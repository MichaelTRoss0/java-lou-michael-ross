/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.unittesting.sectionthree;

/**
 *
 * @author ahill
 */
public class WorkWithLogic {

    /* 
    ** This is a shy method. 
    ** 
    ** It only likes to greet friends by name with a hearty hello, 
    ** but strangers just get a simple quiet 'hi'. Given the name of who's visiting, 
    ** and a boolean of whether or not they're a friend, return the proper greeting.
    ** Keep in mind, you greet named visitors, but not the nameless!
    **
    ** Ex:
    ** friendlyGreeting( "Goofus" , false ) ->   "hi"
    ** friendlyGreeting( "Gallant" , true ) ->   "Hello, Gallant!"
    ** friendlyGreeting( null , false ) ->   "..."
     */
    public static String friendlyGreeting(String visitorName, boolean isFriend) {
        throw new UnsupportedOperationException("Code not yet written...!");
    }

    /* 
    ** 
    ** Given a number, return the 'place rank' word associated with it.
    ** I.e. pretend you're ranking folks running in a race from the order they
    ** arrived at the finish line. Assume nonzero, positive inputs! 
    ** Also, start by going up to 100, but stretch to more if you can!
    **
    **
    ** Ex:
    ** placeOf( 1 ) ->   "1st"
    ** placeOf( 3 ) ->   "3rd"
    ** placeOf( 22 ) ->   "22nd"
     */
    public String placeOf(int place) {
        throw new UnsupportedOperationException("Code not yet written...!");
    }

    /* 
    ** Your dog wants to go for a walk - however, there are other things that decide whether or not you're going. 
    ** You only go walking if it's light outside, or if you have a flashlight. 
    ** Also only if it's not raining, or if you have an umbrella. 
    ** And if it's not too hot (more than 95 degrees) and not too cold (less than 50 degrees).
    **
    ** Ex:
    ** goWalky( true, false, true, true, 75  ) ->  false
    ** goWalky( false, true, false, false, 50  ) ->  true
    ** goWalky( false, false, false, false, 30  ) ->  true
     */
    public static boolean goWalky(boolean isDark, boolean haveFlashlight, boolean isRaining, boolean haveUmbrella, int degreesFarenheit) {
        throw new UnsupportedOperationException("Code not yet written...!");
    }

    /* 
    ** Given two characters, return true if the first parameter comes before the
    ** the second parameter in the alphabet. Don't worry about casing, 
    ** just turn consider everything in lowercase.
    **
    ** Ex:
    ** isFirstTheFirst( 'a' , 'b'  ) ->  true
    ** isFirstTheFirst( 'O' , 'X'  ) ->  true
    ** isFirstTheFirst( 'Z' , 'z'   ) -> false
     */
    public static boolean isFirstTheFirst(char letterOne, char letterTwo) {
        throw new UnsupportedOperationException("Code not yet written...!");
    }

    /* 
    ** Given the following chart, return the correct color designation 
    ** given measured wavelength, frequency and photonic energy. 
    ** If it doesn't fall within correct bands, return "Unknown" instead. 
    ** If it falls exactly within a band transition, 
    ** return a compound color, with the longer wavelength color first. 
    ** 
    ** 	Color	Wavelength	Frequency	Photon energy
    ** 	Violet	380–450 nm	668–789 THz	2.75–3.26 eV
    ** 	Blue	450–495 nm	606–668 THz	2.50–2.75 eV
    ** 	Green	495–570 nm	526–606 THz	2.17–2.50 eV
    ** 	Yellow	570–590 nm	508–526 THz	2.10–2.17 eV
    ** 	Orange	590–620 nm	484–508 THz	2.00–2.10 eV
    ** 	Red	620–750 nm	400–484 THz	1.65–2.00 eV
    **
    ** Ex:
    ** whatColor( 575, 510, 2.15 ) ->  "Yellow"
    ** whatColor( 449, 670, 3.00 ) ->  "Violet"
    ** whatColor( 621, 475, 16.5 ) ->  "Unknown"
    ** whatColor( 590, 508, 2.05 ) ->  "Orange"
    ** whatColor( 570, 526, 2.17 ) ->  "Yellow-Green"
     */
    public static String whatColor(int waveLengthNM, int frequencyTHZ, double photonicEnergyEV) {
        throw new UnsupportedOperationException("Code not yet written...!");
    }

}
