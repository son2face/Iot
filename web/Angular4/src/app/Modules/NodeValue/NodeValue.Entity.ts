import {NodeEntity} from "../Node/Node.Entity";

export class NodeValueEntity {
    id: number;
    value: string;
    time: string;
    nodeId: string;
    NodeEntity: NodeEntity;
    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;

    constructor(NodeValue: any = null) {
        if (NodeValue == null) {
            this.id = null;
            this.value = null;
            this.time = null;
            this.nodeId = null;
            this.NodeEntity = null;
        } else {
            this.id = NodeValue.id;
            this.value = NodeValue.value;
            this.time = NodeValue.time;
            this.nodeId = NodeValue.nodeId;
            this.NodeEntity = NodeValue.NodeEntity;
            // if (this.NodeEntity == null) n;
        }
        this.IsEdit = false;
    }
}