import {Component, ViewContainerRef} from '@angular/core';
import {BottomToastsManager} from "../../Shared/CustomToaster";
import {PagingModel} from "app/Shared/MaterialComponent/paging/paging.model";
import {ModalComponent} from "../../Shared/MaterialComponent/modal/modal.component";
import {SearchNodeEntity} from "./Node.SearchEntity";
import {NodeService} from "./Node.Service";
import {NodeEntity} from "./Node.Entity";
import {NodeValueEntity} from "../NodeValue/NodeValue.Entity";

@Component({
    selector: 'App-Node',
    templateUrl: './Node.Component.html',
    styleUrls: ['./Node.Component.css'],
    providers: [NodeService, BottomToastsManager]
})
export class NodeComponent {
    Title: string = "Node";
    Trick: string = "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp";
    NodeEntities: NodeEntity[];
    CreatedNodeEntity: NodeEntity;
    EditedNodeEntity: NodeEntity;
    EditPosition: number;
    SearchNodeEntity: SearchNodeEntity;
    DeleteData: NodeEntity;
    EditModalComponent: ModalComponent;
    NodeValueList: Array<NodeValueEntity> = [];
    ChoosedNodeValueItem: NodeValueEntity = new NodeValueEntity();
    SearchNodeValueName: string;
    PagingModel = new PagingModel(7, 10, data => {
        this.NodeService.Get().subscribe(p => {
            this.NodeEntities = p;
        });
    });

    constructor(private NodeService: NodeService,
                private toastr: BottomToastsManager, vcr: ViewContainerRef) {
        this.CreatedNodeEntity = new NodeEntity();
        this.EditedNodeEntity = new NodeEntity();
        this.SearchNodeEntity = new SearchNodeEntity();
        this.Search();
        this.EditModalComponent = new ModalComponent();
        this.toastr.setRootViewContainerRef(vcr);
    }

    Search() {
        this.SearchNodeEntity.Skip = this.PagingModel.Take * this.PagingModel.Active;
        this.SearchNodeEntity.Take = this.PagingModel.Take;
        this.NodeService.Get(this.SearchNodeEntity).subscribe(p => {
            this.NodeEntities = p;
            this.Count();
        });
    }

    Count() {
        this.NodeService.Count(this.SearchNodeEntity).subscribe(data => {
            this.PagingModel.TotalPage = Math.ceil(data / this.PagingModel.Take);
        });
    }

    LoadDataToUpdateModal(Node: NodeEntity, index: number) {
        this.EditedNodeEntity = new NodeEntity(Node);
        this.EditPosition = index;
        document.getElementById(this.EditModalComponent.ID).click();
    }

    LoadDataToDelete(Node: NodeEntity) {
        this.DeleteData = Node;
    }

    Edit() {
        this.NodeService.Update(this.EditedNodeEntity).subscribe(p => {
            this.NodeEntities[this.EditPosition] = p;
            this.toastr.ShowSuccess();
        }, e => {
            this.toastr.ShowError(e);
        });
    }

    Add() {
        this.NodeService.Create(this.CreatedNodeEntity).subscribe(p => {
            p.IsEdit = true;
            this.NodeEntities.unshift(p);
            this.CreatedNodeEntity = new NodeEntity();
            this.toastr.ShowSuccess();
        }, e => {
            this.toastr.ShowError(e);
        });
    }

    Delete() {
        this.NodeService.Delete(this.DeleteData).subscribe(p => {
            let indexOf = this.NodeEntities.indexOf(this.DeleteData);
            this.NodeEntities.splice(indexOf, 1);
            this.toastr.ShowSuccess();
        }, e => {
            this.toastr.ShowError(e);
        });
    }

//    Save(NodeEntity: NodeEntity) {
//        if (NodeEntity.Id === undefined || NodeEntity.Id === null) {
//            this.NodeService.Create(NodeEntity).subscribe(p => {
//                Object.assign(NodeEntity, p);
//                NodeEntity.IsEdit = false;
//                this.toastr.ShowSuccess();
//            }, e => {
//                this.toastr.ShowError(e);
//            });
//        } else {
//            this.NodeService.Update(NodeEntity).subscribe(p => {
//                Object.assign(NodeEntity, p);
//               NodeEntity.IsEdit = false;
//                this.toastr.ShowSuccess();
//            }, e => {
//                this.toastr.ShowError(e);
//            });
//        }
//    }
//    Cancel(NodeEntity: NodeEntity) {
//        if (NodeEntity.Id === undefined || NodeEntity.Id === null) {
//            this.NodeEntities.splice(0, 1);
//        } else {
//            Object.assign(NodeEntity, this.temp);
//            NodeEntity.IsEdit = false;
//        }
//    }
}