import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person("Bob", 20),
                                             new Person("Julia", 18),
                                             new Person("Fred", 25));
        persons.stream().forEach(System.out::println);

        Map<String, Person> mapPersons = Streams.of(persons)
                                                .filter(p -> p.getAge() > 20)
                                                .transform( p -> new Person(p.getName(), p.getAge() + 30))
                                                .toMap(p -> p.getName(), p -> p);
        System.out.print("Map of persons: ");
        System.out.println(mapPersons);

        persons.stream().forEach(System.out::println);
    }
}
