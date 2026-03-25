import java.util.ArrayList;
 
public class TransferStation extends Station {
 
    public ArrayList<Station> otherStations;
 
    public TransferStation(String line, String name) {
        super(line, name);
        otherStations = new ArrayList<>();
    }
 
    public void addTransferStationPrev(Station s) {
        s.next = this;
        otherStations.add(s);
    }
 
    public void addTransferStationNext(Station s) {
        s.prev = this;
        otherStations.add(s);
    }
 
   public String toString() {
    String result = super.toString().replace("STATION", "TRANSFERSTATION") + "\n\tTransfers: \n";
    for (Station s : otherStations) {
        String[] lines = s.toString().split("\n");
        for (String l : lines) {
            result += "\t" + l + "\n";
        }
    }
    return result;
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
            System.out.println("Trying primary next: " + next.name + " from " + name);
            int result = next.tripHelper(dest, visited);
            if (result >= 0) return result + 1;
        }
        for (Station s : otherStations) {
            if (s.prev != null && s.prev.name.equals(this.name)) {
                System.out.println("Trying transfer next: " + s.name + " from " + name);
                int result = s.tripHelper(dest, visited);
                if (result >= 0) return result + 1;
            }
        }
        System.out.println("Dead end at: " + name);
        return -1;
    }
}
