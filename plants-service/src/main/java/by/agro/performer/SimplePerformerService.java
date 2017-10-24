package by.agro.performer;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SimplePerformerService implements PerformerService {

    private PerformerDao performerDao;

    @Override
    public void addPerformer(AddPerformerDto addPerformerDto) {

        Performer performer = new Performer();

        performer.setId(addPerformerDto.getId());
        performer.setFirstName(addPerformerDto.getFirstName());
        performer.setLastName(addPerformerDto.getLastName());
        performer.setAge(addPerformerDto.getAge());

        performerDao.save(performer);
    }

    @Override
    public void updatePerformer(UpdatePerformerDto updatePerformerDto) {

        Performer performer = new Performer();

        performer.setId(updatePerformerDto.getId());
        performer.setFirstName(updatePerformerDto.getFirstName());
        performer.setLastName(updatePerformerDto.getLastName());
        performer.setAge(updatePerformerDto.getAge());

        performerDao.update(performer);
    }

    @Override
    public Performer getPerformer(GetPerformerDto getPerformerDto) {

        return performerDao.get(getPerformerDto.getId());
    }

    @Override
    public void deletePerformer(DeletePerformerDto deletePerformerDto) {

        performerDao.delete(deletePerformerDto.getId());
    }

    @Override
    public List<Performer> findAllPerformers() {

        return performerDao.findAll();
    }
}
