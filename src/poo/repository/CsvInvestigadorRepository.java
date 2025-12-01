package poo.repository;

import poo.model.Investigador;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvInvestigadorRepository implements InvestigadorRepository {
	private final Path filePath;
	private final List<Investigador> investigadores = new ArrayList<>();

	public CsvInvestigadorRepository(Path filePath) {
		this.filePath = filePath;
	}

	@Override
	public List<Investigador> findAll() {
		return new ArrayList<>(investigadores);
	}

	@Override
	public Optional<Investigador> findById(String id) {
		return investigadores.stream().filter(i -> i.getId().equals(id)).findFirst();
	}

	@Override
	public void add(Investigador investigador) {
		removeById(investigador.getId());
		investigadores.add(investigador);
	}

	@Override
	public void removeById(String id) {
		investigadores.removeIf(i -> i.getId().equals(id));
	}

	@Override
	public void loadFromFile() throws IOException {
		investigadores.clear();

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
			String area = partes[2];

			investigadores.add(new Investigador(id, nombre, area));
		}
	}

	@Override
	public void saveToFile() throws IOException {
		if (filePath.getParent() != null) {
			Files.createDirectories(filePath.getParent());
		}

		try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
			for (Investigador investigador : investigadores) {
				writer.write(investigador.toCsv());
				writer.newLine();
			}
		}
	}

}
