package io.github.tanguygab.arcurrency.item;

import io.github.tanguygab.arcurrency.ARCurrency;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class BankNoteItem extends Item {

    private final String currency;
    private final int count;

    public BankNoteItem(Properties properties, String currency, int count) {
        super(properties);
        this.currency = currency;
        this.count = count;
    }

    public String name() {
        return ARCurrency.config("currencies."+currency+".displayname",currency);
    }

    @Override
    public ITextComponent getName() {
        return ITextComponent.getTextComponentOrEmpty(count+" "+name());
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return getName();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity p, Hand hand) {
        if (world.isRemote || hand == Hand.OFF_HAND) return ActionResult.resultFail(p.getHeldItem(hand));
        ItemStack item = p.getHeldItem(hand);
        int amount = count*item.getCount();
        p.sendStatusMessage(ITextComponent.getTextComponentOrEmpty("+"+amount+" "+name()),false);
        p.inventory.removeStackFromSlot(p.inventory.currentItem);
        return ActionResult.resultConsume(item);
    }


}
