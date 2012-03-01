mine = Item.findOne(player, "mine")

current = [
        "lvl": mine.dynProp.lvl,
        "production": mine.dynProp.lvl * 20
    ]
    
next = [
        "lvl": mine.dynProp.lvl + 1,
        "production": (mine.dynProp.lvl + 1) * 20,
        "cost": (mine.dynProp.lvl + 1) * 35,
        "xpMin": Math.max(0, mine.dynProp.lvl - 5)
    ]

return ["player": player, "current": current, "next": next]