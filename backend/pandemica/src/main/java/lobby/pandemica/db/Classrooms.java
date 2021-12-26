package lobby.pandemica.db;

import lombok.*;

import java.util.HashMap;
@Data
@Getter
@Setter
/**
 * Represents built in, hardcoded classroom templates as boolean multi-dim arrays; showing available seats
 */
public class Classrooms
{
    private static HashMap<String, Boolean[][]> classrooms;

    private final Boolean[][] b204 = {
            {false, false, true, true, true, true, true, true, false, false},
            {false, true, true, true, true, true, true, true, true, false},
            {true, true, true, true, true, true, true, true, true, true},
            {false, true, true, true, true, true, true, true, true, false},
            {false, false, true, true, true, true, true, true, false, false}
    };

    private final Boolean[][] ee214 = {
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true}
    };

    /**
     *
     */
    public Classrooms()
    {
        classrooms = new HashMap<>();
        addClassroom( "B-204", b204);
        addClassroom( "EE-214", ee214);
    }

    public Boolean[][] getClassroom(String classroomName)
    {
        return classrooms.get(classroomName);
    }

    public void addClassroom(String classroomName, Boolean[][] classroom)
    {
        classrooms.put(classroomName, classroom);
    }
}
