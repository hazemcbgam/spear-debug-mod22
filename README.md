# Spear & ViaBackwards Fix (Minecraft Fabric 1.20.1)

A custom Fabric mod designed to solve:
1. **ViaBackwards ID crash desync**: Safe Client-Side registry translation preventing game crashes when server sends modern blocks/items.
2. **Spear Sprint-Slowness attack bug**: Redirects self-slowness into an offensive target-debuff weapon modifier.
3. **Newer block and item textures**: Integrates 2D asset routing templates.

## 🛠️ How to Compile and Install

This project contains a standard Fabric Mod Gradle workspace structure.

### 1. Prerequisites
- **Java Development Kit (JDK) 17** or newer installed.
- Fabric Loader installed on your Minecraft launcher for version **1.20.1**.

### 2. Compilation
Open your terminal inside this project's root folder and run:
```bash
# For Linux/macOS
./gradlew build

# For Windows
gradlew.bat build
```

The compiled mod jar will be created in:
```
build/libs/spearfix-1.0.0.jar
```

### 3. Setup
1. Copy the compiled jar and place it in your local `mods` folder:
   - Windows: ` %appdata%\.minecraft\mods `
   - macOS: ` ~/Library/Application Support/minecraft/mods `
   - Linux: ` ~/.minecraft/mods `
2. Launch Minecraft 1.20.1 under the Fabric Profile and enjoy!
