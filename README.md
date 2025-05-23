# Meeting Analysis

This project is a Spring Boot application for analyzing meeting videos. It extracts audio from uploaded video files, transcribes the audio using Whisper, and generates a structured JSON summary using an AI model (Ollama).

## Features

- Upload a meeting video file (`.mp4`)
- Extracts audio using `ffmpeg`
- Transcribes audio to text using a Python Whisper script
- Analyzes the transcript with an AI model (Ollama) to generate:
  - Summary
  - Tasks
  - Decisions
  - Open questions

## API Endpoints

### Analyze Transcript

- **POST** `/api/meeting/analyze`
- **Body:**  
  ```json
  {
    "transcript": "Texto de la reunión..."
  }
  ```
- **Response:**  
  ```json
  {
    "summary": "Resumen breve",
    "task": ["Tarea 1", "Tarea 2"],
    "decisions": ["Decisión A", "Decisión B"],
    "openQuestions": ["¿Qué pasa con X?", "¿Quién hará Y?"]
  }
  ```

### Analyze Video File

- **POST** `/api/meeting/upload`
- **Form Data:**  
  - `file`: video file (`.mp4`)
- **Response:**  
  Same as above.

## Requirements

- Java 21+
- Python 3 with [Whisper](https://github.com/openai/whisper) installed
- `ffmpeg` installed and available in PATH
- Ollama server running (see `application.properties` for configuration)

## Setup

1. **Clone the repository**
2. **Install dependencies:**
   - Python: `pip install openai-whisper`
   - System: `ffmpeg`
   - Ollama: [Install Ollama](https://ollama.com/)
3. **Configure application properties** in [`src/main/resources/application.properties`](src/main/resources/application.properties)
4. **Build and run:**
   ```sh
   ./gradlew bootRun
   ```

## File Structure

- `src/main/java/com/amoelcodigo/meetinganalysis/` - Main Java source code
- `src/main/resources/scripts/transcribe.py` - Python script for transcription
- `src/main/resources/application.properties` - Application configuration

## License

This project is licensed under the Apache License 2.0.

---

# Meeting Analysis (🇪🇸)

Este proyecto es una aplicación Spring Boot para analizar videos de reuniones. Extrae el audio de archivos de video subidos, transcribe el audio usando Whisper y genera un resumen estructurado en JSON utilizando un modelo de IA (Ollama).

## Características

- Subida de archivos de video (`.mp4`)
- Extracción de audio usando `ffmpeg`
- Transcripción de audio a texto con un script Python (Whisper)
- Análisis del transcript con IA (Ollama) para generar:
  - Resumen
  - Tareas
  - Decisiones
  - Preguntas abiertas

## Endpoints principales

### Analizar transcript

- **POST** `/api/meeting/analyze`
- **Body:**  
  ```json
  {
    "transcript": "Texto de la reunión..."
  }
  ```
- **Respuesta:**  
  ```json
  {
    "summary": "Resumen breve",
    "task": ["Tarea 1", "Tarea 2"],
    "decisions": ["Decisión A", "Decisión B"],
    "openQuestions": ["¿Qué pasa con X?", "¿Quién hará Y?"]
  }
  ```

### Analizar archivo de video

- **POST** `/api/meeting/upload`
- **Form Data:**  
  - `file`: archivo de video (`.mp4`)
- **Respuesta:**  
  Igual que el endpoint anterior.

## Requisitos

- Java 21+
- Python 3 con [Whisper](https://github.com/openai/whisper) instalado
- `ffmpeg` instalado y disponible en el PATH
- Servidor Ollama corriendo (ver configuración en `application.properties`)

## Instalación

1. **Clona el repositorio**
2. **Instala dependencias:**
   - Python: `pip install openai-whisper`
   - Sistema: `ffmpeg`
   - Ollama: [Instalar Ollama](https://ollama.com/)
3. **Configura las propiedades** en `src/main/resources/application.properties`
4. **Compila y ejecuta:**
   ```sh
   ./gradlew bootRun
   ```

## Estructura de archivos

- `src/main/java/com/amoelcodigo/meetinganalysis/` - Código fuente Java
- `src/main/resources/scripts/transcribe.py` - Script Python para transcripción
- `src/main/resources/application.properties` - Configuración

## Licencia

Este proyecto está bajo la Licencia amoelcodigo.com
