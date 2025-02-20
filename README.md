# MiningZ
MiningZ is a mining level mod.

### Installation
MiningZ is a mod built for the [Fabric Loader](https://fabricmc.net/). It requires [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api) and [Cloth Config API](https://www.curseforge.com/minecraft/mc-mods/cloth-config) to be installed separately; all other dependencies are installed with the mod.

### License
MiningZ is licensed under MIT.

### Datapacks
If you don't know how to create a datapack check out [Data Pack Wiki](https://minecraft.fandom.com/wiki/Data_Pack)
website and try to create your first one for the vanilla game.\
If you know how to create one, the folder path has to be ```data\modid\mining\YOURFILE.json```\
An example:

```json
{
  "1": {
    "replace": false,
    "items": [
      "minecraft:stone_pickaxe"
    ],
    "blocks": [
      "minecraft:stone",
      "minecraft:sandstone"
    ]
  },
  "2": {
    "replace": false,
    "items": [
      "minecraft:diamond_pickaxe"
    ],
    "blocks": [
      "minecraft:coal_ore",
      "minecraft:redstone_ore"
    ]
  }
}
```
