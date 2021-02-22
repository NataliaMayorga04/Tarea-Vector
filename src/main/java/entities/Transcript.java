package entities;

import java.util.Optional;
import java.util.Vector;

public class Transcript {
    public static final int MIN_APPROVED_GRADE = 3;
    private Vector<TranscriptEntry> entries;

    public Transcript() {
        this.entries = new Vector<>();
    }

    public boolean addTranscriptEntry(Course courseTaken, int grade) {
        TranscriptEntry entry = new TranscriptEntry(courseTaken, grade);
        return this.entries.add(entry);
    }

    public boolean addTranscriptEntry(TranscriptEntry entry) {
        return this.entries.add(entry);
    }

    public boolean isApproved(Course course) {
        Optional<TranscriptEntry> entry = this.entries.stream()
                .filter(e -> e.getCourseTaken().equals(course) && e.getGrade() >= MIN_APPROVED_GRADE)
                .findFirst();
        return entry.isPresent();
    }

    public boolean isApproved(Vector<Course> courses) {
        return courses.stream()
                .map(c->isApproved(c))
                .allMatch(a->true);
    }

    public double getAverageGrade() {
        double average = 0;

        if (this.entries.size() > 0) {
            for (TranscriptEntry entry : this.entries) {
                average += entry.getGrade();
            }
            average = average / this.entries.size();
        }
        return average;
    }
}
