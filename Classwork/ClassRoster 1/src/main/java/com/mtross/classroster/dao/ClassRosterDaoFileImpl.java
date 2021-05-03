/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.classroster.dao;

import com.mtross.classroster.dto.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mike
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao {
	
	public static final String ROSTER_FILE = "roster.txt";
	public static final String DELIMITER = "::";
	
	private Map<String, Student> students = new HashMap<>();

	@Override
	public Student addStudent(String studentId, Student student) {
		Student newStudent = students.put(studentId, student);
		return newStudent;
	}

	@Override
	public List<Student> getAllStudents() {
		return new ArrayList<Student>(students.values());
	}

	@Override
	public Student getStudent(String studentId) {
		return students.get(studentId);
	}

	@Override
	public Student removeStudent(String studentId) {
		Student removedStudent = students.remove(studentId);
		return removedStudent;
	}
	
}
