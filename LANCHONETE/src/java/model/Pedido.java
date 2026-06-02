package model;

public class Pedido {

    private int id;
    private int clienteId;
    private double valorTotal;
    private String clienteNome;
    private String observacao;
    private String status;
    private String formaPagamento;
    private String statusPagamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getFormaPagamento() {
    return formaPagamento;
}

    public void setFormaPagamento(String formaPagamento) {
    this.formaPagamento = formaPagamento;
}

    public String getStatusPagamento() {
    return statusPagamento;
}

    public void setStatusPagamento(String statusPagamento) {
    this.statusPagamento = statusPagamento;
}
}