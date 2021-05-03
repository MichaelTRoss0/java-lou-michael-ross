/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.statefulunittesting.dao;

import com.tsg.statefulunittesting.dtos.Llama;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class LlamaDAOFileImpl implements LlamaDAO {

    private Map<String, Llama> llamaHerd;
    private final String DELIMITER = "::";
    private final String FILENAME;
    
    public LlamaDAOFileImpl(){
        llamaHerd = new HashMap<>();
        this.FILENAME = "herd.txt";
    }
    
    public LlamaDAOFileImpl(String fileName) {
        llamaHerd = new HashMap<>();
        this.FILENAME = fileName;
    }

    @Override
    public Llama addLlama(String id, Llama toAdd) throws LlamaDAOException {
        this.loadHerd();
        Llama wasStoredUnderThatId = llamaHerd.put(id, toAdd);
        this.saveHerd();
        return wasStoredUnderThatId;
    }

    @Override
    public Llama getLlama(String id) throws LlamaDAOException {
        this.loadHerd();
        Llama storedUnderThatId = llamaHerd.get(id);
        this.saveHerd();
        return storedUnderThatId;
    }

    @Override
    public List<Llama> getAllLlamas() throws LlamaDAOException {
        this.loadHerd();
        Collection<Llama> allDaLlamas = llamaHerd.values();
        List<Llama> orderedLlamas = new ArrayList<>(allDaLlamas);
        this.saveHerd();
        return orderedLlamas;
    }

    @Override
    public void editLlama(String id, Llama toUpdate) throws LlamaDAOException {
        this.loadHerd();
        llamaHerd.replace(id, toUpdate);
        this.saveHerd();
    }

    @Override
    public Llama removeLlama(String id) throws LlamaDAOException {
        this.loadHerd();
        Llama removed = llamaHerd.remove(id);
        this.saveHerd();
        return removed;
    }
    
    public String marshallLlama(Llama toTextify) {
        String llamaString = "";
        llamaString = llamaString + toTextify.getId() + this.DELIMITER;
        llamaString = llamaString + toTextify.getColor() + this.DELIMITER;
        llamaString = llamaString + toTextify.getName() + this.DELIMITER;
        llamaString = llamaString + toTextify.getYearsOld() + this.DELIMITER;
        llamaString = llamaString + toTextify.getFavFood();
        return llamaString;
    }
    
    public Llama unmarshallLlama(String llamaLine) {
        Llama llamaFromFile = new Llama();
        // FLOOFYFACE::Brown::Dixie::8::johnson grass
        // [FLOOFYFACE, Browm, Dixie, 8, johnson grass]
        //      0         1     2     3    4
        String[] splitLineProperties = llamaLine.split(DELIMITER);
        llamaFromFile.setId(splitLineProperties[0]);
        llamaFromFile.setColor(splitLineProperties[1]);
        llamaFromFile.setName(splitLineProperties[2]);
        
        String ageAsString = splitLineProperties[3];
        int age = Integer.parseInt(ageAsString);
        llamaFromFile.setYearsOld(age);
        
        llamaFromFile.setFavFood(splitLineProperties[4]);
        
        return llamaFromFile;
    }
    
    public void loadHerd() throws LlamaDAOException {
        try {
            // setup the file reader
            FileReader findsTheFile = new FileReader(FILENAME);
            BufferedReader getAllDaText = new BufferedReader(findsTheFile);
            Scanner breakApartTheText = new Scanner(getAllDaText);
            // GO OVER ALL THE LINES
            while (breakApartTheText.hasNextLine()) {
                // GET EACH LINE
                String llamaLine = breakApartTheText.nextLine();
                // TURN EACH LINE INTO A LLAMA
                Llama fromLine = this.unmarshallLlama(llamaLine);
                // STUFF IT IN THE MAP
                this.llamaHerd.put(fromLine.getId(), fromLine);
            }
            // CLEAN UP
            breakApartTheText.close();
            
        } catch (FileNotFoundException ex) {
            throw new LlamaDAOException("Tried to load from file, but it exploded.");
        }
    }
    
    public void saveHerd() throws LlamaDAOException {
        
        try {
            // setup file writer
            FileWriter writesToFile = new FileWriter(FILENAME);
            PrintWriter printer = new PrintWriter(writesToFile);
            
            // GET ALL DA LLAMAS
            List<Llama> llamaHerd = this.getAllLlamas();
            
            // ITERATE OVER EACH LLAMA
            for (Llama toTextify : llamaHerd) {
                // TURN EACH LLAMA INTO A LINE
                String llamaLine = this.marshallLlama(toTextify);
                // WRITE THAT LINE
                printer.println(llamaLine);
            }
            // BE POLITE. FLUSH AND CLOSE THE LID.
            printer.flush();
            printer.close();
            
        } catch (IOException ex) {
            throw new LlamaDAOException("Something went wrong when we tried to save.");
        }
        
        
    }
    
}
