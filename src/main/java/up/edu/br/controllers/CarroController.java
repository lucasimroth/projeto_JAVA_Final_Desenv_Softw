package up.edu.br.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.Exception.NotFoundException;
import up.edu.br.models.Carro;
import up.edu.br.models.Cliente;

import java.util.List;

public class CarroController {
    private static final Logger logger = LogManager.getLogger(CarroController.class);


    /**
     * Método responsável por adicionar um carro ao cliente
     * @param cpf cpf do cliente
     * @param placa placa do carro
     * @param modelo modelo do carro
     * @param cor cor do carro
     */
    public static void AdicionarCarro(String cpf, String placa, String modelo, String cor){

        List<Cliente> clientes = ClienteController.clienteDao.lerArquivo();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                List<Carro> carros = cliente.getCarros();
                Carro carro = new Carro(MaiorID(carros)+1, placa, modelo, cor);
                carros.add(carro);
                ClienteController.clienteDao.salvarArquivo(clientes);
                return;
            }
        }

    }

    /**
     * Método responsável por alterar os dados de um carro
     * @param cpf cpf do cliente
     * @param idCarro id do carro
     * @param modelo modelo do carro
     * @param cor cor do carro
     */
    public static void AlterarCarro(String cpf, int idCarro, String modelo, String cor){
        List<Cliente> clientes = ClienteController.clienteDao.lerArquivo();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)){
                List<Carro> carros = cliente.getCarros();
                for (Carro carro : carros){
                    if (carro.getId() == idCarro){
                        carro.setModelo(modelo);
                        carro.setCor(cor);
                        ClienteController.clienteDao.salvarArquivo(clientes);
                        return;
                    }
                }
            }
        }
        logger.error("Cliente não encontrado na alteração de carro");
        System.out.println("Cliente não encontrado\n");
    }

    /**
     * Método responsável por excluir um carro do cliente, caso o cliente só tenha um carro, não é possível excluir
     * @param cpf cpf do cliente
     * @param idCarro id do carro
     */
    public static void ExcluirCarro(String cpf, int idCarro){
        List<Cliente> clientes = ClienteController.clienteDao.lerArquivo();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)){
                ExcluirCarro2(cliente.getCarros(), idCarro);
                ClienteController.clienteDao.salvarArquivo(clientes);
            }
        }
        logger.error("Cliente não encontrado na exclusão de carro");
        System.out.println("Cliente não encontrado\n");
    }

    public static void ExcluirCarro2(List<Carro> carros, int idCarro){
        if (carros.size() == 1){
            System.out.println("Não é possível excluir o carro, pois o cliente só possui um carro\n");
            return;
        }
        for (Carro carro : carros){
            if(carro.getId() == (idCarro)){
                carros.remove(carro);
                return;
            }
        }
        logger.error("Carro não encontrado na exclusão do carro");
        System.out.println("Carro não encontrado\n");
    }

    /**
     * Método responsável por listar os carros de um cliente
     * @param cpf cpf do cliente
     * @return boolean true se o carro já existe, false se não existe
     */
    public static void ListarCarro(String cpf){
        try{
            Cliente cliente = ClienteController.buscarPorCpf(cpf);
            List<Carro> carros = cliente.getCarros();
            System.out.printf("     %-2s %-10s %-20s %-15s\n\n", "ID", "Placa", "Modelo", "Cor");
            for (Carro c : carros) {
                imprimirCarros(c);
            }
        } catch (NotFoundException e) {
            logger.error("Cliente não encontrado na listagem", e);
            System.out.println("Cliente não encontrado\n");
        }
    }

    /**
     * Método responsável por mostrar os dados de um carro
     * @param cpf cpf do cliente
     * @param idCarro id do carro
     * @return boolean true se o carro foi encontrado, false se não
     */
    public static boolean MostrarCarro(String cpf, int idCarro){
        try {
            Cliente cliente = ClienteController.buscarPorCpf(cpf);
            List<Carro> carros = cliente.getCarros();

            for (Carro c : carros) {
                if (c.getId() == idCarro){
                    System.out.printf("     %-2s %-10s %-20s %-15s\n", "ID", "Placa", "Modelo", "Cor");
                    imprimirCarros(c);
                    return false;
                }
            }
            logger.error("Carro não encontrado na listagem");
            return true;
        }catch (NotFoundException e) {
            logger.error("Cliente não encontrado na listagem", e);
            System.out.println("Cliente não encontrado\n");
            return true;
        }
    }

    /**
     * Método responsável por imprimir os dados de UM carro
     * @param c o carro a ser impresso
     */
    public static void imprimirCarros(Carro c)
    {
        System.out.printf("     %-2s %-10s %-20s %-15s\n",
                c.getId(), c.getPlaca(), c.getModelo(), c.getCor());
    }


    /**
     * metodo para retornar o maiorID dos carros de um cliente
     */
    public static int MaiorID(List<Carro> carros) {
        int maior = 0;

        for (Carro c : carros) {
            if (c.getId() > maior) {
                maior = c.getId();
            }
        }
        return maior;
    }

    /**
     * Método responsável por printar o carro de um cliente no vallet
     * @param cpf o cpf do cliente dono do carro
     * @param idCarro o id do carro
     * @return string formatada com o nome do cliente, modelo e cor do carro
     */
    public static String printToVallet(int idCarro, String cpf)
    {
        List<Cliente> clientes = ClienteController.clienteDao.lerArquivo();
        for (Cliente c : clientes)
        {
            if (c.getCpf().equals(cpf))
            {
                for (Carro carro : c.getCarros())
                {
                    if (carro.getId() == idCarro){return carro.getPlaca()+" - "+carro.getModelo()+" - "+carro.getCor();}
                }
            }
        }
        return null;
    }

    /**
     * Método responsável por verificar se um carro existe
     * @param cpf o cpf do cliente dono do carro
     * @param idCarro o id do carro
     * @return boolean true se o carro existe, false se não
     */
    public static boolean ExistsCarro(String cpf, int idCarro){
        List<Cliente> clientes = ClienteController.clienteDao.lerArquivo();
        for (Cliente c : clientes)
        {
            if (c.getCpf().equals(cpf))
            {
                for (Carro carro : c.getCarros())
                {
                    if (carro.getId() == idCarro){return true;}
                }
            }
        }
        return false;
    }
}

