package thatmartinguy.unwiredwiring.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import thatmartinguy.unwiredwiring.block.BlockWire;
import thatmartinguy.unwiredwiring.item.ItemWrench;

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
				if(player.getHeldItem(hand).getItem() instanceof ItemWrench || player.getHeldItem(hand).getItem() == Items.REDSTONE)
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
	
	public static boolean shouldWireBeRevealed()
	{
		return lastCheck;
	}
	
	public static void refresh(World world, BlockPos pos)
	{
		if(toggled)
		{
			final IBlockState state = world.getBlockState(pos);
			world.setBlockState(pos, state.withProperty(BlockWire.HIDDEN, lastCheck));
		}
	}
}
