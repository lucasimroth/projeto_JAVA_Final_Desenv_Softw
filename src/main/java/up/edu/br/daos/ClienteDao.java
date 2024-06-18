package up.edu.br.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.models.Carro;
import up.edu.br.models.Cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements DaoInterface<Cliente> {
    public static final Logger logger = LogManager.getLogger(ClienteDao.class);
    public static final String FILE_NAME_CLIENTE = "clientes.txt";

    /**
     * Método responsável por carregar os clientes do arquivo
     *
     * @return List<Cliente> ou uma lista vazia caso ocorra um erro
     */
    @Override
    public List<Cliente> lerArquivo() {
        List<Cliente> clientes = new ArrayList<>();

        logger.info("Iniciando leitura do arquivo clientes");
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_CLIENTE)))
        {
            String linha;
            logger.info("Leitura dos clientes do arquivo txt e adição à lista clientes");
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String cpf = dados[2];
                String email = dados[3];
                String telefone = dados[4];
                String carrosString = dados[5];

                List<Carro> carros = new ArrayList<>();
                String[] carrosDados = carrosString.split("\\|");
                for (String carroDados : carrosDados) {
                    String[] carroInfo = carroDados.split(",");
                    int idCarro = Integer.parseInt(carroInfo[0]);
                    String placa = carroInfo[1];
                    String modelo = carroInfo[2];
                    String cor = carroInfo[3];
                    carros.add(new Carro(idCarro, placa, modelo, cor));
                }

                Cliente cliente = new Cliente(id, nome, cpf, email, telefone, carros);
                clientes.add(cliente);
            }
        }catch (Exception e)
        {
            logger.error("Ocorreu um erro ao ler arquivo clientes.", e);
            return new ArrayList<>();
        }
        return clientes;
    }


    /**
     * Método responsável por salvar os clientes no arquivo
     * Utiliza String buider para formatar os dados do cliente
     * @param clientes a lista de clientes a ser salva
     */
    @Override
    public void salvarArquivo(List<Cliente> clientes) {
        logger.info("Iniciando save do arquivo clientes");
        try(PrintWriter writer = new PrintWriter(FILE_NAME_CLIENTE))
        {
            logger.info("Escrevendo os dados do cliente no arquivo txt");
            for (Cliente c : clientes)
            {
                writer.println(FormatacaoString(c));
            }
        }catch (Exception e)
        {
            logger.error("Ocorreu um erro ao salvar arquivo funcionarios.", e);
            System.out.println("Erro ao salvar arquivo");
        }
    }

    @Override
    public String FormatacaoString(Cliente cliente) {
        StringBuilder sb = new StringBuilder();

        sb.append(cliente.getId()).append(";");
        sb.append(cliente.getNome()).append(";");
        sb.append(cliente.getCpf()).append(";");
        sb.append(cliente.getTelefone()).append(";");
        sb.append(cliente.getEmail()).append(";");

        List<Carro> carros = cliente.getCarros();
        for (int i = 0; i < carros.size(); i++) {
            Carro carro = carros.get(i);
            sb.append(carro.getId()).append(",");
            sb.append(carro.getPlaca()).append(",");
            sb.append(carro.getModelo()).append(",");
            sb.append(carro.getCor());

            // Adiciona o separador de carros se não for o último carro
            if (i < carros.size() - 1) {
                sb.append("|");
            }
        }

        return sb.toString();
    }
}
