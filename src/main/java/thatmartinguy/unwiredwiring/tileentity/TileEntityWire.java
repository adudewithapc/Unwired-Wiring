package thatmartinguy.unwiredwiring.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityWire extends TileEntityHideable
{
	private int powerLevel;
	
	public int getPowerLevel()
	{
		return powerLevel;
	}

	public void setPowerLevel(int powerLevel)
	{
		this.powerLevel = powerLevel;
		this.markDirty();
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("powerLevel", powerLevel);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.powerLevel = compound.getInteger("powerLevel");
	}
}
