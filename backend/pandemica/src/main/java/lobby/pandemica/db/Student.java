package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")

public class Student extends BaseEntity {
    //Attributes
    @Column(name = "name")
    private String name;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "bilkentId", unique = true)
    private Integer bilkentId;

    @Column(name = "age")
    private Integer age;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "department")
    private String department;

    @Column(name = "year")
    private String year;

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "friend-id", referencedColumnName = "id", columnDefinition = "uuid[]")
    //private ArrayList<Student> friends;


    //Methods
    /*
    public void sendFriendRequest(FriendRequest friendRequest){}
    public Boolean deleteFriendRequest(FriendRequest friendRequest){}
    public void addNeighbor( Neighbor neighbor){}
    public Boolean removeNeighbor( Neighbor neighbor){}
    public void addFriend( Friend friend){}
    public Boolean unfriend( Friend friend){}*/



}
