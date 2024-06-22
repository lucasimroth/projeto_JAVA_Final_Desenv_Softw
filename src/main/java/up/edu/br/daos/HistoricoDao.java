package up.edu.br.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.Time.Horarios;
import up.edu.br.models.Historico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDao implements DaoInterface<Historico>{
    private static final Logger logger = LogManager.getLogger(HistoricoDao.class);
    private static final String FILE_NAME_HISTORICO = "historico.txt";


    /**
     * Método responsável por carregar os dados do Vallet do arquivo
     *
     */
    @Override
    public List<Historico> lerArquivo()
    {
        List<Historico> h = new ArrayList<>();

        logger.info("inciando leitura do arquivo Historico");
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_HISTORICO)))
        {
            String linha;
            logger.info("leitura do Historico do arquivo txt");
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                String tipo = dados[0];
                int idFuncionario = Integer.parseInt(dados[1]);
                String cpf = dados[2];
                int IdCarro = Integer.parseInt(dados[3]);
                LocalTime horarioEntrada = Horarios.stringToHorario(dados[4]);
                LocalTime horarioSaida = Horarios.stringToHorario(dados[5]);
                Historico histo = new Historico(tipo, idFuncionario, cpf, IdCarro, horarioEntrada, horarioSaida);
                h.add(histo);
            }
        }catch (Exception e)
        {
            return new ArrayList<>();
        }
        return h;
    }

    /**
     * Método responsável por salvar os dados do Vallet no arquivo
     * utiliza a lista e o file_name para salvar os Vallet
     * @param historicos lista de Historicos do sistema
     */
    @Override
    public void salvarArquivo(List<Historico> historicos)
    {
        logger.info("iniciando Save do arquivo Historico");
        try(PrintWriter writer = new PrintWriter(FILE_NAME_HISTORICO))
        {
            logger.info("Escrevendo os dados do Historico no arquivo txt");
            for (Historico h : historicos)
            {
                writer.println(FormatacaoString(h));
            }
        }catch (Exception e)
        {
            logger.error("Ocorreu um erro ao salvar arquivo Historico.", e);
            System.out.println("Erro ao salvar arquivo");
        }

    }

    /**
     * Método responsável por formatar o Historico em uma string
     * @param h Historico
     *
     */
    @Override
    public String FormatacaoString(Historico h)
    {
        return h.getTipo() + ";" +
                h.getIdFuncionario() + ";" +
                h.getCpf() + ";" +
                h.getIdCarro() + ";" +
                Horarios.horarioToString(h.getHorarioEntrada()) + ";" +
                Horarios.horarioToString(h.getHorarioSaida());
    }
}
