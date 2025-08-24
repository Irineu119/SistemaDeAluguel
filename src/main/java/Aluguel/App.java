package Aluguel;

import Aluguel.DAO.ClienteDAO;
import Aluguel.DAO.ContratoDAO;
import Aluguel.DAO.ImovelDAO;
import Aluguel.entidade.Cliente;
import Aluguel.entidade.Contrato;
import Aluguel.entidade.Imovel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        Scanner s = new Scanner(System.in);
        Imovel i = new Imovel();
        System.out.print("Digite a matrícula do imóvel: ");
        long matricula = s.nextLong();
        i.setMatricula(matricula);
        System.out.print("Digite o tamanho em m² do imóvel: ");
        long tamanho = s.nextLong();
        i.setTamanho(tamanho);

        ImovelDAO dao = new ImovelDAO();
        dao.cadastrarImovel(i);
    }

    public static void cadastrarCliente() {
        Scanner s = new Scanner(System.in);
        Cliente c = new Cliente();
        System.out.print("Digite o nome do cliente: ");
        String nome = s.nextLine();
        c.setNome(nome);
        System.out.print("Digite o CPF do cliente: ");
        String cpf = s.nextLine();
        c.setCPF(cpf);

        ClienteDAO dao = new ClienteDAO();
        dao.cadastrarCliente(c);
    }

    public static void cadastrarContrato() {
        Scanner s = new Scanner(System.in);
        Contrato c = new Contrato();
        System.out.print("Digite o ID do imóvel: ");
        long id_imovel = s.nextLong();
        c.setId_imovel(id_imovel);
        System.out.print("Digite o ID do cliente: ");
        long id_cliente = s.nextLong();
        c.setId_cliente(id_cliente);
        System.out.print("Digite o valor: ");
        float valor = s.nextFloat();
        s.nextLine();
        c.setValor(valor);
        System.out.print("Digite a data de início (yyyy-MM-dd HH:mm): ");
        String inicioStr = s.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime inicio = LocalDateTime.parse(inicioStr, formatter);
        c.setData_inicio_aluguel(inicio);
        System.out.print("Digite a data do fim (yyyy-MM-dd HH:mm): ");
        String fimStr = s.nextLine();
        LocalDateTime fim = LocalDateTime.parse(fimStr, formatter);
        c.setData_fim_aluguel(fim);

        ContratoDAO dao = new ContratoDAO();
        dao.cadastrarContrato(c);
    }
}
