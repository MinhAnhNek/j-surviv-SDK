package jsclub.codefest2024.sdk.util.element;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

import jsclub.codefest2024.sdk.socket.data.obstacles.Obstacle;

public class ObstacleUtil {

    public static Map<String, Obstacle> createObstaclesMap() {
        Map<String, Obstacle> obstacleList = new HashMap<>();
        obstacleList.put("Hộp vật phẩm", new Obstacle(40));
        obstacleList.put("Hộp vật phẩm đặc biệt", new Obstacle(40));
        obstacleList.put("Bình hoa", new Obstacle(10));
        obstacleList.put("Bình xăng", new Obstacle(100));
        return obstacleList;
    }

    public Map<String, Obstacle> obstacleList;

    public ObstacleUtil() {
        this.obstacleList = createObstaclesMap();
    }

    @Override
    public String toString() {
        return new Gson().toJson(obstacleList);
    }

    public Obstacle getObstacle(String name) {
        return obstacleList.get(name);
    }
}
