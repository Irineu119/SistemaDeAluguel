package Aluguel;

import Aluguel.DAO.ClienteDAO;
import Aluguel.entidade.Cliente;

public class App 
{
    public static void main( String[] args )
    {
        ClienteDAO dao = new ClienteDAO();
        Cliente c = new Cliente();
        c.setCPF("111.335.689-13");
        c.setNome("Murilo");
        dao.cadastrarCliente(c);
        System.out.println("mako");
    }
}
