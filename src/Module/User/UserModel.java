package Module.User;

import Module.File.FileModel;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "iot", catalog = "")
public class UserModel {
    private int userId;
    private String userName;
    private String passWord;
    private Collection<FileModel> filesByUserId;

    @Id
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userName", nullable = true, length = 100)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "passWord", nullable = true, length = 100)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel that = (UserModel) o;

        if (userId != that.userId) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (passWord != null ? !passWord.equals(that.passWord) : that.passWord != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (passWord != null ? passWord.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<FileModel> getFilesByUserId() {
        return filesByUserId;
    }

    public void setFilesByUserId(Collection<FileModel> filesByUserId) {
        this.filesByUserId = filesByUserId;
    }


}
