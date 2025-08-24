package Aluguel.DAO;

import Aluguel.entidade.Imovel;

import java.sql.*;

public class ImovelDAO extends BaseDAO {
    public void cadastrarImovel(Imovel i) {
        String sql = "INSERT INTO Imovel(matricula, tamanho) VALUES(?, ?)";

        try (Connection con = con(); PreparedStatement s = con.prepareStatement(sql)) {
            s.setLong(1, i.getMatricula());
            s.setLong(2, i.getTamanho());
            s.execute();
            System.out.println("Imóvel cadastrado com sucesso.");
        }
        catch (SQLException e) {
            System.out.printf("Erro ao cadastrar imóvel de matrícula %d.\n", i.getMatricula());
            System.out.println("Erro : " + e.getMessage());
        }
    }

    public void listarImoveisDisponiveis() {
        System.out.println("Imóveis disponíveis para aluguel:");
        String sql = "SELECT id_imovel, matricula, tamanho FROM Imovel";

        try (Connection con = con(); Statement s = con.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            ContratoDAO dao = new ContratoDAO();
            Imovel i = new Imovel();
            while (rs.next()) {
                long id = rs.getLong(1);
                long matricula = rs.getLong(2);
                long tamanho = rs.getLong(3);
                i.setId(id);
                i.setMatricula(matricula);
                i.setTamanho(tamanho);
                if (dao.imovelEstaDisponivel(i))
                    System.out.printf("Imóvel de ID %d, matrícula %d e %dm² está disponível para aluguel.\n",
                                        i.getId(), i.getMatricula(), i.getTamanho());
            }
        }
        catch (SQLException e) {
            System.out.println("Erro ao tentar listar imóveis disponíveis para aluguel.");
            System.out.println("Erro : " + e.getMessage());
        }
    }
}
