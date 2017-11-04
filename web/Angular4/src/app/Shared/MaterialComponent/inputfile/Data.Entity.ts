export class DataEntity {
    Name: string;
    Data: string;
    Length: number;
    Extension: string;

    constructor(DataEntity?: any) {
        if (DataEntity == null) {
            this.Name = null;
            this.Data = null;
            this.Length = null;
            this.Extension = null;
        } else {
            this.Name = DataEntity.Name;
            this.Data = DataEntity.Data;
            this.Length = DataEntity.Length;
            this.Extension = DataEntity.Extension;
        }
    }

    GetKB(): number {
        let Result = Math.round(this.Length / 1024);
        return Result == NaN ? 0 : Result;
    }

    GetMB(): number {
        let Result = Math.round(this.Length / 1024 / 1024);
        return Result == NaN ? 0 : Result;
    }
}
