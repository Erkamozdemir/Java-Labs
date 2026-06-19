public class VolunteerEvent extends CampusEvent {

    private String organization;
    private int serviceHours;

    public VolunteerEvent(String title, String location, String date, String startingTime, String endingTime,
            int maxPeople, String organization, int serviceHours) {
        super(title, location, date, startingTime, endingTime, maxPeople);
        this.organization = organization;
        this.serviceHours = serviceHours;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getServiceHours() {
        return serviceHours;
    }

    public void setServiceHours(int serviceHours) {
        this.serviceHours = serviceHours;
    }

    @Override
    public String getEventType() {
        return "Volunteer";
    }

    @Override
    public double calculateCost() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\nOrganization: " + this.organization + "\n" + "Service Hours: "
                + this.serviceHours;
    }

    @Override
    public int getRecommendationScore(StudentProfile profile) {
        int score = super.getRecommendationScore(profile);
        if (profile.getInterest().equals("service")) {
            score += 10;
        }
        return climitScore(score);
    }

}
