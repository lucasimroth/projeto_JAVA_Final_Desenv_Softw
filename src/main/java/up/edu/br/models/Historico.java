package up.edu.br.models;

import java.time.LocalTime;

public class Historico {
    private final String tipo;
    private final int idFuncionario;
    private String cpf;
    private final int idCarro;
    private final LocalTime horarioEntrada;
    private final LocalTime horarioSaida;

    public Historico(String tipo, int idFuncionario, String cpf, int idCarro, LocalTime horarioEntrada, LocalTime horarioSaida) {
        this.tipo = tipo;
        this.idFuncionario = idFuncionario;
        this.cpf = cpf;
        this.idCarro = idCarro;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }

}
