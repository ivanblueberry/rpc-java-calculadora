public class Calculadora {

    // Método para sumar dos números
    public double sumar(double a, double b) {
        System.out.println("Llamada a SUMAR: " + a + " + " + b);
        return a + b;
    }

    // Método para restar dos números
    public double restar(double a, double b) {
        System.out.println("Llamada a RESTAR: " + a + " - " + b);
        return a - b;
    }

    // Método para multiplicar dos números
    public double multiplicar(double a, double b) {
        System.out.println("Llamada a MULTIPLICAR: " + a + " * " + b);
        return a * b;
    }

    // Método para dividir dos números con manejo de división por cero
    public String dividir(double a, double b) {
        System.out.println("Llamada a DIVIDIR: " + a + " / " + b);

        // Verificar división por cero
        if (b == 0) {
            String errorMsg = "ERROR: División por cero no permitida";
            System.err.println(errorMsg);
            return errorMsg;
        }

        double resultado = a / b;
        return String.valueOf(resultado);
    }
}