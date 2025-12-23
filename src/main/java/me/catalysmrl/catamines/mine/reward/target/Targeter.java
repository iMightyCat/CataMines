package me.catalysmrl.catamines.mine.reward.target;

import java.util.Collection;

import me.catalysmrl.catamines.mine.reward.trigger.context.TriggerContext;

public interface Targeter {
    Collection<Target> resolve(TriggerContext context);
}
