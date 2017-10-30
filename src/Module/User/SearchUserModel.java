package Module.User;

import Module.FilterModel;

import javax.persistence.*;
import javax.ws.rs.QueryParam;

@Entity
@Table(name = "point", schema = "intelligent", catalog = "")
public class SearchUserModel extends FilterModel {
    @QueryParam("userId")
    private int userId;
    @QueryParam("userName")
    private String userName;
    @QueryParam("passWord")
    private String passWord;
}
