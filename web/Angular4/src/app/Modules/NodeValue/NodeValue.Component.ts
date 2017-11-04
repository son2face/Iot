import {Component, ViewContainerRef} from '@angular/core';
import {BottomToastsManager} from "../../Shared/CustomToaster";
import {PagingModel} from "app/Shared/MaterialComponent/paging/paging.model";
import {ModalComponent} from "../../Shared/MaterialComponent/modal/modal.component";
import {SearchNodeValueEntity} from "./NodeValue.SearchEntity";
import {NodeValueService} from "./NodeValue.Service";
import {NodeValueEntity} from "./NodeValue.Entity";
import {ActivatedRoute} from "@angular/router";
import {NodeService} from "../Node/Node.Service";

@Component({
    selector: 'App-NodeValue',
    templateUrl: './NodeValue.Component.html',
    styleUrls: ['./NodeValue.Component.css'],
    providers: [NodeValueService, BottomToastsManager]
})
export class NodeValueComponent {
    Title: string = "NodeValue";
    Trick: string = "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp";
    NodeValueEntities: NodeValueEntity[];
    temp = {};
    CreatedNodeValueEntity: NodeValueEntity;
    EditedNodeValueEntity: NodeValueEntity;
    EditPosition: number;
    SearchNodeValueEntity: SearchNodeValueEntity;
    DeleteData: NodeValueEntity;
    EditModalComponent: ModalComponent;
    DeleteModalComponent: ModalComponent;
    PagingModel = new PagingModel(7, 10, data => {
        this.NodeValueService.Get().subscribe(p => {
            this.NodeValueEntities = p;
        });
    });
    NodeId: string = null;

    constructor(private NodeValueService: NodeValueService, private route: ActivatedRoute, private NodeService: NodeService,
                private toastr: BottomToastsManager, vcr: ViewContainerRef) {
        route.params.subscribe(params => {
            this.NodeId = params['nodeId'];
            this.NodeValueService.url = `api/nodes/${this.NodeId}/values`;
            this.CreatedNodeValueEntity = new NodeValueEntity();
            this.EditedNodeValueEntity = new NodeValueEntity();
            this.SearchNodeValueEntity = new SearchNodeValueEntity();
            this.Search();
            this.EditModalComponent = new ModalComponent();
            this.DeleteModalComponent = new ModalComponent();
            this.toastr.setRootViewContainerRef(vcr);
        });
    }

    Search() {
        this.SearchNodeValueEntity.Skip = this.PagingModel.Take * this.PagingModel.Active;
        this.SearchNodeValueEntity.Take = this.PagingModel.Take;
        this.NodeValueService.Get(this.SearchNodeValueEntity).subscribe(p => {
            this.NodeValueEntities = p;
            this.Count();
        });
    }

    Count() {
        this.NodeValueService.Count(this.SearchNodeValueEntity).subscribe(data => {
            this.PagingModel.TotalPage = Math.ceil(data / this.PagingModel.Take);
        });
    }

    LoadDataToUpdateModal(NodeValue: NodeValueEntity, index: number) {
        this.EditedNodeValueEntity = new NodeValueEntity(NodeValue);
        this.EditPosition = index;
        document.getElementById(this.EditModalComponent.ID).click();
    }

    LoadDataToDelete(NodeValue: NodeValueEntity) {
        this.DeleteData = NodeValue;
    }

    Edit() {
        this.NodeValueService.Update(this.EditedNodeValueEntity).subscribe(p => {
            this.NodeValueEntities[this.EditPosition] = p;
            this.toastr.ShowSuccess();
        }, e => {
            this.toastr.ShowError(e);
        });
    }

    Add() {
        this.NodeValueService.Create(this.CreatedNodeValueEntity).subscribe(p => {
            p.IsEdit = true;
            this.NodeValueEntities.unshift(p);
            this.CreatedNodeValueEntity = new NodeValueEntity();
            this.toastr.ShowSuccess();
        }, e => {
            this.toastr.ShowError(e);
        });
    }

    Delete() {
        this.NodeValueService.Delete(this.DeleteData).subscribe(p => {
            let indexOf = this.NodeValueEntities.indexOf(this.DeleteData);
            this.NodeValueEntities.splice(indexOf, 1);
            this.toastr.ShowSuccess();
        }, e => {
            this.toastr.ShowError(e);
        });
    }

    Save(NodeValueEntity: NodeValueEntity) {
        if (NodeValueEntity.id === undefined || NodeValueEntity.id === null) {
            this.NodeValueService.Create(NodeValueEntity).subscribe(p => {
                Object.assign(NodeValueEntity, p);
                NodeValueEntity.IsEdit = false;
                this.toastr.ShowSuccess();
            }, e => {
                this.toastr.ShowError(e);
            });
        } else {
            this.NodeValueService.Update(NodeValueEntity).subscribe(p => {
                Object.assign(NodeValueEntity, p);
                NodeValueEntity.IsEdit = false;
                this.toastr.ShowSuccess();
            }, e => {
                this.toastr.ShowError(e);
            });
        }
    }

    Cancel(NodeValueEntity: NodeValueEntity) {
        if (NodeValueEntity.id === undefined || NodeValueEntity.id === null) {
            this.NodeValueEntities.splice(0, 1);
        } else {
            Object.assign(NodeValueEntity, this.temp);
            NodeValueEntity.IsEdit = false;
        }
    }
}