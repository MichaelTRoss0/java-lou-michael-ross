/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.classroster;

import com.mtross.classroster.controller.ClassRosterController;
import com.mtross.classroster.dao.ClassRosterDao;
import com.mtross.classroster.dao.ClassRosterDaoFileImpl;
import com.mtross.classroster.ui.ClassRosterView;
import com.mtross.classroster.ui.UserIO;
import com.mtross.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author mike
 */
public class App {
	
	public static void main(String[] args) {
		UserIO myIo = new UserIOConsoleImpl();
		ClassRosterView myView = new ClassRosterView(myIo);
		ClassRosterDao myDao = new ClassRosterDaoFileImpl();
		ClassRosterController controller = new ClassRosterController(myDao, myView);
		
		controller.run();
	}
}
