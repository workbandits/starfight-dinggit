player.dynProp['nbAttack'] = Math.min(9, player.dynProp['nbAttack'] + ticks)

if (hours > 0) {
    mine = Inventory.findOne(player, "mine")
    
    Inventory.merge(player, "platinium", (mine.dynProp['lvl'] * 20))
}

player.ticksRemaining = 0
player.lastActivity = new Date()
Player.save(player)