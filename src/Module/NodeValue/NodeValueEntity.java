package Module.NodeValue;

import Module.Node.NodeEntity;
import Module.Node.NodeModel;

import java.io.Serializable;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class NodeValueEntity implements Serializable {
    public int id;
    public Integer value;
    public Timestamp time;
    public Integer nodeId;
    public NodeEntity nodeEntity;

    public NodeValueEntity() {
    }

    public NodeValueEntity(int id, Integer value, Timestamp time, Integer nodeId) {
        this.id = id;
        this.value = value;
        this.time = time;
        this.nodeId = nodeId;
    }

    public NodeValueEntity(NodeValueModel NodeValueModel, Object... objects) {
        this.id = NodeValueModel.getId();
        this.value = NodeValueModel.getValue();
        this.time = NodeValueModel.getTime();
        for (Object object : objects) {
            if (object instanceof NodeModel) {
                this.nodeEntity = new NodeEntity((NodeModel) object);
            }
        }
    }

    public NodeValueModel toModel() {
        NodeValueModel NodeValueModel = new NodeValueModel();
        NodeValueModel.setId(id);
        NodeValueModel.setTime(time);
        NodeValueModel.setNodeId(nodeId);
        NodeValueModel.setValue(value);
        return NodeValueModel;
    }
}
