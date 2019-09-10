package io.github.karellen.utils.collections;

import java.util.Collection;

/**
 * @author karellen
 */
public class CollectionJoin<A> {

    Collection<A> collection;

    public CollectionJoin(final Collection<A> collection1) {
        this.collection = collection1;
    }

    public <B> CollectionJoinOn<A, B> join(final Collection<B> collection2) {
        return new CollectionJoinOn<A, B>(collection, collection2);
    }

    public <B> CollectionLeftJoinOn<A, B> leftJoin(final Collection<B> collection2) {
        return new CollectionLeftJoinOn<A, B>(collection, collection2);
    }

    public Collection<A> apply() {
        return collection;
    }
}

