package me.pryter.tracks.items;

import me.pryter.tracks.utils.RandomDisc;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class DiscPackage extends Item {

    public DiscPackage() {
        super(new Item.Settings().rarity(Rarity.RARE).maxCount(64).group(ItemGroup.MISC));
    }
    

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.giveItemStack(new ItemStack(RandomDisc.getRandomDisk(), 1));
        user.playSound(SoundEvents.ENTITY_SILVERFISH_HURT,1f,1f);
        user.getMainHandStack().decrement(1);
        return super.use(world, user, hand);
    }

    public Item register() {
        return Registry.register(Registry.ITEM, new Identifier("tracks", "disc_package"), this);
    }
}
