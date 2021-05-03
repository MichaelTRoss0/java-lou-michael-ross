/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.dvdlibrary.dao;

import com.mtross.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
	
	public static final String LIBRARY_FILE = "library.txt";
	public static final String DELIMITER = "::";
	
	private Map<String, DVD> dvds = new HashMap<>();

	@Override
	public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
		DVD newDVD = dvds.put(title, dvd);
		return newDVD;
	}

	@Override
	public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
		return new ArrayList<DVD>(dvds.values());
	}

	@Override
	public DVD getDVD(String title) throws DVDLibraryDaoException {
		return dvds.get(title);
	}

	@Override
	public DVD removeDVD(String title) throws DVDLibraryDaoException {
		DVD removedDVD = dvds.remove(title);
		return removedDVD;
	}
	
	@Override
	public Map<String, DVD> retrieveLibrary() throws DVDLibraryDaoException {
		loadLibrary();
		return dvds;
	}
	
	@Override
	public Map<String, DVD> saveLibrary() throws DVDLibraryDaoException {
		writeLibrary();
		return dvds;
	}
	
	@Override
	public String editReleaseDate(String title, String newReleaseDate) throws DVDLibraryDaoException {
		DVD dvd = dvds.get(title);
        String oldReleaseDate = dvd.getReleaseDate();
		dvd.setReleaseDate(newReleaseDate);
		return oldReleaseDate;
	}

	@Override
	public String editRating(String title, String newRating) throws DVDLibraryDaoException {
		DVD dvd = dvds.get(title);
        String oldRating = dvd.getRating();
		dvd.setRating(newRating);
		return oldRating;
	}

	@Override
	public String editDirector(String title, String newDirector) throws DVDLibraryDaoException {
		DVD dvd = dvds.get(title);
        String oldDirector = dvd.getDirector();
		dvd.setDirector(newDirector);
		return oldDirector;
	}

	@Override
	public String editStudio(String title, String newStudio) throws DVDLibraryDaoException {
		DVD dvd = dvds.get(title);
        String oldStudio = dvd.getStudio();
		dvd.setStudio(newStudio);
		return oldStudio;
	}

	@Override
	public String editNote(String title, String newNote) throws DVDLibraryDaoException {
		DVD dvd = dvds.get(title);
        String oldNote = dvd.getNote();
		dvd.setNote(newNote);
		return oldNote;
	}
	
	private DVD unmarshallDVD(String dvdAsText) {
		// studentAsText is expecting a line read in from our file.
		// For example, it might look like this:
		// Bee Movie::11/2/07::PG::Simon J. Smith, Steve Hickner::DreamWorks Animation::A mediocre film that is the basis of multiple memes.
		//
		// We then split that line on our DELIMITER - which we are using as ::
		// Leaving us with an array of Strings, stored in studentTokens.
		// Which should look like this:
		// ______________________________________________________________________________________________________________________________
		// |         |       |  |                             |                    |                                                    |
		// |Bee Movie|11/2/07|PG|Simon J. Smith, Steve Hickner|DreamWorks Animation|A mediocre film that is the basis of multiple memes.|
		// |         |       |  |                             |                    |                                                    |
		// ------------------------------------------------------------------------------------------------------------------------------
		//     [0]      [1]  [2]           [3]                         [4]                                   [5]
		String[] dvdTokens = dvdAsText.split(DELIMITER);
		
		// Given the pattern above, the title is in index 0 of the array.
		String title = dvdTokens[0];
		
		// Which we can then use to create a new Student object to satisfy
		// the requirements of the Student constructor.
		DVD dvdFromFile = new DVD(title);
		
		// However, there are 5 remaining tokens that need to be set into the
		// new student object. Do this manually by using the appropriate setters.
		
		// Index 1 - ReleaseDate
		dvdFromFile.setReleaseDate(dvdTokens[1]);
		
		// Index 2 - Rating
		dvdFromFile.setRating(dvdTokens[2]);
		
		// Index 3 - Director
		dvdFromFile.setDirector(dvdTokens[3]);
		
		// Index 4 - Studio
		dvdFromFile.setStudio(dvdTokens[4]);
		
		// Index 5 - Note
		dvdFromFile.setNote(dvdTokens[5]);
		
		// We have created a new DVD! Return it!
		return dvdFromFile;
	}
	
	private void loadLibrary() throws DVDLibraryDaoException {
		Scanner scanner;
		
		try {
			// Create Scanner for reading the file
			scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
		} catch (FileNotFoundException e) {
			throw new DVDLibraryDaoException("\"-_- Could not load roster data into memory.", e);
		}
		// currentLine holds the most recent line read from the file
		String currentLine;
		// currentDVD holds the most recent DVD unmarshalled
		DVD currentDVD;
		// Go through ROSTER_FILE line by line, decoding each line into a 
		// Student object by calling the unmarshallStudent method.
		// Process while we have more lines in the file
		while(scanner.hasNextLine()) {
			// get the next line in the file
			currentLine = scanner.nextLine();
			// unmarshall the line into a DVD
			currentDVD = unmarshallDVD(currentLine);
			
			// We are going to use the title as the map key for our DVD object.
			// Put currentDVD into the map using title as the key
			dvds.put(currentDVD.getTitle(), currentDVD);
		}
		// close scanner
		scanner.close();
	}
	
	private String marshallDVD(DVD aDVD) {
		// We need to turn a DVD object into a line of text for our file.
		// For example, we need an in memory object to end up like this:
		// Shrek::4/22/01::PG::Andrew Adamson, Vickey Jenson::DreamWorks Animation::A modern classic.
		
		// It's not a complicated process. Just get out each property,
		// and concatenate with our DELIMITER as a kind of spacer.
		
		// Start with the student id, since that's supposed to be first.
		String dvdAsText = aDVD.getTitle() + DELIMITER;
		
		// add the rest of the properties in the correct order:
		
		// ReleaseDate
		dvdAsText += aDVD.getReleaseDate() + DELIMITER;
		
		// Rating
		dvdAsText += aDVD.getRating() + DELIMITER;
		
		// Director
		dvdAsText += aDVD.getDirector() + DELIMITER;
		
		// Studio
		dvdAsText += aDVD.getStudio() + DELIMITER;
		
		// Note - don't forget to skip the DELIMITER here.
		dvdAsText += aDVD.getNote();
		
		// We have now turned a DVD to text! Return it!
		return dvdAsText;
	}
	
	/**
	 * Writes all DVDs in the roster out to a Library_FILE.  See loadLibrary
	 * for file format.
	 * 
	 * @throws DVDLibraryDaoException if an error occurs writing to the file
	 */
	private void writeLibrary() throws DVDLibraryDaoException {
		
		PrintWriter out;
		
		try {
			out = new PrintWriter(new FileWriter(LIBRARY_FILE));
		} catch (IOException e) {
			throw new DVDLibraryDaoException("Could not save DVD data.", e);
		}
		
		// Write out the DVD objects to the roster file.
		// NOTE TO THE APPRENTICES: We could just grab the dvd map,
		// get the Collection of DVDs and iterate over them but we've
		// already created a method that gets a List of DVDs so
		// we'll reuse it.
		String dvdAsText;
		List<DVD> dvdList = this.getAllDVDs();
		for (DVD currentDVD : dvdList) {
			// turn a Student into a String
			dvdAsText = marshallDVD(currentDVD);
			// write the Student object to the file
			out.println(dvdAsText);
			// force PrintWriter to write line to the file
			out.flush();
		}
		// Clean up
		out.close();
	}

}
