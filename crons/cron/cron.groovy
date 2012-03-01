player.dynProp.nbAttack = Math.min(9, player.dynProp.nbAttack + ticks)

if (hours > 0) {
    mine = Item.findOne(player, "mine")
    
    player.dynProp.platinium += mine.dynProp['lvl'] * 20
}

player.ticksRemaining = 0
player.lastActivity = new Date()
Player.save(player)