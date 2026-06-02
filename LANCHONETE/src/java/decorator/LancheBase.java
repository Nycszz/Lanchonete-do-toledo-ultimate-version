package decorator;

import model.Produto;

public class LancheBase implements ItemLanche {

    private Produto produto;

    public LancheBase(Produto produto) {
        this.produto = produto;
    }

   @Override
    public String getDescricao() {
        return produto.getDescricao();
}

    @Override
    public double custo() {
        return produto.getPreco();
    }
}