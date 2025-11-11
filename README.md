# 🎵 Music Player KMP App

A simple **cross-platform music streaming app** built with **Kotlin Multiplatform (KMP)** and **Jetpack Compose Multiplatform**.  
The app streams trending songs fetched from a public music API and allows playback using Android’s native **MediaPlayer**.

---

## 🚀 How to Run the App
### Get the Apk from release and install it, OR
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/musicplayer-kmp.git
2. Open the project in Android Studio.
3. Let Gradle sync automatically.
4. Run the Android target:
   ```bash
   ./gradlew :composeApp:installDebug
   or generate a signed APK via:
   ```bash
   ./gradlew :composeApp:assembleRelease
   
## 🌐 API Used

### **[Audius Public API](https://docs.audius.org/api)**

- **Endpoint:** `https://discoveryprovider.audius.co/v1/tracks/trending`
- **Authentication:** Not required  
- **Returns:** A list of up to 100 trending tracks with metadata including:
  - `id` – Unique track identifier  
  - `title` – Name of the track  
  - `user.name` – Artist name  
  - `duration` – Track length in seconds  
  - `artwork.480x480` – Artwork URL for thumbnail  
  - `permalink` – Track page link on Audius  

---

### 🧠 Why Audius?

- ✅ Free & public API — no key or OAuth setup required  
- ✅ Provides **direct audio URLs** suitable for streaming  
- ✅ Includes detailed metadata (artist, artwork, duration, genre, etc.)  
- ✅ Stable JSON response structure — easy to parse in Kotlin data classes  
- ✅ Great fit for open-source and educational projects

## 💭 Assumptions Made

- The `duration` field is in **seconds**, converted to a readable `mm:ss` format in the UI.  
- Audio playback works **only on Android**, since it relies on the native `MediaPlayer` API.  
- Each play request streams **directly from the network** — no local caching implemented.  
- A short buffering delay (a few seconds) before playback is **expected API behavior**.  
- Some Network or playback errors are shown in the UI and some are logged to the console for easier debugging.

## 🧱 Tech Stack

| Layer | Technology |
|-------|-------------|
| **UI** | Jetpack Compose Multiplatform |
| **State Management** | Kotlin Coroutines · StateFlow |
| **Dependency Injection** | Koin |
| **Networking** | Ktor Client |
| **Playback (Android-only)** | Android MediaPlayer |
| **Image Loading** | Coil 3 (Ktor Network Fetcher) |
| **Language** | Kotlin (KMP) |




   
