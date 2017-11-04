import {FilterEntity} from "../../Shared/Filter.Entity";

export class SearchNodeEntity extends FilterEntity {
    id: number;
    time: string;

    constructor(Node: any = null) {
        super(Node);
        this.id = Node == null ? null : Node.id;
        this.time = Node == null ? null : Node.time;
    }
}