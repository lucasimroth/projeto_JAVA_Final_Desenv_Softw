package up.edu.br.models;

public class Funcionario extends Pessoa {

    private static int contador = 0;

    public Funcionario(String nome, String cpf, String email, String telefone) {
        super(nome, cpf, email, telefone);
        this.setId(++contador);
    }

    public Funcionario(int id, String nome, String cpf, String email, String telefone) {
        super(id, nome, cpf, email, telefone);
    }
}
