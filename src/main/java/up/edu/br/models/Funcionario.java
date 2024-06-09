package up.edu.br.models;

public class Funcionario extends Pessoa {

    public Funcionario(String nome, String cpf, String email, String telefone) {
        super(nome, cpf, email, telefone);
    }

    public Funcionario(int id, String nome, String cpf, String email, String telefone) {
        super(id, nome, cpf, email, telefone);
    }
}
