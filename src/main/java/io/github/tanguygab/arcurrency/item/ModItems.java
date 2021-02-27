package io.github.tanguygab.arcurrency.item;

import io.github.tanguygab.arcurrency.ARCurrency;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModItems {

    public static Map<String,Map<String,Object>> config = ARCurrency.config("currencies", new HashMap<>());

    public ModItems() {
        RegistryObject<Item> deco = ARCurrency.ITEMS.register("banknote", () -> new BankNoteItem(new Item.Properties(), "Example", 0));

        List<Object> items = new ArrayList<>();

        for (String currency : config.keySet()) {
            for (int i : (List<Integer>) config.get(currency).get("banknotes"))
                items.add(ARCurrency.ITEMS.register("banknote_" + currency.toLowerCase() + "_" + i, () -> new BankNoteItem(new Item.Properties(), currency, i)));
            int t = 9 - ((List<Integer>) config.get(currency).get("banknotes")).size();
            if (t < 0) t = t + 9;
            for (int i = 0; i < t; i++) {
                items.add(ItemStack.EMPTY);
            }
        }

        ARCurrency.creativetab.list.addAll(items);
        ARCurrency.creativetab.displayStack = deco;
    }

}
