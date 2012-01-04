if (!parameters['quantity'].isInteger()) {
    return ["status": "error", "message": "You must enter a positive integer."]
}

quantity = parameters['quantity'] as int

if (quantity <= 0) {
    return ["status": "error", "message": "You must enter a positive integer."]
}

ref = parameters['ref']

army = Item.findOne(app, ref)

if (army == null) {
    return ["status": "error", "message": "Unknown element."]
}

gold = Inventory.findOne(player, "platinium")

if (gold.quantity < (army.dynProp['price'] * quantity)) {
    return ["status": "error", "message": "You don't have enough platinium."]
}

Inventory.merge(player, "platinium", -army.dynProp['price'] * quantity as int)
Inventory.merge(player, ref, quantity)

player.dynProp['attack'] += army.dynProp['attack'] * quantity
player.dynProp['defense'] += army.dynProp['defense'] * quantity
player.dynProp['pop'] += quantity
Player.save(player)

return ["status": "success", "message": "You have created " + quantity + " " + army.name]
