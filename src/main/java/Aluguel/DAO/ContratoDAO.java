package Aluguel.DAO;

import Aluguel.entidade.Cliente;
import Aluguel.entidade.Contrato;
import Aluguel.entidade.Imovel;

import java.sql.*;

public class ContratoDAO extends BaseDAO {
    public void cadastrarContrato(Contrato c) {
        String sql = "INSERT INTO Contrato(id_imovel, id_cliente, data_inicio_aluguel, data_fim_aluguel, valor)" +
                     " VALUES(?, ?, ?, ?, ?)";

        try (Connection con = con(); PreparedStatement s = con.prepareStatement(sql)) {
            s.setLong(1, c.getId_imovel());
            s.setLong(2, c.getId_cliente());
            s.setDate(3, new java.sql.Date(Timestamp.valueOf(c.getData_inicio_aluguel()).getTime()));
            s.setDate(4, new java.sql.Date(Timestamp.valueOf(c.getData_fim_aluguel()).getTime()));
            s.setFloat(5, c.getValor());
            s.execute();
            System.out.println("Contrato cadastrado com sucesso.");
        }
        catch (SQLException e) {
            System.out.printf("Erro ao cadastrar contrato de imóvel com ID %d para cliente com ID %d.\n", c.getId_imovel(), c.getId_cliente());
            System.out.println("Erro : " + e.getMessage());
        }
    }

    public void listarContratosAtivos() {
        String sql = "SELECT id_contrato, id_imovel, id_cliente, data_inicio_aluguel, data_fim_aluguel, valor" +
                     " FROM Contrato WHERE data_fim_aluguel > CURDATE()";

        try (Connection con = con(); Statement s = con.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                long id = rs.getLong(1);
                long id_imovel = rs.getLong(2);
                long id_cliente = rs.getLong(3);
                Date data_inicio_aluguel = rs.getDate(4);
                Date data_fim_aluguel = rs.getDate(5);
                float valor = rs.getFloat(6);
                System.out.printf("ID Contrato: %d\tID Imóvel: %d\tID Cliente: %d\tData de início do contrato: %s\tData do fim do contrato: %s\tValor: %.2f R$\n",
                        id, id_imovel, id_cliente, data_inicio_aluguel.toString(), data_fim_aluguel.toString(), valor);
            }
        }
        catch (SQLException e) {
            System.out.println("Erro ao tentar obter contratos ativos.");
            System.out.println("Erro : " + e.getMessage());
        }
    }

    public boolean imovelEstaDisponivel(Imovel i) {
        String sql = "SELECT id_imovel FROM Contrato WHERE data_fim_aluguel > CURDATE()";

        try (Connection con = con(); Statement s = con.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                long id = rs.getLong(1);
                if (id == i.getId())
                    return false;
            }

            return true;
        }
        catch (SQLException e) {
            System.out.printf("Erro ao verificar se imóvel de ID %d está disponível.\n", i.getId());
            System.out.println("Erro : " + e.getMessage());
        }

        return false;
    }

    public void listarContratosExpirandoEm30Dias() {
        System.out.println("Contratos expirando nos próximos 30 dias:");
        String sql = "SELECT id_contrato, data_fim_aluguel FROM Contrato " +
                "WHERE data_fim_aluguel > CURDATE() AND data_fim_aluguel <= ADDDATE(CURDATE(), INTERVAL 30 DAY)";

        try (Connection con = con(); Statement s = con.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                long id = rs.getLong(1);
                Date dataFinal = rs.getDate(2);
                System.out.printf("Contrato de ID %d (%s).\n", id, dataFinal.toString());
            }
        }
        catch (SQLException e) {
            System.out.println("Erro ao listar contratos expirando nos próximos 30 dias.");
            System.out.println("Erro : " + e.getMessage());
        }
    }

    public int quantidadeDeContratosAtivosParaCliente(Cliente c) {
        String sql = "SELECT * FROM Contrato WHERE id_cliente = ? AND data_fim_aluguel > CURDATE()";
        int ret = 0;

        try (Connection con = con(); PreparedStatement s = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            s.setLong(1, c.getId());
            ResultSet rs = s.executeQuery();
            rs.last();
            ret = rs.getRow();
        }
        catch (SQLException e) {
            System.out.printf("Erro ao obter quantidade de contratos para cliente %s (%d).\n", c.getNome(), c.getId());
            System.out.println("Erro : " + e.getMessage());
        }

        return ret;
    }
}
