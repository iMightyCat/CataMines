package me.catalysmrl.catamines.mine.reward;

import java.util.List;

import me.catalysmrl.catamines.mine.reward.condition.Condition;
import me.catalysmrl.catamines.mine.reward.rewardaction.RewardAction;
import me.catalysmrl.catamines.mine.reward.target.TargetModifier;
import me.catalysmrl.catamines.mine.reward.target.Targeter;

public final class RewardDefinition {

    public final double chance;
    public final List<Condition> conditions;
    public final Targeter targeter;
    public final List<TargetModifier> modifiers;
    public final List<RewardAction> actions;

    public RewardDefinition(
            double chance,
            List<Condition> conditions,
            Targeter targeter,
            List<TargetModifier> modifiers,
            List<RewardAction> actions
    ) {
        this.chance = chance;
        this.conditions = conditions;
        this.targeter = targeter;
        this.modifiers = modifiers;
        this.actions = actions;
    }

}
