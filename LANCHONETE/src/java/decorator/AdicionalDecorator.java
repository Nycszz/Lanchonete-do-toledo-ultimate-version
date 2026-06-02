package decorator;

public class AdicionalDecorator implements ItemLanche {

    protected ItemLanche itemDecorator;

    public AdicionalDecorator(ItemLanche itemDecorator) {
        this.itemDecorator = itemDecorator;
    }

    @Override
    public String getDescricao() {
        return itemDecorator.getDescricao();
    }

    @Override
    public double custo() {
        return itemDecorator.custo();
    }
}