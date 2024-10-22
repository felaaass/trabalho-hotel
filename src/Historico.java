public class Historico {
    String nomeHospede;

    @Override
    public String toString() {
        return "Historico{" +
                "nomeHospede='" + nomeHospede + '\'' +
                ", datadeReserva=" + datadeReserva +
                ", numdeQuartosReservados=" + numdeQuartosReservados +
                ", tipodeQuartosreservados='" + tipodeQuartosreservados + '\'' +
                '}';
    }

    int datadeReserva;
    int numdeQuartosReservados;

    public Historico(int datadeReserva, int numdeQuartosReservados, String tipodeQuartosreservados, String nomeHospede) {
        this.datadeReserva = datadeReserva;
        this.numdeQuartosReservados = numdeQuartosReservados;
        this.tipodeQuartosreservados = tipodeQuartosreservados;
        this.nomeHospede = nomeHospede;
    }

    String tipodeQuartosreservados;
}
