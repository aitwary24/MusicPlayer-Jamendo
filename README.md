# Jamendo Music Player

Android music player assignment app using **Jamendo API**, **Kotlin**, **MVVM**, **Ktor**, **Coroutines**, **Room**, and **MediaPlayer**.

## Features

- Fetches a list of audio tracks from **Jamendo Tracks API** with:
  - Title, Artist, Duration, Thumbnail
- Loading & error states
- Sorting:
  - By name (A–Z)
  - By duration (shortest to longest)
- Audio playback with `MediaPlayer`:
  - Play / Pause
  - Current position + total duration
  - Proper lifecycle and error handling
- Uses **Ktor** for networking and **Coroutines** for async work
- MVVM architecture with `ViewModel` + `StateFlow`
- Handles configuration changes via ViewModel

### Bonus / Optional Implemented

- Seek bar to scrub through audio
- Background playback using a foreground `Service`
- Next/Previous track functionality
- Polished Material UI
- Local caching of API responses using Room
- Thumbnails / album art using Coil
- Dark mode (Theme.MaterialComponents.DayNight)
- Offline mode: if network fails but cached data exists, it will be shown with an offline banner

## Jamendo API

This project uses the Jamendo **tracks** endpoint:

- Base URL: `https://api.jamendo.com/v3.0/tracks`
- Required parameter: `client_id`
- Other parameters: `format=json`, `limit`, `audioformat`, `order`, `include` etc.

See the official docs for details.

### Getting a client_id

1. Sign up for a developer account on Jamendo
2. Create an app to obtain your **client_id**
3. Open `app/build.gradle` and replace:

   ```groovy
   buildConfigField "String", "JAMENDO_CLIENT_ID", "\"YOUR_JAMENDO_CLIENT_ID\""
   ```

   with your actual key.

## How to Run

1. Open this folder in **Android Studio**
2. Make sure you have:
   - Android Gradle Plugin 8.x
   - JDK 17
3. Sync Gradle
4. Plug an Android device or start an emulator
5. Run the `app` configuration

## Assumptions

- Only a single list of popular tracks is displayed (no pagination).
- Background playback is handled via a foreground service with a media-style notification.
- Offline mode uses the last successful network response stored in Room.
- Error messages are kept user-friendly and short.

## Notes

- This is structured to match a real-world, professional Android app:
  - Clear separation of data, domain, and UI layers
  - ViewModel + StateFlow for reactive UI updates
  - Ktor for networking
  - Room for persistence
  - MediaPlayer wrapped in a dedicated Service
