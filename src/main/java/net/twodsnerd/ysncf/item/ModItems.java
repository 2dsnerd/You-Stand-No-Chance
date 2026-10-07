package net.twodsnerd.ysncf.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.twodsnerd.ysncf.ysncf;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    public static final Item IRONSCYTHE = register(
            "ironscythe",
            Item::new,
            new Item.Settings().axe(ToolMaterial.WOOD, 2f, 0.2f)
    );

    public static final Item DIAMONDSCYTHE = register(
            "diamondscythe",
            Item::new,
            new Item.Settings().axe(ToolMaterial.WOOD, 3f, 0.2f)
    );

    public static final Item NETHERITESCYTHE = register(
            "netheritescythe",
            Item::new,
            new Item.Settings().axe(ToolMaterial.WOOD, 5f, 0.2f)
    );

    public static <T extends Item> T register(
            String name,
            Function<Item.Settings, T> itemFactory,
            Item.Settings settings
    ) {
        RegistryKey<Item> itemKey = RegistryKey.of(
                Registries.ITEM.getKey(),
                Identifier.of(ysncf.MOD_ID, name)
        );

        T item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static final RegistryKey<net.minecraft.item.ItemGroup> CUSTOM_CREATIVE_TAB_KEY =
            RegistryKey.of(
                    net.minecraft.registry.RegistryKeys.ITEM_GROUP,
                    Identifier.of(ysncf.MOD_ID, "creative_tab")
            );

    public static final net.minecraft.item.ItemGroup CUSTOM_CREATIVE_TAB =
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(NETHERITESCYTHE))
                    .displayName(Text.translatable("ysncf.scythes"))
                    .entries((context, entries) -> {
                        entries.add(IRONSCYTHE);
                        entries.add(DIAMONDSCYTHE);
                        entries.add(NETHERITESCYTHE);
                    })
                    .build();

    public static void initialize() {
        Registry.register(
                Registries.ITEM_GROUP,
                CUSTOM_CREATIVE_TAB_KEY,
                CUSTOM_CREATIVE_TAB
        );
    }
}