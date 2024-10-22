import objetos.Hospede;
import objetos.Quartos;
import objetos.Reservas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Quartos> listaQuartos = new ArrayList<Quartos>();
    public static List<Reservas> listaReservas = new ArrayList<Reservas>();
    public static List<Hospede> listaHospedes = new ArrayList<Hospede>();
    public static int contadorQuartos = 1;

    public static void main(String[] args) {
        menu();
    }

    static void menu(){
        int opcao=0;
        while(opcao!=4){
            System.out.println(
                    "Digite a opção que deseja: \n" +
                    " [1 cadrastro quarto ] \n" +
                    " [2 reserva quarto ] \n" +
                    " [3 checkin] \n" +
                    " [4 listaQuartos disponiveis ] \n" +
                    " [5 Histórico de Reservas ] \n" +
                    " [6 checkout ] \n" +
                    " [7 sair ] \n " );
            opcao=scanner.nextInt();
            switch (opcao){
                case 1:cadastroQuarto();
                    break;
                case 2:cadastroReserva();
                    break;
                case 3: ocupados();
                    break;
                case 4: disponiveis(); menu();
                    break;
                case 5 : hospedes();
                    break;
                case 6: checkOut();
                    break;
                case 7:
                    break;
                default:System.out.println("Opção inválida. Por favor, escolha uma opção válida.  \n " );
                    break;
            }
        }
    }

    static void cadastroQuarto(){
        System.out.println("\n qual o numQuarto do quarto: ");
        int numQuarto = scanner.nextInt();
        System.out.println("qual o preço diario: \n");
        double diaria = scanner.nextDouble();
        String tipo = "";
        System.out.println("Tipo do quarto  1. solteiro  2. casal  3. suite  ");
        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                tipo  = "Solteiro";
                break;
            case 2:
                tipo  = "Casal";
                break;
            case 3:
                tipo  = "Suite";
                break;
            default:
                System.out.println("Opção inválida. \n");
                break;
        }
        String disponibilidade = " Disponivel ";

        Quartos quarto = new Quartos(numQuarto, tipo, diaria, disponibilidade);
        listaQuartos.add(quarto);

    }

    static void cadastroReserva() {
        int quarto = 0;
        String tipoQuarto = " ";

        System.out.println("\n qual o seu nome: ");
        String nome = scanner.next();
        String nomeTo = nome.toLowerCase();
        System.out.print("\nData de chegada (dd/MM/yyyy): ");
        String dataCheckin = scanner.next();
        LocalDate checkin;

        try (Scanner scanner = new Scanner(dataCheckin)) {
            String datastring = scanner.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            checkin =LocalDate.parse(datastring, formatter);
        }
        System.out.print("\nData de saida (yyyy/MM/dd): ");
        String dataSaida = scanner.next();
        LocalDate checkout;

        try (Scanner scanner = new Scanner(dataSaida)) {
            String datastring = scanner.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            checkout =LocalDate.parse(datastring, formatter);
        }

        System.out.println("Digite o numQuarto do quarto que deseja: \n");
        disponiveis();
        int quartoPronto = scanner.nextInt();
        for(Quartos q: listaQuartos){
            if(quartoPronto==q.numQuarto){
                if(q.disponibilidade == " Indisponivel "){
                    System.out.println(" algo deu errado");
                    disponiveis();
                } else {
                    quarto = quartoPronto;
                }
            }
        }



        double valorQuarto = 0;
        for (Quartos q : listaQuartos) {
            if (quarto == q.numQuarto) {
                valorQuarto = q.precoQuarto;
                tipoQuarto = q.tipoQuarto;
                q.disponibilidade = " Indisponivel ";
            }
        }



        Reservas reservas = new Reservas(nomeTo, checkin, checkout, quarto, tipoQuarto);
        listaReservas.add(reservas);
        Hospede hospedes = new Hospede(nomeTo, checkin, checkout, tipoQuarto, valorQuarto, quarto);
        listaHospedes.add(hospedes);



    }

    static void ocupados(){
        for(Reservas reserva: listaReservas) {
            System.out.println(reserva.toString());
        }
        menu();
    }
    static void disponiveis(){
        for(Quartos quarto: listaQuartos) {
            if(quarto.disponibilidade == " Disponivel ") {
                System.out.println(" O quarto numero " + quarto.numQuarto + " Do tipo " + quarto.tipoQuarto + " custando " + quarto.precoQuarto + " reais, está " + quarto.disponibilidade);
            }
        }

    }
    static void hospedes() {
        int quartos = 0;
        Set<String> hospedesA = new HashSet<>();
        for (Hospede hospedes: listaHospedes) {
            hospedesA.add(hospedes.nome);
        }
        for (String string: hospedesA) {
            System.out.println(string);
        }
        System.out.println( "\n qual o nome do hospede para averiguarmos o histórico \n");
        String nome = scanner.next();
        String nomeTo = nome.toLowerCase();

        if(nome.equals("sair")){
            menu();
        }else {


            for (Hospede hospede : listaHospedes) {
                if (hospede.nome.equals(nomeTo)) {
                    System.out.println(hospede + "\n");
                    quartos++;
                }
            }
            System.out.println(" foram reservados " + quartos+ " quartos");
        }
        hospedes();
    }

    static void checkOut() {
        System.out.println(" Quartos ocupados \n");
        for (Quartos quarto : listaQuartos) {
            if (quarto.disponibilidade == " Indisponivel ")
                System.out.println(" Quarto numQuarto: " + quarto.numQuarto + " do tipo " + quarto.tipoQuarto);
        }
        System.out.println("\n qual o quarto que deseja fazer o check out ou 0 para voltar ao menu ] \n");
        int numero = scanner.nextInt();

        if (numero == 0) {
            return;
        } else {
            for (Hospede hospede : listaHospedes) {
                if (numero == hospede.quarto) {

                    LocalDate checkInDate = hospede.dataReserva;
                    LocalDate checkOutDate = hospede.dataSaida;
                    long diasEstadia = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

                    System.out.println("tempo de estadia:  " + diasEstadia + " dias");
                    System.out.println("preço: " + hospede.valorQuarto * diasEstadia);
                    System.out.println(" 1. para pagar 2. para sair");
                    int opcao = scanner.nextInt();

                    if (opcao == 1) {
                        System.out.println("\nsucesso");

                        for (Quartos quarto : listaQuartos) {
                            if (numero == quarto.numQuarto) {
                                quarto.disponibilidade = " Disponivel ";
                            }
                        }
                        listaReservas.removeIf(reserva -> numero == reserva.quarto);
                        return;
                    } else {

                        return;
                    }
                }
                System.out.println(" erro!!");
                return;
            }
        }
    }
}