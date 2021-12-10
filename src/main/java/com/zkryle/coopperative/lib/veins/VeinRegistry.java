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
        System.out.println(VEINS);
        if(!isAlreadyRegistered(vein)){
            VEINS.add( vein );
            CoopperativeLib.LOGGER.atInfo().log( String.format("Vein with materials: %s , %s , %s and height: minY: %s , maxY: %s successfully registered!",
                    vein.filler(), vein.ore(), vein.rawOreBlock(), vein.minY(), vein.maxY()));
        } else CoopperativeLib.LOGGER.atWarn().log( String.format("Vein with materials: %s , %s , %s has already been registered! Skipping it!",
                vein.filler(), vein.ore(), vein.rawOreBlock()));
    }

    private static boolean isAlreadyRegistered( Vein vein ) {
        boolean isRegistered = false;
        for (Vein vein1 : VEINS) {
            if(vein.filler().equals( vein1.filler() ) && vein.ore().equals( vein1.ore() ) &&
                    vein.rawOreBlock().equals( vein1.rawOreBlock() )) {
                isRegistered = true;
                break;
            }
        }
        return isRegistered;
    }
}
