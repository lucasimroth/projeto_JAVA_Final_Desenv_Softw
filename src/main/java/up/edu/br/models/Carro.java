package up.edu.br.models;

public class Carro {
    private final int id;
    private final String placa;
    private String modelo;
    private String cor;

    public Carro(int id, String placa, String modelo, String cor) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getCor() {
        return cor;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
