import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';
import {Http} from '@angular/http';
import {HttpService} from "../../Shared/HttpService";
import {Injectable} from '@angular/core';
import {NodeEntity} from "./Node.Entity";
import {SearchNodeEntity} from "./Node.SearchEntity";

@Injectable()
export class NodeService {
    public url: string;

    constructor(private Http: Http) {
        this.url = "api/nodes";
    }

    GetData(url: string, data: any): Observable<any> {
        let http = <HttpService>this.Http;
        return http.get(url, {params: data}, false)
            .map(res => {
                return res.json();
            });
    }

    Get(Search?: SearchNodeEntity): Observable<any> {
        return this.Http.get(this.url, {params: Search === undefined ? null : Search.ToParams()})
            .map(res => {
                return res.json().map((item: any) => {
                    return new NodeEntity(item);
                });
            });
    }

    Count(Search?: SearchNodeEntity): Observable<number> {
        Search = Search === undefined ? new SearchNodeEntity() : Search;
        return this.Http.get(this.url + "/Count", {params: Search.ToParams()})
            .map(res => {
                return res.json();
            });
    }

    GetId(Id: string): Observable<any> {
        return this.Http.get(`${this.url}/${Id}`)
            .map(res => {
                return new NodeEntity(res.json());
            });
    }

    Create(data: NodeEntity): Observable<any> {
        return this.Http.post(`${this.url}`, data)
            .map(res => {
                return new NodeEntity(res.json());
            });
    }

    Update(data: NodeEntity): Observable<any> {
        return this.Http.put(`${this.url}/${data.id}`, data)
            .map(res => {
                return new NodeEntity(res.json());
            });
    }

    Delete(data: NodeEntity): Observable<any> {
        return this.Http.delete(`${this.url}/${data.id}`)
            .catch(e => Observable.throw(e));
    }

//		GetNodeValue(NodeId: string) {
//			return this.Http.get(`${this.url}/${NodeId}/NodeValues`)
//				.map(res => {
//					return res.json().map(e => new NodeValueEntity(e));
//				});
//		}
//		AddNodeValue(NodeId: string, NodeValueId: string) {
//			return this.Http.post(`${this.url}/${NodeId}/NodeValues/${NodeValueId}`, {});
//		}
//		UpdateNodeValue(NodeId: string, NodeValueId: string) {
//			return this.Http.put(`${this.url}/${NodeId}/NodeValues/${NodeValueId}`, {});
//		}
//		DeleteNodeValue(NodeId: string, NodeValueId: string) {
//			return this.Http.delete(`${this.url}/${NodeId}/NodeValues/${NodeValueId}`);
//		}
}