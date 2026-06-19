public class ConcertEvent extends CampusEvent {

    private String performerName;
    private double ticketPrice;

    public ConcertEvent(String title, String location, String date, String startingTime, String endingTime,
            int maxPeople, String performerName, double ticketPrice) {
        super(title, location, date, startingTime, endingTime, maxPeople);
        this.performerName = performerName;
        this.ticketPrice = ticketPrice;
    }

    public String getPerformerName() {
        return performerName;
    }

    public void setPerformerName(String performerName) {
        this.performerName = performerName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String getEventType() {
        return "Concert";
    }

    @Override
    public double calculateCost() {
        return ticketPrice;
    }

    @Override
    public String toString() {
        return super.toString() + "\nPerformer: " + this.performerName + "\n" + "Ticket Price: $" + this.ticketPrice;
    }

    @Override
    public int getRecommendationScore(StudentProfile profile) {
        int score = super.getRecommendationScore(profile);
        if (profile.getInterest().equals("music")) {
            score += 10;
        }
        return climitScore(score);
    }

}
