package objetos;


public class Quartos {

    public int numQuarto;
    public String tipoQuarto;
    public double precoQuarto;
    public String disponibilidade;


    public Quartos(int numero, String tipo, double preco, String disponivel) {
        this.numQuarto = numero;
        this.tipoQuarto = tipo;
        this.precoQuarto = preco;
        this.disponibilidade = " Disponivel " ;
    }



    @Override
    public String toString() {
        return  " Numero quarto: " + numQuarto + " Tipo " + tipoQuarto + " Preço " + precoQuarto + " " + disponibilidade;
    }




}