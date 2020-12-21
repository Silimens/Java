package beans.model.dao;

import beans.model.Track;
import beans.model.dao.mapper.TrackRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TrackDAOImpl implements TrackDAO {
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(int userId, int taskId, Date begin, Date end, String comment) {
        jdbcTemplate.update(
                "INSERT INTO TRACKS (USER_ID, TASK_ID, BEGIN, END, COMMENT) VALUES (?, ?, ?, ?, ?)",
                userId, taskId, begin, end, comment);
    }

    @Override
    public void add(Track track) {
        jdbcTemplate.update(
            "INSERT INTO TRACKS (ID, USER_ID, TASK_ID, BEGIN, END, COMMENT) VALUES (?, ?, ?, ?, ?, ?)",
            track.getId(), track.getUserId(), track.getTaskId(), track.getBegin(), track.getEnd(), track.getComment()
        );
    }

    @Override
    public void delete(int trackId) {
        jdbcTemplate.update("DELETE FROM TRACKS WHERE ID = ?", trackId);
    }

    @Override
    public Track get(int trackId) {
        return jdbcTemplate.queryForObject(
            "SELECT ID, USER_ID, TASK_ID, BEGIN, END, COMMENT FROM TRACKS WHERE ID = ?",
            new Object[]{trackId},
            new TrackRowMapper()
        );
    }

    @Override
    public Collection<Track> getAll() {
        return jdbcTemplate.query(
            "SELECT ID, USER_ID, TASK_ID, BEGIN, END, COMMENT FROM TRACKS",
            new TrackRowMapper()
        );
    }

    @Override
    public Collection<Track> getUserTaskTracks(int userId, int taskId) {
        return jdbcTemplate.query(
            "SELECT ID, USER_ID, TASK_ID, BEGIN, END, COMMENT FROM TRACKS WHERE USER_ID = ? AND TASK_ID = ?",
            new Object[] {userId, taskId},
            new TrackRowMapper()
        );
    }

    @Override
    public void recreateTables() {
        jdbcTemplate.update("DROP TABLE IF EXISTS TRACKS");
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS TRACKS (ID INTEGER PRIMARY KEY AUTOINCREMENT, USER_ID INTEGER, TASK_ID INTEGER, BEGIN, END, COMMENT TEXT)");
    }
}
