package com.chef.mod.items.armor;

import com.chef.mod.Chef;
import com.chef.mod.UnixTime;
import com.chef.mod.blocks.CookingFurnace;
import com.chef.mod.init.MyItems;
import com.chef.mod.items.ExtraSpeed;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemChefGarb extends ItemArmor {
    public static HashMap<String, HashMap<String, ExtraSpeed>> extraFurnaceSpeed = new HashMap<String, HashMap<String, ExtraSpeed>>();
    public static int extraSpeed = 0;
    private static int counter;

    public ItemChefGarb(ItemArmor.ArmorMaterial material, int renderIndex, int armorType) {
        super(material, renderIndex, armorType);
        switch (armorType) {
            case 0:
                this.setUnlocalizedName("chef_hat");
                break;
            case 1:
                this.setUnlocalizedName("chef_body");
                break;
            case 2:
                this.setUnlocalizedName("chef_legs");
                break;
            case 3:
                this.setUnlocalizedName("chef_boots");
                break;
        }
        this.setCreativeTab(Chef.tabChef);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        this.purgeList();
        ExtraSpeed speedParameters = new ExtraSpeed();
        int speed = 0;
        int range = 2;

        ItemStack helm = player.getCurrentArmor(3);
        ItemStack body = player.getCurrentArmor(2);
        ItemStack legs = player.getCurrentArmor(1);
        ItemStack boots = player.getCurrentArmor(0);

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                for (int k = -range; k <= range; k++) {
                    int x = MathHelper.floor_double(player.posX) + i;
                    int y = MathHelper.floor_double(player.posY) + j;
                    int z = MathHelper.floor_double(player.posZ) + k;

                    if (!(world.getBlock(x, y, z) instanceof CookingFurnace)) continue;

                    String furnaceName = x + "/" + y + "/" + z;
                    HashMap<String, ExtraSpeed> playersSpeedList = new HashMap<String, ExtraSpeed>();

                    if (helm != null && helm.getItem() == MyItems.chef_hat) speed -= 30;
                    if (body != null && body.getItem() == MyItems.chef_body) speed -= 18;
                    if (legs != null && legs.getItem() == MyItems.chef_leggings) speed -= 18;
                    if (boots != null && boots.getItem() == MyItems.chef_boots) speed -= 6;

                    speedParameters.setExtraSpeed(speed);
                    speedParameters.setTime(UnixTime.TimeNow());

                    if (extraFurnaceSpeed.containsKey(furnaceName)) {
                        playersSpeedList = extraFurnaceSpeed.get(furnaceName);
                    }

                    playersSpeedList.put(player.getGameProfile().getName(), speedParameters);
                    extraFurnaceSpeed.put(furnaceName, playersSpeedList);
                }
            }
        }
    }

    public void purgeList() {
        Iterator<Map.Entry<String, HashMap<String, ExtraSpeed>>> itFurnace = extraFurnaceSpeed.entrySet().iterator();

        while (itFurnace.hasNext()) {
            Map.Entry<String, HashMap<String, ExtraSpeed>> pairFurnace = itFurnace.next();
            HashMap<String, ExtraSpeed> playersSpeedList = pairFurnace.getValue();
            Iterator<Map.Entry<String, ExtraSpeed>> itPlayersSpeedList = playersSpeedList.entrySet().iterator();

            boolean furnaceHasExtraSpeed = false;

            while (itPlayersSpeedList.hasNext()) {
                Map.Entry<String, ExtraSpeed> pairPlayersSpeedList = itPlayersSpeedList.next();
                ExtraSpeed speedParameters = pairPlayersSpeedList.getValue();
                int extraSpeedTime = speedParameters.getTime();

                if (UnixTime.MilliTimeNow() - 1L >= (long) extraSpeedTime) {
                    itPlayersSpeedList.remove();
                } else {
                    furnaceHasExtraSpeed = true;
                }
            }

            if (!furnaceHasExtraSpeed) {
                itFurnace.remove();
            }
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (slot == 0 || slot == 1 || slot == 3) {
            return "chef:textures/model/armor/chef_garb_layer_1.png";
        } else if (slot == 2) {
            return "chef:textures/model/armor/chef_garb_layer_2.png";
        }
        return null;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        int i = EntityLiving.getArmorPosition(itemStackIn) - 1;
        ItemStack currentArmor = playerIn.getCurrentArmor(i);

        if (currentArmor == null) {
            playerIn.setCurrentItemOrArmor(i + 1, itemStackIn.copy());
            itemStackIn.stackSize = 0;
        }

        return itemStackIn;
    }
}
