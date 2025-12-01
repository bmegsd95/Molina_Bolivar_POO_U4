package poo.repository;
import poo.model.Actor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
public interface ActorRepository {
	List<Actor> findAll();

    Optional<Actor> findById(String id);

    void add(Actor actor);

    void removeById(String id);

    void loadFromFile() throws IOException;

    void saveToFile() throws IOException;

}
