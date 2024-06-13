package up.edu.br.validar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import up.edu.br.controllers.ClienteController;
import up.edu.br.controllers.FuncionarioController;
import up.edu.br.daos.FuncionarioDao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidaCrud {
    static Scanner scan = new Scanner (System.in);
    private static final Logger logger = LogManager.getLogger(ValidaCrud.class);

    public static int validaId(){
        logger.info("Iniciando validação do ID");
        while(true) {
            try {
                int id = scan.nextInt();
                scan.nextLine();
                if(id > 0){
                    return id;
                }
                System.out.println("ID inválido, digite novamente: ");
            } catch (InputMismatchException e) {
                logger.error("Erro ao validar ID", e);
                System.out.println("ID inválido, digite novamente: ");
            }
        }
    }

    public static boolean confirmarId(int id) {
        System.out.println("é o usuário que deseja? (1 - sim, 2 - não)");
        int confirmacao = scan.nextInt();
        scan.nextLine();
        return confirmacao != 1;
    }

    public static String validaNome(){
        String nome = scan.nextLine();
        while (!nome.matches("[a-zA-Z\\s]{3,}+")){
            System.out.println("Nome inválido, digite novamente: ");
            nome = scan.nextLine();
        }
        return nome;
    }

    public static String validaCpf(){
        String cpf = scan.nextLine().replaceAll("\\D", "");
        while(!validaCpf2(cpf)){
            cpf = scan.nextLine().replaceAll("\\D", "");
        }
        return cpf;
    }

    public static boolean validaCpf2(String cpf){

        if(cpf.length() != 11 || !cpf.matches("\\d+") || cpf.matches("(\\d)\\1*") || FuncionarioController.jaExisteCpf(cpf) || ClienteController.jaExisteCpf(cpf))
        {
            System.out.println("CPF inválido, digite novamente: ");
            return false;
        }
        for(int j = 0; j < 2; j++){
            int soma = 0, resto;
            for(int i = 10 + j; i >= 2; i--){
                soma += i * Integer.parseInt(cpf.charAt(j + 10 - i) + "");
            }
            resto = (soma % 11);

            if(resto < 2) {
                resto = 0;
            } else {
                resto = 11 - resto;
            }

            if(resto != Integer.parseInt(cpf.charAt(j + 9) + "")){
                System.out.println("CPF inválido, digite novamente: ");
                return false;
            }
        }
        return true;
    }

    public static String validaEmail(){
        String email = scan.nextLine();
        while (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            System.out.println("Email inválido, digite novamente: ");
            email = scan.nextLine();
        }
        return email;
    }

    public static String validaTelefone(){
        String telefone = scan.nextLine();
        while (!telefone.matches("\\d+") || (telefone.length() < 8 || telefone.length() > 11) || telefone.matches("(\\d)\\1*")){
            System.out.println("Telefone inválido, digite novamente: ");
            telefone = scan.nextLine();
        }
        return telefone;
    }

    public static String validaPlaca(){
        String placa = scan.nextLine().replaceAll("[^a-zA-Z0-9]", "");
        while (!placa.matches("[a-zA-Z]{3}\\d{4}")){
            System.out.println("Placa inválida, digite novamente: ");
            placa = scan.nextLine().replaceAll("[^a-zA-Z0-9]", "");
        }
        return placa;
    }

    public static String validaModelo(){
        String modelo = scan.nextLine();
        while (!modelo.matches("[a-zA-Z\\s]{3,}+")){
            System.out.println("Modelo inválido, digite novamente: ");
            modelo = scan.nextLine();
        }
        return modelo;
    }

    public static String validaCor(){
        String cor = scan.nextLine();
        while (!cor.matches("[a-zA-Z\\s]{3,}+")){
            System.out.println("Cor inválida, digite novamente: ");
            cor = scan.nextLine();
        }
        return cor;
    }

}
