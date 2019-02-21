package saveWesteros;
import java.util.*;

public class Cell {
    public String type;
    public ArrayList <String> visited = new ArrayList <String> ();
    
    public Cell(String type) {
        this.type = type;
    }
    public Cell(String type, ArrayList <String> visited) {
        this.type = type;
        this.visited = visited;
    }
}
