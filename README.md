# 💬 Chat Cliente-Servidor Concurrente en Java (Sockets)

## 📌 Descripción
Este proyecto implementa una aplicación **cliente-servidor en Java** utilizando **sockets TCP**.  
Permite la comunicación mediante mensajes de texto entre uno o varios clientes y un servidor.

La práctica se desarrolla en **cuatro fases**, con el objetivo de entender el bloqueo en servidores monohilo, la comunicación iterativa y la concurrencia mediante hilos.

---

## 🛠️ Tecnologías utilizadas
- Java SE
- Sockets TCP (`java.net`)
- Entrada/Salida (`java.io`)
- Hilos (`Thread`, `Runnable`)
- IntelliJ IDEA
- Maven

---


---

## 🚀 Fases de la práctica

### 🔹 Fase 1 – Análisis del bloqueo
- Servidor monohilo.
- Se introduce un `Thread.sleep(15000)` tras aceptar un cliente.
- Se demuestra que el servidor queda bloqueado y no puede atender nuevas conexiones.

---

### 🔹 Fase 2 – Comunicación iterativa 1 a 1
- Comunicación continua entre cliente y servidor.
- Uso de un bucle `while`.
- La comunicación finaliza cuando el cliente envía el mensaje **`FIN`**.

---

### 🔹 Fase 3 – Servidor concurrente
- El servidor acepta múltiples clientes simultáneamente.
- Por cada cliente:
  - se crea un objeto `GestorCliente`
  - se lanza un hilo independiente
- Los clientes se atienden en paralelo.

---

### 🔹 Fase 4 – Mejoras adicionales
- Se muestra la **dirección IP del cliente** al conectarse.
- Se controla la **desconexión abrupta** del cliente sin enviar `FIN`.

---

## 🧩 Clases principales

### 📌 ClienteChat
- Se conecta al servidor (host y puerto configurables).
- Lee mensajes desde teclado.
- Envía mensajes al servidor y muestra la respuesta.
- Finaliza al escribir `FIN`.

### 📌 ServidorFase3y4
- Escucha conexiones en un puerto determinado.
- Acepta clientes de forma continua.
- Crea un hilo por cada cliente conectado.

### 📌 GestorCliente
- Implementa `Runnable`.
- Gestiona la comunicación con un único cliente.
- Maneja correctamente:
  - el protocolo `FIN`
  - errores de entrada/salida
  - desconexiones inesperadas

---

## ▶️ Ejecución

### 1️⃣ Iniciar el servidor
```bash
java ServidorFase3y4
