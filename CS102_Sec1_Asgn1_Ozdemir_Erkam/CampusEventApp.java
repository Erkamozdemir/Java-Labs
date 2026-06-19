import java.util.ArrayList;

public class CampusEventApp {
    public static void main(String[] args) {

        StudentProfile aylin = new StudentProfile("Aylin", "technology", 50.0);
        ArrayList<CampusEvent> events = new ArrayList<CampusEvent>();

        WorkshopEvent workshop = new WorkshopEvent("Intro to Coding", "Computer Lab 2",
                "2026-07-02", "13:00", "15:00", 2, "coding with Java", 7.5);
        workshop.addRequiredMaterial("Laptop");
        workshop.addRequiredMaterial("Notebook");

        SportsEvent sports = new SportsEvent("Bilkent Volleyball", "Student Dormitory Sports Center",
                "2026-07-21", "14:00", "16:00", 4, "volleyball", false);

        ConcertEvent concert = new ConcertEvent("Summer Concert Series", "Odeon",
                "2026-07-03", "21:00", "23:00", 1000, "Saving Grace with Robert Plant", 30.0);

        VolunteerEvent volunteer = new VolunteerEvent("Campus Cleanup", "Springfest Green Field",
                "2026-07-14", "10:00", "12:00", 2, "Green Campus Club", 2);

        events.add(workshop);
        events.add(sports);
        events.add(concert);
        events.add(volunteer);

        System.out.println("All Campus Events:");
        System.out.println("----------------------------");
        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i));
            System.out.println();
        }

        System.out.println("Registering for events:");
        System.out.println("----------------------------");
        System.out.println("Intro to Coding: " + workshop.register());
        System.out.println("Intro to Coding: " + workshop.register());
        System.out.println("Intro to Coding: " + workshop.register());
        System.out.println("Summer Concert Series: " + concert.register());
        System.out.println();

        System.out.println("Updated Event Information:");
        System.out.println("----------------------------");
        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i));
            System.out.println();
        }

        System.out.println("Checking Schedule Conflict:");
        System.out.println("----------------------------");
        if (workshop.conflictsWith(sports)) {
            System.out.println("Intro to Coding conflicts with Bilkent Volleyball");
        } else {
            System.out.println("Intro to Coding does not conflict with Bilkent Volleyball");
        }
        System.out.println();

        System.out.println("Schedule Summaries:");
        System.out.println("----------------------------");
        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i).getScheduleSummary());
        }
        System.out.println();

        System.out.println("Recommendation Scores for Aylin:");
        System.out.println("----------------------------");
        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i).getTitle() + ": " + events.get(i).getRecommendationScore(aylin));
        }
    }
}
