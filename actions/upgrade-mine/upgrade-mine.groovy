mine = Item.findOne(player, "mine")

xpMin = Math.max(0, mine.dynProp.lvl - 5)

if (player.dynProp.xp < xpMin) {
    return [
        "status": "error",
        "message": "You don't have the minimum level."
    ]
}

cost = (mine.dynProp.lvl + 1) * 35

if (player.dynProp.platinium < cost) {
    return [
        "status": "error",
        "message": "You don't have enough platinium."
    ]
}

player.dynProp.platinium -= cost
Player.save(player)

mine.dynProp.lvl++
Item.save(mine)

return [
        "status": "success",
        "message": "Your mine has evolved."
    ]