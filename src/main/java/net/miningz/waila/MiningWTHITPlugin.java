package net.miningz.waila;

import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import net.minecraft.block.Block;
import net.miningz.MiningClient;

public class MiningWTHITPlugin implements IWailaPlugin {

    @Override
    public void register(IRegistrar registrar) {
        registrar.addConfig(MiningClient.HARVESTABLE_INFO, true);
        registrar.addComponent(new MiningWTHITProvider(), TooltipPosition.BODY, Block.class);
    }

}
