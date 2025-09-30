
// import java.util.ArrayList;
// import java.util.List;

// public class Pedido {
//     private Cliente cliente;
//     private List<OrderLine> linhas = new ArrayList<>();

//     public Pedido(Cliente cliente) {
//         this.cliente = cliente;
//     }

//     // Polimorfismo de compilação: sobrecarga (addItem) - um exemplo de polimorfismo
//     // em tempo de compilação
//     public void addItem(Item item) {
//         addItem(item, 1);
//     }

//     public void addItem(Item item, int quantidade) {
//         if (item == null)
//             throw new IllegalArgumentException("item nulo");
//         if (quantidade <= 0)
//             throw new IllegalArgumentException("quantidade deve ser >= 1");
//         linhas.add(new OrderLine(item, quantidade));
//     }

//     public double getTotal() {
//         double total = 0.0;
//         for (OrderLine ol : linhas) {
//             total += ol.getItem().getPreco() * ol.getQuantidade(); // chama getPrice()
//         }
//         return total;
//     }

//     public void printRecibo() {
//         System.out.println("Pedido para: " + cliente);
//         System.out.println("------------------------------");
//         for (OrderLine ol : linhas) {
//             System.out.println(ol.getQuantidade() + " x " + ol.getItem().toString());
//         }
//         System.out.println("------------------------------");
//         System.out.println("Total: R$ " + String.format("%.2f", getTotal()));
//     }

//     // Classe interna para representar linha do pedido (encapsula a associação
//     // item+quantidade)
//     private static class OrderLine {
//         private Item item;
//         private int quantidade;

//         public OrderLine(Item item, int quantidade) {
//             this.item = item;
//             this.quantidade = quantidade;
//         }

//         public Item getItem() {
//             return item;
//         }

//         public int getQuantidade() {
//             return quantidade;
//         }
//     }

//     public void validarPedido() {
//         if (calcularTotal() == 0) {
//             throw new IllegalStateException("Não é permitido finalizar um pedido com valor zero.");
//         }
//     }

//     public double calcularTotal() {
//         double total = 0;
//         for (Item item : ) {
//             total += item.getPreco();
//         }
//         return total;
//     }

// }

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<Item> itens;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getPreco();
        }
        return total;
    }

    public void validarPedido() throws IllegalArgumentException {
        double total = calcularTotal();
        if (total <= 0) {
            throw new IllegalArgumentException("Não é permitido criar um pedido com valor total igual a zero.");
        }
    }

    public void finalizarPedido() {
        validarPedido();
        System.out.println("Pedido finalizado para o cliente: " + cliente.getNome() +
                " | Total: R$ " + calcularTotal());
    }
}
