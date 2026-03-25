public class EndStation extends Station {
 
    public EndStation(String line, String name) {
        super(line, name);
    }
 
    public void makeEnd() {
      if (next != null) {
        prev = next;
      } 
      else {
        next = prev;
      }
    }

    public String toString() {
      return super.toString().replace("STATION", "ENDSTATION");
    }
}
