package Aluguel.DAO;

import Aluguel.entidade.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.min;

public class ClienteDAO extends BaseDAO {
    public void cadastrarCliente(Cliente c) {
        String sql = "INSERT INTO Cliente(CPF, nome) VALUES(?, ?)";

        try (Connection con = con(); PreparedStatement s = con.prepareStatement(sql)) {
            s.setString(1, c.getCPF());
            s.setString(2, c.getNome());
            s.execute();
        }
        catch (SQLException e) {
            System.out.printf("Erro ao cadastrar cliente %s (%s).\n", c.getNome(), c.getCPF());
            System.out.println("Erro : " + e.getMessage());
        }
    }

    public List<Cliente> obterTodos() {
        List<Cliente> lista = new ArrayList<Cliente>();
        String sql = "SELECT id_cliente, CPF, nome FROM Cliente";

        try (Connection con = con(); Statement s = con.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getLong(1));
                c.setCPF(rs.getString(2));
                c.setNome(rs.getString(3));
                lista.add(c);
            }
        }
        catch (SQLException e) {
            System.out.println("Erro ao obter todos os clientes");
            System.out.println("Erro : " + e.getMessage());
        }

        return lista;
    }

    private class ComparadorContrato implements Comparator<Cliente> {
        @Override
        public int compare(Cliente c1, Cliente c2) {
            ContratoDAO dao = new ContratoDAO();
            return dao.quantidadeDeContratosAtivosParaCliente(c1) - dao.quantidadeDeContratosAtivosParaCliente(c2);
        }
    }

    public void listarClientesComMaisContratos(int limite) {
        List<Cliente> clientes = obterTodos();
        limite = min(limite, clientes.size());
        System.out.printf("%d cliente(s) com mais contratos:\n", limite);
        clientes.sort(new ComparadorContrato());
        ContratoDAO dao = new ContratoDAO();
        for (int i = 0; i < limite; i++) {
            System.out.printf("%s: %d contratos.\n", clientes.get(i).getNome(), dao.quantidadeDeContratosAtivosParaCliente(clientes.get(i)));
        }
    }
}
