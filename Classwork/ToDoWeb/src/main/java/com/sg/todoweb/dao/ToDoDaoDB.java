package com.sg.todoweb.dao;

import com.sg.todoweb.entities.ToDo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kylerudy
 */
public class ToDoDaoDB implements ToDoDao {

    JdbcTemplate jdbc;

    public void setJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ToDo getToDo(int id) {
        try {
            return jdbc.queryForObject("SELECT * FROM todo WHERE id = ?", new ToDoMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<ToDo> getAllToDos() {
        return jdbc.query("SELECT * FROM todo", new ToDoMapper());
    }

    @Override
    @Transactional
    public ToDo addToDo(ToDo todo) {
        jdbc.update("INSERT INTO todo(todo, note) VALUES(?,?)",
                todo.getTodo(),
                todo.getNote());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        todo.setId(newId);
        todo.setFinished(false);

        return todo;
    }

    @Override
    public void updateToDo(ToDo todo) {
        jdbc.update("UPDATE todo SET todo = ?, note = ?, finished = ? WHERE id = ?",
                todo.getTodo(),
                todo.getNote(),
                todo.isFinished(),
                todo.getId());
    }

    @Override
    public void removeToDo(int id) {
        jdbc.update("DELETE FROM todo WHERE id = ?", id);
    }

    private static final class ToDoMapper implements RowMapper<ToDo> {

        @Override
        public ToDo mapRow(ResultSet rs, int index) throws SQLException {
            ToDo td = new ToDo();
            td.setId(rs.getInt("id"));
            td.setTodo(rs.getString("todo"));
            td.setNote(rs.getString("note"));
            td.setFinished(rs.getBoolean("finished"));
            return td;
        }

    }

}
