package Aluguel;

import Aluguel.DAO.ClienteDAO;
import Aluguel.DAO.ContratoDAO;
import Aluguel.DAO.ImovelDAO;
import Aluguel.entidade.Cliente;
import Aluguel.entidade.Contrato;
import Aluguel.entidade.Imovel;

import java.util.List;
import java.util.Scanner;

public class App 
{
    static boolean quit = false;

    public static void main( String[] args )
    {
        while (!quit) {
            System.out.println("Escolha uma opção:");
            System.out.print("1. Cadastrar imóvel.\n" +
                             "2. Cadastrar cliente.\n" +
                             "3. Cadastrar contrato.\n" +
                             "4. Listar imóveis disponíveis para aluguel.\n" +
                             "5. Listar contratos ativos.\n" +
                             "6. Listar clientes com mais contratos.\n" +
                             "7. Listar contratos expirando nos próximos 30 dias.\n" +
                             "8. Sair.\n");

            Scanner s = new Scanner(System.in);
            int opcao = s.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarImovel();
                    break;

                case 2:
                    cadastrarCliente();
                    break;

                case 3:
                    cadastrarContrato();
                    break;

                case 4:
                    ImovelDAO dao = new ImovelDAO();
                    dao.listarImoveisDisponiveis();
                    break;

                case 5:
                    ContratoDAO dao2 = new ContratoDAO();
                    dao2.listarContratosAtivos();
                    break;

                case 6:
                    ClienteDAO dao3 = new ClienteDAO();
                    dao3.listarClientesComMaisContratos(3);
                    break;

                case 7:
                    ContratoDAO dao4 = new ContratoDAO();
                    dao4.listarContratosExpirandoEm30Dias();
                    break;

                case 8:
                    quit = true;
                    break;

                default:
                    System.out.println("opção inválida.");
                    break;
            }

            if (!quit) {
                try {
                    Thread.currentThread().sleep(2500);
                }
                catch (Exception e) {

                }
            }
            System.out.print("\n");
        }
    }

    public static void cadastrarImovel() {

    }

    public static void cadastrarCliente() {

    }

    public static void cadastrarContrato() {

    }
}
