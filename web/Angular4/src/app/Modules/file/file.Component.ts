import {Component, ViewContainerRef} from '@angular/core';
import {BottomToastsManager} from "../../Shared/CustomToaster";
import {PagingModel} from "app/Shared/MaterialComponent/paging/paging.model";
import {ModalComponent} from "../../Shared/MaterialComponent/modal/modal.component";
import {SearchfileEntity} from "./file.SearchEntity";
import {fileService} from "./file.Service";
import {FileEntity} from "./file.Entity";

@Component({
    selector: 'App-file',
    templateUrl: './file.Component.html',
    styleUrls: ['./file.Component.css'],
    providers: [fileService, BottomToastsManager]
})
export class fileComponent {
    Title: string = "file";
    fileEntities: FileEntity[];
    PagingModel = new PagingModel(7, 10, data => {
        this.fileService.Get().subscribe(p => {
            this.fileEntities = p;
        });
    });
    temp = {};
    CreatedfileEntity: FileEntity;
    EditedfileEntity: FileEntity;
    EditPosition: number;
    SearchfileEntity: SearchfileEntity;
    DeleteData: FileEntity;
    EditModalComponent: ModalComponent;
    // userList: Array<userEntity> = [];
    DeleteModalComponent: ModalComponent;
    // ChooseduserItem: userEntity = new userEntity();
    SearchuserName: string;

    constructor(private fileService: fileService,
                private toastr: BottomToastsManager, vcr: ViewContainerRef) {
        this.CreatedfileEntity = new FileEntity();
        this.EditedfileEntity = new FileEntity();
        this.SearchfileEntity = new SearchfileEntity();
        this.Search();
        this.EditModalComponent = new ModalComponent();
        this.DeleteModalComponent = new ModalComponent();
        this.toastr.setRootViewContainerRef(vcr);
    }

    Search() {
        this.SearchfileEntity.Skip = this.PagingModel.Take * this.PagingModel.Active;
        this.SearchfileEntity.Take = this.PagingModel.Take;
        this.fileService.Get(this.SearchfileEntity).subscribe(p => {
            this.fileEntities = p;
            this.Count();
        });
    }

    Count() {
        this.fileService.Count(this.SearchfileEntity).subscribe(data => {
            this.PagingModel.TotalPage = Math.ceil(data / this.PagingModel.Take);
        });
    }

    LoadDataToUpdateModal(file: FileEntity, index: number) {
        this.EditedfileEntity = new FileEntity(file);
        this.EditPosition = index;
        document.getElementById(this.EditModalComponent.ID).click();
    }

    LoadDataToDelete(file: FileEntity) {
        this.DeleteData = file;
        document.getElementById(this.DeleteModalComponent.ID).click();
    }

    Edit() {
        this.fileService.Update(this.EditedfileEntity).subscribe(p => {
            this.fileEntities[this.EditPosition] = p;
            this.toastr.ShowSuccess();
        }, e => {
            this.toastr.ShowError(e);
        });
    }

    Add() {
        this.fileService.Create(this.CreatedfileEntity).subscribe(p => {
            p.IsEdit = true;
            this.fileEntities.unshift(p);
            this.CreatedfileEntity = new FileEntity();
            this.toastr.ShowSuccess();
        }, e => {
            this.toastr.ShowError(e);
        });
    }

    Delete() {
        this.fileService.Delete(this.DeleteData).subscribe(p => {
            let indexOf = this.fileEntities.indexOf(this.DeleteData);
            this.fileEntities.splice(indexOf, 1);
            this.toastr.ShowSuccess();
        }, e => {
            this.toastr.ShowError(e);
        });
    }


}