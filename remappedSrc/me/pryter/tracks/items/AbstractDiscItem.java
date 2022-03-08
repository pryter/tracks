package me.pryter.tracks.items;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;

public class AbstractDiscItem extends MusicDiscItem {

    protected AbstractDiscItem(int comparatorOutput, SoundEvent sound, Settings settings) {
        super(comparatorOutput, sound, settings);
    }
    
}
