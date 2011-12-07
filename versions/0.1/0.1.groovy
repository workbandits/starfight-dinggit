player.dynProp["xp"] = 0
player.dynProp["pop"] = 0
player.dynProp["nbAttack"] = 9

Player.save(player)

Inventory.merge(player, "platinium", 500)
Inventory.merge(player, "mine", 1)
