# 🧮 Calculadora RPC en Java

## 📋 Descripción del Proyecto

Este proyecto implementa una **calculadora distribuida** utilizando **Remote Procedure Call (RPC)** con **XML-RPC** en Java. La aplicación permite realizar operaciones matemáticas básicas (suma, resta, multiplicación y división) de forma remota, donde el cliente puede conectarse a un servidor que ejecuta los cálculos y devuelve los resultados.

### 🎯 Objetivos del Proyecto

- Demostrar el funcionamiento de la arquitectura **cliente-servidor** usando RPC
- Implementar comunicación remota entre procesos usando **XML-RPC**
- Proporcionar una interfaz de usuario interactiva para operaciones matemáticas
- Manejar errores de red y validación de entrada de datos

### 🏗️ Arquitectura del Sistema

```
┌─────────────────┐         XML-RPC         ┌─────────────────┐
│                 │    ◄─────────────►     │                 │
│     Cliente     │                        │    Servidor     │
│ (ClienteCalc)   │     Puerto 8080        │ (ServidorCalc)  │
│                 │                        │                 │
└─────────────────┘                        └─────────────────┘
        │                                           │
        │                                           │
   ┌────▼────┐                                 ┌────▼────┐
   │ Menú    │                                 │ Clase   │
   │ Inter-  │                                 │ Calcu-  │
   │ activo  │                                 │ ladora  │
   └─────────┘                                 └─────────┘
```

## 🚀 Características

✅ **Operaciones Matemáticas**: Suma, resta, multiplicación y división  
✅ **Conexión Local y Remota**: Soporte para localhost y servidores remotos  
✅ **Interfaz Interactiva**: Menú fácil de usar en consola  
✅ **Manejo de Errores**: Validación de entrada y errores de red  
✅ **División por Cero**: Manejo seguro de división por cero  
✅ **Timeouts Configurables**: Control de tiempo de espera en conexiones  

## 📁 Estructura del Proyecto

```
rpc-java-calculadora/
├── README.md                    # Este archivo
├── .gitignore                   # Archivos ignorados por Git
├── lib/                         # Librerías XML-RPC
│   ├── commons-logging-1.2.jar
│   ├── ws-commons-util-1.0.2.jar
│   ├── xmlrpc-client-3.1.3.jar
│   ├── xmlrpc-common-3.1.3.jar
│   └── xmlrpc-server-3.1.3.jar
├── src/                         # Código fuente
│   ├── Calculadora.java         # Lógica de operaciones matemáticas
│   ├── ClienteCalculadora.java  # Cliente RPC
│   └── ServidorCalculadora.java # Servidor RPC
└── build/                       # Clases compiladas (generado)
    ├── Calculadora.class
    ├── ClienteCalculadora.class
    └── ServidorCalculadora.class
```

## 🛠️ Requisitos del Sistema

- **Java Development Kit (JDK)** 8 o superior
- **Sistema Operativo**: Windows, macOS, o Linux
- **Red**: Puerto 8080 disponible (configurable)
- **Librerías**: XML-RPC (incluidas en `/lib`)

## ⚙️ Instalación y Compilación

### 1. Clonar o Descargar el Proyecto
```bash
# Clonar el repositorio
git clone https://github.com/ivanblueberry/rpc-java-calculadora.git
cd rpc-java-calculadora
```

### 2. Compilar el Proyecto
```bash
# Crear directorio para clases compiladas
mkdir -p build

# Compilar todos los archivos Java
javac -cp "lib/*" -d build src/*.java
```

### 3. Verificar la Compilación
```bash
ls -la build/
# Deberías ver: Calculadora.class, ClienteCalculadora.class, ServidorCalculadora.class
```

## 🚀 Ejecución del Sistema

### Paso 1: Ejecutar el Servidor

Abre una terminal y ejecuta:

```bash
java -cp "lib/*:build" ServidorCalculadora
```

**Salida esperada:**
```
===========================================
    SERVIDOR CALCULADORA RPC
===========================================
Iniciando servidor en el puerto 8080...
✓ Servidor XML-RPC iniciado correctamente
✓ Esperando peticiones de clientes...
✓ URL: http://localhost:8080/
===========================================

Para conectar desde otra computadora:
  - Usa la IP de este equipo en lugar de 'localhost'
  - Asegúrate que el puerto 8080 esté abierto

Presiona Ctrl+C para detener el servidor
```

### Paso 2: Ejecutar el Cliente

En **otra terminal**, ejecuta:

```bash
java -cp "lib/*:build" ClienteCalculadora
```

**Salida esperada:**
```
===========================================
    CLIENTE CALCULADORA RPC
===========================================

¿Desea conectarse a un servidor remoto? (s/n): n
Conectando a: http://localhost:8080/
✓ Cliente configurado correctamente

===========================================
         CALCULADORA RPC - MENÚ
===========================================
1. Sumar
2. Restar
3. Multiplicar
4. Dividir
5. Salir
===========================================
Seleccione una opción:
```

## 💡 Ejemplos de Uso

### Ejemplo 1: Suma Local
```
Seleccione una opción: 1
Ingrese el primer número: 15.5
Ingrese el segundo número: 24.3

→ Enviando petición al servidor...
✓ Respuesta recibida del servidor:
  15.5 + 24.3 = 39.8
```

### Ejemplo 2: División con Error
```
Seleccione una opción: 4
Ingrese el primer número: 10
Ingrese el segundo número: 0

→ Enviando petición al servidor...
✓ Respuesta recibida del servidor:
  10.0 / 0.0 = ERROR: División por cero no permitida
```

### Ejemplo 3: Conexión Remota
```
¿Desea conectarse a un servidor remoto? (s/n): s
Ingrese la IP del servidor (ej. 192.168.1.100): 192.168.1.50
Ingrese el puerto (por defecto 8080): 8080
Conectando a: http://192.168.1.50:8080/
```

## 🌐 Configuración de Red

### Para Conexiones Locales
- El servidor se ejecuta en `localhost:8080`
- No requiere configuración adicional

### Para Conexiones Remotas
1. **En el servidor**: Asegúrate de que el puerto 8080 esté abierto
2. **En el cliente**: Usa la IP real del servidor
3. **Firewall**: Permite tráfico en el puerto 8080

### Configuración de Puerto (Opcional)
Para cambiar el puerto, modifica la variable `puerto` en `ServidorCalculadora.java`:
```java
int puerto = 8080; // Cambiar aquí
```

## 🔧 Solución de Problemas

### Error: "Puerto ya está en uso"
```bash
# Verificar qué proceso usa el puerto 8080
lsof -i :8080

# Terminar el proceso si es necesario
kill -9 <PID>
```

### Error: "No se puede conectar al servidor"
- Verifica que el servidor esté ejecutándose
- Confirma la IP y puerto correctos
- Revisa configuración de firewall

### Error: "Clase no encontrada"
```bash
# Verifica que las clases estén compiladas
ls -la build/

# Recompila si es necesario
javac -cp "lib/*" -d build src/*.java
```

### APIs Deprecadas (Advertencia)
La advertencia sobre APIs deprecadas es normal y no afecta el funcionamiento:
```
Note: src/ClienteCalculadora.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
```

## 📚 Conceptos Técnicos

### RPC (Remote Procedure Call)
- Permite ejecutar funciones en un servidor remoto como si fueran locales
- El cliente hace llamadas que se ejecutan en el servidor
- Transparencia de ubicación para el desarrollador

### XML-RPC
- Protocolo basado en XML y HTTP
- Serialización automática de parámetros y resultados
- Multiplataforma e independiente del lenguaje

### Arquitectura Cliente-Servidor
- **Servidor**: Expone servicios y procesa peticiones
- **Cliente**: Consume servicios del servidor
- **Comunicación**: Bidireccional a través de red

## 👨‍💻 Desarrollo

### Agregar Nueva Operación
1. Agregar método en `Calculadora.java`
2. Actualizar menú en `ClienteCalculadora.java`
3. Agregar caso en `obtenerNombreMetodo()` y `getSimboloOperacion()`
4. Recompilar el proyecto

### Ejemplo: Potencia
```java
// En Calculadora.java
public double potencia(double base, double exponente) {
    System.out.println("Llamada a POTENCIA: " + base + "^" + exponente);
    return Math.pow(base, exponente);
}
```

## 📄 Licencia

Este proyecto es de uso educativo y está disponible bajo licencia libre para fines académicos.

## 🤝 Contribuciones

Si quieres contribuir al proyecto:
1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crea un Pull Request

---

**¡Gracias por usar la Calculadora RPC! 🚀**
