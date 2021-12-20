package lobby.pandemica.db;

import lobby.pandemica.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")

public class Student extends BaseEntity {
    //Attributes
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "bilkent_id", unique = true, nullable = false)
    private Integer bilkentId;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "phone-number", nullable = false)
    private String phoneNumber;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "year", nullable = false)
    private String year;

    /*
    @ManyToMany
    @JoinTable(name = "neighbors31",
            joinColumns={@JoinColumn(name="first-neighbor", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="second-neighbor", referencedColumnName="id")}
    )
    private Collection<Student> students;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST)
    private Collection<Student> students2;


    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "friend-id", referencedColumnName = "id", columnDefinition = "uuid[]")
    //private ArrayList<Student> friends;*/


    //Methods
    /*
    public void sendFriendRequest(FriendRequest friendRequest){}
    public Boolean deleteFriendRequest(FriendRequest friendRequest){}
    public void addNeighbor( Neighbor neighbor){}
    public Boolean removeNeighbor( Neighbor neighbor){}
    public void addFriend( Friend friend){}
    public Boolean unfriend( Friend friend){}*/



}
