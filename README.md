# 📇 Gestor de Contactos en Java (Swing + Archivos)

Una aplicación de escritorio desarrollada en **Java Swing** que permite **gestionar una lista de contactos**.  
El programa ofrece una interfaz gráfica sencilla para **agregar, modificar, eliminar y visualizar contactos**, con persistencia de datos en un archivo de texto (`contactos.txt`).

---

## 🚀 Características
- **Interfaz gráfica (Swing)** intuitiva.
- **Gestión de contactos**:
  - ➕ Agregar un nuevo contacto.
  - 📝 Modificar un contacto existente.
  - ❌ Eliminar contactos seleccionados.
  - 📋 Visualizar todos los contactos en una tabla.
- **Persistencia de datos**:
  - Los contactos se guardan automáticamente en un archivo `contactos.txt`.
  - Al iniciar, los contactos se cargan desde el archivo.

---

## 🛠️ Tecnologías utilizadas
- **Lenguaje:** Java 8+  
- **Interfaz gráfica:** Swing (`JFrame`, `JPanel`, `JTable`, `JButton`, etc.)  
- **Manejo de archivos:** `BufferedReader` y `BufferedWriter`  

---

## ▶️ Ejecución de la aplicación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/FredyGus/Gestor-De-Contactos.git
   cd Gestor-De-Contactos
   
2. **Si se usa Maven**
   ```
   mvn clean package
   
   ```
   **O directamente**
   ```
   javac src/main/java/com/gestor/gestordecontactos/*.java -d out
   
   ```
3. **Ejecutar la aplicacion**
   ```
   java -cp out com.gestor.gestordecontactos.GestorDeContactos

   ```
  
## 📸 Capturas de Pantalla

### 🖼️ Interfaz Principal
![Interfaz Principal](capturas/interfaz.png)


### ➕ Agregar Contacto
![Agregar 1](capturas/agregar1.png)
![Agregar 2](capturas/agregar2.png)



### ❌ Eliminar Contacto
![Eliminar 1](capturas/eliminar1.png)
![Eliminar 2](capturas/eliminar2.png)


### 📝 Modificar Contacto
![Modificar 1](capturas/modificar1.png)
![Modificar 2](capturas/modificar2.png)
![Modificar 3](capturas/modificar3.png)
