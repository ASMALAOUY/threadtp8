# LabThreadsAsyncTask

Application Android pédagogique démontrant l'utilisation des **threads** et **AsyncTask** pour éviter de bloquer l’interface utilisateur (UI).  
Ce laboratoire montre comment effectuer des traitements longs (chargement d’image, calcul lourd) sans provoquer d’ANR (Application Not Responding).

---

##  Objectifs pédagogiques

- Comprendre la différence entre **UI Thread** (Main Thread) et **Worker Thread**.
- Créer un thread manuel avec `Runnable` et `Handler` pour revenir sur l’UI.
- Mettre à jour l’interface depuis un thread de fond avec `View.post()` et `Handler`.
- Utiliser `AsyncTask` (approche pédagogique) avec gestion de progression.
- Éviter les erreurs classiques : UI bloquée, modification d’une vue hors du thread principal.

---

##  Fonctionnalités

- **Charger image (Thread)** : simule un chargement long (1 seconde) dans un thread séparé, puis affiche l’image.
- **Calcul lourd (AsyncTask)** : effectue un calcul intensif en arrière-plan avec mise à jour d’une `ProgressBar` (0 à 100%).
- **Afficher Toast** : test de réactivité – le Toast s’affiche immédiatement même pendant les traitements longs.

L’interface reste fluide à tout moment.

---

##  Technologies utilisées

- Langage : **Java**
- Framework : **Android SDK** (API 21+)
- Composants : `Thread`, `Handler`, `AsyncTask`, `ProgressBar`, `ImageView`, `Toast`

---

##  Aperçu

| Écran principal | Chargement image | Calcul lourd |
|----------------|------------------|---------------|
| ![main](screenshots/main.png) | ![loading](screenshots/loading.png) | ![calcul](screenshots/calcul.png) |

*(Ajoutez vos propres captures dans un dossier `screenshots/`)*

---

##  Demo



https://github.com/user-attachments/assets/16f6711e-2bb0-48f9-9b94-114997614d89

