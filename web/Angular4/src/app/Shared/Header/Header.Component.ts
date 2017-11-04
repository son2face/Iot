import {Component} from "@angular/core";
import {MenuModel} from "../../menu-model";
import {Http} from "@angular/http";
// import {AuthService} from "../../Modules/Auth/Auth.Service";
// import {UserEntity} from "../../Modules/User/User.Entity";
// import {UserService} from "../../Modules/User/User.Service";

// import {LayerAccessControlEntity} from "../../Modules/LayerAccessControl/LayerAccessControl.Entity";

@Component({
    selector: 'public-header',
    templateUrl: './Header.Component.html',
    styleUrls: ['./Header.Component.css']
})
export class HeaderComponent {
    MenuList: MenuModel[];
    // static User: UserEntity;
    // ParentLayerControl: LayerAccessControlEntity;
    // UserEntity: UserEntity;

    constructor(public Http: Http,
                // private UserService: UserService
                // , public AuthService: AuthService
    ) {
        // UserService.GetCurrent().subscribe(x => {
        // HeaderComponent.User = x;
        // this.UserService.UserEntity = x;
        // this.UserEntity = x;
        // });
        this.MenuList = Array<MenuModel>();
        let Home = new MenuModel("Trang chủ", "Home");
        let nodevalues = new MenuModel("Value", "nodevalues");
        let nodes = new MenuModel("Node", "nodes");
        let users = new MenuModel("Người dùng", "users");
        let files = new MenuModel("File", "files");
        this.MenuList.push(Home);
        this.MenuList.push(nodes);
        this.MenuList.push(nodevalues);
        this.MenuList.push(files);
        this.MenuList.push(users);

    }
}