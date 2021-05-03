/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.todoweb.dao;

import com.sg.todoweb.entities.ToDo;
import java.util.List;

/**
 *
 * @author kylerudy
 */
public interface ToDoDao {
    ToDo getToDo(int id);
    List<ToDo> getAllToDos();
    ToDo addToDo(ToDo todo);
    void updateToDo(ToDo todo);
    void removeToDo(int id);
}
