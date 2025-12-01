# Sistema de Contenido Audiovisual  
Proyecto Académico – Programación Orientada a Objetos  
Unidad 4 – Programación Limpia

## 🧩 Descripción
Este proyecto implementa un sistema para gestionar información de contenidos audiovisuales:
- Películas  
- Series de TV  
- Documentales  
- Actores  
- Temporadas  
- Investigadores  

El sistema está desarrollado aplicando:
- Principios **SOLID**
- Patrón **MVC**
- **Manejo de archivos CSV**
- **Refactorización y código limpio**
- **Pruebas unitarias con JUnit 5**
  

---
## 🔧 Instalación y ejecución del proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/bmegsd95/Molina_Bolivar_POO_U4.git
cd Molina_Bolivar_POO_U4
2. Importar en Eclipse

Abrir Eclipse.

Ir a File → Import…

Seleccionar Existing Projects into Workspace.

En Select root directory, buscar la carpeta clonada Molina_Bolivar_POO_U4.

Marcar el proyecto y presionar Finish.

Requisitos: JDK 17 o superior configurado en Eclipse.

3. Ejecutar la aplicación

En el panel de paquetes, abrir src/poo/app/PruebaAudioVisual.java.

Clic derecho sobre la clase → Run As → Java Application.

Se abrirá el menú del sistema en la consola de Eclipse.

4. Ejecutar las pruebas unitarias

Clic derecho sobre la carpeta test.

Seleccionar Run As → JUnit Test.

Verificar que la barra de JUnit se muestre en verde, indicando que todas las pruebas pasan correctamente.

---

## 📁 Estructura del Proyecto

src/
├── poo.app/ # Clase main (PruebaAudioVisual)
├── poo.model/ # Modelo del dominio
├── poo.repository/ # Repositorios (lectura/escritura CSV)
├── poo.controller/ # Lógica de negocio
└── poo.view/ # Interfaz por consola (Vista)
test/
└── poo/ # Pruebas unitarias JUnit 5
data/
├── contenidos.csv
├── actores.csv
├── temporadas.csv
└── investigadores.csv
---

## 🧠 Principios SOLID Aplicados

### ✔️ SRP – Single Responsibility Principle  
Cada clase tiene una única responsabilidad.

### ✔️ OCP – Open/Closed Principle  
Nuevos tipos de contenido pueden añadirse sin modificar código existente.

### ✔️ LSP – Liskov Substitution Principle  
Pelicula, SerieTV y Documental pueden ser tratadas como ContenidoAudiovisual.

### ✔️ ISP – Interface Segregation Principle  
Interfaces específicas por tipo de dato (ContenidoRepository, ActorRepository, etc).

### ✔️ DIP – Dependency Inversion Principle  
El controlador depende de interfaces, no de implementaciones concretas.

---

## 📂 Persistencia con Archivos CSV

El proyecto utiliza archivos CSV para almacenar datos.  
Los repositorios leen y escriben usando `Files.newBufferedWriter`, `Files.readAllLines` y `Path`.

---

## 🖥️ Ejecución

### Ejecutar el programa:

### El menú permite:
- Crear/listar contenidos
- Crear/listar actores, temporadas e investigadores
- Guardar datos y salir

---

## 🧪 Pruebas Unitarias (JUnit 5)

Incluye pruebas para:
- Modelo (PeliculaTest, ActorTest, TemporadaTest, etc.)
- Repositorios (CsvContenidoRepositoryTest)
- Controlador (SistemaContenidoControllerTest)

Para ejecutar:

### El menú permite:
- Crear/listar contenidos
- Crear/listar actores, temporadas e investigadores
- Guardar datos y salir

---



## 📎 Autor
**Molina Bolívar**


