package lobby.pandemica.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")

public class Student extends User {
    //Attributes
    @Column(name = "department")
    private String department;

    @Column(name = "year")
    private String year;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friendRequest-id", referencedColumnName = "id", columnDefinition = "uuid[]")
    private FriendRequest[] friendRequests;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "neighbor-id", referencedColumnName = "id", columnDefinition = "uuid[]")
    private Neighbor[] neighbors;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend-id", referencedColumnName = "id", columnDefinition = "uuid[]")
    private Student[] friends;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend-id", referencedColumnName = "id", columnDefinition = "uuid[]")
    private Student[] friends;

    //Methods
    /*
    public void sendFriendRequest(FriendRequest friendRequest){}
    public Boolean deleteFriendRequest(FriendRequest friendRequest){}
    public void addNeighbor( Neighbor neighbor){}
    public Boolean removeNeighbor( Neighbor neighbor){}
    public void addFriend( Friend friend){}
    public Boolean unfriend( Friend friend){}*/



}
