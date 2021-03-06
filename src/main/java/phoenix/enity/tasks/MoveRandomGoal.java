package phoenix.enity.tasks;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import phoenix.enity.TalpaEntity;

import java.util.EnumSet;

public class MoveRandomGoal extends Goal
{
    TalpaEntity entity;
    public MoveRandomGoal(TalpaEntity entity) {
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        this.entity = entity;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any STATE necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        return !entity.getMoveHelper().isUpdating() && entity.getRNG().nextInt(7) == 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return false;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick()
    {
        BlockPos blockpos = entity.getBoundOrigin();
        if (blockpos == null)
        {
            blockpos = new BlockPos(entity);
        }

        for(int i = 0; i < 3; ++i)
        {
            BlockPos randompoint = blockpos.add(entity.getRNG().nextInt(15) - 7, entity.getRNG().nextInt(11) - 5, entity.getRNG().nextInt(15) - 7);
            if (entity.world.isAirBlock(randompoint))
            {
                entity.getMoveHelper().setMoveTo((double)randompoint.getX() + 0.5D, (double)randompoint.getY() + 0.5D, (double)randompoint.getZ() + 0.5D, 0.25D);
                if (entity.getAttackTarget() == null)
                {
                    entity.getLookController().setLookPosition((double)randompoint.getX() + 0.5D, (double)randompoint.getY() + 0.5D, (double)randompoint.getZ() + 0.5D, 180.0F, 20.0F);
                }
                break;
            }
        }
    }
}
