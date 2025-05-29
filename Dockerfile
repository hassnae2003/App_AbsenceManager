# Utilise une image de base légère avec OpenJDK (nécessaire pour Kotlin/Java)
FROM openjdk:17-jdk-slim

# Crée un dossier dans le conteneur pour y copier l'apk
WORKDIR /app

# Copie l'APK compilé dans le conteneur
COPY app/build/outputs/apk/debug/app-debug.apk ./app-debug.apk

# Commande pour garder le conteneur actif (sinon il s'arrête tout de suite)
CMD ["tail", "-f", "/dev/null"]

