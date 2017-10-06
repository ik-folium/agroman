package by.agro.performer;

import by.agro.plant.Performer;

import java.util.List;

public interface PerformerDao {

    Performer save(Performer performer);

    Performer update(Performer performer);

    Performer get(long id);

    Performer delete(long id);

    List<Performer> findAll();
}
