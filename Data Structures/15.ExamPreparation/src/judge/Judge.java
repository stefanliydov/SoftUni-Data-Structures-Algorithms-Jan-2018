package judge;

import judge.IJudge;

import java.util.*;
import java.util.stream.Collectors;

public class Judge implements IJudge {

    private TreeSet<Integer> usersId;
    private TreeSet<Integer> contestsId;
    private TreeMap<Integer, Submission> submissionId;
    private TreeMap<Integer, List<Submission>> byPoints;

    public Judge() {
        this.usersId = new TreeSet<>();
        this.contestsId = new TreeSet<>();
        this.submissionId = new TreeMap<>();
        this.byPoints = new TreeMap<>();
    }

    public void addContest(int contestId) {
        this.contestsId.add(contestId);
    }

    public void addSubmission(Submission submission) {
        if (!this.usersId.contains(submission.getUserId())) {
            throw new UnsupportedOperationException();
        }
        if (!this.contestsId.contains(submission.getContestId())) {
            throw new UnsupportedOperationException();
        }
        this.submissionId.put(submission.getId(), submission);
        if (!this.byPoints.containsKey(submission.getPoints())) {
            this.byPoints.put(submission.getPoints(),new ArrayList<>());
        }
        this.byPoints.get(submission.getPoints()).add(submission);
    }

    public void addUser(int userId) {
        this.usersId.add(userId);
    }

    public void deleteSubmission(int submissionId) {
        if (!this.submissionId.containsKey(submissionId)) {
            throw new IllegalArgumentException();
        }
       Submission submission= this.submissionId.remove(submissionId);
        this.byPoints.get(submission.getPoints()).remove(submission);
    }

    public Iterable<Submission> getSubmissions() {
        return new ArrayList<>(this.submissionId.values());
    }

    public Iterable<Integer> getUsers() {
        return new ArrayList<>(this.usersId);
    }

    public Iterable<Integer> getContests() {
        return new ArrayList<>(this.contestsId);
    }

    public Iterable<Submission> submissionsWithPointsInRangeBySubmissionType(int minPoints, int maxPoints, SubmissionType submissionType) {
        List<Submission> submissions = new ArrayList<>();
         this.byPoints.subMap(minPoints, maxPoints + 1)
                .values()
                .forEach(submissions::addAll);

    return submissions.stream().filter(x -> x.getType().equals(submissionType))
        .collect(Collectors.toList());
    }

    public Iterable<Integer> contestsByUserIdOrderedByPointsDescThenBySubmissionId(int userId) {
        return submissionId.values()
                .stream()
                .filter(x -> x.getUserId() == userId)
                .sorted((a, b) -> {
                    int firstCrit = Integer.compare(b.getPoints(),a.getPoints());
                    if(firstCrit == 0){
                        return Integer.compare(a.getId(),b.getId());
                    }
                    return firstCrit;
                }).map(Submission::getContestId)
                .collect(Collectors.toList());
    }

    public Iterable<Submission> submissionsInContestIdByUserIdWithPoints(int points, int contestId, int userId) {
        if (!this.usersId.contains(userId) || !this.contestsId.contains(contestId)){
            throw new IllegalArgumentException();
        }
       return this.byPoints.get(points).stream().filter(x-> x.getContestId()==contestId && x.getUserId()==userId).collect(Collectors.toList());
    }

    public Iterable<Integer> contestsBySubmissionType(SubmissionType submissionType) {
        return this.submissionId.values().stream().filter(x->x.getType().equals(submissionType)).map(Submission::getContestId).collect(Collectors.toList());
    }
}
