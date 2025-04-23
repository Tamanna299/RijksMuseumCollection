# Rijksmuseum Collection Search

An Android app built with Jetpack Compose and MVVM architecture that lets users explore artworks from the [Rijksmuseum API](https://data.rijksmuseum.nl/). Users can search for artworks, browse results in a paginated list, and view detailed information about each piece.

##  Features

- **Search Artworks**: Enter a keyword to explore art from the Rijksmuseum collection.
- **Paginated Results**: Smooth, lazy loading of search results using pagination.
-  **Detail Screen**: View detailed information including:
      - Artwork image
      - Maker and manufacture date
      - Description
-  **Modern UI**: Built entirely with Jetpack Compose for a responsive and modern look.
-  **Unit Tests**: Unit-tested with JUnit.

##  Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **MVVM Architecture**
- **Coroutines + Flow**
- **Retrofit** for network requests
- **Dagger Hilt** for dependency injection
- **JUnit** for testing
  
##  Architecture Pattern

The app follows the **Model-View-ViewModel (MVVM)** architecture pattern. This pattern was chosen to ensure:
- Clear separation of concerns between UI and business logic.
- Improved testability of core logic (e.g., ViewModels).
- Better lifecycle awareness with Jetpack components like `LiveData` and `StateFlow`.
- Scalability as more features or screens are added.

By leveraging MVVM along with Hilt for dependency injection and Kotlin Coroutines for asynchronous tasks, the codebase remains clean, modular, and easy to maintain.

##  Future Improvements

- Add offline caching with Room or DataStore.
- Add filters or categories to refine search results.
- Support for favorite artworks with persistent storage.
- Localization for multilingual support.

##  Requirements
To build and run this project, ensure you have the following installed:

- **Android Studio Hedgehog (2023.1.1+)** (or newer)
- **Gradle 8.2+**
- **Kotlin 1.9+**
- **Android Gradle Plugin 8.1+**
- **Java 17** (recommended for latest Android Studio compatibility)

> 💡 If you're using an older version of Android Studio, you may encounter issues opening or syncing the project. Updating to the latest stable version is recommended.

> The Rijksmuseum API key is already provided in `Constants.kt`, so no additional setup is required.

## 📄 License

This project is for demo/assessment purposes only.
