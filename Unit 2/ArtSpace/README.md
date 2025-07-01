# Art Space - Android App (Jetpack Compose)

This is an Android sample app built with **Jetpack Compose**, inspired by Google's Android Basics "Art Space" project.

It allows users to view and navigate through a small gallery of artworks, displaying each artwork with its title and artist information.

---

## Features

- Compose-based UI with Material 3
- Display artwork, artist name, and year
- Navigation between artworks using "Previous" and "Next" buttons
- Scrollable layout with responsive spacing
- Optimized for different screen sizes

---

## Screenshots

<img src="https://raw.githubusercontent.com/runningpig66/PicGo/master/202507012315428.png" alt="Screenshot_20250701-231340" style="zoom:50%;" />

---

## Version History

| Version | Description                                               |
|---------|-----------------------------------------------------------|
| `v0.1`  | Implement scrollable layout with static content           |
| `v0.2`  | Enable image navigation with Previous/Next buttons        |
| `v0.3`  | Create alternate layout without scrolling (responsive UI) |

---

## Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- State management with `remember` and `mutableStateOf`
- `painterResource()` for loading local drawable images

---

## Project Structure

MainActivity.kt           // App entry point
ArtSpaceApp()          // Main Composable
ArtImageDisplay()     // Image rendering with Surface
ArtDescription()         // Title + Artist info
ArtNavigationControls()  // Navigation buttons


## Keywords

Android, Jetpack Compose, Kotlin, ArtSpace, Android Basics, Google, UI, Responsive Layout, Gallery App

## License

This project is released under the MIT License. See LICENSE file for details.

## Author

TIAN YANYU
Email: runningpig66@gmail.com
GitHub: https://github.com/runningpig66
Location: Japan / China