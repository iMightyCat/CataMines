package me.catalysmrl.catamines.mine.reward.weight;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class WeightedContainer<T> {

    private final RollMode rollMode;
    private final List<WeightedEntry<T>> entries;

    public WeightedContainer(RollMode rollMode, List<WeightedEntry<T>> entries) {
        this.rollMode = rollMode;
        this.entries = List.copyOf(entries);
    }

    public RollMode getRollMode() {
        return rollMode;
    }

    public List<WeightedEntry<T>> getEntries() {
        return entries;
    }

    public List<T> roll(Random random) {
        return rollMode.roll(entries, random);
    }

    public Optional<T> rollSingle(Random random) {
        List<T> result = roll(random);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }
}

