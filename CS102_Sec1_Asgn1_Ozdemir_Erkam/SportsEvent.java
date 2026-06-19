public class SportsEvent extends CampusEvent {

    private String sport;
    private boolean equipmentRequired;

    public SportsEvent(String title, String location, String date, String startingTime, String endingTime,
            int maxPeople, String sport, boolean equipmentRequired) {
        super(title, location, date, startingTime, endingTime, maxPeople);
        this.sport = sport;
        this.equipmentRequired = equipmentRequired;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public boolean isEquipmentRequired() {
        return equipmentRequired;
    }

    public void setEquipmentRequired(boolean equipmentRequired) {
        this.equipmentRequired = equipmentRequired;
    }

    @Override
    public String getEventType() {
        return "Sports";
    }

    @Override
    public double calculateCost() {
        if (equipmentRequired) {
            return 5.0;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSport: " + this.sport + "\n" + "Equipment Required: " + this.equipmentRequired;
    }

    @Override
    public int getRecommendationScore(StudentProfile profile) {
        int score = super.getRecommendationScore(profile);
        if (profile.getInterest().equals("sports")) {
            score += 10;
        }
        return climitScore(score);
    }

}
