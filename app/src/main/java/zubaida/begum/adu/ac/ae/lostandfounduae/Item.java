package zubaida.begum.adu.ac.ae.lostandfounduae;

public class Item {

    private int id;
    private String itemName;
    private String description;
    private String location;
    private String date;
    private String imageLink;
    private String type;
    private String status;

    public Item(int id, String itemName, String description, String location,
                String date, String imageLink, String type, String status) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.location = location;
        this.date = date;
        this.imageLink = imageLink;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return itemName + "\n" + description + "\nLocation: " + location
                + "\nDate: " + date + "\nType: " + type;
    }
}