package com.zkryle.coopperative.lib.mixin.veins;

import com.zkryle.coopperative.lib.veins.common.Vein;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.NoiseSampler;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomSource;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static com.zkryle.coopperative.lib.veins.VeinRegistry.VEINS;

@Mixin(NoiseSampler.class)
public abstract class MixinNoiseSampler{

    @Final
    @Shadow
    private NoiseChunk.InterpolatableNoise veininess;

    @Final
    @Shadow
    private NoiseChunk.InterpolatableNoise veinA;

    @Final
    @Shadow
    private NoiseChunk.InterpolatableNoise veinB;

    @Final
    @Shadow
    private PositionalRandomFactory oreVeinsPositionalRandomFactory;

    @Final
    @Shadow
    private NormalNoise gapNoise;

    /**
     * @author zKryle
     */
    @Inject(at = @At("HEAD"), method = "makeOreVeinifier", cancellable = true)
    protected void makeOreVeinifier( NoiseChunk p_189058_ , boolean p_189059_ , CallbackInfoReturnable <NoiseChunk.BlockStateFiller> cir ){
        if(!p_189059_){
            cir.setReturnValue( ( p_189024_ , p_189025_ , p_189026_ ) -> null );
        }else{
            NoiseChunk.Sampler noisechunk$sampler = this.veininess.instantiate( p_189058_ );
            NoiseChunk.Sampler noisechunk$sampler1 = this.veinA.instantiate( p_189058_ );
            NoiseChunk.Sampler noisechunk$sampler2 = this.veinB.instantiate( p_189058_ );
            BlockState blockstate = null;
            cir.setReturnValue( ( p_189024_ , p_189025_ , p_189026_ ) -> {
                        RandomSource randomsource = this.oreVeinsPositionalRandomFactory.at( p_189024_ , p_189025_ , p_189026_ );
                        double d0 = noisechunk$sampler.sample();
                        Vein veinType = this.getVeinType( d0 , p_189025_ );
                        if(veinType == null){
                            return blockstate;
                        }else if(randomsource.nextFloat() > 0.7F){
                            return blockstate;
                        }else if(this.isVein( noisechunk$sampler1.sample() , noisechunk$sampler2.sample() )){
                            double d1 = Mth.clampedMap( Math.abs( d0 ) , (double) 0.4F , (double) 0.6F , (double) 0.1F , (double) 0.3F );
                            if((double) randomsource.nextFloat() < d1 && this.gapNoise.getValue( (double) p_189024_ , (double) p_189025_ , (double) p_189026_ ) > (double) -0.3F){
                                return randomsource.nextFloat() < 0.02F ? veinType.rawOreBlock() : veinType.ore();
                            }else{
                                return veinType.filler();
                            }
                        }else{
                            return blockstate;
                        }
                    }
            );
        }
    }

    @Unique
    private Vein getVeinType( double p_189080_ , int p_189081_ ){
        int clampedValue = (int) Math.max( 0 , Math.min( VEINS.size() - 1 , new Random().nextFloat() * VEINS.size() ) );
        Vein veinType = VEINS.get( clampedValue );
        int i = veinType.maxY() - p_189081_;
        int j = p_189081_ - veinType.minY();
        if(j >= 0 && i >= 0){
            int k = Math.min( i , j );
            double d0 = Mth.clampedMap( (double) k , 0.0D , 20.0D , -0.2D , 0.0D );
            return Math.abs( p_189080_ ) + d0 < (double) 0.4F ? null : veinType;
        }else{
            return null;
        }

    }

    @Shadow
    private boolean isVein( double p_188959_ , double p_188960_ ){
        throw new IllegalStateException( "Mixin failed to shadow isVein()" );
    }
}
