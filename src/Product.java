import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String description;
    private String id;
    private double cost;

    // Constructor
    public Product(String name, String description, String id, double cost) {
        this.name = padString(name, 35);
        this.description = padString(description, 75);
        this.id = padString(id, 6);
        this.cost = cost;
    }

    // Pad the string to the specified length
    private String padString(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length);
        } else {
            StringBuilder padded = new StringBuilder(str);
            while (padded.length() < length) {
                padded.append(" ");
            }
            return padded.toString();
        }
    }

    // Getters and setters (if needed)
    public String getName() {
        return name.trim();
    }

    public void setName(String name) {
        this.name = padString(name, 35);
    }

    public String getDescription() {
        return description.trim();
    }

    public void setDescription(String description) {
        this.description = padString(description, 75);
    }

    public String getId() {
        return id.trim();
    }

    public void setId(String id) {
        this.id = padString(id, 6);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name.trim() + '\'' +
                ", description='" + description.trim() + '\'' +
                ", id='" + id.trim() + '\'' +
                ", cost=" + cost +
                '}';
    }
}
