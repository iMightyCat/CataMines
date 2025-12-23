package me.catalysmrl.catamines.mine.reward;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import me.catalysmrl.catamines.mine.reward.rewardaction.RewardAction;
import me.catalysmrl.catamines.mine.reward.target.Target;
import me.catalysmrl.catamines.mine.reward.target.TargetModifier;
import me.catalysmrl.catamines.mine.reward.trigger.context.TriggerContext;

public class RewardExecutor {
    private final Random random = new Random();

    public void execute(TriggerContext ctx, List<RewardDefinition> rewards) {

        for (RewardDefinition reward : rewards) {

            // Conditions
            if (!reward.conditions.stream().allMatch(c -> c.test(ctx))) {
                continue;
            }

            // Chance
            if (random.nextDouble() * 100 > reward.chance) {
                continue;
            }

            // Resolve targets ONCE
            Collection<Target> targets = reward.targeter.resolve(ctx);

            // Apply modifiers
            for (TargetModifier modifier : reward.modifiers) {
                targets = modifier.apply(targets, ctx);
            }

            // Execute actions
            for (RewardAction action : reward.actions) {
                for (Target target : targets) {
                    if (!action.supportedTargets().contains(target.type())) {
                        continue;
                    }
                    action.execute(ctx, target);
                }
            }
        }
    }
}
