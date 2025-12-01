package poo.repository;

import poo.model.ContenidoAudiovisual;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Abstracción de acceso a datos de contenidos audiovisuales. los controladores
 * dependen de esta interfaz y no de una implementación concreta.
 */

public interface ContenidoRepository {
	List<ContenidoAudiovisual> findAll();

	Optional<ContenidoAudiovisual> findById(String id);

	void add(ContenidoAudiovisual contenido);

	void removeById(String id);

	/**
	 * Carga los datos desde el archivo asociado al repositorio.
	 */
	void loadFromFile() throws IOException;

	/**
	 * Guarda los datos en el archivo asociado al repositorio.
	 */
	void saveToFile() throws IOException;

}
