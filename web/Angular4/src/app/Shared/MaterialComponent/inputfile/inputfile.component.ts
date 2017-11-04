import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {DataEntity} from "./Data.Entity";

@Component({
    selector: 'inputfile',
    templateUrl: './inputfile.component.html'
})
export class InputfileComponent implements OnInit {
    Id: string = this.MakeRandomId();

    @Input() DataEntity: DataEntity = new DataEntity();
    @Output() onFileChanged: EventEmitter<DataEntity> = new EventEmitter();

    constructor() {
    }

    @Input('FileId')
    set Innit(value: string) {
        if (value !== undefined)
            this.Id = value;
        else console.warn("FileId Field isn't setted !")
    }

    ngOnInit() {
    }

    OpenFile() {
        document.getElementById(this.Id).click();
    }


    LoadFile(files) {
        let f = files[0],
            r = new FileReader();
        this.DataEntity.Name = f.name;
        r.onloadend = (e => { //callback after files finish loading
            this.DataEntity.Data = r.result;
            this.DataEntity.Data = this.DataEntity.Data.substr(this.DataEntity.Data.indexOf(',') + 1);
        });
        this.DataEntity.Length = f.size;
        let Arr = this.DataEntity.Name.split('.');
        this.DataEntity.Extension = Arr.length > 1 ? Arr[Arr.length - 1] : Arr[0];
        r.readAsDataURL(f);
        this.onFileChanged.emit(this.DataEntity);
    }

    MakeRandomId(): string {
        let text = "";
        let possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (let i = 0; i < 5; i++)
            text += possible.charAt(Math.floor(Math.random() * possible.length));
        return text;
    }
}

