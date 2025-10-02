import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.XmlRpcException;

import java.net.URL;
import java.net.MalformedURLException;
import java.net.ConnectException;
import java.util.Scanner;

public class ClienteCalculadora {
    private static XmlRpcClient client;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        try {
            // Configurar conexión con el servidor
            configurarCliente();

            // Menú principal
            boolean continuar = true;
            while (continuar) {
                mostrarMenu();
                int opcion = leerOpcion();

                if (opcion == 5) {
                    continuar = false;
                    System.out.println("\n¡Hasta luego!");
                } else if (opcion >= 1 && opcion <= 4) {
                    realizarOperacion(opcion);
                } else {
                    System.out.println("\n✗ Opción inválida. Intente nuevamente.");
                }
            }

        } catch (MalformedURLException e) {
            System.err.println("\n✗ ERROR: URL del servidor mal formada");
            System.err.println("  Verifica la dirección IP y puerto");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("\n✗ ERROR INESPERADO EN EL CLIENTE:");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void configurarCliente() throws MalformedURLException {
        System.out.println("===========================================");
        System.out.println("    CLIENTE CALCULADORA RPC");
        System.out.println("===========================================");
        System.out.print("\n¿Desea conectarse a un servidor remoto? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();

        String urlServidor;
        if (respuesta.equals("s")) {
            System.out.print("Ingrese la IP del servidor (ej. 192.168.1.100): ");
            String ip = scanner.nextLine().trim();
            System.out.print("Ingrese el puerto (por defecto 8080): ");
            String puertoStr = scanner.nextLine().trim();
            int puerto = puertoStr.isEmpty() ? 8080 : Integer.parseInt(puertoStr);
            urlServidor = "http://" + ip + ":" + puerto + "/";
        } else {
            urlServidor = "http://localhost:8080/";
        }

        System.out.println("\nConectando a: " + urlServidor);

        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(urlServidor));
        config.setConnectionTimeout(5000); // Timeout de 5 segundos
        config.setReplyTimeout(5000);

        client = new XmlRpcClient();
        client.setConfig(config);

        System.out.println("✓ Cliente configurado correctamente\n");
    }

    private static void mostrarMenu() {
        System.out.println("\n===========================================");
        System.out.println("         CALCULADORA RPC - MENÚ");
        System.out.println("===========================================");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Multiplicar");
        System.out.println("4. Dividir");
        System.out.println("5. Salir");
        System.out.println("===========================================");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void realizarOperacion(int opcion) {
        try {
            // Solicitar los números al usuario
            System.out.print("\nIngrese el primer número: ");
            double num1 = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Ingrese el segundo número: ");
            double num2 = Double.parseDouble(scanner.nextLine().trim());

            // Preparar parámetros para la llamada RPC
            Object[] params = new Object[]{num1, num2};

            // Llamar al método correspondiente
            String metodo = obtenerNombreMetodo(opcion);
            System.out.println("\n→ Enviando petición al servidor...");

            Object resultado = client.execute("Calculadora." + metodo, params);

            // Mostrar resultado
            System.out.println("✓ Respuesta recibida del servidor:");
            System.out.println("  " + num1 + " " + getSimboloOperacion(opcion) + " " + num2 + " = " + resultado);

        } catch (NumberFormatException e) {
            System.err.println("\n✗ ERROR: Debe ingresar números válidos");
        } catch (XmlRpcException e) {
            System.err.println("\n✗ ERROR EN LA LLAMADA RPC:");
            System.err.println("  Código de error: " + e.code);
            System.err.println("  Mensaje: " + e.getMessage());
            System.err.println("\nPosibles causas:");
            System.err.println("  - El servidor no está ejecutándose");
            System.err.println("  - Problema de red o firewall");
            System.err.println("  - El método no existe en el servidor");
        } catch (Exception e) {
            System.err.println("\n✗ ERROR INESPERADO:");
            System.err.println("  " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String obtenerNombreMetodo(int opcion) {
        switch (opcion) {
            case 1: return "sumar";
            case 2: return "restar";
            case 3: return "multiplicar";
            case 4: return "dividir";
            default: return "";
        }
    }

    private static String getSimboloOperacion(int opcion) {
        switch (opcion) {
            case 1: return "+";
            case 2: return "-";
            case 3: return "*";
            case 4: return "/";
            default: return "";
        }
    }
}