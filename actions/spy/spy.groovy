if (player.dynProp.messages == null || player.dynProp.messages.size() >= 15) {
    player.dynProp.messages = []
}

cost = 5
if (player.dynProp.platinium < cost) {
    return ["status": "error", "message":"You don't have enough platinium."]
}

player.dynProp.platinium -= cost

playerToSpy = Player.get(app, parameters['id'])
if (playerToSpy == null) {
    return ["status": "error", "message": "Unknown player."]
}

message = [
    "status":"spy", 
    "date": new Date().format('dd/MM'), 
    "player": playerToSpy.name, 
    "pop": playerToSpy.dynProp.pop, 
    "platinium": playerToSpy.dynProp.platinium
    ]

player.dynProp.messages = [message] + player.dynProp.messages
Player.save(player)