package Aluguel.DAO;

import Aluguel.entidade.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO extends BaseDAO {
    public void cadastrarCliente(Cliente c) {
        String sql = "INSERT INTO Cliente(CPF, nome) VALUES(?, ?)";

        try (Connection con = con(); PreparedStatement s = con.prepareStatement(sql))
        {
            s.setString(1, c.getCPF());
            s.setString(2, c.getNome());
            s.execute();
        }
        catch (SQLException e)
        {
            System.out.printf("Erro ao cadastrar cliente %s (%s).\n", c.getNome(), c.getCPF());
            System.out.println("Erro : " + e.getMessage());
        }
    }
}
