package io.github.karellen.utils.collections;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import io.github.karellen.utils.tuples.*;

/**
 * @author karellen
 */
public class CollectionJoinOn<A, B> {
    Collection<A> collection1;
    Collection<B> collection2;

    public CollectionJoinOn(final Collection<A> collection1, final Collection<B> collection2) {
        this.collection1 = collection1;
        this.collection2 = collection2;
    }

    public CollectionJoin<Tuple2<A, B>> on(final BiFunction<A, B, Boolean> compare) {
        List<Tuple2<A, B>> collect = CollectionJoinHelper
                .apply(collection1, collection2, compare)
                .filter(pair -> pair._2.isPresent())
                .map(pair -> new Tuple2<A, B>(pair._1, pair._2.get()))
                .collect(Collectors.toList());

        return new CollectionJoin<Tuple2<A, B>>(collect);
    }
}
