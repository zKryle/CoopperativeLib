package com.zkryle.coopperative.lib.veins;

import com.zkryle.coopperative.CoopperativeLib;
import com.zkryle.coopperative.lib.veins.common.Vein;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class VeinRegistry{
    public static final ArrayList <Vein> VEINS = new ArrayList <>();

    /**
     * Register a new {@link Vein} instance with
     * @param vein
     * to the VEINS list in {@link VeinRegistry}
     * @author zKryle
     */
    public static void registerVein( Vein vein ){
        if(!VEINS.contains( vein )){
            VEINS.add( vein );
            CoopperativeLib.LOGGER.atInfo().log( String.format("A Vein with materials: %s , %s , %s", vein.filler(), vein.ore(), vein.rawOreBlock()));
        } else throw new IllegalStateException("A vein with these materials has already been registered!");
    }

}
