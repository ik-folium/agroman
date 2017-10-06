package by.agro.plant;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SimplePlantPlantingService implements PlantPlantingService {

    private PlantPlantingDao plantPlantingDao;

    @Override
    public void addPlantingPlant(AddPlantingPlantDto addPlantingPlantDto) {

        PlantPlanting planting = new PlantPlanting();

        planting.setId(addPlantingPlantDto.getId());
        planting.setName(addPlantingPlantDto.getName());
        planting.setSowingArea(addPlantingPlantDto.getSowingArea());
        planting.setMaintainer(addPlantingPlantDto.getMaintainer());

        plantPlantingDao.save(planting);
    }

    @Override
    public void updatePlantingPlant(UpdatePlantingPlantInfoDto updatePlantingPlantInfoDto) {

        PlantPlanting planting = new PlantPlanting();

        planting.setId(updatePlantingPlantInfoDto.getId());
        planting.setName(updatePlantingPlantInfoDto.getName());
        planting.setSowingArea(updatePlantingPlantInfoDto.getSowingArea());
        planting.setMaintainer(updatePlantingPlantInfoDto.getMaintainer());

        plantPlantingDao.update(planting);
    }

    @Override
    public PlantPlanting getPlantingPlant(GetPlantingPlantDto getPlantingPlantDto) {

        return plantPlantingDao.get(getPlantingPlantDto.getId());
    }

    @Override
    public void deletePlantingPlant(DeletePlantingPlantDto deletePlantingPlantDto) {

        plantPlantingDao.delete(deletePlantingPlantDto.getId());
    }

    @Override
    public List<PlantPlanting> findAllPlantingPlants() {

        return plantPlantingDao.findAll();
    }
}
