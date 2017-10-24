package by.agro.plant;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;

public class SpringJdbcPlantPlantingDao implements PlantPlantingDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<PlantPlanting> rowMapper = new RowMapper<PlantPlanting>() {
        @Override
        public PlantPlanting mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            PlantPlanting plantPlanting = new PlantPlanting();
            plantPlanting.setId(resultSet.getInt("id"));
            plantPlanting.setName(resultSet.getString("name"));
            plantPlanting.setSowingArea(resultSet.getDouble("sowingArea"));
            plantPlanting.setMaintainer(resultSet.getString("maintainer"));
            plantPlanting.setCreated(resultSet.getInt("created"));
            plantPlanting.setModified(resultSet.getInt("modified"));
            return plantPlanting;
        }
    };

    @Override
    public void save(PlantPlanting plantPlanting) {

        String sql = "" +
            "INSERT INTO Plants (" +
            "   id, name, sowingArea, maintainer, created, modified" +
            ") VALUES (" +
            "   :id, :name, :sowingArea, :maintainer, :created, :modified" +
            ")";

        HashMap<String, Object> params = new HashMap<>();
        params.put("id", plantPlanting.getId());
        params.put("name", plantPlanting.getName());
        params.put("sowingArea", plantPlanting.getSowingArea());
        params.put("maintainer", plantPlanting.getMaintainer());
        long now = Instant.now().getEpochSecond();
        params.put("created", now);
        params.put("modified", now);
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void update(PlantPlanting plantPlanting) {

        String sql = "" +
            "UPDATE Plants " +
            " SET sowingArea = :sowingArea, maintainer = :maintainer, modified = :modified " +
            " WHERE id = :id ";

        HashMap<String, Object> params = new HashMap<>();
        params.put("id", plantPlanting.getId());
        params.put("name", plantPlanting.getName());
        params.put("sowingArea", plantPlanting.getSowingArea());
        params.put("maintainer", plantPlanting.getMaintainer());
        long now = Instant.now().getEpochSecond();
        params.put("created", now);
        params.put("modified", now);
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public PlantPlanting get(long id) {

        String sql = "SELECT * FROM Plants WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", Long.valueOf(id));
        PlantPlanting plantPlanting = (PlantPlanting) namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper);
        return plantPlanting;
    }

    @Override
    public void delete(long id) {

        String sql = "DELETE FROM Plants WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", Long.valueOf(id));
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public List<PlantPlanting> findAll() {

        String sql = "SELECT * FROM Plants";
        List plantPlanting = (List) namedParameterJdbcTemplate.query(sql, rowMapper);
        return plantPlanting;
    }

    public SpringJdbcPlantPlantingDao(DataSource dataSource) {

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
