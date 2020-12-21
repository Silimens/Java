package beans.model.dao;

import beans.model.Role;
import beans.model.Task;
import beans.model.dao.mapper.RoleRowMapper;
import beans.model.dao.mapper.TaskRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: roman
 * Date: 28.11.12
 * Time: 8:13
 * To change this template use File | Settings | File Templates.
 */
public class RoleDAOImpl implements RoleDAO {
    private JdbcTemplate jdbcTemplate;
    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Role role) {
        jdbcTemplate.update(
                "INSERT INTO ROLES (ID, NAME) VALUES (?, ?)",
                role.getId(), role.getName());
    }

    @Override
    public void delete(int roleId) {
        jdbcTemplate.update("DELETE FROM ROLES WHERE ID = ?", roleId);
    }

    @Override
    public Role get(int roleId) {
        return jdbcTemplate.queryForObject(
                "SELECT ID, NAME FROM ROLES WHERE ID = ?",
                new Object[]{ roleId},
                new RoleRowMapper());
    }

    @Override
    public Collection<Role> getAll() {
        return jdbcTemplate.query("SELECT ID, NAME FROM ROLES",
                new RoleRowMapper());
    }

    @Override
    public void addRoleTask(int roleId, int taskId) {
        jdbcTemplate.update("INSERT INTO ROLE_TASKS (ROLE_ID, TASK_ID) VALUES (?, ?)", roleId, taskId);
    }

    @Override
    public Collection<Task> getRoleTasks(int roleId) {
        return jdbcTemplate.query(
            "SELECT TASKS.ID, TASKS.NAME FROM TASKS JOIN ROLE_TASKS ON ROLE_TASKS.TASK_ID = TASKS.ID " +
            "WHERE ROLE_TASKS.ROLE_ID = ?", new Object[]{roleId}, new TaskRowMapper());
    }

    @Override
    public void deleteRoleTask(int roleId, int taskId) {
        jdbcTemplate.update("DELETE FROM ROLE_TASKS WHERE ROLE_ID = ? AND TASK_ID = ?", roleId, taskId);
    }

    @Override
    public void add(Role role, int... roleTaskIds) {
        add(role);
        for (int roleTaskId : roleTaskIds)
            addRoleTask(role.getId(), roleTaskId);
    }

    @Override
    public void recreateTables() {
        jdbcTemplate.update("DROP TABLE IF EXISTS ROLES");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS ROLES (ID INTEGER PRIMARY KEY, NAME TEXT)");
        jdbcTemplate.update("DROP TABLE IF EXISTS ROLE_TASKS");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS ROLE_TASKS (ROLE_ID, TASK_ID)");
    }
}
