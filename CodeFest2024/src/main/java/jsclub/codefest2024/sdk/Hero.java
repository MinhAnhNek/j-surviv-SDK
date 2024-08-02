/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsclub.codefest2024.sdk;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import jsclub.codefest2024.sdk.model.GameMap;
import jsclub.codefest2024.sdk.model.Inventory;
import jsclub.codefest2024.sdk.socket.EventName;
import jsclub.codefest2024.sdk.socket.SocketClient;
import jsclub.codefest2024.sdk.socket.data.emit_data.*;
import jsclub.codefest2024.sdk.util.MsgPackUtil;

import java.io.IOException;

public class Hero {
    private String playerName = "";
    private String gameID = "";
    private final SocketClient socketClient;
    private final GameMap gameMap;
    private final Inventory inventory;
    private Emitter.Listener onMapUpdate;

    public Hero(String playerName, String gameID) {
        this.playerName = playerName;
        this.gameID = gameID;
        this.gameMap = new GameMap();
        this.inventory = new Inventory();
        this.socketClient = new SocketClient(this.inventory);
    }

    public void start(String serverURL) {
        if (this.onMapUpdate == null) {
            throw new RuntimeException("onMapUpdate is not set");
        }

        socketClient.connectToServer(serverURL + "/sdk", this.onMapUpdate);
    }

    public String getPlayerID() {
        return playerName;
    }
    public String getGameID() {
        return gameID;
    }

    public void move(String move) throws IOException {
        Socket socket = socketClient.getSocket();

        if (socket != null) {
            PlayerMoveAction botMove = new PlayerMoveAction(move);

            byte[] bytes = MsgPackUtil.encodeFromObject(botMove);
            socket.emit(EventName.EMIT_MOVE, (Object) bytes);
        }
    }

    public void shoot() throws IOException {
        Socket socket = socketClient.getSocket();

        if (socket != null) {
            String data = "{}";
            byte[] bytes = MsgPackUtil.encodeFromObject(data);
            socket.emit(EventName.EMIT_SHOOT, (Object) bytes);
        }
    }

    public void attack() throws IOException {
        Socket socket = socketClient.getSocket();

        if (socket != null) {
            String data = "{}";
            byte[] bytes = MsgPackUtil.encodeFromObject(data);
            socket.emit(EventName.EMIT_ATTACK, (Object) bytes);
        }
    }

    public void throwItem(int itemId, int destinationX, int destinationY) throws IOException {
        Socket socket = socketClient.getSocket();

        if (socket != null) {
            PlayerThrowItemAction botThrow = new PlayerThrowItemAction(itemId, destinationX, destinationY);

            byte[] bytes = MsgPackUtil.encodeFromObject(botThrow);
            socket.emit(EventName.EMIT_THROW, (Object) bytes);
        }
    }

    public void pickupItem() throws IOException {
        Socket socket = socketClient.getSocket();

        if (socket != null) {
            String data = "{}";
            byte[] bytes = MsgPackUtil.encodeFromObject(data);
            socket.emit(EventName.EMIT_PICKUP_ITEM, (Object) bytes);
        }
    }

    public void useItem(int itemId) throws IOException {
        Socket socket = socketClient.getSocket();

        if (socket != null) {
            PlayerUseItemAction botUseItem = new PlayerUseItemAction(itemId);

            byte[] bytes = MsgPackUtil.encodeFromObject(botUseItem);
            socket.emit(EventName.EMIT_USE_ITEM, (Object) bytes);
        }
    }

    public void revokeItem(int itemId) throws IOException {
        Socket socket = socketClient.getSocket();

        if (socket != null) {
            PlayerRevokeItemAction botRevokeItem = new PlayerRevokeItemAction(itemId);

            byte[] bytes = MsgPackUtil.encodeFromObject(botRevokeItem);
            socket.emit(EventName.EMIT_REVOKE_ITEM, (Object) bytes);
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setOnMapUpdate(Emitter.Listener onMapUpdate) {
        this.onMapUpdate = onMapUpdate;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
