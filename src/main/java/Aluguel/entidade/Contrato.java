package Aluguel.entidade;

import java.time.LocalDateTime;

public class Contrato {
    private long id;
    private long id_imovel;
    private long id_cliente;
    private LocalDateTime data_inicio_aluguel;
    private LocalDateTime data_fim_aluguel;
    private float valor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_imovel() {
        return id_imovel;
    }

    public void setId_imovel(long id_imovel) {
        this.id_imovel = id_imovel;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDateTime getData_inicio_aluguel() {
        return data_inicio_aluguel;
    }

    public void setData_inicio_aluguel(LocalDateTime data_inicio_aluguel) {
        this.data_inicio_aluguel = data_inicio_aluguel;
    }

    public LocalDateTime getData_fim_aluguel() {
        return data_fim_aluguel;
    }

    public void setData_fim_aluguel(LocalDateTime data_fim_aluguel) {
        this.data_fim_aluguel = data_fim_aluguel;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
