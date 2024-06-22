package up.edu.br.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.Time.Horarios;
import up.edu.br.models.Funcionario;
import up.edu.br.models.Vallet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ValletDao implements DaoInterface<Vallet>{
    private static final Logger logger = LogManager.getLogger(ValletDao.class);
    private static final String FILE_NAME_VALLET = "vallet.txt";


    /**
     * Método responsável por carregar os dados do Vallet do arquivo
     *
     * @return List<Vallet>
     */
    @Override
    public List<Vallet> lerArquivo()
    {
        List<Vallet> v = new ArrayList<>();

        logger.info("inciando leitura do arquivo Vallet");
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_VALLET)))
        {
            String linha;
            logger.info("leitura dos Vallet do arquivo txt e adicao a lista Vallet");
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                int IdVallet = Integer.parseInt(dados[0]);
                int IdFuncionario = Integer.parseInt(dados[1]);
                String cpf = dados[2];
                int IdCarro = Integer.parseInt(dados[3]);
                LocalTime horario = Horarios.stringToHorario(dados[4]);
                Vallet vallet = new Vallet(IdVallet, IdFuncionario, cpf, IdCarro, horario);
                v.add(vallet);
            }
        }catch (Exception e)
        {
            logger.error("Ocorreu um erro ao ler arquivo Vallet.", e);
            return new ArrayList<>();
        }
        return v;
    }

    /**
     * Método responsável por salvar os dados do Vallet no arquivo
     * utiliza a lista e o file_name para salvar os Vallet
     * @param vallets lista de Vallets do sistema
     */
    @Override
    public void salvarArquivo(List<Vallet> vallets)
    {
        logger.info("iniciando Save do arquivo Vallet");
        try(PrintWriter writer = new PrintWriter(FILE_NAME_VALLET))
        {
            logger.info("Escrevendo os dados do Vallet no arquivo txt");
            for (Vallet v : vallets)
            {
                writer.println(FormatacaoString(v));
            }
        }catch (Exception e)
        {
            logger.error("Ocorreu um erro ao salvar arquivo Vallet.", e);
            System.out.println("Erro ao salvar arquivo");
        }

    }

    /**
     * Método responsável por formatar os dados do Vallet
     * @param v Vallet a ser formatado
     * @return String formatada
     */
    @Override
    public String FormatacaoString(Vallet v)
    {
        return (v.getIdVallet() + ";" +
                v.getIdFuncionario() + ";" +
                v.getCpfCliente() + ";" +
                v.getIdCarro() + ";" +
                Horarios.horarioToString(v.getDataEntrada()));
    }
}
