import whisper
import sys

print("Inicio de la transcripción...", flush=True)

model = whisper.load_model("base")
result = model.transcribe(sys.argv[1], language="es")

print("Transcripción completada.", flush=True)
print(result["text"], flush=True)
