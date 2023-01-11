# Minecraft-Fireworks-2023
### What is Minecraft-Fireworks-2023
Minecraft-fireworks 2023 is a Minecraft server plugin. This plugin supports all Minecraft versions from 1.14 to 1.19.3. The plug-in is only supported for installation on Java version servers because it is developed according to the Spigot API.

After installing this plug-in, your server will be able to automatically set off fireworks according to the rules you set. The plug-in also supports fireworks effects with specified shapes, heights, flicker, trail, and unlimited color mixes.

### Features
#### Lightweight
This plug-in does not consume too much server resources, but whether it is a heavy load on your server depends on your Settings. This plugin can customize the interval between fireworks. Normally, if you set it within a reasonable range, there will be no load on the server. But if you go to the extreme of firing 100 fireworks a second, your server may be at risk of going down.

#### Support almost all new version of MC
The developers of this plugin focus on changes and updates to the Spigot API. These will determine whether the code compiles with new versions of Minecraft. This plugin is dedicated to keeping up with new versions of Minecraft.

#### Multilanguage support
You can easily change the language by changing the name of the language file to which the configuration file points. However, some other language files (except English and simplified Chinese) may require you to translate them yourself. If you would like to help us translate, please feel free to contact us.

### Download and Install on your server
Click Release on the right to download the latest version, and copy the `.jar` file into your `./plugins` folder. Note: Because develop is a volatile development branch, we do not provide automatic build services. 
If you are interested in developing a version, you are welcome to clone the repository and build your own.

### Self-build method
Clone the repository, then execute `mvn clean package` in the local root directory (provided you have Maven installed), and you can see the built plugin in the `./target`  directory.

### For Chinese Mainland Users
As developers in China, we are well aware that access to GitHub can be unstable in mainland China. If you are Chinese users, you can enter http://gitlab.glowberry.space/glowberry-community/minecraft-fireworks-2023/-/tree/master for the page.

### Usage Instructions
- `/fw create <PointName> [randomly]` : Create a fireworks point at your location, only Player in game can execute. ("`randomly`" is a optional argument). If you use `randomly`, the point will generate with a random property. If not, the point will generarte with configuration in `default.yml` in your Datafoler.
- `/fw launch <PointName1> [PointName2] ...` : Launch the specific fireworks point using point name. At least 1 of point name should be used.
- `/fw createLoop <loopName> <interval=?> <pointName1> [PointName2] ...` : Create a loop, where interval=? indicates the interval time of each point in this loop. Please replace "?" with a Integer value, whose unit is `ticks`.
- `/fw startLoop <loopName1> [loopName2] ...` : Start loop(s) by loop name.
- `/fw loops` : Inspect the status and information of all loops.
- `/fw update` : Check update. ***(Note: Current version is not able to check automatically, please check update manually in this page)***
- `/fw reload` : Reload this plugin.
