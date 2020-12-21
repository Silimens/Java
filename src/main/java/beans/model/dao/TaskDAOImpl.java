package beans.model.dao;

import beans.model.Role;
import beans.model.Task;
import beans.model.Track;
import beans.model.dao.mapper.RoleRowMapper;
import beans.model.dao.mapper.TaskRowMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Collection;

public class TaskDAOImpl implements TaskDAO {
    private static final Log log = LogFactory.getLog(UserDAOImpl.class);
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Task task) {
        jdbcTemplate.update(
            "INSERT INTO TASKS (ID, NAME) VALUES (?, ?)",
            task.getId(), task.getName()
        );
    }

    @Override
    public void delete(int taskId) {
        jdbcTemplate.update(
                "DELETE FROM TASKS WHERE ID = ?",
                taskId
        );
    }

    @Override
    public Task get(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT ID, NAME FROM TASKS WHERE ID = ?",
                new Object[]{id},
                new TaskRowMapper()
        );
    }

    @Override
    public Collection<Task> getAll() {
        return jdbcTemplate.query(
                "SELECT ID, NAME FROM TASKS",
                new TaskRowMapper()
        );
    }

    @Override
    public void recreateTable() {
        jdbcTemplate.update("DROP TABLE IF EXISTS TASKS");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS TASKS (ID INTEGER PRIMARY KEY, NAME TEXT)");
    }
}
