package me.catalysmrl.catamines.mine.reward.trigger.context.impl;

import java.util.Optional;

import org.bukkit.entity.Player;

import me.catalysmrl.catamines.api.mine.CataMine;
import me.catalysmrl.catamines.mine.components.composition.CataMineBlock;
import me.catalysmrl.catamines.mine.components.composition.CataMineComposition;
import me.catalysmrl.catamines.mine.components.region.CataMineRegion;
import me.catalysmrl.catamines.mine.reward.trigger.context.BlockBreakContext;

public class BlockBreakContextImpl implements BlockBreakContext {

    private final Player player;
    private final CataMineBlock block;
    private final CataMine mine;
    private final CataMineRegion region;
    private final CataMineComposition composition;

    public BlockBreakContextImpl(Player player, CataMineBlock block, CataMine mine, CataMineRegion region, CataMineComposition composition) {
        this.player = player;
        this.block = block;
        this.mine = mine;
        this.region = region;
        this.composition = composition;
    }

    @Override
    public Player player() {
        return player;
    }

    @Override
    public Optional<CataMineBlock> block() {
        return Optional.ofNullable(block);
    }

    @Override
    public Optional<CataMine> mine() {
        return Optional.ofNullable(mine);
    }

    @Override
    public Optional<CataMineRegion> region() {
        return Optional.ofNullable(region);
    }

    @Override
    public Optional<CataMineComposition> composition() {
        return Optional.ofNullable(composition);
    }
}
