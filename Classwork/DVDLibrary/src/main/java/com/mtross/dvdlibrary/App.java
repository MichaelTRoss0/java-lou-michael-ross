/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.dvdlibrary;

import com.mtross.dvdlibrary.controller.DVDLibraryController;
import com.mtross.dvdlibrary.dao.DVDLibraryDao;
import com.mtross.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.mtross.dvdlibrary.ui.DVDLibraryView;
import com.mtross.dvdlibrary.ui.UserIO;
import com.mtross.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author mike
 */
public class App {
	
	public static void main(String[] args) {
		UserIO myIo = new UserIOConsoleImpl();
		DVDLibraryView myView = new DVDLibraryView(myIo);
		DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
		DVDLibraryController controller = new DVDLibraryController(myDao, myView);
		
		controller.run();
	}
}
