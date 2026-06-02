package model;

public class Produto {

    private int id;
    private String descricao;
    private double preco;
    private String categoria; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Produto() {
        super();
    }
    
    public static ProdutoBuilder getBuilder() {
        return new ProdutoBuilder();
    }

    public static class ProdutoBuilder {

        private Produto prod = new Produto();

        public ProdutoBuilder comId(int id) {
            prod.id = id;
            return this;
        }

        public ProdutoBuilder comDescricao(String descricao) {
            prod.descricao = descricao;
            return this;
        }

        public ProdutoBuilder comPreco(double preco) {
            prod.preco = preco;
            return this;
        }

        public ProdutoBuilder comCategoria(String categoria) {
            prod.categoria = categoria;
            return this;
        }

        public Produto build() {
            return prod;
        }
    }
}