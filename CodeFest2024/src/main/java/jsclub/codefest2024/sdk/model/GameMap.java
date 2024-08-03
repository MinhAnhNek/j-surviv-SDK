/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsclub.codefest2024.sdk.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import jsclub.codefest2024.sdk.model.enemies.*;
import jsclub.codefest2024.sdk.model.equipments.*;
import jsclub.codefest2024.sdk.model.obstacles.*;
import jsclub.codefest2024.sdk.model.players.Player;
import jsclub.codefest2024.sdk.model.weapon.*;

public class GameMap {
    private int mapSize = 0;
    private int darkAreaSize = 0;
    private List<Obstacle> listIndestructibleObstacles = new ArrayList<>();
    private List<Enemy> listEnemies = new ArrayList<>();
    private List<Obstacle> listTraps = new ArrayList<>();
    private List<Obstacle> listChests = new ArrayList<>();
    private List<Weapon> listWeapons = new ArrayList<>();
    private List<HealingItem> listHealingItems = new ArrayList<>();
    private List<Armor> listArmors = new ArrayList<>();
    private List<Bullet> listBullets = new ArrayList<>();
    private List<Player> otherPlayerInfo = new ArrayList<>();
    private Player currentPlayer;

    public GameMap() {}

    // @Phi
    // Update data of this map when game send on init map event
    public void updateOnInitMap() {

    }

    // @Phi
    // Update data of this map when game send on update map event
    public void updateOnUpdateMap() {

    }

    // @Phi
    // Get element by index on map
    public Element getElementByIndex(int x, int y) {
        Element element = null;
        element = getElementByIndex(x, y, listIndestructibleObstacles.toArray());
        if(element != null) return element;

        element = getElementByIndex(x, y, listEnemies.toArray());
        if(element != null) return element;

        element = getElementByIndex(x, y, listTraps.toArray());
        if(element != null) return element;

        element = getElementByIndex(x, y, listChests.toArray());
        if(element != null) return element;

        element = getElementByIndex(x, y, listWeapons.toArray());
        if(element != null) return element;

        element = getElementByIndex(x, y, listHealingItems.toArray());
        if(element != null) return element;

        element = getElementByIndex(x, y, listArmors.toArray());
        if(element != null) return element;

        element = getElementByIndex(x, y, listBullets.toArray());
        if(element != null) return element;

        element = getElementByIndex(x, y, otherPlayerInfo.toArray());
        if(element != null) return element;

        return new Element(x, y, "ROAD", ElementType.ROAD);
    }

    public Element getElementByIndex(int x, int y, Object[] elements){
        for(Object element : elements){
            Element e = (Element) element;
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        return null;
    }

    public List<Weapon> getAllGun() {
        List<Weapon> guns = new ArrayList<>();
        for (Weapon weapon : listWeapons) {
            if (weapon.getType() == ElementType.GUN) {
                guns.add(weapon);
            }
        }
        return guns;
    }

    public List<Weapon> getAllMelee() {
        List<Weapon> melees = new ArrayList<>();
        for (Weapon weapon : listWeapons) {
            if (weapon.getType() == ElementType.MELEE) {
                melees.add(weapon);
            }
        }
        return melees;
    }

    public List<Weapon> getAllThrowable() {
        List<Weapon> throwables = new ArrayList<>();
        for (Weapon weapon : listWeapons) {
            if (weapon.getType() == ElementType.THROWABLE) {
                throwables.add(weapon);
            }
        }
        return throwables;
    }

    public int getMapSize() {
        return mapSize;
    }

    public int getDarkAreaSize() {
        return darkAreaSize;
    }

    public List<Obstacle> getListIndestructibleObstacles() {
        return listIndestructibleObstacles;
    }

    public List<Enemy> getListEnemies() {
        return listEnemies;
    }

    public List<Obstacle> getListTraps() {
        return listTraps;
    }

    public List<Obstacle> getListChests() {
        return listChests;
    }

    public List<Weapon> getListWeapons() {
        return listWeapons;
    }

    public List<HealingItem> getListHealingItems() {
        return listHealingItems;
    }

    public List<Armor> getListArmors() {
        return listArmors;
    }

    public List<Bullet> getListBullets() {
        return listBullets;
    }

    public List<Player> getOtherPlayerInfo() {
        return otherPlayerInfo;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public void setDarkAreaSize(int darkAreaSize) {
        this.darkAreaSize = darkAreaSize;
    }

    public void setListIndestructibleObstacles(List<Obstacle> listIndestructibleObstacles) {
        this.listIndestructibleObstacles = listIndestructibleObstacles;
    }

    public void setListEnemies(List<Enemy> listEnemies) {
        this.listEnemies = listEnemies;
    }

    public void setListTraps(List<Obstacle> listTraps) {
        this.listTraps = listTraps;
    }

    public void setListChests(List<Obstacle> listChests) {
        this.listChests = listChests;
    }

    public void setListWeapons(List<Weapon> listWeapons) {
        this.listWeapons = listWeapons;
    }

    public void setListHealingItems(List<HealingItem> listHealingItems) {
        this.listHealingItems = listHealingItems;
    }

    public void setListArmors(List<Armor> listArmors) {
        this.listArmors = listArmors;
    }

    public void setListBullets(List<Bullet> listBullets) {
        this.listBullets = listBullets;
    }

    public void setOtherPlayerInfo(List<Player> otherPlayerInfo) {
        this.otherPlayerInfo = otherPlayerInfo;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
