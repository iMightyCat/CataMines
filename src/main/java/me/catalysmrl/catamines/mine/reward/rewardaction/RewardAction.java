package me.catalysmrl.catamines.mine.reward.rewardaction;

import java.util.Set;

import me.catalysmrl.catamines.mine.reward.target.Target;
import me.catalysmrl.catamines.mine.reward.target.TargetType;
import me.catalysmrl.catamines.mine.reward.trigger.context.TriggerContext;

public interface RewardAction {

    Set<TargetType> supportedTargets();
    void execute(TriggerContext context, Target target);

}
