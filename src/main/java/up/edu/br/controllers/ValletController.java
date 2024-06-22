package up.edu.br.controllers;
import up.edu.br.Exception.NotFoundException;
import up.edu.br.Time.Horarios;
import up.edu.br.Time.ValorHora;
import up.edu.br.daos.ValletDao;
import up.edu.br.models.Cliente;
import up.edu.br.models.Vallet;

import java.util.List;

import static up.edu.br.Time.Horarios.PegarHorarioSaida;

public class ValletController {
    static ValletDao valletDao = new ValletDao();


    /**
     * Método responsável por estacionar um carro no estacionamento no vallet
     * @param idFuncionario o id do funcionário que está estacionando o carro
     * @param cpf o cpf do cliente dono do carro
     * @param idCarro o id do carro a ser estacionado
     * o Id e o horario de entrada sao por metodos
     */
    public static void estacionarCarro(int idFuncionario, String cpf, int idCarro){
        List<Vallet> vallets = valletDao.lerArquivo();
        Vallet vallet = new Vallet(maiorID()+1, idFuncionario, cpf, idCarro, Horarios.PegarHorarioEntrada());
        vallets.add(vallet);
        valletDao.salvarArquivo(vallets);
    }


    /**
     * Método responsável por listar os carros estacionados no vallet
     * ele chama metodos de print de outros controllers
     */
    public static void listarEstacionamento(){
        List<Vallet> vallets = valletDao.lerArquivo();
        if(vallets.isEmpty()){
            System.out.println("Não há carros estacionados");
            return;
        }
        for (Vallet v : vallets){
            System.out.printf("%-10s|%s|%s\n",
                    FuncionarioController.printToVallet(v.getIdFuncionario()), //retorna o nome do funcionario
                    ClienteController.printToListVallet(v.getCpfCliente(), v.getIdCarro()), //retorna um string buider do nome cliente, carro, modelo e cor
                    v.getDataEntrada());
        }
    }

    /**
     * Método responsável por mostrar os dados de um carro estacionado no vallet
     * @param cpf o cpf do cliente dono do carro
     */
    public static void mostrarVallet(String cpf) throws NotFoundException {
        List<Vallet> vallets = valletDao.lerArquivo();
        if(vallets.isEmpty()){
            throw new NotFoundException("Não há carros estacionados");
        }
        for (Vallet v : vallets){
            if (v.getCpfCliente().equals(cpf)){
                System.out.println("Funcionario: "+FuncionarioController.printToVallet(v.getIdFuncionario()));
                System.out.println("Cliente:   "+ClienteController.printToVallet(v.getCpfCliente()));
                System.out.println("cpf:    "+v.getCpfCliente());
                System.out.println("Carro:      "+CarroController.printToVallet(v.getIdCarro(), cpf));
                System.out.println("Data de entrada:   "+v.getDataEntrada());
                System.out.println("Valor atual:   "+ ValorHora.CalcularValor(v.getDataEntrada(), PegarHorarioSaida()));
                return;
            }
        }
        System.out.println("Cliente não encontrado");
    }

    /**
     * Método responsável por retirar um carro do estacionamento
     * @param cpf o cpf do cliente dono do carro
     */
    public static void retirarCarro(String cpf) throws NotFoundException {
        List<Vallet> vallets = valletDao.lerArquivo();
        if (vallets.isEmpty()){
            throw new NotFoundException("Não há carros estacionados");
        }
        for (Vallet v : vallets){
            if (v.getCpfCliente().equals(cpf)){
                //funcao que salva no historico;
                HistoricoController.salvarHistorico("Vallet", v.getIdFuncionario(), v.getCpfCliente(), v.getIdCarro(), v.getDataEntrada(), PegarHorarioSaida());
                vallets.remove(v);
                valletDao.salvarArquivo(vallets);
                System.out.println("\nCarro retirado com sucesso\n");
                return;
            }
        }
        throw new NotFoundException("Cliente não encontrado");
    }

    //metodo para pegar o maior id
    public static int maiorID(){
        List<Vallet> vallets = valletDao.lerArquivo();
        if (vallets.isEmpty())
        {
            return 0;
        }
        int maior = 0;
        for (Vallet v : vallets)
        {
            maior = Math.max(v.getIdVallet(), maior);
        }
        return maior;
    }

    public static boolean jaEstacionou(String cpf) throws NotFoundException {
        List<Vallet> vallets = valletDao.lerArquivo();
        if(vallets.isEmpty())
        {
            return true;
        }
        for (Vallet v : vallets)
        {
            if (v.getCpfCliente().equals(cpf))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean funcionarioEstaVinculado(int idFuncionario) {
        List<Vallet> vallets = valletDao.lerArquivo();
        for (Vallet v : vallets)
        {
            if (v.getIdFuncionario() == idFuncionario)
            {
                return false;
            }
        }
        return true;
    }

    public static boolean clienteEstaVinculado(String cpf) {
        List<Vallet> vallets = valletDao.lerArquivo();
        for (Vallet v : vallets)
        {
            if (v.getCpfCliente().equals(cpf))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean carroEstaVinculado(int idCarro, String cpf) {
        List<Vallet> vallets = valletDao.lerArquivo();
        for (Vallet v : vallets)
        {
            if (v.getIdCarro() == idCarro && v.getCpfCliente().equals(cpf))
            {
                return false;
            }
        }
        return true;
    }

}
