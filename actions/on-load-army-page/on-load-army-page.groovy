platinium = Inventory.findOne(player, "platinium")

army = Inventory.findAll(player, ["dynProp.category": "army", "order":"dynProp.price"])

return ["player": player, "platinium": platinium, "army": army]