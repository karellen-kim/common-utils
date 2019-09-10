package io.github.karellen.utils.collections;

import io.github.karellen.utils.collections.models.*;
import io.github.karellen.utils.tuples.Tuple2;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author karellen
 */
public class CollectionJoinTest {

    @Test
    public void testJoinWhenHasSameCollectionSize() {
        List<Item> items = Arrays.asList(
                new Item(1, "item1", 100), /* category id = 100 */
                new Item(2, "item2", 200), /* category id = 200 */
                new Item(3, "item3", 300));  /* category id = 300 */

        List<Category> categories = Arrays.asList(
                new Category(100, "category1"),
                new Category(200, "category2"),
                new Category(300, "category3"));

        Collection<Tuple2<Item, Category>> results = new CollectionJoin<Item>(items)
                .join(categories)
                .on((item, category) -> item.categoryId.equals(category.id))
                .apply();

        // 크기는 첫번째 collection 사이즈와 같다
        assertThat(results.size(), is(items.size()));
        results.forEach(mapping -> {
            Item item = mapping._1;
            Category category = mapping._2;

            // join 조건에 맞게 두 객체가 매핑되어 있다.
            assertThat(item.categoryId, is(category.id));
        });
    }

    @Test
    public void testJoinWhenHasDifferentCollectionSize() {
        List<Item> items = Arrays.asList(
                new Item(1, "item1", 100), /* category id = 100 */
                new Item(2, "item2", 200));  /* category id = 200 */


        List<Category> categories = Arrays.asList(
                new Category(100, "category1"),
                new Category(200, "category2"),
                new Category(500, "category5"));

        Collection<Tuple2<Item, Category>> results = new CollectionJoin<Item>(items)
                .join(categories)
                .on((item, category) -> item.categoryId.equals(category.id))
                .apply();

        // 크기는 두 collection 공통된 매핑 개수와 같다
        assertThat(results.size(), is(2));
        results.forEach(mapping -> {
            Item item = mapping._1;
            Category category = mapping._2;

            // join 조건에 맞게 두 객체가 매핑되어 있다.
            assertThat(item.categoryId, is(category.id));
        });
    }

    @Test
    public void testLeftJoin() {
        List<Item> items = Arrays.asList(
                new Item(1, "item1", 100), /* category id = 100 */
                new Item(2, "item2", 200), /* category id = 200 */
                new Item(3, "item3", 300)); /* category id = 300 */

        List<Category> categories = Arrays.asList(
                new Category(100, "category1"),
                new Category(300, "category3")
        );

        Collection<Tuple2<Item, Optional<Category>>> results = new CollectionJoin<Item>(items)
                .leftJoin(categories)
                .on((item, category) -> item.categoryId.equals(category.id))
                .apply();

        // 크기는 첫번째 colleciton의 사이즈와 같다
        assertThat(results.size(), is(items.size()));
        results.forEach(mapping -> {
            Item item = mapping._1;
            Optional<Category> maybeCategory = mapping._2;

            // category 정보는 같거나 없을 수도 있다.
            if (maybeCategory.isPresent()) {
                assertThat(item.categoryId, is(maybeCategory.get().id));
            }
        });
    }
}



