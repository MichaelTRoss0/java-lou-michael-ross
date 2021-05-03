/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.unittesting.sectionthree;

import static com.tsguild.unittesting.sectionthree.ExampleWork.areTheLlamasHappy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author ahill
 */
public class HappyLlamasTest {
    
    /*  Test Plan:
    **  Normal trampoline tests!
    **  areTheLlamasHappy(false, 20) → false
    **  areTheLlamasHappy(false, 30) → true
    **  areTheLlamasHappy(false, 42) → true
    **  areTheLlamasHappy(false, 24) → true
    **  areTheLlamasHappy(false, 50) → false
    **  areTheLlamasHappy(false, 43) → false
    **  areTheLlamasHappy(false, 23) → false
    **
    **  Ultra-bouncy tests!
    **  areTheLlamasHappy(false, 20) → false
    **  areTheLlamasHappy(true, 30) → true
    **  areTheLlamasHappy(true, 42) → true
    **  areTheLlamasHappy(true, 43) → true
    **  areTheLlamasHappy(true, 60) → true
    **  areTheLlamasHappy(true, 24) → true
    **  areTheLlamasHappy(true, 2)  → false
    */
    
    public HappyLlamasTest() {
    }
    
    @Test
    public void testNormalTrampoline20() {
        // ARRANGE - for simple methods, this means setting up the parameters
        boolean isNasaFabric = false;
        int numTrampolines = 20;
        
        // ACT - for simple methods, this generally means calling the method under test
        // and then capturing its return to assert on
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);
        
        // ASSERT - basically just a conditional that proves the result is what
        // you expect it to be, plus an extra message to display if it doesn't match.
        //
        // There are a wide variety of assert types, here we
        // just want to assert that it returned false. But we could have also used
        // assertEquals and passed in a false value.
        
        assertFalse( result , "20 Llamas w/ Normal Trampolines should be Unhappy!" );
    }
    
    @Test
    public void testNormalTrampoline30() {
    	// For simple methods, you can also get away w/ arranging, acting & asserting
    	// all on a single line. But make sure you add in a failure message.
    	// It'll make test failures MUCH easier to understand.
    	
    	// ARRANGE ACT & ASSERT - w/ Msg
        Assertions.assertTrue( areTheLlamasHappy(false, 30) , "30 Llamas w/ Normal Trampolines should be Happy!" );
    }

    @Test
    public void testNormalTrampoline42() {
        // ARRANGE
        boolean isNasaFabric = false;
        int numTrampolines = 42;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        Assertions.assertTrue(result, "42 Llamas w/ Normal Trampolines should be Happy!" );
    }
    
    @Test
    public void testNormalTrampoline24() {
        // ARRANGE
        boolean isNasaFabric = false;
        int numTrampolines = 24;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        
        Assertions.assertTrue(result, "24 Llamas w/ Normal Trampolines should be Happy!");
    }

    @Test
    public void testNormalTrampoline50() {
        // ARRANGE
        boolean isNasaFabric = false;
        int numTrampolines = 50;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        Assertions.assertFalse(result, "50 Llamas w/ Normal Trampolines should be Unhappy!");
    }

    @Test
    public void testNormalTrampoline43() {
        // ARRANGE
        boolean isNasaFabric = false;
        int numTrampolines = 43;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        Assertions.assertFalse(result, "43 Llamas w/ Normal Trampolines should be Unhappy!");
    }
    
    @Test
    public void testNormalTrampoline23() {
        // ARRANGE
        boolean isNasaFabric = false;
        int numTrampolines = 23;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        Assertions.assertFalse(result, "20 Llamas w/ Normal Trampolines should be Unhappy!");
    }
    
    @Test
    public void testUltraBouncyTrampoline20() {
        // ARRANGE
        boolean isNasaFabric = true;
        int numTrampolines = 20;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        Assertions.assertFalse(result, "20 Llamas w/ UltraBouncy Trampolines should be Unhappy!");
    }
    
    @Test
    public void testUltraBouncyTrampoline30() {
        // ARRANGE
        boolean isNasaFabric = true;
        int numTrampolines = 30;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        Assertions.assertTrue(result, "30 Llamas w/ UltraBouncy Trampolines should be Happy!");
    }

    @Test
    public void testUltraBouncyTrampoline42() {
        // ARRANGE
        boolean isNasaFabric = true;
        int numTrampolines = 42;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        Assertions.assertTrue(result, "42 Llamas w/ UltraBouncy Trampolines should be Happy!");
    }
    
    @Test
    public void testUltraBouncyTrampoline43() {
        // ARRANGE
        boolean isNasaFabric = true;
        int numTrampolines = 43;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        Assertions.assertTrue(result, "43 Llamas w/ UltraBouncy Trampolines should be Happy!");
    }

    @Test
    public void testUltraBouncyTrampoline60() {
        // ARRANGE
        boolean isNasaFabric = true;
        int numTrampolines = 60;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        Assertions.assertTrue(result, "60 Llamas w/ UltraBouncy Trampolines should be Happy!");
    }
    
    @Test
    public void testUltraBouncyTrampoline24() {
        // ARRANGE
        boolean isNasaFabric = true;
        int numTrampolines = 24;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        Assertions.assertTrue(result, "24 Llamas w/ UltraBouncy Trampolines should be Happy!");
    }

    @Test
    public void testUltraBouncyTrampoline2() {
        // ARRANGE
        boolean isNasaFabric = true;
        int numTrampolines = 2;
        
        // ACT 
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);        
        
        // ASSERT
        Assertions.assertFalse( result, "2 Llamas w/ UltraBouncy Trampolines should be Unhappy!");
    }
    
}
