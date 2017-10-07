package by.agro.plant;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;

public class SpringJdbcPlantPlantingDao implements PlantPlantingDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    }

    @Override
    public PlantPlanting get(long id) {

        return null;
    }

    @Override
    public PlantPlanting delete(long id) {

        return null;
    }

    @Override
    public List<PlantPlanting> findAll() {

        return null;
    }

    public SpringJdbcPlantPlantingDao(DataSource dataSource) {

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
