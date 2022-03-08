package me.pryter.tracks.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item.Settings;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class DiscItem {

    private Settings settings;
    
    public DiscItem() {
        this.settings = new Item.Settings().rarity(Rarity.RARE).maxCount(1).group(ItemGroup.MISC);
    }

    public DiscItem(Settings settings) {
        this.settings = settings;
    }


    public Item register(String id, SoundEvent event) {
        return Registry.register(Registry.ITEM, new Identifier("tracks", id), new AbstractDiscItem(14, event, settings));
    }


}