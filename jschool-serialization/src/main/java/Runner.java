import java.io.File;

public class Runner {
    public static void main(String[] args) throws ClassNotFoundException {
        Student student = new Student();
        String fileName = "student.ser";
        SerializationUtils.serialzeObject(student, fileName);
        student = (Student) SerializationUtils.deSerialzeObject(fileName);
        File file = new File(fileName);
        file.delete();
        System.out.println(student);
    }
}
