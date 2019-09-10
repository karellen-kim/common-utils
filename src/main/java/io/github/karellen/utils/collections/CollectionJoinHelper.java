package io.github.karellen.utils.collections;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import io.github.karellen.utils.tuples.*;

/**
 * @author karellen
 */
public class CollectionJoinHelper {

    // TODO : do parallel
    public static <A, B> Stream<Tuple2<A, Optional<B>>> apply(final Collection<A> collection1, final Collection<B> collection2, final BiFunction<A, B, Boolean> compare) {
        return collection1.stream()
                .map(a -> {
                    Optional<B> maybeB = collection2.parallelStream().filter(b -> compare.apply(a, b)).findFirst();
                    return new Tuple2(a, maybeB);
                });
    }
}
