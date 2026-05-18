package com.exiledeye.nefext.mixin;

import com.exiledeye.nefext.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Dynamic;

@Mixin(value = LeavesBlock.class, priority = 10000)
public abstract class AppleMixin extends Block {

    protected AppleMixin(int id, Material material) {
        super(id, material);
    }
    /**
     * @author Exiled Eye
     * @reason Overwriting base mod/vanilla behavior to implement custom apple drop rates from ModConfig.
     */
    @SuppressWarnings("target")
    @Dynamic
    @Overwrite(remap = false)
    @Override
    public void dropStacks(World world, int x, int y, int z, int meta, float luck) {
        if (world.isRemote) return;

        int itemCount = this.getDroppedItemCount(world.random);
        for (int i = 0; i < itemCount; ++i) {
            if (world.random.nextFloat() <= luck) {
                int saplingId = this.getDroppedItemId(meta, world.random);
                if (saplingId > 0) {
                    this.dropStack(world, x, y, z, new ItemStack(saplingId, 1, this.getDroppedItemMeta(meta)));
                }
            }
        }

        if ((meta & 3) == 0 && ModConfig.DROP_RATES_CONFIG.appleChance > 0.0F) {
            if (world.random.nextFloat() <= ModConfig.DROP_RATES_CONFIG.appleChance) {
                this.dropStack(world, x, y, z, new ItemStack(Item.APPLE.id, 1, 0));
            }
        }
    }
}