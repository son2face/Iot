import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';
import {Http} from '@angular/http';
import {HttpService} from "../../Shared/HttpService";
import {Injectable} from '@angular/core';
import {UserEntity} from "./user.Entity";
import {SearchuserEntity} from "./user.SearchEntity";
import {FileEntity} from "../file/file.Entity";

@Injectable()
export class userService {
    public url: string;

    constructor(private Http: Http) {
        this.url = "api/Users";
    }

    GetData(url: string, data: any): Observable<any> {
        let http = <HttpService>this.Http;
        return http.get(url, {params: data}, false)
            .map(res => {
                return res.json();
            });
    }

    Get(Search?: SearchuserEntity): Observable<any> {
        return this.Http.get(this.url, {params: Search === undefined ? null : Search.ToParams()})
            .map(res => {
                return res.json().map((item: any) => {
                    return new UserEntity(item);
                });
            });
    }

    Count(Search?: SearchuserEntity): Observable<number> {
        Search = Search === undefined ? new SearchuserEntity() : Search;
        return this.Http.get(this.url + "/Count", {params: Search.ToParams()})
            .map(res => {
                return res.json();
            });
    }

    GetId(Id: string): Observable<any> {
        return this.Http.get(`${this.url}/${Id}`)
            .map(res => {
                return new UserEntity(res.json());
            });
    }

    Create(data: UserEntity): Observable<any> {
        return this.Http.post(`${this.url}`, data)
            .map(res => {
                return new UserEntity(res.json());
            });
    }

    Update(data: UserEntity): Observable<any> {
        return this.Http.put(`${this.url}/${data.userId}`, data)
            .map(res => {
                return new UserEntity(res.json());
            });
    }

    Delete(data: UserEntity): Observable<any> {
        return this.Http.delete(`${this.url}/${data.userId}`)
            .catch(e => Observable.throw(e));
    }

    Getfile(userId: string) {
        return this.Http.get(`${this.url}/${userId}/files`)
            .map(res => {
                return res.json().map(e => new FileEntity(e));
            });
    }

    Addfile(userId: string, fileId: string) {
        return this.Http.post(`${this.url}/${userId}/files/${fileId}`, {});
    }

    Updatefile(userId: string, fileId: string) {
        return this.Http.put(`${this.url}/${userId}/files/${fileId}`, {});
    }

    Deletefile(userId: string, fileId: string) {
        return this.Http.delete(`${this.url}/${userId}/files/${fileId}`);
    }



    Addproblem(userId: string, problemId: string) {
        return this.Http.post(`${this.url}/${userId}/problems/${problemId}`, {});
    }

    Updateproblem(userId: string, problemId: string) {
        return this.Http.put(`${this.url}/${userId}/problems/${problemId}`, {});
    }

    Deleteproblem(userId: string, problemId: string) {
        return this.Http.delete(`${this.url}/${userId}/problems/${problemId}`);
    }



    Addshape(userId: string, shapeId: string) {
        return this.Http.post(`${this.url}/${userId}/shapes/${shapeId}`, {});
    }

    Updateshape(userId: string, shapeId: string) {
        return this.Http.put(`${this.url}/${userId}/shapes/${shapeId}`, {});
    }

    Deleteshape(userId: string, shapeId: string) {
        return this.Http.delete(`${this.url}/${userId}/shapes/${shapeId}`);
    }
}