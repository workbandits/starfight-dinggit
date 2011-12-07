if (player.dynProp['messages'] == null) {
    player.dynProp['messages'] = []
}

playerToSpy = Player.get(app, parameters['id'])

if (playerToSpy == null) {
    return ["status": "error", "message": "Unknown player."]
}

platinium = Inventory.findOne(playerToSpy, "platinium")

message = ["status":"spy", "date": new Date().format('dd/MM'), "player": playerToSpy.name, "pop": playerToSpy.dynProp['pop'], "platinium": platinium.quantity]

player.dynProp["messages"] = [message] + player.dynProp["messages"]
Player.save(player)
