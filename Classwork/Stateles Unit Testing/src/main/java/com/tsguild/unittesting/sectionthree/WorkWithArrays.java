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
public class WorkWithArrays {
    
    /* 
    ** Given an array of ints, find and return the maximum value.
    **
    ** Ex:
    ** maxOfArray( {1}  ) ->  1
    ** maxOfArray( {3,4,5}  ) ->  5
    ** maxOfArray( {-9000, -700, -50, -3}  ) ->  -3
    */
    public static int maxOfArray(int[] numbers){
        throw new UnsupportedOperationException("Code not yet written...!");
    }
    
    /* 
    ** Given a integer and an array of ints, times each number in the array by the multiplier.
    **
    ** Ex:
    ** multiplyAll( 5 , [ 1 , 2 , 3 , 4 , 5 ] ) ->  [ 5 , 10 , 15 , 20 , 25 ]
    ** multiplyAll( 0 , [ 1 , 1 , 1 , 1 , 1  , 1 , 1 , 1 , 1 ] ) ->  [ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ]
    ** multiplyAll( -1 , [ -2 , 0 , 0 , 1 ] ) ->  [ 2 , 0 , 0 , -1 ]
    */
    public static int[] multiplyAll(int multiplier, int[] numbers){
        throw new UnsupportedOperationException("Code not yet written...!");
    }

    /* 
    ** Given an array of integers, return them all as a single continuous text value.
    **
    ** Ex:
    ** stringThemTogether( { 1, 2, 3, 7 }  ) ->  "1337"
    ** stringThemTogether( { 1, 33, 555, 7777, 99999 } ) ->  "133555777799999"
    ** stringThemTogether( { }  ) ->  ""
    */
    public String stringThemTogether(int[] nums){
        throw new UnsupportedOperationException("Code not yet written...!");
    }
    
    /* 
    ** Given an array of doubles, return the biggest number of the lot ... as if the decimal had gone missing!
    **
    ** Ex:
    ** pointFree( [1.1, .22]  ) ->  11
    ** pointFree( [ .039 , 20 , .005005 ]  ) ->  5005
    ** pointFree( [ -9.9 , -700 , -.5  ]  ) ->  -5
    */
    public static int pointFree(double[] numbers){
        throw new UnsupportedOperationException("Code not yet written...!");
    }
    
    /* 
    ** Given an array of words turn it into a single camelCased phrase. 
    ** Lower case the first word, capitalize the first letter (but only the first) of the rest.
    **
    ** Ex:
    ** camelCaseIt( {"llama", "llama", "duck"}  ) ->  "llamaLlamaDuck"
    ** camelCaseIt( {"lambs", "eat", "oats", "and", "does", "eat", "oats"}  ) ->  "lambsEatOatsAndDoesEatOats"
    ** camelCaseIt( {"DO", "OR", "DO", "NOT", "THERE", "IS", "NO", "TRY"}  ) ->  "doOrDoNotThereIsNoTry"
    */
    public static String camelCaseIt(String[] words){
        throw new UnsupportedOperationException("Code not yet written...!");
    }
    
}
