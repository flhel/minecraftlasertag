package lasertag.item;

import lasertag.Utils;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
    public static final RegistryObject<Phaser> PHASER = ITEMS.register("phaser", Phaser::new);
    public static final RegistryObject<LaserstrahlItem> LASERSTRAHL_ITEM = ITEMS.register("laserstrahl_item", LaserstrahlItem::new );
}