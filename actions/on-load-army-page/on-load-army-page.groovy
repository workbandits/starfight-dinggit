platinium = Inventory.findOne(player, "platinium")

items = Item.findAll(app, ["dynProp.category": "army", "order":"dynProp.price"])
army = Inventory.findAllToMap(player, ["dynProp.category": "army"])

return ["player": player, "platinium": platinium, "army": army, "items": items]
