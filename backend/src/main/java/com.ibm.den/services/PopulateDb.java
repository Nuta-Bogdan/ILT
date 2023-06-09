package com.ibm.den.services;

import com.ibm.den.entities.*;
import com.ibm.den.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PopulateDb {

    @Autowired
    private final ActivityRepository activityRepository ;
    @Autowired
    private final AttendanceRepository attendanceRepository;
    @Autowired
    private final GradeRepository   gradeRepository;
    @Autowired
    private final MentorRepository  mentorRepository;
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private final TeamRepository    teamRepository;

    public PopulateDb(ActivityRepository activityRepository, AttendanceRepository attendanceRepository, GradeRepository gradeRepository, MentorRepository mentorRepository, StudentRepository studentRepository, TaskRepository taskRepository, TeamRepository teamRepository) {
        this.activityRepository = activityRepository;
        this.attendanceRepository = attendanceRepository;
        this.gradeRepository = gradeRepository;
        this.mentorRepository = mentorRepository;
        this.studentRepository = studentRepository;
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
    }

    public void populateDb(){

        Activity activity1 = new Activity();
        activity1.setName("Activity1");
        Activity activity2 = new Activity();
        activity2.setName("Activity2");

        List<Task> tasks1 = List.of(
                new Task("Task1", "Description1"),
                new Task("Task2", "Description2"),
                new Task("Task3", "Description3")
        );

        List<Task> tasks2 = List.of(
                new Task("Task4", "Description4"),
                new Task("Task5", "Description5"),
                new Task("Task6", "Description6")
        );

        activity1.setTasks(tasks1);
        activity2.setTasks(tasks2);

        activityRepository.save(activity1);
        activityRepository.save(activity2);

        for (Task task : tasks1) {
            taskRepository.save(task);
        }
        for (Task task : tasks2) {
            taskRepository.save(task);
        }



        Team team1 = new Team();
        Team team2 = new Team();
        Team team3 = new Team();
        Team team4 = new Team();
        Team team5 = new Team();
        Team team6 = new Team();

        team1.setActivity(activity1);
        team2.setActivity(activity1);
        team3.setActivity(activity2);
        team4.setActivity(activity2);
        team5.setActivity(activity2);
        team6.setActivity(activity2);

        team1.setConfirmed(true);
        team2.setConfirmed(true);
        team3.setConfirmed(true);
        team4.setConfirmed(true);
        team5.setConfirmed(false);
        team6.setConfirmed(false);

        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        teamRepository.save(team4);
        teamRepository.save(team5);
        teamRepository.save(team6);

        ArrayList<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);
        teams.add(team5);
        teams.add(team6);




        for (int i = 0; i < 6; i++) {

            Student student1 = new Student();
            Student student2 = new Student();
            Student student3 = new Student();
            Student student4 = new Student();
            Student student5 = new Student();

            student1.setName("Student1"+Integer.toString(i));
            student2.setName("Student2"+Integer.toString(i));
            student3.setName("Student3"+Integer.toString(i));
            student4.setName("Student4"+Integer.toString(i));
            student5.setName("Student5"+Integer.toString(i));

            student1.setEmail("email1"+Integer.toString(i));
            student2.setEmail("email2"+Integer.toString(i));
            student3.setEmail("email3"+Integer.toString(i));
            student4.setEmail("email4"+Integer.toString(i));
            student5.setEmail("email5"+Integer.toString(i));

            student1.setLeader(true);
            student2.setLeader(false);
            student3.setLeader(false);
            student4.setLeader(false);
            student5.setLeader(false);

            student1.setTeam(teams.get(i));
            student2.setTeam(teams.get(i));
            student3.setTeam(teams.get(i));
            student4.setTeam(teams.get(i));
            student5.setTeam(teams.get(i));

            student1.setPassword("password1"+Integer.toString(i));
            student2.setPassword("password2"+Integer.toString(i));
            student3.setPassword("password3"+Integer.toString(i));
            student4.setPassword("password4"+Integer.toString(i));
            student5.setPassword("password5"+Integer.toString(i));

            studentRepository.save(student1);
            studentRepository.save(student2);
            studentRepository.save(student3);
            studentRepository.save(student4);
            studentRepository.save(student5);
        }

        Mentor mentor1 = new Mentor();
        Mentor mentor2 = new Mentor();

        mentor1.setName("Mentor1");
        mentor2.setName("Mentor2");

        mentor1.setPassword("password1");
        mentor2.setPassword("password2");

        mentorRepository.save(mentor1);
        mentorRepository.save(mentor2);

        Attendance attendance1 = new Attendance();
        Attendance attendance2 = new Attendance();
        Attendance attendance3 = new Attendance();
        Attendance attendance4 = new Attendance();

        Grade grade1 = new Grade();
        Grade grade2 = new Grade();
        Grade grade3 = new Grade();
        Grade grade4 = new Grade();

        ArrayList<Student> students = studentRepository.findAll();

        attendance1.setStudent(students.get(1));
        attendance2.setStudent(students.get(2));
        attendance3.setStudent(students.get(3));
        attendance4.setStudent((students.get(4)));

        ArrayList <Task> tasks = taskRepository.findAll();

        attendance1.setTask(tasks.get(1));
        attendance2.setTask(tasks.get(2));
        attendance3.setTask(tasks.get(3));

        attendance1.setPresent(true);
        attendance2.setPresent(true);
        attendance3.setPresent(false);

        attendanceRepository.save(attendance1);
        attendanceRepository.save(attendance2);
        attendanceRepository.save(attendance3);

        grade1.setStudent(students.get(1));
        grade2.setStudent(students.get(2));
        grade3.setStudent(students.get(3));
        grade4.setStudent(students.get(4));


        grade1.setTask(tasks.get(1));
        grade2.setTask(tasks.get(2));
        grade3.setTask(tasks.get(3));
        grade4.setTask(tasks.get(1));

        grade1.setValue(5L);
        grade2.setValue(4L);
        grade3.setValue(3L);
        grade4.setValue(2L);

        grade1.setComment("Comment1");
        grade2.setComment("Comment2");

        grade1.setMentor(mentor1);
        grade2.setMentor(mentor1);
        grade3.setMentor(mentor2);
        grade4.setMentor(mentor2);

        gradeRepository.save(grade1);
        gradeRepository.save(grade2);
        gradeRepository.save(grade3);
        gradeRepository.save(grade4);


    }

    public void addGrades() {
        ArrayList<Student> students = studentRepository.findAll();
        ArrayList<Task> tasks = taskRepository.findAll();
        ArrayList<Mentor> mentors = mentorRepository.findAll();

        for (int i = 0; i < 10; i++) {
            Grade grade = new Grade();
            grade.setStudent(students.get(i));
            grade.setTask(tasks.get(i%6));
            grade.setMentor(mentors.get(i%2));
            grade.setValue((long) i);
            grade.setComment("Comment" + i);
            gradeRepository.save(grade);
        }
    }
}
