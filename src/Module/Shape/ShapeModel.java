package Module.Shape;

import Module.Edge.EdgeModel;
import Module.Problem.ProblemModel;
import Module.User.UserModel;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ShapeModel implements Serializable {
    public int shapeId;
    public Integer problemId;
    public Integer level;
    public Integer userId;
    public List<EdgeModel> edgeModelList;
    public ProblemModel problemModel;
    public UserModel userModel;

    public ShapeModel() {
    }

    public ShapeModel(int shapeId, Integer problemId, Integer level, Integer userId) {
        this.shapeId = shapeId;
        this.level = level;
        this.problemId = problemId;
        this.userId = userId;
    }

    public ShapeModel(ShapeEntity ShapeEntity) {
        this.shapeId = ShapeEntity.getShapeId();
        this.problemId = ShapeEntity.getProblemId();
        this.level = ShapeEntity.getLevel();
        this.userId = ShapeEntity.getUserId();
        this.userModel = new UserModel(ShapeEntity.getUserByUserId());
        this.problemModel = new ProblemModel(ShapeEntity.getProblemByProblemId());
        this.edgeModelList = ShapeEntity.getEdgesByShapeId().parallelStream().map(EdgeModel::new).collect(Collectors.toList());
    }

    public ShapeEntity toEntity() {
        ShapeEntity ShapeEntity = new ShapeEntity();
        ShapeEntity.setShapeId(shapeId);
        ShapeEntity.setLevel(level);
        ShapeEntity.setProblemByProblemId(this.problemModel.toEntity());
        ShapeEntity.setUserByUserId(this.userModel.toEntity());
        ShapeEntity.setEdgesByShapeId(edgeModelList.parallelStream().map(EdgeModel::toEntity).collect(Collectors.toList()));
        return ShapeEntity;
    }
}
