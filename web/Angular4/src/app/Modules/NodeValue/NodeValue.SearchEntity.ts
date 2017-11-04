import {FilterEntity} from "../../Shared/Filter.Entity";

export class SearchNodeValueEntity extends FilterEntity {
    id: number;
    value: string;
    time: string;

    constructor(NodeValue: any = null) {
        super(NodeValue);
        this.id = NodeValue == null ? null : NodeValue.id;
        this.value = NodeValue == null ? null : NodeValue.value;
        this.time = NodeValue == null ? null : NodeValue.time;
    }
}