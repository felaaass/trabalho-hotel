package objetos;

import java.time.LocalDate;

public class Reservas {
    public String nome;
    public LocalDate checkin;
    public LocalDate checkout;
    public int quarto;
    public String tipo;




    public Reservas(String nome , LocalDate dataentrada, LocalDate datasaida, int quarto, String tipo) {
        this.nome = nome;
        this.checkin = dataentrada;
        this.checkout = datasaida;
        this.quarto = quarto;
        this.tipo = tipo;




    }

    @Override
    public String toString() {
        return  " 0 cliente " + nome + " entrou no dia  " + checkin + " e sairá no dia  " + checkout + " está no quarto numQuarto " + quarto + " do tipo " + tipo;
    }
}