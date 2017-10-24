package Module.Edge;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class EdgeModel implements Serializable {

    public int edgeId;
    public Double startX;
    public Double startY;
    public Double endX;
    public Double endY;


    public EdgeModel() {
    }

    public EdgeModel(int edgeId, Double startX, Double startY, Double endX, Double endY) {
        this.edgeId = edgeId;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public EdgeModel(EdgeEntity EdgeEntity) {
        this.edgeId = EdgeEntity.getEdgeId();
        this.startX = EdgeEntity.getStartX();
        this.endX = EdgeEntity.getEndX();
        this.startY = EdgeEntity.getStartY();
        this.endY = EdgeEntity.getEndY();
    }

    public EdgeEntity toEntity() {
        EdgeEntity EdgeEntity = new EdgeEntity();
        EdgeEntity.setEdgeId(edgeId);
        EdgeEntity.setStartX(startX);
        EdgeEntity.setEndX(endX);
        EdgeEntity.setStartY(startY);
        EdgeEntity.setEndY(endY);
        return EdgeEntity;
    }
}
