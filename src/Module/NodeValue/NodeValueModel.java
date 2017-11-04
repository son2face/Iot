package Module.NodeValue;

import Module.Node.NodeModel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "nodevalue", schema = "iot", catalog = "")
public class NodeValueModel {
    private int id;
    private Integer value;
    private Timestamp time;
    private Integer nodeId;
    private NodeModel nodeByNodeId;

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
    @Column(name = "value", nullable = true)
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "nodeId", nullable = true)
    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeValueModel that = (NodeValueModel) o;

        if (id != that.id) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (nodeId != null ? !nodeId.equals(that.nodeId) : that.nodeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (nodeId != null ? nodeId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "nodeId", referencedColumnName = "id", insertable=false, updatable=false)
    public NodeModel getNodeByNodeId() {
        return nodeByNodeId;
    }

    public void setNodeByNodeId(NodeModel nodeByNodeId) {
        this.nodeByNodeId = nodeByNodeId;
    }
}
