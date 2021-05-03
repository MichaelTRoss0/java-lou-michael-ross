/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.statecapitals;

import java.util.HashMap;
import java.util.Set;

import com.mtross.view.UserIO;
import com.mtross.view.UserIOImpl;

/**
 *
 * @author mike
 */
public class StateCapitals2 {
	public static void main(String[] args) {
		HashMap<String, Capital> stateCapitals = new HashMap<>();
		UserIO io = new UserIOImpl();
		int lowerLimit;
		
		stateCapitals.put("Alabama", new Capital("Montgomery", 373903, 159.8));
		stateCapitals.put("Alaska", new Capital("Juneau", 31275, 2716.7));
		stateCapitals.put("Arizona", new Capital("Phoenix", 1660272, 517.6));
		stateCapitals.put("Arkansas", new Capital("Little Rock", 193524, 116.2));
		stateCapitals.put("California", new Capital("Sacramento", 508529, 97.9));
		stateCapitals.put("Colorado", new Capital("Denver", 716492, 153.3));
		stateCapitals.put("Connecticut", new Capital("Hartford", 124775, 17.3));
		stateCapitals.put("Deleware", new Capital("Dover", 36047, 22.4));
		stateCapitals.put("Florida", new Capital("Florida", 181376, 95.7));
		stateCapitals.put("Georgia", new Capital("Atlanta", 498044, 133.5));
		
		stateCapitals.put("Hawaii", new Capital("Honolulu", 359870, 68.4));
		stateCapitals.put("Idaho", new Capital("Boise", 205671, 63.8));
		stateCapitals.put("Illinois", new Capital("Springfield", 116250, 54.0));
		stateCapitals.put("Indiana", new Capital("Indianapolis", 867125, 361.5));
		stateCapitals.put("Iowa", new Capital("Des Moines", 203433, 75.8));
		stateCapitals.put("Kansas", new Capital("Topeka", 127473, 56.0));
		stateCapitals.put("Kentucky", new Capital("Frankfort", 25527, 14.7));
		stateCapitals.put("Louisiana", new Capital("Baton Rouge", 225374, 76.8));
		stateCapitals.put("Maine", new Capital("Augusta", 19136, 55.4));
		stateCapitals.put("Maryland", new Capital("Annapolis", 38394, 6.73));
		
		stateCapitals.put("Massachussetts", new Capital("Boston", 694583, 89.6));
		stateCapitals.put("Michigan", new Capital("Lansing", 114297, 35.0));
		stateCapitals.put("Minnesota", new Capital("St. Paul", 285068, 52.8));
		stateCapitals.put("Mississippi", new Capital("Jackson", 173514, 104.9));
		stateCapitals.put("Missouri", new Capital("Jefferson City", 43079, 27.3));
		stateCapitals.put("Montana", new Capital("Helena", 28190, 14.0));
		stateCapitals.put("Nebraska", new Capital("Lincoln", 258379, 74.6));
		stateCapitals.put("Nevada", new Capital("Carson City", 55274, 143.4));
		stateCapitals.put("New Hampshire", new Capital("Concord", 42695, 64.3));
		stateCapitals.put("New Jersey", new Capital("Trenton", 84913, 7.66));
		
		stateCapitals.put("New Mexico", new Capital("Santa Fe", 75764, 37.3));
		stateCapitals.put("New York", new Capital("Albany", 97856, 21.4));
		stateCapitals.put("North Carolina", new Capital("Raleigh", 403892, 114.6));
		stateCapitals.put("North Dakota", new Capital("Bismarck", 61272, 26.9));
		stateCapitals.put("Ohio", new Capital("Columbus", 892553, 210.3));
		stateCapitals.put("Oklahoma", new Capital("Oklahoma City", 649021, 620.3));
		stateCapitals.put("Oregon", new Capital("Salem", 154637, 45.7));
		stateCapitals.put("Pennsylvania", new Capital("Harrisburg", 49528, 8.11));
		stateCapitals.put("Rhode Island", new Capital("Providence", 178042, 18.5));
		stateCapitals.put("South Carolina", new Capital("Columbia", 129272, 125.2));
		
		stateCapitals.put("South Dakota", new Capital("Pierre", 13646, 13.0));
		stateCapitals.put("Tennessee", new Capital("Nashville", 691243, 525.9));
		stateCapitals.put("Texas", new Capital("Austin", 964254, 305.1));
		stateCapitals.put("Utah", new Capital("Salt Lake City", 186440, 109.1));
		stateCapitals.put("Vermont", new Capital("Montpelier", 7855, 10.2));
		stateCapitals.put("Virginia", new Capital("Richmond", 204214, 60.1));
		stateCapitals.put("Washington", new Capital("Olympia", 46478, 16.7));
		stateCapitals.put("West Virginia", new Capital("Charleston", 51400, 31.6));
		stateCapitals.put("Wisconsin", new Capital("Madison", 233209, 31.6));
		stateCapitals.put("Wyoming", new Capital("Cheyenne", 59466, 31.6));
		
		Set<String> states = stateCapitals.keySet();
		
		System.out.println("STATE/CAPITAL PAIRS:");
		System.out.println("====================");
		for (String s : states) {
			System.out.println(s + " - " + stateCapitals.get(s).getName()
				+ " | Pop: " + stateCapitals.get(s).getPopulation()
				+ " | Area: " + stateCapitals.get(s).getSquareMileage() + " sq mi");
		}
		
		System.out.println();
		
		lowerLimit = io.readInt("Please enter the lower limit for capital city population:");
		
		System.out.println();
		
		System.out.println("LISTING CAPITALS WITH POPULATIONS GREATER THAN " + lowerLimit + ":\n");
		for (String s : states) {
			if (stateCapitals.get(s).getPopulation() >= lowerLimit) {
				System.out.println(s + " - " + stateCapitals.get(s).getName()
					+ " | Pop: " + stateCapitals.get(s).getPopulation()
					+ " | Area: " + stateCapitals.get(s).getSquareMileage() + " sq mi");
			}
		}
		
	}	// End of the main method
}
