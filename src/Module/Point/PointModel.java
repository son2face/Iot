package Module.Point;

import Module.Problem.ProblemModel;

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
    public Integer problemId;
    public ProblemModel problemModel;


    public PointModel() {
    }

    public PointModel(int pointId, Integer x, Integer y, Integer problemId) {
        this.pointId = pointId;
        this.x = x;
        this.y = y;
        this.problemId = problemId;
    }

    public PointModel(PointEntity PointEntity) {
        this.pointId = PointEntity.getPointId();
        this.x = PointEntity.getX();
        this.y = PointEntity.getY();
        this.problemId = PointEntity.getProblemId();
        this.problemModel = new ProblemModel(PointEntity.getProblemByProblemId());
    }

    public PointEntity toEntity() {
        PointEntity PointEntity = new PointEntity();
        PointEntity.setPointId(pointId);
        PointEntity.setX(x);
        PointEntity.setY(y);
        PointEntity.setProblemId(problemId);
        PointEntity.setProblemByProblemId(problemModel.toEntity());
        return PointEntity;
    }
}
