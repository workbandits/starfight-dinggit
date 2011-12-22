playerToFight = Player.get(app, parameters['id'])

if (playerToFight == null) {
    return ["status": "error", "message": "Player unknown."]
}

if (player.dynProp['nbAttack'] <= 0) {
    return ["status": "error", "message": "No more attack available."]
}

if (player.dynProp['messages'] == null) {
    player.dynProp['messages'] = []
}

if (playerToFight.dynProp['messages'] == null) {
    playerToFight.dynProp['messages'] = []
}

player.dynProp['nbAttack']--

playerAttack = player.dynProp['attack'] - playerToFight.dynProp['defense']
playerToFightAttack = playerToFight.dynProp['attack'] - player.dynProp['defense']

if (playerAttack > playerToFightAttack) {
    // Player win
    armyPlayer = Inventory.findAll(player, ["dynProp.category":"army"])
    
    messagePlayer = ['status':'win', "date": new Date().format('dd/MM'), 'player': playerToFight.name, 'pop': 0, 'platinium': 0]
    
    for (armyElement in armyPlayer) {
        quantity = ((armyElement.quantity / 10) as int)
        player.dynProp['pop'] -= quantity
        player.dynProp['attack'] -= armyElement.item.dynProp['attack'] * quantity
        player.dynProp['defense'] -= armyElement.item.dynProp['defense'] * quantity
        Inventory.merge(player, armyElement.item.ref, -quantity)
        
        messagePlayer['pop'] += quantity
    }
    
    player.dynProp['xp']++
    Player.save(player)
    
    armyPlayerToFight = Inventory.findAll(playerToFight, ["dynProp.category":"army"])
    
    messagePlayerToFight = ['status':'lose', "date": new Date().format('dd/MM'), 'player': player.name, 'pop':0, 'platinium':0]
    
    for (armyElement in armyPlayerToFight) {
        quantity = ((armyElement.quantity / 4) as int)
        playerToFight.dynProp['pop'] -= quantity
        playerToFight.dynProp['attack'] -= armyElement.item.dynProp['attack'] * quantity
        playerToFight.dynProp['defense'] -= armyElement.item.dynProp['defense'] * quantity
        Inventory.merge(playerToFight, armyElement.item.ref, -quantity)

        messagePlayerToFight['pop'] += quantity
    }
    
    Player.save(playerToFight)
    
    platiniumPlayerToFight = Inventory.findOne(playerToFight, "platinium")
    quantity = ((platiniumPlayerToFight.quantity / 4) as int)
    Inventory.merge(player, "platinium", quantity)
    messagePlayer['platinium'] = quantity
    
    Inventory.merge(playerToFight, "platinium", -quantity)
    messagePlayerToFight['platinium'] = quantity
        
    player.dynProp['messages'] = [messagePlayer] + player.dynProp['messages']
    Player.save(player)
    playerToFight.dynProp['messages'] = [messagePlayerToFight] + playerToFight.dynProp['messages']
    Player.save(playerToFight)
} else {
    // PlayerToFight win
    armyPlayerToFight = Inventory.findAll(playerToFight, ["dynProp.category":"army"])
    
    messagePlayerToFight = ['status':'win', "date": new Date().format('dd/MM'), 'player': player.name, 'pop':0, 'platinium':0]
    
    for (armyElement in armyPlayerToFight) {
        quantity = ((armyElement.quantity / 10) as int)
        playerToFight.dynProp['pop'] -= quantity
        playerToFight.dynProp['attack'] -= armyElement.item.dynProp['attack'] * quantity
        playerToFight.dynProp['defense'] -= armyElement.item.dynProp['defense'] * quantity
        Inventory.merge(playerToFight, armyElement.item.ref, -quantity)
        
        messagePlayerToFight['pop'] += quantity
    }
    
    Player.save(playerToFight)
    
    armyPlayer = Inventory.findAll(player, ["dynProp.category":"army"])
    
    messagePlayer = ['status':'lose', "date": new Date().format('dd/MM'), 'player': playerToFight.name, 'pop':0, 'platinium':0]
    
    for (armyElement in armyPlayer) {
        quantity = ((armyElement.quantity / 4) as int)
        player.dynProp['pop'] -= quantity
        player.dynProp['attack'] -= armyElement.item.dynProp['attack'] * quantity
        player.dynProp['defense'] -= armyElement.item.dynProp['defense'] * quantity
        Inventory.merge(player, armyElement.item.ref, -quantity)

        messagePlayer['pop'] += quantity
    }
    
    player.dynProp['messages'] = [messagePlayer] + player.dynProp['messages']
    Player.save(player)
    playerToFight.dynProp['messages'] = [messagePlayerToFight] + playerToFight.dynProp['messages']
    Player.save(playerToFight)
}
