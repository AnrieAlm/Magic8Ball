# 🎱 Magic 8 Ball – JavaFX Application  

![JavaFX](https://img.shields.io/badge/JavaFX-UI_Framework-green)  
![Status](https://img.shields.io/badge/Status-Completed-brightgreen)  
![License](https://img.shields.io/badge/License-MIT-blue)  

---

## 📖 Overview  
This project is a **Magic 8 Ball simulation built with JavaFX**.  
Users can ask questions, click the ball, and receive random predictions.  

The application demonstrates:  
- **UI Design Principles** (alignment, proximity, consistency, feedback)  
- **JavaFX animations and interactivity**  
- **CSV file handling** (loading predictions from external files)  
- **Customization** with image selection and themes  

It was developed as part of the **HDC – HGP Assignment 01** at Griffith College Dublin.  

---

## ✨ Features  

- ✅ **Random Predictions**  
  - Standard Magic 8 Ball answers loaded from `magicAnswers.csv`  
  - Irish-themed answers via “Feeling Lucky” mode  

- ✅ **Irish Lucky Theme (Extra Feature)**  
  - Green Magic 8 Ball image + burgundy background  
  - Gaelic-style predictions  
  - Glow effect button  

- ✅ **Leprechaun Easter Egg (Extra Feature)**  
  - Triggered after 5 clicks of “Feeling Lucky”  
  - Animated leprechaun slides in with a special message  

- ✅ **Animations & Feedback**  
  - Ball shake effect when clicked  
  - Hover & glow effects for buttons  

- ✅ **Custom Image Selection**  
  - Users can replace the ball image (PNG only) via File → Change Image  

- ✅ **User-Friendly Design**  
  - MenuBar with File & About options  
  - Clear prompts and answer display  
  - Responsive layout and theming  

---

## 🖼️ Screenshots  

### Main UI  
![Main UI Screenshot](docs/screenshots/main-ui.png)  

### Irish Theme  
![Irish Theme Screenshot](docs/screenshots/irish-theme.png)  

### Leprechaun Easter Egg  
![Leprechaun Screenshot](docs/screenshots/leprechaun.png)  

---

## 🛠️ Tech Stack  

- **Java 17+**  
- **JavaFX 17+**  
- **Scene Builder** (for layout design, optional)  

**Layout Containers Used:**  
- `BorderPane (bpMain)` – main layout with 5 regions  
- `VBox (vbTop, vbBottom)` – for stacking controls vertically  
- `StackPane (imageStack)` – overlay answer text on ball image  

**Naming Convention:**  
- Hungarian notation + camelCase  
  - Example: `btnFeelingLucky`, `lblAnswer`, `imvBall`  

---

## 🚀 Getting Started  

### Prerequisites  
- [Java 17 or later]
- [JavaFX SDK]
- IDE (IntelliJ / Eclipse / NetBeans)  

### Run the Application  

```bash
# Clone repository
git clone https://github.com/your-AnrielAlm/Magic8BallFX.git

# Navigate into project
cd Magic8BallFX

# Compile & run (example using command line)
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls Magic8Ball.java
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls Magic8Ball
