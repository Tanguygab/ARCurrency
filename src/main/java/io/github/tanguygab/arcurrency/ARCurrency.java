package io.github.tanguygab.arcurrency;

import io.github.tanguygab.arcurrency.block.ModBlocks;
import io.github.tanguygab.arcurrency.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Mod(ARCurrency.MOD_ID)
public class ARCurrency {

    public static final String MOD_ID = "arcurrency";
    public static CreativeTab creativetab;

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ARCurrency.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ARCurrency.MOD_ID);

    public ARCurrency() {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modBus);
        ITEMS.register(modBus);

        creativetab = new CreativeTab("ARCurrency");
        new ModBlocks();
        new ModItems();
    }

    public static <T> T config(String path, T def) {
        Map<String,Object> config = null;
        try {
            InputStream input = new FileInputStream(FMLPaths.CONFIGDIR.get().resolve(ARCurrency.MOD_ID + ".yml").toFile());
            config = new Yaml().load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        T result = def;
        if (config != null) {
            List<String> paths = Arrays.asList(path.split("\\."));
            String key = "";
            for (String str : paths) {
                if (paths.indexOf(str) == paths.size()-1)
                    key = str;
                else if (config.containsKey(str)) {
                    Map<String, Object> map = (Map<String, Object>) config.get(str);
                    config = map;
                } else return result;
            }
            if (!config.containsKey(key)) return result;
            else
                result = (T) config.get(key);
        }
        return result;
    }


}
