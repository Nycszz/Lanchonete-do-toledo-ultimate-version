package decorator;

public class Queijo extends AdicionalDecorator {

    public Queijo(ItemLanche item) {
        super(item);
    }

    @Override
    public String getDescricao() {
        return itemDecorator.getDescricao() + ", Queijo";
    }

    @Override
    public double custo() {
        return itemDecorator.custo() + 2.00;
    }
}