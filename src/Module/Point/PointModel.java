package Module.Point;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class PointModel implements Serializable {
    public int pointId;
    public Integer x;
    public Integer y;


    public PointModel() {
    }

    public PointModel(int pointId, Integer x, Integer y) {
        this.pointId = pointId;
        this.x = x;
        this.y = y;
    }

    public PointModel(PointEntity PointEntity) {
        this.pointId = PointEntity.getPointId();
        this.x = PointEntity.getX();
        this.y = PointEntity.getY();
    }

    public PointEntity toEntity() {
        PointEntity PointEntity = new PointEntity();
        PointEntity.setPointId(pointId);
        PointEntity.setX(x);
        PointEntity.setY(y);
        return PointEntity;
    }
}
