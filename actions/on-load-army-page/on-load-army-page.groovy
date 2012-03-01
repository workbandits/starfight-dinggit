itemTemplates = ItemTemplate.findAll(app, ["dynProp.category":"army", "order":"dynProp.price"])
army = Item.findAllUnspecializedToMap(player, ["dynProp.category":"army"])

itemTemplatesWithQuantity = []
for (itemTemplate in itemTemplates) {
    if (army[itemTemplate.ref] != null) {
        itemTemplate.dynProp.quantity = army[itemTemplate.ref].quantity
    } else {
        itemTemplate.dynProp.quantity = 0
    }
    
    itemTemplatesWithQuantity.push(itemTemplate)
}

return ["player": player, "army": itemTemplatesWithQuantity]