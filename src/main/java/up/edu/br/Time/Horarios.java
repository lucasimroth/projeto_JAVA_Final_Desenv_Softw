package up.edu.br.Time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Horarios {

    //COMEÇO  FUNC QUE PEGA  O HORARIO DE ENTRADA
    public static LocalTime PegarHorarioEntrada(){

        LocalTime horaEntrada = LocalTime.now();
        String horaEntradaFormatada = horarioToString(horaEntrada);

        System.out.println("Hora entrada: " + horaEntradaFormatada);
        return horaEntrada;
    }

    //COMEÇO  FUNC QUE PEGA  O HORARIO DE SAIDA
    public static LocalTime PegarHorarioSaida(){

        LocalTime horaSaida = LocalTime.now();
        String horaSaidaFormatada = horarioToString(horaSaida);

        System.out.println("Hora saida: " + horaSaidaFormatada);
        return horaSaida;
    }

    //FUNCAO PARA VER QUAL O TEMPO TOTAL EM SEGUNDOS
    public static int VerDiferençaHorarios(LocalTime horaEntrada, LocalTime horaSaida){

        if ((horaEntrada == null || horaSaida == null) || (horaEntrada.equals(horaSaida))) {
            //LANCAR UM THROW
            return 0;
        }

        Duration duracao = Duration.between(horaEntrada, horaSaida);
        long horas = duracao.toHours();
        long minutos = duracao.toMinutes() % 60;
        long segundos = duracao.getSeconds() % 60;

        //RETURN TRANFORMA O TEMPO EM SEGUNDOS
        return (int)(horas*3600+minutos*60+segundos);
    }

    public static String horarioToString(LocalTime hora) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm:ss");
        return hora.format(formatador);
    }

    public static LocalTime stringToHorario(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(timeString, formatter);
    }


    //FileManager maybe????
    public static void escreverHora(String hora) {
        try (FileWriter escritor = new FileWriter("horas.txt")) {
            escritor.write(hora);
            //FAZER LOG
            System.out.println("Hora salva com sucesso!.");
        } catch (IOException e) {
            //FAZER LOG
            System.err.println("Erro ao escrever hora: " + e.getMessage());
        }
    }

    public static String pegarHora() {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader leitor = new BufferedReader(new FileReader("horas.txt"))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                conteudo.append(linha);
            }
            //FAZER LOG
            System.out.println("Hora lida com sucesso!.");
        } catch (IOException e) {
            //FAZER LOG
            System.err.println("Erro ao ler hora: " + e.getMessage());
        }
        return conteudo.toString();
    }

}
