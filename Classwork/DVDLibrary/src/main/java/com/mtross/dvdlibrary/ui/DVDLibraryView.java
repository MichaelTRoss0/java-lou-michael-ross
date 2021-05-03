/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.dvdlibrary.ui;

import com.mtross.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author mike
 */
public class DVDLibraryView {
	
	UserIO io;
	
	public DVDLibraryView(UserIO io) {
		this.io = io;
	}
	
	public int printMenuAndGetSelection() {
		io.print("Main Menu");
		io.print("1. List DVD Titles");
		io.print("2. Add New DVD");
		io.print("3. View a DVD");
		io.print("4. Remove a DVD");
		io.print("5. Edit a DVD");
		io.print("6. Exit");
		
		return io.readInt("Please select from the above choices.", 1, 6);
	}
	
	public DVD getNewDVDInfo() {
		String title = io.readString("Please enter the Title");
		String releaseDate = io.readString("Please enter the Release Date");
		String rating = io.readString("Please enter the MPAA Rating");
		String director = io.readString("Please enter the Director's name");
		String studio = io.readString("Please enter the Studio name");
		String note = io.readString("Please enter any additional information");
		DVD currentDVD = new DVD(title);
		currentDVD.setReleaseDate(releaseDate);
		currentDVD.setRating(rating);
		currentDVD.setDirector(director);
		currentDVD.setStudio(studio);
		currentDVD.setNote(note);
		
		return currentDVD;
	}
	
	public void displayCreateDVDBanner() {
		io.print("=== Create DVD ===");
	}
	
	public void displayCreateSuccessBanner() {
		io.readString("DVD successfully created. Please hit enter to continue");
	}
	
	public void displayDVDList(List<DVD> DVDList) {
		for (DVD currentDVD : DVDList) {
			io.print(currentDVD.getTitle());
		}
		io.readString("Please hit enter to continue.");
	}
	
	public void displayDisplayAllBanner() {
		io.print("=== Display All DVDs ===");
	}
	
	public void displayDisplayDVDBanner() {
		io.print("=== Display DVD ===");
	}
	
	public String getDVDTitleChoice() {
		return io.readString("Please enter the Title.");
	}
	
	public void displayDVD(DVD dvd) {
		if (dvd != null) {
			io.print(dvd.getTitle());
			io.print(dvd.getReleaseDate());
			io.print(dvd.getRating());
			io.print(dvd.getDirector());
			io.print(dvd.getStudio());
			io.print(dvd.getNote());
			io.print("");
		} else {
			io.print("No such DVD");
		}
		io.readString("Please hit enter to continue.");
	}
	
	public void displayRemoveDVDBanner() {
		io.print("=== Remove DVD ===");
	}
	
	public void displayRemoveSuccessBanner() {
		io.readString("DVD Successfully removed. Please hit enter to continue.");
	}
	
	public void displayExitBanner() {
		io.print("Good Bye!!!");
	}
	
	public void displayUnknownCommandBanner() {
		io.print("Unknown Command!!!");
	}
	
	public void displayErrorMessage(String errorMsg) {
		io.print("=== ERROR ===");
	}
	
	public void displayOpeningLibraryBanner() {
		io.print("Retrieving the DVD Library from memory...");
	}
	
	public void displayLibraryOpenedBanner() {
		io.print("The DVD Library has been opened");
	}
	
	public void displayClosingLibraryBanner() {
		io.print("Saving the DVD Library to memory...");
	}
	
	public void displayLibraryClosedBanner() {
		io.print("The DVD Library has been saved");
	}
	
	public void displayEditDVDBanner() {
		io.print("=== Edit DVD ===");
	}

	public int printEditMenuAndGetEditSelection(DVD dvd) {
		if (dvd != null) {
			io.print("Edit Menu");
			io.print("1. Edit Release DAte");
			io.print("2. Edit Rating");
			io.print("3. Edit Director");
			io.print("4. Edit Studio");
			io.print("5. Edit Note");
			io.print("6. Return to Main Menu");
			return io.readInt("Please select from the above choices.", 1, 6);
		} else {
			io.print("No such DVD");
			io.readString("Please hit enter to continue.");
			return 6;
		}
	}

	public void displayEditReleaseDateBanner() {
		io.print("=== Edit Release Date ===");
    }
	
	public String getNewReleaseDate() {
        String newDate = io.readString("Please enter the new Release Date");
        return newDate;
    }

	public void displayEditRatingBanner() {
		io.print("=== Edit Rating ===");
    }
	
	public String getNewRating() {
		String newRating = io.readString("Please enter the new Rating");
        return newRating;
	}

	public void displayEditDirectorBanner() {
		io.print("=== Edit Director ===");
    }
	
	public String getNewDirector() {
		String newDirector = io.readString("Please enter the new Director");
        return newDirector;
    }

	public void displayEditStudioBanner() {
		io.print("=== Edit Studio ===");
    }
	
	public String getNewStudio() {
		String newStudio = io.readString("Please enter the new Studio");
        return newStudio;
    }

	public void displayEditNoteBanner() {
		io.print("=== Edit Note ===");
    }

	public String getNewNote() {
		String newNote = io.readString("What would you like to say about this DVD?");
        return newNote;
	}

    public void displayEditSuccessBanner() {
        io.readString("DVD successfully edited. Please hit enter to continue.");
    }
	
}
