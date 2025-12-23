package me.catalysmrl.catamines.mine.reward;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import me.catalysmrl.catamines.mine.reward.trigger.context.TriggerContext;
import me.catalysmrl.catamines.mine.reward.weight.RollMode;
import me.catalysmrl.catamines.mine.reward.weight.WeightedContainer;
import me.catalysmrl.catamines.mine.reward.weight.WeightedEntry;

public class CascadeRewardResolver {

    public Optional<WeightedContainer<RewardDefinition>> resolve(
            String triggerId,
            TriggerContext ctx
    ) {

        List<RewardContainer> containers =
                collectContainers(triggerId, ctx);

        if (containers.isEmpty()) {
            return Optional.empty();
        }

        List<WeightedEntry<RewardDefinition>> merged = new ArrayList<>();
        RollMode rollMode = null;

        for (RewardContainer container : containers) {

            CascadeMode cascade = container.getCascadeMode();

            if (cascade == CascadeMode.DISABLE) {
                return Optional.empty();
            }

            if (cascade == CascadeMode.OVERRIDE) {
                merged.clear();
                rollMode = container.getRewards().getRollMode();
            }

            WeightedContainer<RewardDefinition> rewards =
                    container.getRewards();

            merged.addAll(rewards.getEntries());

            if (rollMode == null) {
                rollMode = rewards.getRollMode();
            }
        }

        if (merged.isEmpty() || rollMode == null) {
            return Optional.empty();
        }

        return Optional.of(
                new WeightedContainer<>(rollMode, merged)
        );
    }

    private List<RewardContainer> collectContainers(
            String triggerId,
            TriggerContext ctx
    ) {

        List<RewardContainer> result = new ArrayList<>();

        // Most specific → least specific

        ctx.block().ifPresent(block -> {
            RewardContainer rc = block.getRewardContainer(triggerId);
            if (rc != null) result.add(rc);
        });

        ctx.composition().ifPresent(comp -> {
            RewardContainer rc = comp.getRewardContainer(triggerId);
            if (rc != null) result.add(rc);
        });

        ctx.region().ifPresent(region -> {
            RewardContainer rc = region.getRewardContainer(triggerId);
            if (rc != null) result.add(rc);
        });

        ctx.mine().ifPresent(mine -> {
            RewardContainer rc = mine.getRewardContainer(triggerId);
            if (rc != null) result.add(rc);
        });

        return result;
    }
}


