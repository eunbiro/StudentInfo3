package school.report;

import java.util.ArrayList;

import Grade.*;
import Utills.Define;
import school.*;

public class GenerateGradeReport {
	
	School school = School.getInstance();		// 싱글톤 객체 생성
	
	public static final String TITLE 	= "수강생 학점 \t\t\n";
	public static final String HEADER 	= "이름 | 학번 | 필수과목 | 점수     \n";
	public static final String LINE 	= "-------------------------------------\n";
	
	private StringBuffer buffer = new StringBuffer();
	
		// 헤더, 몸통, 푸터를 만들어준다.
	public String getReport() {
		ArrayList<Subject> subjectList = school.getSubjectList();		// 부모리스트 불러오기
		for (Subject subject : subjectList) {
			makeHeader(subject);
			makeBody(subject);
			makeFooter();
		}
		
		return buffer.toString();		// buffer의 데이터를 String형태로 바꿈
	}
	
		// HEADER
	public void makeHeader(Subject subject) {
		buffer.append(GenerateGradeReport.LINE);
		buffer.append("\t" + subject.getSubjectName());
		buffer.append(GenerateGradeReport.TITLE);
		buffer.append(GenerateGradeReport.HEADER);
		buffer.append(GenerateGradeReport.LINE);
	}
	
	
	public void makeBody(Subject subject) {
			// 해당과목을 수강신청한 학생 리스트를 가져온다.
		ArrayList<Student> studentList = subject.getStudentList();
		
		for (int i = 0; i < studentList.size(); i++) {
			Student student = studentList.get(i);
			buffer.append(student.getStudentName());
			buffer.append(" | ");
			buffer.append(student.getStudentId());
			buffer.append(" | ");
			buffer.append(student.getMajorsubject().getSubjectName() + "\t");
			buffer.append(" | ");
			
				// 학생별 수강과목 점수와 학점
			getScoreGrade(student, subject.getSubjectId());
			buffer.append("\n");
			buffer.append(GenerateGradeReport.LINE);
		}
	}
	
		// 학생의 수강한 과목의 점수와 학점을 얻는 메소드
	public void getScoreGrade(Student student, int subjectId) {
			// 해당 학생이 수강한 과목의 점수 리스트(국어, 수학)
		ArrayList<Score> scoreList = student.getScoreList();
		
			// 해당 학생의 필수과목의 과목코드
		int majorId = student.getMajorsubject().getSubjectId();
		
		
//		GradeEvaluation gradeEvaluations1 = new BasicEvaluation();
//		GradeEvaluation gradeEvaluations2 = new MajorEvaluation();
		GradeEvaluation[] gradeEvaluations = { new BasicEvaluation(), new MajorEvaluation() };
		
		for (int i = 0; i < scoreList.size(); i++) {
			Score score = scoreList.get(i);
			
				// 과목코드가 같은지 확인
			if (score.getSubject().getSubjectId() == subjectId) {
				String grade;
				
				if (score.getSubject().getSubjectId() == majorId) {	// 필수과목일 때 학점산출
					grade = gradeEvaluations[Define.SAB_TYPE].getGrade(score.getPoint());
					
				} else {
					grade = gradeEvaluations[Define.AB_TYPE].getGrade(score.getPoint());
					
				}
				
				buffer.append(score.getPoint());
				buffer.append(":");
				buffer.append(grade);
				buffer.append(" | ");
			}
		}
	}
	
	public void makeFooter() {
		buffer.append("\n");	// buffer += "\n"
	}
}
