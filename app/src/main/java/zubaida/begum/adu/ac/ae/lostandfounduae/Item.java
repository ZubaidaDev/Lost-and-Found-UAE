package zubaida.begum.adu.ac.ae.lostandfounduae;
public class Item {

    private int id;
    private String itemName;
    private String description;
    private String location;
    private String date;
    private String type; // "lost" or "found"

    public Item(int id, String itemName, String description, String location, String date, String type) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.location = location;
        this.date = date;
        this.type = type;
    }

    public int getId() { return id; }
    public String getItemName() { return itemName; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public String getType() { return type; }
}
