public class StudentProfile {
    private String name;
    private String interest;
    private double maxBudget;

    public StudentProfile(String name, String interest, double maxBudget) {
        this.name = name;
        this.interest = interest;
        this.maxBudget = maxBudget;
    }

    public String getName() {
        return name;
    }

    public String getInterest() {
        return interest;
    }

    public double getMaxBudget() {
        return maxBudget;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public void setMaxBudget(double maxBudget) {
        this.maxBudget = maxBudget;
    }

    @Override
    public String toString() {
        return "Student: " + name + " Interest: " + interest + " MaxBudget: $" + maxBudget;
    }
}
