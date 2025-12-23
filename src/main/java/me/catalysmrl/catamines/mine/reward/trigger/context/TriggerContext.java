package me.catalysmrl.catamines.mine.reward.trigger.context;

import java.util.Optional;

import me.catalysmrl.catamines.api.mine.CataMine;
import me.catalysmrl.catamines.mine.components.composition.CataMineBlock;
import me.catalysmrl.catamines.mine.components.composition.CataMineComposition;
import me.catalysmrl.catamines.mine.components.region.CataMineRegion;

public interface TriggerContext {
    Optional<CataMine> mine();
    Optional<CataMineRegion> region();
    Optional<CataMineComposition> composition();
    Optional<CataMineBlock> block();
}