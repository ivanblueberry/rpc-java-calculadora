import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

public class ServidorCalculadora {
    public static void main(String[] args) {
        try {
            // Puerto donde escuchará el servidor
            int puerto = 8080;

            System.out.println("===========================================");
            System.out.println("    SERVIDOR CALCULADORA RPC");
            System.out.println("===========================================");
            System.out.println("Iniciando servidor en el puerto " + puerto + "...");

            // Crear el servidor web que escuchará en el puerto especificado
            WebServer webServer = new WebServer(puerto);
            XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();

            // Configurar el mapeo de handlers (registrar la clase Calculadora)
            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler("Calculadora", Calculadora.class);

            xmlRpcServer.setHandlerMapping(phm);

            // Iniciar el servidor web
            webServer.start();

            System.out.println("✓ Servidor XML-RPC iniciado correctamente");
            System.out.println("✓ Esperando peticiones de clientes...");
            System.out.println("✓ URL: http://localhost:" + puerto + "/");
            System.out.println("===========================================");
            System.out.println("\nPara conectar desde otra computadora:");
            System.out.println("  - Usa la IP de este equipo en lugar de 'localhost'");
            System.out.println("  - Asegúrate que el puerto " + puerto + " esté abierto");
            System.out.println("\nPresiona Ctrl+C para detener el servidor\n");

        } catch (Exception e) {
            System.err.println("\n✗ ERROR AL INICIAR EL SERVIDOR:");
            System.err.println("  Descripción: " + e.getMessage());
            System.err.println("\nPosibles causas:");
            System.err.println("  - El puerto 8080 ya está en uso");
            System.err.println("  - No hay permisos suficientes");
            System.err.println("  - Faltan librerías XML-RPC");
            e.printStackTrace();
        }
    }
}