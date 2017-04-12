package thatmartinguy.unwiredwiring.client;

import java.util.Iterator;
import java.util.List;

import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import thatmartinguy.unwiredwiring.UnwiredWiring;
import thatmartinguy.unwiredwiring.block.BlockHideable;
import thatmartinguy.unwiredwiring.item.ItemWrench;
import thatmartinguy.unwiredwiring.item.ModItems;
import thatmartinguy.unwiredwiring.item.baubles.ModBaubleItems;

public class HideManager
{
	private static volatile boolean lastCheck;
	private static boolean toggled;

	private static boolean shouldWireBeHidden(EntityPlayer player)
	{	
		for(final EnumHand hand : EnumHand.values())
		{
			if(player.getHeldItem(hand) != null)
			{
				if(player.getHeldItem(hand).getItem() instanceof ItemWrench || player.getHeldItem(hand).getItem().getCreativeTab() == CreativeTabs.REDSTONE || player.getHeldItem(hand).getItem().getCreativeTab() == UnwiredWiring.tabUnwiredWiring)
				{
					return false;
				}
			}
		}
		Iterator armorList = player.getArmorInventoryList().iterator();
		while(armorList.hasNext())
		{
			Item armorItem = ((ItemStack) armorList.next()).getItem();
			if(armorItem == ModItems.itemRedstoneGlasses)
			{
				return false;
			}
		}
		if(UnwiredWiring.isBaublesLoaded)
		{
			IBaublesItemHandler baubleCapability = player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, null);
			for(int i = 0; i < baubleCapability.getSlots(); i++)
			{
				if(baubleCapability.getStackInSlot(i).getItem() == ModBaubleItems.baubleRedstoneGlasses)
				{
					return false;
				}
			}
			Iterator baublesArmorList = player.getArmorInventoryList().iterator();
			while(baublesArmorList.hasNext())
			{
				Item baublesArmorItem = ((ItemStack) baublesArmorList.next()).getItem();
				if(baublesArmorItem == ModBaubleItems.baubleRedstoneGlasses)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	@SubscribeEvent
	public void clientCheck(TickEvent.ClientTickEvent event)
	{
		if(event.phase != TickEvent.Phase.END) return;
		
		final EntityPlayer player = Minecraft.getMinecraft().player;
		if(player == null) return;
		
		final boolean check = shouldWireBeHidden(player);
		toggled = check != lastCheck;
		lastCheck = check;
	}
	
	public static boolean shouldBlockBeRevealed()
	{
		return lastCheck;
	}
	
	public static void refresh(World world, BlockPos pos)
	{
		if(toggled)
		{
			final IBlockState state = world.getBlockState(pos);
			world.setBlockState(pos, state.withProperty(BlockHideable.HIDDEN, lastCheck));
		}
	}
}
