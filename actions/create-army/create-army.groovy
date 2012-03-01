if (!parameters['quantity'].isInteger()) {
    return ["status": "error", "message": "You must enter a positive integer."]
}

quantity = parameters['quantity'] as int

if (quantity <= 0) {
    return ["status": "error", "message": "You must enter a positive integer."]
}

ref = parameters['ref']

army = ItemTemplate.findOne(app, ref)
if (army == null) {
    return ["status": "error", "message": "Unknown item."]
}

cost = army.dynProp.price * quantity
if (player.dynProp.platinium < cost) {
    return ["status": "error", "message": "You don't have enough platinium."]
}

Item.merge(player, ref, quantity)

player.dynProp.platinium -= cost
player.dynProp.attack += army.dynProp.attack * quantity
player.dynProp.defense += army.dynProp.defense * quantity
player.dynProp.pop += quantity
Player.save(player)

achievements = Achievement.progress(player, ["unit-2", "unit-5"], quantity)

return ["status": "success", "message": "You have created " + quantity + " " + army.name, "achievements":achievements]