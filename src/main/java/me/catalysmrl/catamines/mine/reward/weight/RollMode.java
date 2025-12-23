package me.catalysmrl.catamines.mine.reward.weight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum RollMode {

    /**
     * Each entry rolls independently.
     * Zero, one, or many entries may be selected.
     */
    INDEPENDENT {
        @Override
        public <T> List<T> roll(List<WeightedEntry<T>> entries, Random random) {
            List<T> result = new ArrayList<>();
            for (WeightedEntry<T> entry : entries) {
                if (random.nextDouble() * 100 <= entry.weight()) {
                    result.add(entry.value());
                }
            }
            return result;
        }
    },

    /**
     * Exactly one entry is selected.
     * Weights act as relative probabilities.
     */
    WEIGHTED_ONE {
        @Override
        public <T> List<T> roll(List<WeightedEntry<T>> entries, Random random) {
            double totalWeight = entries.stream()
                    .mapToDouble(WeightedEntry::weight)
                    .sum();

            if (totalWeight <= 0) return List.of();

            double roll = random.nextDouble() * totalWeight;
            double current = 0;

            for (WeightedEntry<T> entry : entries) {
                current += entry.weight();
                if (roll <= current) {
                    return List.of(entry.value());
                }
            }
            return List.of();
        }
    },

    /**
     * At most one entry is selected.
     * It is possible that nothing is selected.
     */
    WEIGHTED_MAYBE_ONE {
        @Override
        public <T> List<T> roll(List<WeightedEntry<T>> entries, Random random) {
            double totalWeight = entries.stream()
                    .mapToDouble(WeightedEntry::weight)
                    .sum();

            if (random.nextDouble() * 100 > totalWeight) {
                return List.of();
            }

            return WEIGHTED_ONE.roll(entries, random);
        }
    },

    /**
     * All entries are selected, ignoring weights.
     */
    ALL {
        @Override
        public <T> List<T> roll(List<WeightedEntry<T>> entries, Random random) {
            return entries.stream()
                    .map(WeightedEntry::value)
                    .toList();
        }
    };

    public abstract <T> List<T> roll(List<WeightedEntry<T>> entries, Random random);
}

