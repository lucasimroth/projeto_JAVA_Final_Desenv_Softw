package up.edu.br.daos;

import up.edu.br.models.Funcionario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FuncionarioDao implements DaoInterface<Funcionario> {
    private static final Logger logger = LogManager.getLogger(FuncionarioDao.class);
    private static final String FILE_NAME_FUNCIONARIO = "funcionarios.txt";

    /**
     * Método responsável por carregar os funcionários do arquivo
     *
     * @return List<Funcionario>
     */
    @Override
    public List<Funcionario> lerArquivo()
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
            return new ArrayList<>();
        }
        return f;
    }

    /**
     * Método responsável por salvar os funcionários no arquivo
     * utiliza a lista e o file_name para salvar os funcionários
     */
    @Override
    public void salvarArquivo(List<Funcionario> funcionarios)
    {
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
    @Override
    public String FormatacaoString(Funcionario f)
    {
        return (f.getId() + ";" + f.getNome() + ";" + f.getCpf() + ";" + f.getEmail() + ";" + f.getTelefone());
    }
}
