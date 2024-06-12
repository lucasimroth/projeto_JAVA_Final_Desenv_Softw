package up.edu.br.daos;

import up.edu.br.models.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FuncionarioDao implements CrudInterface<Funcionario> {
    private static final Logger logger = LogManager.getLogger(FuncionarioDao.class);
    private static final String FILE_NAME_FUNCIONARIO = "C:\\Users\\Lucas\\Documents\\GitHub\\projeto_final_JAVA\\src\\main\\java\\up\\edu\\br\\arquivos\\funcionarios.txt";
    private static List<Funcionario> funcionarios = lerArquivo();

    /**
     * Método responsável por carregar os funcionários do arquivo
     *
     * @return List<Funcionario>
     */
    private static List<Funcionario> lerArquivo()
    {
        List<Funcionario> f = new ArrayList<>();

        logger.info("inciando leitura do arquivo funcionarios");
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_FUNCIONARIO)))
        {
            String linha;
            logger.info("leitura dos funcionarios do arquivo txt e adicao a lista funcionarios");
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                Funcionario funcionario = new Funcionario(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3], dados[4]);
                f.add(funcionario);
            }
        }catch (Exception e)
        {
            logger.error("Ocorreu um erro ao ler arquivo funcionarios.", e);
            System.out.println("Erro ao ler arquivo");
        }
        return f;
    }

    /**
     * Método responsável por buscar um funcionário pelo id
     * @param id o id do do funcionario a ser buscado
     * @return Funcionario O funcionário encontrado, ou null se nenhum funcionário com o id fornecido for encontrado
     */
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

    /**
     * Método responsável por salvar os funcionários no arquivo
     * utiliza a lista e o file_name para salvar os funcionários
     */
    @Override
    public void salvarArquivo() {
        logger.info("iniciando Save do arquivo Funcionarios");
        try(PrintWriter writer = new PrintWriter(FILE_NAME_FUNCIONARIO))
        {
            logger.info("Escrevendo os dados do funcionario no arquivo txt");
            for (Funcionario f : funcionarios)
            {
                writer.println(FormatacaoString(f));
            }
        }catch (Exception e)
        {
            logger.error("Ocorreu um erro ao salvar arquivo funcionarios.", e);
            System.out.println("Erro ao salvar arquivo");
        }

    }

    /**
     * Método responsável por formatar os dados do funcionário para salvar no arquivo
     * @param f o funcionário a ser formatado
     * @return String dos dados do funcionário formatados
     */
    public String FormatacaoString(Funcionario f)
    {
        return (f.getId() + ";" + f.getNome() + ";" + f.getCpf() + ";" + f.getEmail() + ";" + f.getTelefone());
    }

    /**
     * Método responsável por cadastrar um funcionário usando a lista e salvar no arquivo
     * @param funcionario o funcionário a ser cadastrado
     */
    @Override
    public void cadastrar(Funcionario funcionario) {
        funcionarios.add(funcionario);
        salvarArquivo();
    }

    /**
     * Método responsável por alterar um funcionário usando a lista e salvar no arquivo
     * @param id o id do funcionário a ser alterado
     * @param funcionario o objeto funcionário com os novos dados
     */
    @Override
    public void alterar(int id, Funcionario funcionario)
    {
        for (Funcionario f : funcionarios)
        {
            if (f.getId() == id)
            {
                f.setNome(funcionario.getNome());
                f.setEmail(funcionario.getEmail());
                f.setTelefone(funcionario.getTelefone());
                return;
            }
        }
        System.out.println("Funcionário não encontrado\n");
    }

    /**
     * Método responsável por excluir um funcionário da lista e salvar no arquivo a lista atualizada
     * @param id o id do funcionário a ser excluído
     */
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

    /**
     * Método responsável por listar os funcionários de maneira formatada
     */
    @Override
    public void listar()
    {
        //impressao com formatacao para aparecer dividido na tela
        System.out.printf("%-10s %-20s %-15s %-30s %-15s\n", "ID", "Nome", "CPF", "Email", "Telefone");
        for (Funcionario f : funcionarios)
        {
            imprimirFuncionario(f);
        }
    }

    /**
     * Método responsável por imprimir UM funcionário de maneira formatada
     * @param f o funcionário a ser impresso
     */
    public void imprimirFuncionario(Funcionario f)
    {
        System.out.printf("%-10d %-20s %-15s %-30s %-15s\n",
                f.getId(), f.getNome(), f.getCpf(), f.getEmail(), f.getTelefone());
    }

    /**
     * Método responsável por retornar o maior id da lista de funcionários
     * @return int o maior id da lista de funcionários
     */
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

    /**
     * Método responsável por verificar se um cpf já existe na lista de funcionários
     * @param cpf o cpf a ser verificado
     * @return boolean true se o cpf já existe, false se não existe
     */
    public boolean jaExisteCpf(String cpf)
    {
        for (Funcionario f : funcionarios)
        {
            if (f.getCpf().equals(cpf))
            {
                return true;
            }
        }
        return false;
    }
}
