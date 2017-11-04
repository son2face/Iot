import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';
import {Http} from '@angular/http';
import {HttpService} from "../../Shared/HttpService";
import {Injectable} from '@angular/core';
import {NodeValueEntity} from "./NodeValue.Entity";
import {SearchNodeValueEntity} from "./NodeValue.SearchEntity";

@Injectable()
export class NodeValueService {
    public url: string;

    constructor(private Http: Http) {
        this.url = "api/values";
    }

    GetData(url: string, data: any): Observable<any> {
        let http = <HttpService>this.Http;
        return http.get(url, {params: data}, false)
            .map(res => {
                return res.json();
            });
    }

    Get(Search?: SearchNodeValueEntity): Observable<any> {
        return this.Http.get(this.url, {params: Search === undefined ? null : Search.ToParams()})
            .map(res => {
                return res.json().map((item: any) => {
                    return new NodeValueEntity(item);
                });
            });
    }

    Count(Search?: SearchNodeValueEntity): Observable<number> {
        Search = Search === undefined ? new SearchNodeValueEntity() : Search;
        return this.Http.get(this.url + "/Count", {params: Search.ToParams()})
            .map(res => {
                return res.json();
            });
    }

    GetId(Id: string): Observable<any> {
        return this.Http.get(`${this.url}/${Id}`)
            .map(res => {
                return new NodeValueEntity(res.json());
            });
    }

    Create(data: NodeValueEntity): Observable<any> {
        return this.Http.post(`${this.url}`, data)
            .map(res => {
                return new NodeValueEntity(res.json());
            });
    }

    Update(data: NodeValueEntity): Observable<any> {
        return this.Http.put(`${this.url}/${data.id}`, data)
            .map(res => {
                return new NodeValueEntity(res.json());
            });
    }

    Delete(data: NodeValueEntity): Observable<any> {
        return this.Http.delete(`${this.url}/${data.id}`)
            .catch(e => Observable.throw(e));
    }
}