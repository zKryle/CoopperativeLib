package com.zkryle.coopperative;

import com.zkryle.coopperative.lib.veins.common.Vein;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.zkryle.coopperative.lib.veins.VeinRegistry.registerVein;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CoopperativeLib.MOD_ID)
public class CoopperativeLib
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    protected static final String MOD_ID = "coopperative";

    public CoopperativeLib() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener( this::registerVeins );
        // Register ourselves for server and other game events we are interested in.
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Register default vanilla veins.
    public void registerVeins( FMLCommonSetupEvent event ){
        registerVein( new Vein( Blocks.GRANITE, Blocks.COPPER_ORE, Blocks.RAW_COPPER_BLOCK, 0, 50  ) );
        registerVein( new Vein( Blocks.TUFF, Blocks.DEEPSLATE_IRON_ORE, Blocks.RAW_IRON_BLOCK, -60, -8  ) );
    }
}
