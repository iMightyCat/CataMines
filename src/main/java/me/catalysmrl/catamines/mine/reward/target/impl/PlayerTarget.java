package me.catalysmrl.catamines.mine.reward.target.impl;

import org.bukkit.entity.Player;

import me.catalysmrl.catamines.mine.reward.target.Target;
import me.catalysmrl.catamines.mine.reward.target.TargetType;

public record PlayerTarget(Player player) implements Target {
    @Override public TargetType type() { return TargetType.PLAYER; }
}
