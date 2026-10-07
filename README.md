# 🐦 Fluffy Bird Game

A simple 2D **Flappy Bird-style game** developed in **Java Swing** as a university project. The player controls a bird, avoids continuously moving pipes, earns points by passing pipes, and tries to survive without hitting the pipes or ground.

## 🎮 Features

* Bird movement and gravity
* Bird jumping using `SPACE`
* Mouse click control
* Pipe generation
* Random pipe gap heights
* Continuous pipe spawning
* Horizontal pipe movement
* Bird-pipe collision detection
* Bird-ground collision detection
* Score system
* Score display on screen
* Game Over system
* Final score display
* Restart system
* Simple game UI

## 🛠️ Technologies

* **Java**
* **Java Swing**
* **Java AWT**
* **ArrayList**
* **Java Timer**
* **Random**
* **Rectangle Collision Detection**

## 💻 Requirements

* JDK 17 or newer
* Any Java IDE such as **NetBeans**, IntelliJ IDEA, or Eclipse

## 🚀 How to Run

1. Open the project in a Java IDE.
2. Locate `java.java`.
3. Make sure the `java` class is inside the correct package.
4. Run the `main()` method.
5. The Fluffy Bird game window will open.

> **Note:** The main game class in this project is named `java.java` according to the current project structure.

## 🎮 Controls

| **Key / Input** | **Action**               |
| --------------- | ------------------------ |
| `SPACE`         | Make the bird fly upward |
| `Mouse Click`   | Make the bird fly upward |
| `R`             | Restart after Game Over  |

## 📁 Project Structure

```text
src/
├── Main.java
├── pipe.java
└── score/
    └── java.java
```

### Main Classes

* **Main.java** — Original/main project class.
* **pipe.java** — Contains the earlier pipe generation and movement implementation.
* **java.java** — Main Fluffy Bird game class containing bird movement, pipe generation, collision detection, scoring, Game Over, UI, and restart logic.
* **Pipe** — Inner class of `java.java` that stores pipe properties and controls horizontal pipe movement.

## 🐦 Bird System

The bird is controlled using gravity and an upward jump force.

```java
double verticalVelocity = 0.0;
double gravity = 0.5;
double jumpForce = -9.0;
```

Gravity continuously pulls the bird downward, while pressing `SPACE` or clicking the mouse applies the upward jump force.

```java
verticalVelocity = jumpForce;
```

The bird position is updated continuously through the game timer.

## 🟩 Pipe Generation

Pipes are stored using an `ArrayList`:

```java
ArrayList<Pipe> pipes = new ArrayList<>();
```

New pipes are created using:

```java
createPipe();
```

The pipe gap height is randomly selected using `Random`, which makes the position of the gaps different during gameplay.

Each pipe contains:

* `x` position
* `topHeight`
* `bottomY`
* `gap`
* `width`
* `speed`

## ↔️ Pipe Movement

The `Pipe` class contains:

```java
public void move() {
    x = x - speed;
}
```

This continuously moves the pipes from **right to left**.

When a pipe leaves the screen:

```java
return x + width < 0;
```

it is removed from the `ArrayList`.

## 💥 Collision Detection

Collision detection is implemented in the `checkCollision()` method.

The bird has a hitbox:

```java
Rectangle birdHitbox = new Rectangle(
    birdX,
    (int) birdY,
    birdWidth,
    birdHeight
);
```

Top and bottom pipes also have hitboxes.

Collision is checked using:

```java
birdHitbox.intersects(topPipeHitbox)
```

and

```java
birdHitbox.intersects(bottomPipeHitbox)
```

This detects:

* **Bird → Top Pipe**
* **Bird → Bottom Pipe**
* **Bird → Ground**

If a collision occurs:

```java
gameOver = true;
```

## 🏆 Score System

The score starts at:

```java
int score = 0;
```

When the bird completely passes a pipe:

```java
score++;
```

Each pipe has a `passed` variable:

```java
boolean passed = false;
```

This prevents the same pipe from increasing the score multiple times.

The current score is displayed using:

```java
g.drawString("Score: " + score, 20, 50);
```

## 🛑 Game Over System

When the bird hits a pipe or the ground:

```java
gameOver = true;
```

The game stops updating the bird and pipes.

The screen then displays:

```text
GAME OVER
Score: [current score]
Press R to Restart
```

## 🔄 Restart System

Pressing `R` after Game Over calls:

```java
restartGame();
```

The restart method:

* Resets bird position
* Resets velocity
* Resets score
* Clears existing pipes
* Resets pipe timer
* Creates a new pipe
* Sets `gameOver` to `false`

The game can then be played again.

---

# 📅 Weekly Development

### Week 1 — Planning & Research

Planned the **Fluffy Bird** game concept, gameplay mechanics, required components, and development timeline.

### Week 2 — Game Flow & Logic

Planned the game flow, bird movement, gravity, jumping, pipe obstacles, collision, scoring, and Game Over logic.

### Week 3 — Project Setup

Created the Java project in NetBeans and prepared the basic Swing game window.

### Week 4 — Bird & Game Panel

Created the game panel using `JPanel`, added the bird, game background, ground, and basic keyboard/mouse controls.

### Week 5 — Bird Movement

Implemented gravity, upward jump force, bird movement, game timer, and basic bird drawing.

### Week 6 — Pipe Generation

Added:

* Pipe objects/classes
* Obstacle spawning logic
* Random pipe gap heights
* Horizontal pipe movement
* Pipe removal after leaving the screen

### Week 7 — Collision Detection

Added:

* Bird hitbox
* Pipe hitboxes
* Bird vs. pipe collision
* Bird vs. ground collision
* Game state change after collision
* Collision detection using `Rectangle.intersects()`

### Week 8 — Score System & UI

Added:

* Score variable
* Pipe-passing score logic
* On-screen score display
* Game Over interface
* Final score display
* Restart instruction

### Week 9 — Testing & Debugging

Tested the game to identify and fix gameplay and logic issues, including pipe movement, collision detection, scoring, and restart behavior.

### Week 10 — Documentation & Submission

Prepared the project documentation, organized the source code, tested the final game, and prepared the project for submission and presentation.

---

# 🏆 Game Rules

* Bird flies upward when `SPACE` is pressed or the mouse is clicked.
* Gravity continuously pulls the bird downward.
* Avoid the top and bottom pipes.
* Avoid hitting the ground.
* Pass a pipe → **Score +1**
* Hitting a pipe → **Game Over**
* Hitting the ground → **Game Over**
* Press `R` → **Restart the game**

## 🔮 Future Improvements

* Better bird and pipe graphics
* Bird animation
* Sound effects and background music
* Start menu
* Pause button
* High-score system
* Multiple difficulty levels
* Different bird skins
* Day/night backgrounds
* Mobile/touch controls

## 👨‍💻 Project Information

**Project:** Fluffy Bird Game
**Language:** Java
**GUI:** Java Swing / AWT
**Game Type:** 2D Arcade Game
**Development Environment:** NetBeans
**Type:** University Project
**Status:** Completed
