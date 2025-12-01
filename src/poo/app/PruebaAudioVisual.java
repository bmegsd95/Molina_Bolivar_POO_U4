package poo.app;

import poo.controller.SistemaContenidoController;
import poo.repository.*;
import poo.view.ConsolaView;

import java.nio.file.Path;

public class PruebaAudioVisual {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Path baseDir = Path.of("data");

		ContenidoRepository contenidoRepo = new CsvContenidoRepository(baseDir.resolve("contenidos.csv"));
		ActorRepository actorRepo = new CsvActorRepository(baseDir.resolve("actores.csv"));
		TemporadaRepository temporadaRepo = new CsvTemporadaRepository(baseDir.resolve("temporadas.csv"));
		InvestigadorRepository investigadorRepo = new CsvInvestigadorRepository(baseDir.resolve("investigadores.csv"));

		SistemaContenidoController controller = new SistemaContenidoController(contenidoRepo, actorRepo, temporadaRepo,
				investigadorRepo);

		ConsolaView view = new ConsolaView(controller);
		view.ejecutar(); // aquí se lanza el menú de la app

	}

}
