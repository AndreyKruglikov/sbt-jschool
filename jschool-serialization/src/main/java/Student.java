import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    transient private String password;
    private Date creationDate;
    private Course course;

    public enum Course {
        FIRST,
        SECOND
    }

    public Student(String name, int age, String password, Date creationDate, Course course) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.creationDate = creationDate;
        this.course = course;
    }

    public Student(){
        this.name = "Default name";
        this.password = "Default password";
        this.age = 0;
        this.creationDate = Calendar.getInstance().getTime();
        this.course = Course.FIRST;
    }

    private Object writeReplace() {
        return new StudentProxy(this);
    }

    private static class StudentProxy implements Serializable {
        private static final long serialVersionUID = -1L;
        private String name;
        private int age;
        transient private String password;
        private Date creationDate;
        private Course course;
        StudentProxy(Student student) {
            this.name = student.name;
            this.age = student.age;
            this.password = student.password;
            this.creationDate = student.creationDate;
            this.course = student.course;
        }
        private Object readResolve() {
            return new Student(name, age, password, creationDate, course);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                ", course=" + course +
                '}';
    }
}
