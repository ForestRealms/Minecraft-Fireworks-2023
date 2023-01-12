# Minecraft-Fireworks-2023
### What is "Minecraft-Fireworks-2023"

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
- `/fw stopLoop <loopName1> [loopName2] ...` : Stop loop(s) by loop name.
- `/fw loops` : Inspect the status and information of all loops.
- `/fw update` : Check update. ***(Note: Current version is not able to check automatically, please check update manually in this page)***
- `/fw addPoints <loopName> <pointName1> [pointName2] ... ` : Add one or more fireworks point in a loop.
- `/fw removePoints <loopName> <pointName1> [pointName2] ... ` : Remove one or more fireworks point in a loop. However, at least 1 point must be reserved in this loop, otherwise, the plugin will show error message and this remove operation will be cancelled.
- `/fw helps` : Show this help in the server.
- `/fw reload` : Reload this plugin.

### Permission nodes

The preceding commands and operations consume certain server resources. Therefore, all the preceding commands require the `fw.admin` permission (default value). You can change this permission node name in the default configuration file `config.yml` and reload the plug-in.

### Lauguage File

As a plugin for Minecraft server users around the world, we take the design and modification of language files very seriously. The plug-in default language is simplified Chinese, but you can also create your own copy by copying the default language file to change the language schema. Next, this article focuses on how language files are configured.

#### Step 1: Make a copy of default language file `zh-cn.yml`

In order to be able to create new language files, you must first copy the default language file `zh-cn.yml`

#### Step 2: Rename the new language file with a specific name.

To differentiate, rename the new language file.

#### Step 3: Modify the internal value

When you open the language file, you will find a large number of key-value pairs. *<u>However, make sure you are familiar with YAML syntax before editing this file</u>*. If the server cannot read the language file due to ignorance of the syntax, the consequences of `java.lang.NullPointerException` will be taken at its own risk.

Each key in the file represents the name of an event. Each value is the response text to the command sender after the event. Where, the content with the percent sign "%" is the variable name. Note that although the name of these variables and look `PlaceholderAPI` plug-in provided by the variable name is somewhat similar, but these variables and `PlaceholderAPI` has nothing to do. The variable name is replaced with the variable associated with the event during the actual run, so the variable name cannot be changed at will, nor can the external percent sign be omitted.

#### Step 4: Save file and modify the configuration file `config.yml`

Save the file in the root directory of your plug-in folder (that is, in the same directory as `zh-cn.yml`). Then, change the value of the language entry in the configuration file config.yml to your file name, but do not suffix it. (For example, if your custom language filename is called `en-US.yml`, you would put `en-US` in the language). If the path you entered does not exist or is inaccessible, the server may output some error messages.

#### Step 5: Reload the plugin, or restart your server

Reload the plug-in or restart the server to take effect

### Self-check on configuration files

In order to prevent the plug-in loading failure or even server crash caused by the non-standard or ungrammatical content of the configuration file config.yml, the plug-in designed the automatic check function of the configuration file. If your configuration file is wrong or not conforming, in order to ensure the security of server operation, the plug-in will inform you of the error and the reason in the console, and automatically shut down the plug-in. But all of this assumes that your language files are configured correctly.

Currently, it is not equipped with automatic repair function, please follow the prompts to repair yourself.

***Note: The plug-in may output some error messages on the console when it detects that the configuration file is invalid. This is a normal phenomenon. Do not report it to the community.***

### Join us

First of all, we would like to thank you for your great support of our development efforts.
If you need to join the collaboration, you can Fork the project on GitHub and launch a Pull Request.

<u>If you have any questions, you can contact us via email or email on GitHub.</u>

### Appendix

#### Default language file (Chinese)

```yaml
# 配置文件自动检查文案
CheckingConfigurationFile: "&a正在检查配置文件"
CompleteCheckingConfigurationFile: "&a配置文件检查完成，共发现&c %errors% &a个错误"
NoPointsFoundInConfigurationFile: "&c配置文件中找不到任何的烟花燃放点！"
InvalidPointCoordinates: "&c%pointName%的坐标无效！"
NullWorld: "&c%pointName%所在世界为空（未配置）"
InvalidWorldName: "&c%pointName%所在的世界%worldName%无效"
NoColorsConfigured: "&c没有为%pointName%配置任何颜色"
NoFadeColorsConfigured: "&c没有为%pointName%配置任何褪色后颜色"
InvalidColor: "&c为%pointName%配置的颜色%colorName%无效！"
InvalidFadeColor: "&c为%pointName%配置的褪色后颜色%colorName%无效！"
NoShapeConfigured: "&c没有为%pointName%配置形状"
InvalidShape: "&c为%pointName%配置的形状%shape%无效"
NoLoopsFoundInConfigurationFile: "&c配置文件中找不到任何的循环序列！"
NoPointInLoop: "&c没有为循环%loopName%配置任何烟花燃放点！"
InvalidPoint: "&c循环%loopName%中配置的烟花燃放点%pointName%无效！"
InvalidInterval: "&c循环%loopName%的间隔时间值%interval%无效！"
InvalidLoopStatus: "&c循环%loopName%的状态值无效！有效的值为：”WORKING“或者”STOPPED“，而当前的值为%status_value%"
InvalidConfig-ShutDown: "&cFireworks插件配置文件无效，为保证服务质量，插件将自动禁用！"
# 帮助内容
helps:
  create: "&b/fw create <燃放点名称> [randomly]  &a创建一个烟花燃放点（randomly为可选参数）"
  launch: "&b/fw launch <烟花燃放点名称> [烟花燃放点名称...]  &a直接发射烟花燃放点（仅一次）"
  createLoop: "&b/fw createLoop <循环名称> <interval=?> <燃放点> [燃放点] ...  &a创建新的循环"
  startLoop: "&b/fw startLoop <循环名称> [循环名称] ...  &a启动一个或多个循环"
  stopLoop: "&b/fw stopLoop <循环名称> [循环名称] ...  &a停止一个或多个循环"
  loops: "&b/fw loops  &a查看所有循环的状态"
  addPoints: "&b/fw addPoint <循环名称> <烟花燃放点1> [烟花燃放点2] ...  &a向循环中添加一个燃放点"
  removePoint: "&b/fw removePoint <循环名称> <烟花燃放点1> [烟花燃放点2] ...  &a向循环中删除一个燃放点"
  update: "&b/fw update  &a检查更新"
  help: "&b/fw help  &a显示此帮助菜单"

IO-Exception: "&c遇到I/O错误，请检查服务器！"
success-on-create: "&a创建烟花燃放点%pointName%成功！"
success-on-createLoop: "&a创建循环%loopName%成功！"
success-on-startLoop: "&a启动循环%loopName%成功！"
success-on-stopLoop: "&a停止循环%loopName%成功！"
failed-on-createLoop: "&c创建循环%loopName%失败！"
success-on-addPoint: "&a成功向循环%loopName%中添加烟花燃放点%pointName%！"
success-on-removePoint: "&d成功从循环%loopName%中删除烟花燃放点%pointName%！"
PlayerOnly: "&c仅游戏内玩家可执行此命令"
NoPermission: "&c您没有执行此命令的权限"
PointNotExist: "&c烟花燃放点%pointName%不存在！"
PointAlreadyExist: "&c烟花燃放点%pointName%已存在，请勿重复创建！"
PointAlreadyExistInLoop: "&c烟花燃放点%pointName%已存在于循环%loopName%中，请勿重复添加！"
LoopNotExist: "&c循环%loopName%不存在！"
LoopAlreadyExist: "&c循环%loopName%已存在，请勿重复创建！"
PointNotExistInLoop: "&c烟花燃放点%pointName%不存在于循环%loopName%中，无需删除！"
ZeroInterval: "&c燃放间隔时间非法"
ReloadComplete: "&a重载完毕！"
LoopIsWorking: "&c循环%loopName%正在运行，请先使用 /fw stopLoop %loopName%  以停止该循环后继续操作。"
LoopAlreadyWorking: "&c循环%loopName%已经运行了，无需再次启动"
LoopAlreadyStopped: "&c循环%loopName%已经停止了，无需再次停止"
AtLeastOneOfPointShouldBeReserved: "&c至少有一个烟花燃放点需要保留，请勿全部删除！"
loops:
  - "&a ===================="
  - "&b 循环名称： &e%loopName%"
  - "&b 包含的燃放点： &e%pointNames%"
  - "&b 间隔： &e%ticks%游戏刻(ticks)(约等于%second%秒)"
  - "&b 循环状态： &e%loopState%"
```

#### Default Configuration file

```yaml
# The language that expected to be used
language: zh-cn
# The permission node
permission: "fw.admin"
# The loop configuration
loops:
  # Loop name
  loop1:
    points:
    # The fireworks points expected to be used in this loop (Just the names of points)
    - point1
    - point2
    - point3
    # The interval time expected to be applied during the adjacent point. (Unit is "ticks", 20 ticks = 1 second)
    interval: 5
    # The loop status. Only "WORKING" and "STOPPED" could be accepted
    status: STOPPED

# The Points configuration
points:
  # Point name
  point1:
    # The coordinates of the firework point
    coordinates:
      - 0
      - 0
      - 0
    # The world that the point in.
    world: world
    # The colors
    colors:
      # Color name
      color1:
        # RGB value of this color
        - 128
        - 128
        - 128
      color2:
        - 192
        - 192
        - 192
    # The fade colors
    fade_colors:
      color1:
        - 128
        - 128
        - 128
      color2:
        - 192
        - 192
        - 192
    # Whether the firework will have trail?
    trail: true
    # Whether the firework will have flicker?
    flicker: true
    # The power value of this firework point
    power: 4
    # The shape value of this firework point.
    # Valid values: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/FireworkEffect.Type.html
    shape: BALL

  point2:
    coordinates:
      - 10
      - 10
      - 10
    world: world
    colors:
      color1:
        - 128
        - 128
        - 128
      color2:
        - 192
        - 192
        - 192
    fade_colors:
      color1:
        - 128
        - 128
        - 128
      color2:
        - 192
        - 192
        - 192
    trail: true
    flicker: true
    power: 4
    shape: BALL

  point3:
    coordinates:
      - -10
      - -10
      - -10
    world: world
    colors:
      color1:
        - 128
        - 128
        - 128
      color2:
        - 192
        - 192
        - 192
    fade_colors:
      color1:
        - 128
        - 128
        - 128
      color2:
        - 192
        - 192
        - 192
    trail: true
    flicker: true
    power: 4
    shape: BALL
```

