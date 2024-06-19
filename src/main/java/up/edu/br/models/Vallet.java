package up.edu.br.models;

import java.time.LocalTime;

public class Vallet {
    private final int idVallet;
    private int idFuncionario;
    private String cpfCliente;
    private int idCarro;
    private LocalTime dataEntrada;

    public Vallet(int idVallet, int idFuncionario, String cpf, int idCarro, LocalTime dataEntrada) {
        this.idVallet = idVallet;
        this.idFuncionario = idFuncionario;
        this.cpfCliente = cpf;
        this.idCarro = idCarro;
        this.dataEntrada = dataEntrada;
    }

    public int getIdVallet() { return idVallet;}

    public int getIdFuncionario() { return idFuncionario;}

    public String getCpfCliente() {return cpfCliente;}

    public int getIdCarro() {return idCarro;}

    public LocalTime getDataEntrada() {return dataEntrada;}
}
