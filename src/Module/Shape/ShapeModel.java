package Module.Shape;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ShapeModel implements Serializable {
    public int shapeId;
    public Integer level;


    public ShapeModel() {
    }

    public ShapeModel(int shapeId, Integer level) {
        this.shapeId = shapeId;
        this.level = level;
    }

    public ShapeModel(ShapeEntity ShapeEntity) {
        this.shapeId = ShapeEntity.getShapeId();
        this.level = ShapeEntity.getLevel();
    }

    public ShapeEntity toEntity() {
        ShapeEntity ShapeEntity = new ShapeEntity();
        ShapeEntity.setShapeId(shapeId);
        ShapeEntity.setLevel(level);
        return ShapeEntity;
    }
}
