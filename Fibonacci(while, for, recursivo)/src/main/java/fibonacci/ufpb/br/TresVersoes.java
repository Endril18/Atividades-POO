package fibonacci.ufpb.br;

public class TresVersoes {
    public static void main(String [] args){

        System.out.println("Sequência de fibonacci, 20");
        int sequencia = 20;
        for(int i = 0; i < sequencia; i++){
            System.out.println(fib3(i));
        }

    }

    // For
    public static int fib1(int n){
        if (n == 0) return 0;
        if (n == 1) return 1;

        int a = 0, b = 1, resultado = 0;

        for(int k = 2; k <= n; k++){
            resultado = a + b;
            a = b;
            b = resultado;
        }
        return resultado;
    }

    // Recursivo
    public static int fib2(int n){
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fib2(n - 1) + fib2(n - 2);
    }

    // While
    public static int fib3(int n){
        if (n == 0) return 0;
        if (n == 1) return 1;

        int a = 0, b = 1, resultado = 0;
        int k = 2;

        while(k <= n){
            resultado = a + b;
            a = b;
            b = resultado;
            k++;
        }
        return resultado;
    }
}
