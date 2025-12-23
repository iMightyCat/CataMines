package me.catalysmrl.catamines.mine.reward.target.impl;

import org.bukkit.Location;

import me.catalysmrl.catamines.mine.reward.target.Target;
import me.catalysmrl.catamines.mine.reward.target.TargetType;

public record LocationTarget(Location location) implements Target {
    @Override public TargetType type() { return TargetType.LOCATION; }
}
