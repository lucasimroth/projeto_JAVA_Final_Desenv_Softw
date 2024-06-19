package up.edu.br.controllers;
import up.edu.br.Time.Horarios;
import up.edu.br.Time.ValorHora;
import up.edu.br.daos.ValletDao;
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
    public void estacionarCarro(int idFuncionario, String cpf, int idCarro){
        List<Vallet> vallets = valletDao.lerArquivo();
        Vallet vallet = new Vallet(maiorID()+1, idFuncionario, cpf, idCarro, Horarios.PegarHorarioEntrada());
        vallets.add(vallet);
        valletDao.salvarArquivo(vallets);
    }


    /**
     * Método responsável por listar os carros estacionados no vallet
     * ele chama metodos de print de outros controllers
     */
    public void listarEstacionamento(){
        List<Vallet> vallets = valletDao.lerArquivo();
        if(vallets.isEmpty()){
            System.out.println("Não há carros estacionados");
            return;
        }
        for (Vallet v : vallets){
            System.out.printf("%-10s %-30s %-15s\n",
                    FuncionarioController.printToVallet(v.getIdFuncionario()), //retorna o nome do funcionario
                    ClienteController.printToListVallet(v.getCpfCliente(), v.getIdCarro()), //retorna um string buider do nome cliente, carro, modelo e cor
                    v.getDataEntrada());
        }
    }

    /**
     * Método responsável por mostrar os dados de um carro estacionado no vallet
     * @param cpf o cpf do cliente dono do carro
     * @return true se o carro foi encontrado, false se não(condicao para fazer o view funcionar corretamente)
     */
    public boolean mostrarVallet(String cpf){
        List<Vallet> vallets = valletDao.lerArquivo();
        if(vallets.isEmpty()){
            System.out.println("Não há carros estacionados");
            return false;
        }
        for (Vallet v : vallets){
            if (v.getCpfCliente().equals(cpf)){
                System.out.println("Funcionario: "+FuncionarioController.printToVallet(v.getIdFuncionario()));
                System.out.print("Cliente: "+ClienteController.printToVallet(v.getCpfCliente()));
                System.out.println("cpf: "+v.getCpfCliente());
                System.out.println("Carro: "+CarroController.printToVallet(v.getIdCarro(), cpf));
                System.out.println("Data de entrada: "+v.getDataEntrada());
                System.out.println("Valor atual: "+ ValorHora.CalcularValor(v.getDataEntrada(), PegarHorarioSaida()));
                return true;
            }
        }
        return false;
    }

    /**
     * Método responsável por retirar um carro do estacionamento
     * @param cpf o cpf do cliente dono do carro
     */
    public void retirarCarro(String cpf){
        List<Vallet> vallets = valletDao.lerArquivo();
        for (Vallet v : vallets){
            if (v.getCpfCliente().equals(cpf)){
                //funcao que salva no historico;
                vallets.remove(v);
                valletDao.salvarArquivo(vallets);
                return;
            }
        }
        System.out.println("Carro não encontrado");
    }

    //metodo para pegar o maior id
    public int maiorID(){
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
}
