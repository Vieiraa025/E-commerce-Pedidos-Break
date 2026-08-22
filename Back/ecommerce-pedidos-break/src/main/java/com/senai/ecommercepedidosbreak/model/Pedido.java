import java.util.Random;

public class Pedido {

    private static final double VALOR_POR_QUILO = 7.50;
    private static final double FRETE_MINIMO = 15.00;
    private static final double TAXA_DESCONTO = 0.10;
    private static final double DESCONTO_MAXIMO = 50.00;
    private static final double VALOR_FRETE_GRATIS = 300.00;

    private static final int ANO_MINIMO = 1000;
    private static final int ANO_MAXIMO = 9999;
    private static final int NUMERO_MINIMO = 10000;
    private static final int NUMERO_MAXIMO = 99999;

    private PedidoUtils() {
    }

    public static String gerarNumeroDoPedido() {
        Random random = new Random();

        int ano = ANO_MINIMO + random.nextInt(ANO_MAXIMO - ANO_MINIMO + 1);
        int numero = NUMERO_MINIMO + random.nextInt(NUMERO_MAXIMO - NUMERO_MINIMO + 1);

        return String.format("PED-%04d-%05d", ano, numero);
    }

    public static double calcularSubtotal(double[] precos, int[] quantidades) {
        double subtotal = 0.0;

        for (int i = 0; i < precos.length; i++) {
            subtotal += precos[i] * quantidades[i];
        }

        return subtotal;
    }

    public static double calcularFrete(double pesoEmQuilos, double subtotal) {
        if (subtotal >= VALOR_FRETE_GRATIS) {
            return 0.0;
        }

        double frete = Math.ceil(pesoEmQuilos) * VALOR_POR_QUILO;

        return Math.max(frete, FRETE_MINIMO);
    }

    public static double calcularDesconto(double subtotal) {
        double desconto = subtotal * TAXA_DESCONTO;

        return Math.min(desconto, DESCONTO_MAXIMO);
    }

    public static String formatarLinhaDoRecibo(
            String nome, double preco, int quantidade) {

        double total = preco * quantidade;

        return String.format(
                "%-30s %5d x R$ %8.2f = R$ %8.2f",
                nome, quantidade, preco, total
        );
    }
}