package by.agro.performer;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class SpringJdbcPerformerDao implements PerformerDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Performer> rowMapper = new RowMapper<Performer>() {
        @Override
        public Performer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Performer performer = new Performer();
            performer.setId(resultSet.getInt("id"));
            performer.setFirstName(resultSet.getString("firstName"));
            performer.setLastName(resultSet.getString("lastName"));
            performer.setAge(resultSet.getInt("age"));
            return performer;
        }
    };

    @Override
    public void save(Performer performer) {

        String sql = "" +
            "INSERT INTO Performers (" +
            " id, firstName, lastName, age )" +
            " VALUES ( :id, :firstName, :lastName, :age)";

        HashMap<String, Object> params = new HashMap<>();
        params.put("id", performer.getId());
        params.put("firstName", performer.getFirstName());
        params.put("lastName", performer.getLastName());
        params.put("age", performer.getAge());
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void update(Performer performer) {

        String sql = "" +
            "UPDATE Performers " +
            " SET firstName = :firstName, lastName = :lastName, age = :age " +
            " WHERE id = :id ";

        HashMap<String, Object> params = new HashMap<>();
        params.put("id", performer.getId());
        params.put("firstName", performer.getFirstName());
        params.put("lastName", performer.getLastName());
        params.put("age", performer.getAge());
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Performer get(long id) {

        String sql = "SELECT * FROM Performers WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", Long.valueOf(id));
        Performer performer = (Performer) namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        return performer;
    }

    @Override
    public void delete(long id) {

        String sql = "DELETE FROM Performers WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", Long.valueOf(id));
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public List<Performer> findAll() {

        String sql = "SELECT * FROM Plants";
        List performer = (List) namedParameterJdbcTemplate.query(sql, rowMapper);
        return performer;
    }

    public SpringJdbcPerformerDao(DataSource dataSource) {

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
