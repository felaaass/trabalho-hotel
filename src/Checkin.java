public class Checkin {
    int quartosOcupados;

    @Override
    public String toString() {
        return "Checkin{" +
                "quartosOcupados=" + quartosOcupados +
                ", tipoQuartosOcupados='" + tipoQuartosOcupados + '\'' +
                ", periodoOcupacao=" + periodoOcupacao +
                '}';
    }

    public Checkin(String tipoQuartosOcupados, int quartosOcupados, int periodoOcupacao) {
        this.tipoQuartosOcupados = tipoQuartosOcupados;
        this.quartosOcupados = quartosOcupados;
        this.periodoOcupacao = periodoOcupacao;
    }

    String tipoQuartosOcupados;
    int periodoOcupacao;
}
