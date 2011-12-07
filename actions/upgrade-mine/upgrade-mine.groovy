platinium = Inventory.findOne(player, "platinium")
mine = Inventory.findOne(player, "mine")

xpMin = Math.max(0, mine.dynProp['lvl'] - 5)

if (player.dynProp['xp'] < xpMin) {
    return [
        "status": "error",
        "message": "You don't have the minimum level."
    ]
}

cost = (mine.dynProp['lvl'] + 1) * 35

if (platinium.quantity < cost) {
    return [
        "status": "error",
        "message": "You don't have enough platinium."
    ]
}

Inventory.merge(player, "platinium", -cost)
mine.dynProp['lvl'] = mine.dynProp['lvl'] + 1
Inventory.save(mine)

return [
        "status": "success",
        "message": "Your mine has evolved."
    ]