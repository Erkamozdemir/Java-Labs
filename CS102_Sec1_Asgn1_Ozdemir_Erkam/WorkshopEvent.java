import java.util.ArrayList;

public class WorkshopEvent extends CampusEvent {
    private String topic;
    private double price;
    private ArrayList<String> requiredObjects;

    public WorkshopEvent(String title, String location, String date, String startingTime, String endingTime,
            int maxPeople, String topic, double price) {
        super(title, location, date, startingTime, endingTime, maxPeople);
        this.topic = topic;
        this.price = price;
        this.requiredObjects = new ArrayList<String>();
    }

    public void addRequiredMaterial(String material) {
        this.requiredObjects.add(material);
    }

    public boolean removeRequiredMaterial(String material) {
        for (int i = 0; i < this.requiredObjects.size(); i++) {
            if (material.equals(this.requiredObjects.get(i))) {
                this.requiredObjects.remove(i);
                return true;
            }
        }
        return false;
    }

    public String getMaterialsList() {
        String str = "";
        if (this.requiredObjects.isEmpty()) {
            return "No materials required.";
        }
        for (int i = 0; i < this.requiredObjects.size(); i++) {
            str += " - " + this.requiredObjects.get(i) + "\n";
        }
        return str;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<String> getRequiredObjects() {
        return requiredObjects;
    }

    public void setRequiredObjects(ArrayList<String> requiredObjects) {
        this.requiredObjects = requiredObjects;
    }

    @Override
    public String getEventType() {
        return "Workshop";
    }

    @Override
    public double calculateCost() {
        return price;
    }

    @Override
    public String toString() {
        return super.toString() + "\nTopic: " + this.topic + "\n" + "Materials Fee: $" + this.getPrice() + "\n"
                + "Required Materials: \n" + this.getMaterialsList();
    }

    @Override
    public int getRecommendationScore(StudentProfile profile) {
        int score = super.getRecommendationScore(profile);
        if (profile.getInterest().equals("technology")
                && (this.topic.contains("coding") || this.topic.contains("robotics"))) {
            score += 10;
        }
        return climitScore(score);
    }

}
