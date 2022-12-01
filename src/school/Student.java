package school;

import java.util.ArrayList;

public class Student {
	private int studentId;				// 학번
	private String studentName;			// 이름
	private Subject majorsubject;		// 필수과목
		// addSubjectScore(Score score) 메소드가 호출되면 이 리스트에 추가됨
	private ArrayList<Score> scoreList = new ArrayList<Score>();	// 학생이 수강한 과목의 점수 리스트
	
	public Student(int studentId, String studentName, Subject majorSubject) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.majorsubject = majorSubject;
	}
	
	public void addSubjectScore(Score score) {
		scoreList.add(score);
	}

		// getter, setter 메소드
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Subject getMajorsubject() {
		return majorsubject;
	}

	public void setMajorsubject(Subject majorsubject) {
		this.majorsubject = majorsubject;
	}

	public ArrayList<Score> getScoreList() {
		return scoreList;
	}

	public void setScoreList(ArrayList<Score> scoreList) {
		this.scoreList = scoreList;
	}
	
	
}
