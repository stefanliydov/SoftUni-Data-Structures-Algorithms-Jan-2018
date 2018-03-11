package judge;

public class Submission implements Comparable<Submission> {
    private int id;
    private int points;
    private SubmissionType submissionType;
    private int contestId;
    private int userId;

    public Submission(int id, int points, SubmissionType submissionType, int contestId, int userId) {
        this.id = id;
        this.points = points;
        this.submissionType = submissionType;
        this.contestId = contestId;
        this.userId = userId;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public SubmissionType getType() {
        return this.submissionType;
    }

    public void setSubmissionType(SubmissionType submissionType) {
        this.submissionType = submissionType;
    }

    public int getContestId() {
        return this.contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int compareTo(Submission o) {
        return Integer.compare(this.getUserId(), o.getUserId());
    }
}
