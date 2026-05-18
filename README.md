# Not Enough Food - Extension
**Not Enough Food - Extension** (nefext) is a Minecraft b1.7.3 mod built on Babric and StationAPI. It requires the base mod: [Not Enough Food](https://github.com/louiszn/not-enough-food) and extends it with new items, configurable drop rates, and tweaks to NEF's default behavior via [GlassConfigAPI](https://github.com/magistermaks/fabric-glass-config).  
Everything is designed to be configurable, giving players total control over their experience.

## Requirements
- [Not Enough Food](https://modrinth.com/mod/b1.7.3-not-enough-food) 1.0.1
- [GlassConfigAPI](https://modrinth.com/mod/gcapi) 3.2.5+
- Minecraft b1.7.3 (Babric)

## Food Items

- **Rotten Flesh:** new item dropped by Zombies. Eating it has an 80% chance to deal 1 heart of damage and a 20% chance to heal 2 hearts. Drop is toggleable and its amount follows the configurable mob meat min/max rules.
- **Pumpkin Pie:** new item crafted with a Carved Pumpkin, a Milk Bucket and an Egg (shapeless). Heals 4 hearts.
- **Food stack size:** all food items now have a configurable stack size (1–64). Cookies always respect their vanilla minimum of 8.
- **NEF soups:** NEF added soups heals 4 hearts: healing can now be buffed to 5 hearts to match vanilla Mushroom Stew (toggleable), and bowl return on consumption can also be toggled independently.
- **Golden Carrot recipe:** The NEF added Golden Carrot is craftable with Gold Ingots: it can now be switched to a Gold Blocks recipe or both can be enabled at the same time, independently toggleable.
- **Golden Apple recipe:** NEF added a Gold Ingots recipe for the Golden Apple: it can now be disabled (toggleable).

## Drop Rates

- **Apple drop from Oak Leaves:** NEF added a fixed apple drop chance: it is now fully configurable and overrides NEF's default.
- **Zombie drops:** NEF added Carrot and Potato drops from Zombies: the drop chance is now configurable, Iron Ingots are added to the pool (toggleable), and a small chance for the Potato drop to be Poisonous is also toggleable and the vanilla Feathers drop is now toggleable.
- **Grass crop drops:** Tall Grass can now drop Carrots or Potatoes with a configurable chance (disabled by default).
- **Mob meat drop amount:** NEF added meat drops to Cows, Chickens and Sheep: the minimum and maximum amount is now configurable and also applies to Pigs (and Zombies if rotten flesh is enabled).
- **Pig Buff:** Pigs now guarantee slightly more meat than other mobs to keep them relevant alongside NEF's additions (toggleable).

## Other Stuff

- Mobs that die while on fire drop their cooked meat variant instead of raw (vanilla behaviour extended to NEF added meat for consistency)
- If [BHCreative](https://modrinth.com/mod/bh-creative) is installed, all NEF and nefext food items and crops are grouped into a dedicated **Not Enough Food** creative tab.
- For full documentation on every config option see the [wiki](../../wiki).
- The default configuration of the mod is based on what I think is best for b1.7.3 vanilla balancing without things feeling out of place while keeping an eye to what modern minecraft does.

## Support

If you encounter any problems or have suggestions, please [open an issue](https://github.com/ExiledEye/not-enough-food-extension/issues).

## License

Copyright (c) 2026 Exiled Eye  
This project is licensed under the MIT License.  
Refer to the [LICENSE](LICENSE) file for details.
