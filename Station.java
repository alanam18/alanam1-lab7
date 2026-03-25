import java.util.ArrayList;
 
public class Station {
    protected String line;
    protected String name;
    protected boolean inService;
    protected Station prev;
    protected Station next;
 
    public Station(String line, String name) {
        this.line = line;
        this.name = name;
        this.inService = true;
        this.prev = null;
        this.next = null;
    }
  public void addNext(Station s) {
        this.next = s;
        s.prev = this;
    }
 
    public void addPrev(Station s) {
        this.prev = s;
        s.next = this;
    }
 
    public void connect(Station s) {
        this.next = s;
        s.prev = this;
    }
 
    public boolean isAvailable() {
        return inService;
    }
 
    public void switchAvailable() {
        inService = !inService;
    }
 
    public boolean equals(Object o) {
        if (!(o instanceof Station)) return false;
        Station other = (Station) o;
        return this.line.equals(other.line) && this.name.equals(other.name);
    }
 
    public String toString() {
        String prevName = (prev == null) ? "none" : prev.name;
        String nextName = (next == null) ? "none" : next.name;
        return "STATION " + name + ": " + line + " line, in service: " + inService +
               ", previous station: " + prevName + ", next station: " + nextName;
    }
  public int tripLength(Station dest) {
        ArrayList<Station> visited = new ArrayList<>();
        return tripHelper(dest, visited);
    }
 
    protected int tripHelper(Station dest, ArrayList<Station> visited) {
        if (visited.contains(this)) {
            System.out.println("Already visited " + name + ", backtracking");
            return -1;
        }
        if (this.equals(dest)) {
            System.out.println("Found destination: " + name);
            return 0;
        }
        visited.add(this);
        if (next != null) {
            System.out.println("Trying next: " + next.name + " from " + name);
            int result = next.tripHelper(dest, visited);
            if (result >= 0) return result + 1;
        }
        System.out.println("Dead end at: " + name);
        return -1;
    }
}
 
