import java.util.ArrayList;
import java.util.List;

public class ComboItem extends Item {
    private List<Item> items = new ArrayList<>();
    private double desconto; // ex: 10 = 10%
    private static final double DESCONTO_ADICIONAL = 5.0; // Exemplo: 5% adicional por combo extra

    // Quantidade de combos que o cliente está comprando
    private int quantidade = 1;

    public ComboItem(String id, String nome, double desconto) {
        super(id, nome);
        setDesconto(desconto);
    }

    public void addItem(Item item) {
        if (item == null)
            throw new IllegalArgumentException("item nulo");
        items.add(item);
    }

    public List<Item> getItems() {
        return new ArrayList<Item>(items); // cópia para proteger lista interna
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        if (desconto < 0 || desconto > 100)
            throw new IllegalArgumentException("desconto inválido");
        this.desconto = desconto;
    }

    // NOVO: Permite definir a quantidade de combos comprados
    public void setQuantidade(int quantidade) {
        if (quantidade < 1)
            throw new IllegalArgumentException("quantidade deve ser >= 1");
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public double getPreco() {
        double sum = 0.0;
        for (Item it : items) {
            sum += it.getPreco(); // chama a implementação correta em runtime
        }
        // Calcula desconto base
        double calcDesconto = sum * (desconto / 100.0);

        // Se o cliente está comprando mais de um combo, aplica desconto adicional
        double descontoExtra = 0.0;// não esta sendo usada
        if (quantidade > 1) {
            // Exemplo: aplica DESCONTO_ADICIONAL% para cada combo extra
            double descontoAdicionalPercentual = DESCONTO_ADICIONAL * (quantidade - 1);
            // Limita o desconto total para 100%
            double descontoTotalPercentual = Math.min(desconto + descontoAdicionalPercentual, 100.0);
            double calcDescontoTotal = sum * (descontoTotalPercentual / 100.0);
            return (sum - calcDescontoTotal) * quantidade;
        }

        return (sum - calcDesconto) * quantidade;
    }

    @Override
    public String getDescricao() {
        StringBuilder sb = new StringBuilder(super.getNome() + " (Combo: ");
        for (Item it : items) {
            sb.append(it.getNome()).append(", ");
        }
        if (!items.isEmpty())
            sb.setLength(sb.length() - 2);
        sb.append(") - ").append(desconto).append("% off");

        if (quantidade > 1) {
            sb.append(" + ").append(DESCONTO_ADICIONAL)
                    .append("% adicional por combo extra (").append(quantidade).append(" combos)");
        }
        return sb.toString();
    }
}