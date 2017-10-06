package by.agro.plant;

import java.util.List;

public interface PlantPlantingDao {

    PlantPlanting save(PlantPlanting plantPlanting);

    PlantPlanting update(PlantPlanting plantPlanting);

    PlantPlanting get(long id);

    PlantPlanting delete(long id);

    List<PlantPlanting> findAll();
}
