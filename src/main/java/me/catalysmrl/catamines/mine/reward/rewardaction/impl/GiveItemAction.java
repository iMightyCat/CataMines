package me.catalysmrl.catamines.mine.reward.rewardaction.impl;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.catalysmrl.catamines.mine.reward.rewardaction.RewardAction;
import me.catalysmrl.catamines.mine.reward.target.Target;
import me.catalysmrl.catamines.mine.reward.target.TargetType;
import me.catalysmrl.catamines.mine.reward.target.impl.PlayerTarget;
import me.catalysmrl.catamines.mine.reward.trigger.context.TriggerContext;

public class GiveItemAction implements RewardAction {

    private final ItemStack item;

    public GiveItemAction(ItemStack item) {
        this.item = item;
    }

    @Override
    public Set<TargetType> supportedTargets() {
        return Set.of(TargetType.PLAYER);
    }

    @Override
    public void execute(TriggerContext context, Target target) {
        Player player = ((PlayerTarget) target).player();
        player.getInventory().addItem(item.clone());
    }

}
