package up.edu.br.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.Exception.NotFoundException;
import up.edu.br.models.Carro;
import up.edu.br.models.Cliente;

import java.util.List;

public class CarroController {
    private static final Logger logger = LogManager.getLogger(CarroController.class);

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

    public static void imprimirCarros(Carro c)
    {
        System.out.printf("     %-2s %-10s %-20s %-15s\n",
                c.getId(), c.getPlaca(), c.getModelo(), c.getCor());
    }


    public static int MaiorID(List<Carro> carros) {
        int maior = 0;

        for (Carro c : carros) {
            if (c.getId() > maior) {
                maior = c.getId();
            }
        }
        return maior;
    }

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

