package io.github.tanguygab.arcurrency;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class CreativeTab extends ItemGroup {

    public RegistryObject<Item> displayStack;
    public List<Object> list = new ArrayList<>();

    public CreativeTab(String label) {
        super(label);
    }


    @Override
    public void fill(NonNullList<ItemStack> items) {
        for (Object item : list) {
            if (item instanceof RegistryObject)
                items.add(new ItemStack(((RegistryObject<Item>) item).get()));
            else items.add((ItemStack) item);
        }
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(displayStack.get());
    }

}
