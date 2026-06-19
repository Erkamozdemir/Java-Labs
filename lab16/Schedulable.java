public interface Schedulable {
    boolean conflictsWith(CampusEvent other);

    String getScheduleSummary();
}