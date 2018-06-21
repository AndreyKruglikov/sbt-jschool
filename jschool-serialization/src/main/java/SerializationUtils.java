import java.io.*;

public class SerializationUtils {

    public static void serialzeObject(Object obj, String fileName){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))) {
            oos.writeObject(obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerialzeObject(String fileName) throws ClassNotFoundException{
        Object obj = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))) {
            obj = ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
