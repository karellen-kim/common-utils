package io.github.karellen.utils.collections;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import io.github.karellen.utils.tuples.*;

/**
 * @author karellen
 */
public class CollectionLeftJoinOn<A, B> {
    Collection<A> collection1;
    Collection<B> collection2;

    public CollectionLeftJoinOn(final Collection<A> collection1, final Collection<B> collection2) {
        this.collection1 = collection1;
        this.collection2 = collection2;
    }

    public CollectionJoin<Tuple2<A, Optional<B>>> on(final BiFunction<A, B, Boolean> compare) {
        List<Tuple2<A, Optional<B>>> collect = CollectionJoinHelper
                .apply(collection1, collection2, compare)
                .collect(Collectors.toList());

        return new CollectionJoin<Tuple2<A, Optional<B>>>(collect);
    }
}
