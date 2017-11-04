import {FileEntity} from "../file/file.Entity";

export class UserEntity {
    userId: number;
    userName: string;
    passWord: string;
    fileEntities: FileEntity[];
    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;

    constructor(user: any = null) {
        if (user == null) {
            this.userId = null;
            this.userName = null;
            this.passWord = null;
            this.fileEntities = [];
        } else {
            this.userId = user.userId;
            this.userName = user.userName;
            this.passWord = user.passWord;
            if (user.fileEntities != null) {
                this.fileEntities = [];
                for (let item of user.fileEntities) {
                    this.fileEntities.push(new FileEntity(item));
                }
            }

            if (this.fileEntities == null) this.fileEntities = [];
        }
        this.IsEdit = false;
    }
}