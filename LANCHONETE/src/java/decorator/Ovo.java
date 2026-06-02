package decorator;

public class Ovo extends AdicionalDecorator {

    public Ovo(ItemLanche item) {
        super(item);
    }

    @Override
    public String getDescricao() {
        return itemDecorator.getDescricao() + ", Ovo";
    }

    @Override
    public double custo() {
        return itemDecorator.custo() + 1.50;
    }
}