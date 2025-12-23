package me.catalysmrl.catamines.mine.reward.condition;

import me.catalysmrl.catamines.mine.reward.trigger.context.TriggerContext;

public interface Condition {
    boolean test(TriggerContext context);
}
