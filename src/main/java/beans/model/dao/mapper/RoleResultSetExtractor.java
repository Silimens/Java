package beans.model.dao.mapper;

import beans.model.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleResultSetExtractor implements ResultSetExtractor<Role> {
    @Override
    public Role extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        return new Role(resultSet.getInt("ID"), resultSet.getString("NAME"));
    }
}
