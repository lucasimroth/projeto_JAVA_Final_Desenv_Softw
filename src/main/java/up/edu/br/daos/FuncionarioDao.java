package up.edu.br.daos;

import up.edu.br.models.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class FuncionarioDao implements CrudInterface<Funcionario> {
    private static final String FILE_NAME_FUNCIONARIO = "C:\\Users\\Lucas\\Documents\\GitHub\\projeto_final_JAVA\\src\\main\\java\\up\\edu\\br\\arquivos\\funcionarios.txt";
    private static List<Funcionario> funcionarios = CarregarFuncionarios();

    private static List<Funcionario> CarregarFuncionarios()
    {
        List<Funcionario> f = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_FUNCIONARIO)))
        {
            String linha;
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                Funcionario funcionario = new Funcionario(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3], dados[4]);
                f.add(funcionario);
            }
        }catch (Exception e)
        {
            System.out.println("Erro ao ler arquivo");
        }
        return f;
    }

    public Funcionario buscarPorId(int id)
    {
        for (Funcionario f : funcionarios)
        {
            if (f.getId() == id)
            {
                return f;
            }
        }
        return null;
    }

    @Override
    public void salvarArquivo() {

        try(PrintWriter writer = new PrintWriter(FILE_NAME_FUNCIONARIO))
        {
            for (Funcionario f : funcionarios)
            {
                writer.println(FormatacaoString(f));
            }
        }catch (Exception e)
        {
            System.out.println("Erro ao salvar arquivo");
        }

    }

    public String FormatacaoString(Funcionario f)
    {
        return (f.getId() + ";" + f.getNome() + ";" + f.getCpf() + ";" + f.getEmail() + ";" + f.getTelefone());
    }

    @Override
    public void lerArquivo()
    {
        funcionarios = CarregarFuncionarios();
    }

    @Override
    public void cadastrar(Funcionario funcionario) {
        funcionarios.add(funcionario);
        salvarArquivo();
    }

    @Override
    public void alterar(int id, Funcionario funcionario)
    {
        for (Funcionario f : funcionarios)
        {
            if (f.getId() == id)
            {
                f.setNome(funcionario.getNome());
                f.setCpf(funcionario.getCpf());
                f.setEmail(funcionario.getEmail());
                f.setTelefone(funcionario.getTelefone());
                return;
            }
        }
        System.out.println("Funcionário não encontrado\n");
    }

    @Override
    public void excluir(int id)
    {
        for (Funcionario f : funcionarios)
        {
            if (f.getId() == id)
            {
                funcionarios.remove(f);
                return;
            }
        }
        System.out.println("Funcionário não encontrado\n");
    }

    @Override
    public void listar()
    {
        System.out.printf("%-10s %-20s %-15s %-30s %-15s\n", "ID", "Nome", "CPF", "Email", "Telefone");
        for (Funcionario f : funcionarios)
        {
            imprimirFuncionario(f);
        }
    }
    public void imprimirFuncionario(Funcionario f)
    {
        System.out.printf("%-10d %-20s %-15s %-30s %-15s\n",
                f.getId(), f.getNome(), f.getCpf(), f.getEmail(), f.getTelefone());
    }

    public static int MaiorID()
    {
        int maior = 0;

        for (Funcionario f : funcionarios)
        {
            if (f.getId() > maior)
            {
                maior = f.getId();
            }
        }
        return maior;
    }
}
