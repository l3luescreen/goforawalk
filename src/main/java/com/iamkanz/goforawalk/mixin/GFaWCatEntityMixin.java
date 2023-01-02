package com.iamkanz.goforawalk.mixin;

import com.iamkanz.goforawalk.Goforawalk;
import net.minecraft.client.report.ReporterEnvironment;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.passive.CatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CatEntity.class)
public class GFaWCatEntityMixin {

    @Redirect( method = "interactMob", at = @At( value = "INVOKE", target = "Lnet/minecraft/entity/passive/CatEntity;setSitting(Z)V"))
    public void setMobMode(CatEntity instance, boolean b) {
        if (instance.isSitting()) {
           instance.goalSelector.getGoals().forEach((goal) -> {
               if (goal.getGoal().toString().equals("FollowOwnerGoal")) {
                   instance.setGlowing(true);
               }
           });
            instance.setSitting(false);
        } else {
            instance.setSitting(!instance.isSitting());
        }
    }
}
