players = Player.findAll(app, [
    "dynProp.xp >" : player.dynProp['xp'] - 5, 
    "dynProp.xp <": player.dynProp['xp'] + 5
    ])

players.remove(player)

return ["player": player, "players":players]