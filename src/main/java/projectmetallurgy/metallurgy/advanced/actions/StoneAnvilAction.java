package projectmetallurgy.metallurgy.advanced.actions;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import projectmetallurgy.metallurgy.block.blockEntity.StoneAnvilBlockEntity;
import projectmetallurgy.metallurgy.item.ItemRegistry;
import projectmetallurgy.metallurgy.item.raw.ItemRawOre;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class StoneAnvilAction {
    public static Map<Player,Boolean> passed = new HashMap<>();

    @SubscribeEvent
    public static void onPlaceOreOnTheAnvil(PlayerInteractEvent.RightClickBlock event){
        //if (!event.getWorld().isClientSide){
            passed.putIfAbsent(event.getPlayer(), false);
            if (! passed.get(event.getPlayer())) {
                passed.put(event.getPlayer(), true);
                return;
            }
            if (event.getPlayer().getMainHandItem().getItem() instanceof ItemRawOre && event.getWorld().getBlockEntity(event.getPos()) instanceof StoneAnvilBlockEntity) {
                StoneAnvilBlockEntity anvil = (StoneAnvilBlockEntity) event.getWorld().getBlockEntity(event.getPos());
                Player player = event.getPlayer();
                ItemStack itemStack = player.getMainHandItem();
                if (anvil.itemPlacedOn != Items.AIR) {
                    BlockPos pos = event.getPos();
                    ItemStack item2drop = new ItemStack(anvil.itemPlacedOn);
                    item2drop.setTag(anvil.tag);
                    event.getWorld().addFreshEntity(new ItemEntity(event.getWorld(), pos.getX(), pos.getY(), pos.getZ(), item2drop));
                }
                anvil.tag = itemStack.getTag();
                anvil.itemPlacedOn = itemStack.getItem();
                itemStack.setCount(itemStack.getCount() - 1);

            }else

            if (event.getPlayer().getMainHandItem().is(Items.AIR) && event.getWorld().getBlockEntity(event.getPos()) instanceof StoneAnvilBlockEntity) {
                StoneAnvilBlockEntity anvil = (StoneAnvilBlockEntity) event.getWorld().getBlockEntity(event.getPos());
                Player player = event.getPlayer();
                ItemStack itemStack2Give = new ItemStack(anvil.itemPlacedOn);
                if (!itemStack2Give.is(Items.AIR)) {
                    itemStack2Give.setTag(anvil.tag);
                }
                BlockPos pos = event.getPos();
                anvil.itemPlacedOn=Items.AIR;
                anvil.tag=new CompoundTag();
                event.getWorld().addFreshEntity(new ItemEntity(event.getWorld(), pos.getX(), pos.getY(), pos.getZ(), itemStack2Give));
            }
            passed.put(event.getPlayer(), false);

    }
}
