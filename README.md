# Final Project – Android Movie & Person Directory

A modern Android application built with **Jetpack Compose**, **Hilt**, **Retrofit**, and **CameraX**.  
It showcases popular movies, detailed information about each title, actor/actress profiles, and includes an in‑app camera screen.  The project is intended as a clean, end‑to‑end reference for building a multi‑feature Compose app with a layered architecture.

> **Target SDK 34 • Minimum SDK 24 • Kotlin 1.9.23**

---

## ✨ Features

| Category | Highlights |
|----------|------------|
| **Movies** | Browse a paginated list of movies, pull to refresh, lazy loading. |
| **Movie details** | Full‑screen Compose screen with poster, synopsis, cast list, trailers. |
| **Persons** | Separate API call + list screen to explore actors / crew members. |
| **Camera** | CameraX photo capture with preview, using the system‑scoped storage workflow. |
| **DI & Networking** | Hilt modules with Retrofit 2.11, OkHttp interceptor, kotlinx‑serialization (JSON). |
| **Navigation** | Navigation‑Compose with a bottom bar and typed routes. |
| **Theming** | Material 3 dark/light themes, adaptive icons. |
| **Build goodies** | Kotlin Kapt, Jetpack BOM, configuration‑cache enabled, proguard template. |

---

## 🏛 Project Structure
```
com.linkdev.finalproject
├── data
│   └── remote
│       ├── networkformoviesapi/
│       │   └── ApiMovies, NetworkConstants, response/*
│       └── networkforpersonapi/
│           └── ApiPerson, PersonNetworkConstants, response/*
├── di/               ← Hilt modules (AppModule, MoviesNetworkModule, ...)
├── navigation/       ← BottomBarScreen, BottomNavGraph, Routes
├── screens/
│   ├── camerascreen/ ← CameraRepository, CameraScreen.kt, CameraViewModel
│   ├── moviesscreen/ ← MoviesRepository, MoviesScreen.kt, MoviesViewModel
│   ├── moviedetailsscreen/
│   └── personscreen/
└── ui/theme/         ← Color, Typography, Shape, Themes
```

Each *feature* keeps its Repository ↔️ ViewModel ↔️ Screen in one folder to keep changes localized.

---

## 🔧 Prerequisites

| Tool | Minimum version |
|------|-----------------|
| **Android Studio** | Hedgehog (AGP 8.4+) |
| **JDK** | 17 |
| **Gradle** | Wrapper distributed with the repo |
| **ADB / Device** | API 24 – API 34 (Camera strongly recommended) |

---

## 🚀 Getting Started

1. **Clone** the repository
   ```bash
   git clone https://github.com/<your-org>/final-project.git
   cd final-project
   ```
2. **Open** the root *build.gradle* in Android Studio and let it import Gradle settings.
3. **Add API keys** – create a *local.properties* file and insert:
   ```properties
   TMDB_API_KEY=xxxxxxxxxxxxxxxx
   ```
4. **Run** → *app* on a device or emulator.

If you need exact alarms or scoped-media permissions on Android 14, the app will prompt you automatically.

---

## 🧪 Testing
```bash
./gradlew test                 # JVM tests
./gradlew connectedAndroidTest # Instrumented Compose tests
```
> ✨ Coming soon: MockWebServer UI tests & Kotlin Flow turbine tests.

---

## 📸 Screenshots

| Home (Movies) | Movie Details | Person | Camera |
|---------------|--------------|--------|--------|
| _placeholder_ | _placeholder_ | _placeholder_ | _placeholder_ |

Add your own screenshots in */docs/img* and update the table above.

---

## 🖇 Useful Gradle Tasks

| Command | What it does |
|---------|--------------|
| `./gradlew :app:dependencies` | Inspect dependency graph (look for duplicates). |
| `./gradlew lintRelease`       | Static analysis (Compose, coroutines, injections). |
| `./gradlew assembleRelease`   | Minified release APK (no Play Signing). |

---

## 🎯 Roadmap / TODO

- [ ] Migrate **kapt → KSP** for faster builds.
- [ ] Replace Glide‑Compose with Coil only (deduplicate image loaders).
- [ ] Paging 3 integration for movie list.
- [ ] Unit tests for ViewModels (turbine).  
  *(Feel free to open issues / PRs!)*

---

## 🤝 Contributing

1. Fork the repo & create your branch: `git checkout -b feature/YourFeature`  
2. Commit your changes: `git commit -m "Add awesome feature"`  
3. Push to the branch: `git push origin feature/YourFeature`  
4. Open a Pull Request.

This project follows the **Conventional Commits** spec and uses **Ktlint** & **Detekt** in CI.

---

## 🔒 License
```
MIT License
Copyright (c) 2025 <Saleh Ayoub>
```
> **Disclaimer:** This is a learning project and is *not* affiliated with TMDB or any external API provider.

---

### 📬 Support / Contact
For questions, reach out via the repo *Discussions* tab or open an *Issue*.
