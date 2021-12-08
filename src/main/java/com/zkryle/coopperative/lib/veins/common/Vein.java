package com.zkryle.coopperative.lib.veins.common;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class Vein{
    private final BlockState filler;
    private final BlockState ore;
    private final BlockState rawOreBlock;
    private final int minY;
    private final int maxY;

    public Vein( Block filler , Block ore , Block rawOreBlock , int minY , int maxY ){
        this.filler = filler.defaultBlockState();
        this.ore = ore.defaultBlockState();
        this.rawOreBlock = rawOreBlock.defaultBlockState();
        this.minY = minY;
        this.maxY = maxY;
    }

    public BlockState filler(){
        return this.filler;
    }

    public BlockState ore(){
        return this.ore;
    }

    public BlockState rawOreBlock(){
        return this.rawOreBlock;
    }

    public int minY(){
        return this.minY;
    }

    public int maxY(){
        return this.maxY;
    }
}
