package by.agro.plant;

import java.util.List;

public interface PlantPlantingService {

    void addPlantingPlant(AddPlantingPlantDto addPlantingPlantDto);

    void updatePlantingPlant(UpdatePlantingPlantInfoDto updatePlantingPlantInfoDto);

    PlantPlanting getPlantingPlant(GetPlantingPlantDto getPlantingPlantDto);

    void deletePlantingPlant(DeletePlantingPlantDto deletePlantingPlantDto);

    List<PlantPlanting> findAllPlantingPlants();
}
