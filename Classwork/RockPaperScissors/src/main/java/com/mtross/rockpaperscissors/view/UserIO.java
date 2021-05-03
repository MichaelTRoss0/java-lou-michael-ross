/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.rockpaperscissors.view;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author mike
 */
public interface UserIO {
    void print(String msg);
    
    BigDecimal readBigDecimal(String prompt);
    
    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);
	
	double readDouble(String prompt);
	
	double readDouble(String prompt, double min, double max);
	
	float readFloat(String prompt);
	
	float readFloat(String prompt, float min, float max);
	
	int readInt(String prompt);
	
	int readInt(String prompt, int min, int max);
    
    LocalDate readLocalDate(String prompt);
    
    LocalDate readLocalDate(String prompt, LocalDate min, LocalDate max);
	
	long readLong(String prompt);
	
	long readLong(String prompt, long min, long max);
	
	String readString(String prompt);
}
