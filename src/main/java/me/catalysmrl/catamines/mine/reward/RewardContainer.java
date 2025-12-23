package me.catalysmrl.catamines.mine.reward;

import me.catalysmrl.catamines.mine.reward.weight.WeightedContainer;

public final class RewardContainer {

    private final String triggerId;
    private final CascadeMode cascadeMode;
    private final WeightedContainer<RewardDefinition> rewards;

    public RewardContainer(
            String triggerId,
            CascadeMode cascadeMode,
            WeightedContainer<RewardDefinition> rewards
    ) {
        this.triggerId = triggerId;
        this.cascadeMode = cascadeMode;
        this.rewards = rewards;
    }

    public String getTriggerId() {
        return triggerId;
    }

    public CascadeMode getCascadeMode() {
        return cascadeMode;
    }

    public WeightedContainer<RewardDefinition> getRewards() {
        return rewards;
    }
}
