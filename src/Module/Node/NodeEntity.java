package Module.Node;

import Module.NodeValue.NodeValueEntity;
import Module.NodeValue.NodeValueModel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class NodeEntity implements Serializable {
    public int id;
    public Timestamp time;
    public List<NodeValueEntity> nodeValueEntities;

    public NodeEntity() {
    }

    public NodeEntity(int id, Timestamp time) {
        this.id = id;
        this.time = time;
    }

    public NodeEntity(NodeModel NodeModel, Object... objects) {
        this.id = NodeModel.getId();
        this.time = NodeModel.getTime();
        for (Object object : objects) {
          if (object instanceof Collection) {
                for (Object o : (Collection<Object>) object) {
                    if (o instanceof NodeValueModel) {
                        this.nodeValueEntities = ((Collection<NodeValueModel>) object).parallelStream().map(NodeValueEntity::new).collect(Collectors.toList());
                        break;
                    }
                }
            }
        }
    }

    public NodeModel toModel() {
        NodeModel NodeModel = new NodeModel();
        NodeModel.setId(id);
        NodeModel.setTime(time);
        return NodeModel;
    }
}
