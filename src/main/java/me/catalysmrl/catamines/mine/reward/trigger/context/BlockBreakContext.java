package me.catalysmrl.catamines.mine.reward.trigger.context;

import org.bukkit.entity.Player;

import me.catalysmrl.catamines.mine.components.composition.CataMineBlock;


public interface BlockBreakContext extends TriggerContext {
    Player player();
}
