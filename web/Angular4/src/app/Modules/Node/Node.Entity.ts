import {NodeValueEntity} from "../NodeValue/NodeValue.Entity";

export class NodeEntity {
    id: number;
    time: string;
    nodeValueEntities: NodeValueEntity[];
    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;

    constructor(Node: any = null) {
        if (Node == null) {
            this.id = null;
            this.time = null;
            this.nodeValueEntities = [];
        } else {
            this.id = Node.id;
            this.time = Node.time;
            if (Node.nodeValueEntities != null) {
                this.nodeValueEntities = [];
                for (let item of Node.nodeValueEntities) {
                    this.nodeValueEntities.push(new NodeValueEntity(item));
                }
            }
            if (this.nodeValueEntities == null) this.nodeValueEntities = [];
        }
        this.IsEdit = false;
    }
}