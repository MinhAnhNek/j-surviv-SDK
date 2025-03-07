package jsclub.codefest2024.sdk.socket.event_handler;

import com.google.gson.Gson;
import io.socket.emitter.Emitter;
import jsclub.codefest2024.sdk.factory.ArmorFactory;
import jsclub.codefest2024.sdk.factory.HealingItemFactory;
import jsclub.codefest2024.sdk.factory.WeaponFactory;
import jsclub.codefest2024.sdk.model.ElementType;
import jsclub.codefest2024.sdk.model.Inventory;
import jsclub.codefest2024.sdk.socket.data.receive_data.InventoryUpdateData;
import jsclub.codefest2024.sdk.util.MsgPackUtil;

import java.io.IOException;

public class onPlayerInventoryUpdate implements Emitter.Listener {
    private final Inventory heroInventory;
    Gson gson = new Gson();

    public onPlayerInventoryUpdate(Inventory heroInventory) {
        this.heroInventory = heroInventory;
    }

    @Override
    public void call(Object... args) {
        try {
            String message = MsgPackUtil.decode(args[0]);
            InventoryUpdateData inventoryUpdateData = gson.fromJson(message, InventoryUpdateData.class);

            ElementType type = inventoryUpdateData.itemType;
            String id = inventoryUpdateData.id;
            String action = inventoryUpdateData.action;
            switch (action) {
                case "ADD":
                    switch (type) {
                        case GUN:
                            heroInventory.setGun(WeaponFactory.getWeaponById(id));
                            break;
                        case MELEE:
                            heroInventory.setMelee(WeaponFactory.getWeaponById(id));
                            break;
                        case THROWABLE:
                            heroInventory.getListThrowable().add(WeaponFactory.getWeaponById(id));
                            break;
                        case ARMOR:
                            heroInventory.setArmor(ArmorFactory.getArmorById(id));
                            break;
                        case HEALING_ITEM:
                            heroInventory.getListHealingItem().add(HealingItemFactory.getHealingItemById(id));
                            break;
                    }
                    break;
                case "REMOVE":
                    switch (type) {
                        case GUN:
                            heroInventory.setGun(null);
                            break;
                        case MELEE:
                            heroInventory.setMelee(WeaponFactory.getWeaponById("HAND"));
                            break;
                        case THROWABLE:
                            heroInventory.getListThrowable().remove(WeaponFactory.getWeaponById(id));
                            break;
                        case ARMOR:
                            heroInventory.setArmor(null);
                            break;
                        case HEALING_ITEM:
                            heroInventory.getListHealingItem().remove(HealingItemFactory.getHealingItemById(id));
                            break;
                    }
                    break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
