package me.catalysmrl.catamines.mine.reward.rewardaction.impl;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import me.catalysmrl.catamines.mine.reward.rewardaction.RewardAction;
import me.catalysmrl.catamines.mine.reward.target.Target;
import me.catalysmrl.catamines.mine.reward.target.TargetType;
import me.catalysmrl.catamines.mine.reward.target.impl.LocationTarget;
import me.catalysmrl.catamines.mine.reward.trigger.context.TriggerContext;

public class DropItemAction implements RewardAction {
    private final ItemStack item;

    public DropItemAction(ItemStack item) {
        this.item = item;
    }

    @Override
    public Set<TargetType> supportedTargets() {
        return Set.of(TargetType.LOCATION);
    }

    @Override
    public void execute(TriggerContext ctx, Target target) {
        Location loc = ((LocationTarget) target).location();
        loc.getWorld().dropItemNaturally(loc, item.clone());
    }
}
