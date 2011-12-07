platinium = Inventory.findOne(player, "platinium")

players = Player.findAll(app, ["order": "-dynProp.xp", "limit": 10])

return ["player": player, "platinium": platinium, "players":players]