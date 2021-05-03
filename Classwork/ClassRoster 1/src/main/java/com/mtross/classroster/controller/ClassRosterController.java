/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.classroster.controller;

import com.mtross.classroster.dao.ClassRosterDao;
import com.mtross.classroster.dto.Student;
import com.mtross.classroster.ui.ClassRosterView;
import java.util.List;

/**
 *
 * @author mike
 */
public class ClassRosterController {
	
	ClassRosterDao dao;
	ClassRosterView view;
	
	public ClassRosterController(ClassRosterDao dao, ClassRosterView view) {
		this.dao = dao;
		this.view = view;
	}
	
	public void run() {
		boolean keepGoing = true;
		int menuSelection = 0;
		while (keepGoing) {
			
			menuSelection = getMenuSelection();
			
			switch (menuSelection) {
				case 1:
					listStudents();
					break;
				case 2:
					createStudent();
					break;
				case 3:
					viewStudent();
					break;
				case 4:
					removeStudent();
					break;
				case 5:
					keepGoing = false;
					break;
				default:
					unknownCommand();
			}
			
		}
		exitMessage();
		
	}	// End of run()
	
	private int getMenuSelection() {
		return view.printMenuAndGetSelection();
	}
	
	private void createStudent() {
		view.displayCreateStudentBanner();
		Student newStudent = view.getNewStudentInfo();
		dao.addStudent(newStudent.getStudentId(), newStudent);
		view.displayCreateSuccessBanner();
	}
	
	private void listStudents() {
		view.displayDisplayAllBanner();
		List<Student> studentList = dao.getAllStudents();
		view.displayStudentList(studentList);
	}
	
	private void viewStudent() {
		view.displayDisplayStudentBanner();
		String studentId = view.getStudentIdChoice();
		Student student = dao.getStudent(studentId);
		view.displayStudent(student);
	}
	
	private void removeStudent() {
		view.displayRemoveStudentBanner();
		String studentId = view.getStudentIdChoice();
		dao.removeStudent(studentId);
		view.displayRemoveSuccessBanner();
	}
	
	private void unknownCommand() {
		view.displayUnknownCommandBanner();
	}
	
	private void exitMessage() {
		view.displayExitBanner();
	}
	
}
