package me.catalysmrl.catamines.mine.reward;

public interface RewardHolder {

    boolean hasRewardContainer(String triggerId);

    RewardContainer getRewardContainer(String triggerId);

}
