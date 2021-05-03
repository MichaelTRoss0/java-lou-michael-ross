/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.dvdlibrary.controller;

import com.mtross.dvdlibrary.dao.DVDLibraryDao;
import com.mtross.dvdlibrary.dao.DVDLibraryDaoException;
import com.mtross.dvdlibrary.dto.DVD;
import com.mtross.dvdlibrary.ui.DVDLibraryView;
import java.util.List;

/**
 *
 * @author mike
 */
public class DVDLibraryController {
	
	DVDLibraryDao dao;
	DVDLibraryView view;
	
	public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
		this.dao = dao;
		this.view = view;
	}
	
	// TODO: Add an option to edit a DVD
	
	public void run() {
		boolean keepGoing = true;
		int menuSelection = 0;
		String dvdTitle = "";
		int editSelection = 0;
		try {
			
			openLibrary();
			while (keepGoing) {

				menuSelection = getMenuSelection();

				switch (menuSelection) {
					case 1:
						listDVDs();
						break;
					case 2:
						createDVD();
						break;
					case 3:
						viewDVD();
						break;
					case 4:
						removeDVD();
						break;
					case 5:
						dvdTitle = getDVDTitle();
						editSelection = getEditSelection(dvdTitle);
						
						switch (editSelection) {
							case 1:
								editDVDReleaseDate(dvdTitle);
								break;
							case 2:
								editDVDRating(dvdTitle);
								break;
							case 3:
								editDVDDirector(dvdTitle);
								break;
							case 4:
								editDVDStudio(dvdTitle);
								break;
							case 5:
								editDVDNote(dvdTitle);
								break;
							case 6:
								break;
							default:
								unknownCommand();
						}
                        break;
						
					case 6:
						keepGoing = false;
						break;
					default:
						unknownCommand();
				}

			}
			closeLibrary();
			exitMessage();
		} catch (DVDLibraryDaoException e) {
			view.displayErrorMessage(e.getMessage());
		}
		
	}	// End of run()
	
	private int getMenuSelection() {
		return view.printMenuAndGetSelection();
	}
	
	private void createDVD() throws DVDLibraryDaoException {
		view.displayCreateDVDBanner();
		DVD newDVD = view.getNewDVDInfo();
		dao.addDVD(newDVD.getTitle(), newDVD);
		view.displayCreateSuccessBanner();
	}
	
	private void listDVDs() throws DVDLibraryDaoException {
		view.displayDisplayAllBanner();
		List<DVD> dvdList = dao.getAllDVDs();
		view.displayDVDList(dvdList);
	}
	
	private void viewDVD() throws DVDLibraryDaoException {
		view.displayDisplayDVDBanner();
		String title = view.getDVDTitleChoice();
		DVD dvd = dao.getDVD(title);
		view.displayDVD(dvd);
	}
	
	private void removeDVD() throws DVDLibraryDaoException {
		view.displayRemoveDVDBanner();
		String title = view.getDVDTitleChoice();
		dao.removeDVD(title);
		view.displayRemoveSuccessBanner();
	}
	
	private void unknownCommand() {
		view.displayUnknownCommandBanner();
	}
	
	private void exitMessage() {
		view.displayExitBanner();
	}
	
	private void openLibrary() throws DVDLibraryDaoException {
		view.displayOpeningLibraryBanner();
		dao.retrieveLibrary();
		view.displayLibraryOpenedBanner();
	}
	
	private void closeLibrary() throws DVDLibraryDaoException {
		view.displayClosingLibraryBanner();
		dao.saveLibrary();
		view.displayLibraryClosedBanner();
	}
		
	private String getDVDTitle() {
		view.displayEditDVDBanner();
		return view.getDVDTitleChoice();
	}
	
	private int getEditSelection(String title) throws DVDLibraryDaoException {
		DVD dvd = dao.getDVD(title);
		return view.printEditMenuAndGetEditSelection(dvd);
	}
	
	private void editDVDReleaseDate(String title) throws DVDLibraryDaoException {
		view.displayEditReleaseDateBanner();
		String newReleaseDate = view.getNewReleaseDate();
		dao.editReleaseDate(title, newReleaseDate);
        view.displayEditSuccessBanner();
	}

	private void editDVDRating(String title) throws DVDLibraryDaoException {
		view.displayEditRatingBanner();
		String newRating  = view.getNewRating();
		dao.editRating(title, newRating);
        view.displayEditSuccessBanner();
	}

	private void editDVDDirector(String title) throws DVDLibraryDaoException {
		view.displayEditDirectorBanner();
		String newDirector  = view.getNewDirector();
		dao.editDirector(title, newDirector);
        view.displayEditSuccessBanner();
	}

	private void editDVDStudio(String title) throws DVDLibraryDaoException {
		view.displayEditStudioBanner();
		String newStudio  = view.getNewStudio();
		dao.editStudio(title, newStudio);
        view.displayEditSuccessBanner();
	}

	private void editDVDNote(String title) throws DVDLibraryDaoException {
		view.displayEditNoteBanner();
		String newNote  = view.getNewNote();
		dao.editNote(title, newNote);
        view.displayEditSuccessBanner();
	}
	
}
