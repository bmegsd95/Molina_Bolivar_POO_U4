package poo.repository;

import poo.model.Actor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvActorRepository implements ActorRepository {

	private final Path filePath;
	private final List<Actor> actores = new ArrayList<>();

	public CsvActorRepository(Path filePath) {
		this.filePath = filePath;
	}

	@Override
	public List<Actor> findAll() {
		return new ArrayList<>(actores);
	}

	@Override
	public Optional<Actor> findById(String id) {
		return actores.stream().filter(a -> a.getId().equals(id)).findFirst();
	}

	@Override
	public void add(Actor actor) {
		removeById(actor.getId());
		actores.add(actor);
	}

	@Override
	public void removeById(String id) {
		actores.removeIf(a -> a.getId().equals(id));
	}

	@Override
	public void loadFromFile() throws IOException {
		actores.clear();

		if (Files.notExists(filePath)) {
			return;
		}

		var lineas = Files.readAllLines(filePath, StandardCharsets.UTF_8);
		for (String linea : lineas) {
			if (linea.isBlank()) {
				continue;
			}
			String[] partes = linea.split(";");
			if (partes.length < 3) {
				continue;
			}
			String id = partes[0];
			String nombre = partes[1];
			String nacionalidad = partes[2];

			actores.add(new Actor(id, nombre, nacionalidad));
		}
	}

	@Override
	public void saveToFile() throws IOException {
		if (filePath.getParent() != null) {
			Files.createDirectories(filePath.getParent());
		}

		try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
			for (Actor actor : actores) {
				writer.write(actor.toCsv());
				writer.newLine();
			}
		}
	}

}
