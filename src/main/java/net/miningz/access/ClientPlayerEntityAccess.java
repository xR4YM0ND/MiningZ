package net.miningz.access;

import net.minecraft.util.Identifier;

import java.util.List;

public interface ClientPlayerEntityAccess {

    void miningZ$setProspectorBlocks(List<Identifier> blockIds);

    List<Identifier> miningZ$getProspectorBlocks();
}
