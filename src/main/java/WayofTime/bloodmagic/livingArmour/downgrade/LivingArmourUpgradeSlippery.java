package WayofTime.bloodmagic.livingArmour.downgrade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import WayofTime.bloodmagic.api.Constants;
import WayofTime.bloodmagic.api.livingArmour.ILivingArmour;
import WayofTime.bloodmagic.api.livingArmour.LivingArmourUpgrade;

public class LivingArmourUpgradeSlippery extends LivingArmourUpgrade
{
    public static final int[] costs = new int[] { -50 };
    public static final int[] slipperyDuration = new int[] { 30 * 20 };

    public LivingArmourUpgradeSlippery(int level)
    {
        super(level);
    }

    @Override
    public void onTick(World world, EntityPlayer player, ILivingArmour livingArmour)
    {
        if (world.isRemote && player.onGround)
        {
//            if (player.moveForward == 0)
            {

                float f6 = 0.91F;
                BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain(player.posX, player.getEntityBoundingBox().minY - 1.0D, player.posZ);

                if (player.onGround)
                {
                    f6 = world.getBlockState(blockpos$pooledmutableblockpos).getBlock().slipperiness * 0.91F;
                }

                player.motionX /= (double) (f6 / 0.91);
                player.motionZ /= (double) (f6 / 0.91);

                float f7 = 0.16277136F / (f6 * f6 * f6);
                float f8;

                if (player.onGround)
                {
                    f8 = player.getAIMoveSpeed() * f7;
                } else
                {
                    f8 = player.jumpMovementFactor;
                }

                player.moveRelative(-player.moveStrafing, -player.moveForward, f8);

                player.moveRelative(player.moveStrafing, player.moveForward, f8 / 10);

                player.motionX *= 0.90;
                player.motionY *= 0.90;
            }
        }
    }

    @Override
    public boolean runOnClient()
    {
        return true;
    }

    @Override
    public String getUniqueIdentifier()
    {
        return Constants.Mod.MODID + ".upgrade.slippery";
    }

    @Override
    public int getMaxTier()
    {
        return 1;
    }

    @Override
    public int getCostOfUpgrade()
    {
        return costs[this.level];
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
    }

    @Override
    public String getUnlocalizedName()
    {
        return tooltipBase + "slippery";
    }
}