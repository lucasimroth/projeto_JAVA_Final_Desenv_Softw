package up.edu.br.utils;

import up.edu.br.daos.FuncionarioDao;

public class Util {
    public static int FuncionarioMaiorID() {
        return FuncionarioDao.MaiorID();
    }
}
