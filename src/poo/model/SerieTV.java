package poo.model;
/**
 * Serie de TV. Puede asociarse con Temporadas.
 */
public class SerieTV extends ContenidoAudiovisual {
	private int numeroTemporadas;

    public SerieTV(String id,
                   String titulo,
                   int anioEstreno,
                   int duracionMinutos,
                   String genero,
                   double calificacion,
                   int numeroTemporadas) {
        super(id, titulo, anioEstreno, duracionMinutos, genero, calificacion);
        this.numeroTemporadas = numeroTemporadas;
    }
    
    public int getNumeroTemporadas() {
        return numeroTemporadas;
    }

    public void setNumeroTemporadas(int numeroTemporadas) {
        this.numeroTemporadas = numeroTemporadas;
    }

    public String toCsv() {
        return String.join(";",
                "SERIE",
                toCsvBase(),
                String.valueOf(numeroTemporadas)
        );
    }
	

}
