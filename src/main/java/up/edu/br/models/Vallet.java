package up.edu.br.models;

public class Vallet {
    private int idFuncionario;
    private int idCliente;
    private int idCarro;
    private String dataEntrada;

    public Vallet(int idFuncionario, int idCliente, int idCarro, String dataEntrada) {
        this.idFuncionario = idFuncionario;
        this.idCliente = idCliente;
        this.idCarro = idCarro;
        this.dataEntrada = dataEntrada;
    }

    public int getIdFuncionario() { return idFuncionario;}

    public int getIdCliente() {return idCliente;}

    public int getIdCarro() {return idCarro;}

    public String getDataEntrada() {return dataEntrada;}

    public void setIdFuncionario(int idFuncionario) {this.idFuncionario = idFuncionario;}

    public void setIdCliente(int idCliente) {this.idCliente = idCliente;}

    public void setIdCarro(int idCarro) {this.idCarro = idCarro;}

    public void setDataEntrada(String dataEntrada) {this.dataEntrada = dataEntrada;}
}
