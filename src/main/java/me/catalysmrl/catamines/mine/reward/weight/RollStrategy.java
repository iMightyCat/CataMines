package me.catalysmrl.catamines.mine.reward.weight;

import java.util.List;
import java.util.Random;

public interface RollStrategy {
    <T> List<T> roll(List<WeightedEntry<T>> entries, Random random);
}
