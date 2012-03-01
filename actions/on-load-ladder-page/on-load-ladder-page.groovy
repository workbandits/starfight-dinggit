players = Player.findAll(app, ["order": "-dynProp.xp", "limit": 10])

return ["player": player, "ladder":players]