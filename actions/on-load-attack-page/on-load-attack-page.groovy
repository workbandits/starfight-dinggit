platinium = Inventory.findOne(player, "platinium")

players = Player.findAll(app, [
    "dynProp.xp >" : player.dynProp['xp'] - 5, 
    "dynProp.xp <": player.dynProp['xp'] + 5
    ])

return ["player": player, "platinium": platinium, "players":players]