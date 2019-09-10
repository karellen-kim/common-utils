## Collection
### Join
```java
Collection<Tuple2<Item, Category>> results = new CollectionJoin<Item>(items)
    .join(categories)
    .on((item, category) -> item.categoryId.equals(category.id))
    .apply();
```

### LeftJoin
```java
Collection<Tuple2<Item, Optional<Category>>> results = new CollectionJoin<Item>(items)
    .leftJoin(categories)
    .on((item, category) -> item.categoryId.equals(category.id))
    .apply();
```
