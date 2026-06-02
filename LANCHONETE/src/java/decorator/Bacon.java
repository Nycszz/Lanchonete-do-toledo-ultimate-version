package decorator;

public class Bacon extends AdicionalDecorator {

    public Bacon(ItemLanche item) {
        super(item);
    }

    @Override
    public String getDescricao() {
        return itemDecorator.getDescricao() + ", Bacon";
    }

    @Override
    public double custo() {
        return itemDecorator.custo() + 3.50;
    }
}