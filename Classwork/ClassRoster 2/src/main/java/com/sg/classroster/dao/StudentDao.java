/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import com.sg.classroster.entity.Student;
import java.util.List;

/**
 *
 * @author mike
 */
public interface StudentDao {

    Student getStudentById(int id);

    List<Student> getAllStudents();

    Student addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(int id);

}
