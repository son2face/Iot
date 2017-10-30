package Module.User;

import Module.File.FileEntity;
import Module.Problem.ProblemEntity;
import Module.Shape.ShapeEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "intelligent", catalog = "")
public class UserEntity {
    private int userId;
    private String userName;
    private String passWord;
    private Collection<FileEntity> filesByUserId;
    private Collection<ProblemEntity> problemsByUserId;
    private Collection<ShapeEntity> shapesByUserId;

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

        UserEntity that = (UserEntity) o;

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
    public Collection<FileEntity> getFilesByUserId() {
        return filesByUserId;
    }

    public void setFilesByUserId(Collection<FileEntity> filesByUserId) {
        this.filesByUserId = filesByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ProblemEntity> getProblemsByUserId() {
        return problemsByUserId;
    }

    public void setProblemsByUserId(Collection<ProblemEntity> problemsByUserId) {
        this.problemsByUserId = problemsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ShapeEntity> getShapesByUserId() {
        return shapesByUserId;
    }

    public void setShapesByUserId(Collection<ShapeEntity> shapesByUserId) {
        this.shapesByUserId = shapesByUserId;
    }
}
