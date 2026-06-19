public abstract class CampusEvent implements Schedulable, Recommendable {
    private String title;
    private String location;
    private String date;
    private String startingTime;
    private String endingTime;
    private int maxPeople;
    private int currentPeople;

    public CampusEvent(String title, String location, String date, String startingTime, String endingTime,
            int maxPeople) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.maxPeople = maxPeople;
        this.currentPeople = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public int getCurrentPeople() {
        return currentPeople;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void setCurrentPeople(int currentPeople) {
        this.currentPeople = currentPeople;
    }

    public int seatsLeft() {
        return this.maxPeople - this.currentPeople;
    }

    public boolean register() {
        if (this.currentPeople < this.maxPeople) {
            this.currentPeople++;
            return true;
        }
        return false;
    }

    public boolean unregister() {
        if (this.currentPeople > 0) {
            this.currentPeople--;
            return true;
        } else {
            return false;
        }
    }

    protected static final int BASE_SCORE = 50;

    public static int climitScore(int score) {
        if (score > 100) {
            return 100;
        } else if (score < 0) {
            return 0;
        }
        return score;
    }

    public int getRecommendationScore(StudentProfile profile) {
        int score = BASE_SCORE;
        if (this.calculateCost() <= profile.getMaxBudget()) {
            score += 10;
        }
        if (this.maxPeople == this.currentPeople) {
            score -= 20;
        }
        return score;
    }

    public abstract String getEventType();

    public abstract double calculateCost();

    public String getScheduleSummary() {
        return this.title + " on " + this.date + " from " + this.startingTime + " to " + this.endingTime;
    }

    public boolean conflictsWith(CampusEvent other) {
        if (this.date.equals(other.date)) {
            if (this.startingTime.compareTo(other.endingTime) >= 0
                    || this.endingTime.compareTo(other.startingTime) <= 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String typeOfEvent = this.getEventType();
        return typeOfEvent + ": " + title + "\n" + "Location: " + location + "\n" + "Date: " + date + "\n"
                + "Time: " + startingTime + "-" + endingTime + "\n" + "Capacity: " + currentPeople + "/" + maxPeople
                + "\n" + "Cost: $" + this.calculateCost();
    }

}
