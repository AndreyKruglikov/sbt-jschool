import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Streams<T> {

    private Collection<? extends T> collection;

    private Streams(Collection<? extends T> collection) {
        this.collection = collection;
    }

    public static <T> Streams<T> of(Collection<? extends T> collection) {
        return new Streams<>(collection);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        this.collection = collection.stream().filter(predicate).collect(Collectors.toList());
        return this;
    }

    public Streams<T> transform(Function<? super T, ? extends T> transformer) {
        this.collection = collection.stream().map(transformer).collect(Collectors.toList());
        return this;
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyMapper,
                                  Function<? super T, ? extends V> valueMapper) {
        return collection.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }
}
