/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.guessthenumber;

import com.mtross.guessthenumber.controller.GuessTheNumberController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author mike
 */
@SpringBootApplication
public class App {

    @Autowired
    GuessTheNumberController controller;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
