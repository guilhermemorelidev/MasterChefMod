# **MasterChef Mod (Forge 1.7.10)**

This is a culinary mod for Minecraft 1.7.10 originally created by **Minetronic**.

## **⚠️ Original Issue**

When starting a world, the game would crash with the following error:
```
java.lang.NullPointerException: Ticking memory connection 
at cpw.mods.fml.common.network.internal.FMLProxyPacket.func_148833_a(FMLProxyPacket.java:101)
```

This error occurred due to a network failure when fetching the latest mod version through the `JavaGetUrl` class. The system didn't properly handle connection error cases, resulting in `null` during network packet processing.

## **🛠️ Fix Process**

### 1. **Deobfuscation with BON2**
The original MasterChef mod `.jar` was obfuscated (with unreadable names like `func_148833_a`). To restore readable names, I used **BON2 (tterrag1098)**, a tool designed for 1.7.10 mods with MCP support.

### 2. **Decompilation with CFR**
After deobfuscation, I used the **CFR** decompiler to obtain the mod's `.java` source code.

### 3. **Project Restructuring**
* Organized packages and files into a ForgeGradle-compatible project.

### 4. **Code Fixes**
* Fixed typing errors with `HashMap`.
* Added exception handling in `JavaGetUrl` and `MessageChecker` classes.
* Fixed `ItemChefGarb` logic to prevent `ConcurrentModificationException`.

## **✅ What Was Fixed**

### **🔧 File: `ItemChefGarb.java`**
* Fixed incorrect map typing:
```java
HashMap<Object, Object> → HashMap<String, ExtraSpeed>
```
* Fixed `extraFurnaceSpeed` variable usage to properly accept typed maps.
* Fixed `purgeList()` logic to prevent `ConcurrentModificationException`.

### **🌐 File: `JavaGetUrl.java`**
* Added network exception handling.
* Fixed `InputStream` access.
* Fixed separation between `VERSION` and `EXTRA_INFO`.

## **🛠️ Build Environment**

This project uses:
* **ForgeGradle** maintained by anatawa12, adapted to compile legacy mods (like 1.7.10) on modern Gradle setups: https://github.com/anatawa12/ForgeGradle-example
* **Java 8** (recommended: Oracle JDK 1.8.0_202)
* **Gradle 7.x+**

## **📦 How to Compile**

```bash
./gradlew build
```

After building, the generated `.jar` will be in `build/libs/`.

## **📥 How to Install**

1. Install Forge 1.7.10 (recommended: 10.13.4.1614)
2. Place the `.jar` in `.minecraft/mods`
3. Start Minecraft with Forge

## **👥 Credits**

* 👨‍🍳 **Minetronic** — Original mod author
* 🛠️ **anatawa12** — Modern ForgeGradle used for compilation
* 🔁 **Fixes and maintenance:** This fork was fixed with focus on maintaining mod compatibility, which was no longer working.

## **🤝 Contributing**

Suggestions, improvements, or fixes are always welcome.

You can help with:
* New recipes or items
* Graphics or performance improvements
* Translations and localization
* Compatibility with other mods
