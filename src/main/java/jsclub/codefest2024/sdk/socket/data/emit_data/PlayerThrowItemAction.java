package jsclub.codefest2024.sdk.socket.data.emit_data;

import com.google.gson.annotations.SerializedName;

public class PlayerThrowItemAction {
    @SerializedName("item_id")
    private int itemId;

    @SerializedName("direction")
    private String direction;

    public PlayerThrowItemAction(int itemId, String direction) {
        this.itemId = itemId;
        this.direction = direction;
    }
}
