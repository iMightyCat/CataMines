package me.catalysmrl.catamines.mine.reward.trigger;

import me.catalysmrl.catamines.mine.reward.trigger.context.TriggerContext;

public interface Trigger {

    String getId();

    Class<? extends TriggerContext> getContextType();

    void fire(TriggerContext context);

}
