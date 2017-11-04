package Module.Node;

import Module.NodeValue.NodeValueModel;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "node", schema = "iot", catalog = "")
public class NodeModel {
    private int id;
    private Timestamp time;
    private Collection<NodeValueModel> nodevaluesById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeModel nodeModel = (NodeModel) o;

        if (id != nodeModel.id) return false;
        if (time != null ? !time.equals(nodeModel.time) : nodeModel.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "nodeByNodeId", orphanRemoval = true)
    public Collection<NodeValueModel> getNodevaluesById() {
        return nodevaluesById;
    }

    public void setNodevaluesById(Collection<NodeValueModel> nodevaluesById) {
        this.nodevaluesById = nodevaluesById;
    }
}
