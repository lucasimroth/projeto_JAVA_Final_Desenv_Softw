package up.edu.br.models;

import java.util.List;

public class Cliente extends Pessoa{
    private List<Carro> carros;

    public Cliente(int id, String nome, String cpf, String email, String telefone, List<Carro> carros) {
        super(id, nome, cpf, email, telefone);
        this.carros = carros;
    }

    public List<Carro> getCarros() {
        return this.carros;
    }

}
