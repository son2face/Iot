import {RouterModule, Routes} from "@angular/router";
//import {RegisterComponent} from "./Component/Registry/Register/Register.Component";
//import {SourceDomainComponent} from "./Component/Registry/SourceDomain/SourceDomain.Component";
//import {DestinationDomainComponent} from "./Component/Registry/DestinationDomain/DestinationDomain.Component";
import {HomeComponent} from "./Modules/Home/Home.Component";
//[IMPORT MODULE]
// import {[MODULE]Component} from "./Modules/[MODULE]/[MODULE].Component";
import {fileComponent} from "./Modules/file/file.Component";
import {userComponent} from "./Modules/user/user.Component";
import {NodeValueComponent} from "./Modules/NodeValue/NodeValue.Component";
import {NodeComponent} from "./Modules/Node/Node.Component";
// import {[MODULE]Component} from "./Modules/[MODULE]/[MODULE].Component";
//[END]
const routes: Routes = [
    //{
    //    path: 'Registry/SourceDomains',
    //    component: SourceDomainComponent
    //},
    {
        path: 'Home',
        component: HomeComponent,
        // canActivate: [AuthGuard]
    },
//[IMPORT MODULE]
// {    path: '[MODULE]',    component: [MODULE]Component},
    {path: 'files', component: fileComponent, pathMatch: 'full'},
    {path: 'users', component: userComponent, pathMatch: 'full'},
    {path: 'nodevalues', component: NodeValueComponent, pathMatch: 'full'},
    {path: 'nodes', component: NodeComponent, pathMatch: 'full'},
    {path: 'nodes/:nodeId/nodevalues', component: NodeValueComponent, pathMatch: 'full'},
// {    path: '[MODULE]',    component: [MODULE]Component},
    //[END]
    {
        path: '**',
        redirectTo: 'Home',
    },
    {
        path: 'Fams',
        redirectTo: 'Fams/PurchaseRequest',
        pathMatch: 'full'
    },
    {
        path: 'List',
        redirectTo: 'List/Vendor',
        pathMatch: 'full'
    },
    {
        path: 'Permission',
        redirectTo: 'Permission/User',
        pathMatch: 'full'
    }
];
export const Routing = RouterModule.forRoot(routes);
