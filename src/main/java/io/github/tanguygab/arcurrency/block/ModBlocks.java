package io.github.tanguygab.arcurrency.block;

import io.github.tanguygab.arcurrency.ARCurrency;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModBlocks {

    public ModBlocks() {
        RegistryObject<Block> vending_machine  = ARCurrency.BLOCKS.register("vending_machine",() -> new VendingMachineBlock(Block.Properties.create(Material.ROCK)));
        RegistryObject<Item> vending_machine_item = ARCurrency.ITEMS.register("vending_machine",()-> new BlockItem(vending_machine.get(),new Item.Properties()));
        ARCurrency.creativetab.list.add(vending_machine_item);

    }
}
