# JDBC vs JPA (Spring Boot)
---
## 📦 Tech Stack
* Spring Boot 3.x
* Spring JDBC
* (Optional) Spring Data JPA
* H2 / MySQL / PostgreSQL
---

## 📌 INSERT (JDBC)

### Take the generate id

```java
KeyHolder keyHolder = new GeneratedKeyHolder();

jdbcTemplate.update(con -> {
    PreparedStatement ps = con.prepareStatement(
        INSERT_QUERY, Statement.RETURN_GENERATED_KEYS
    );
    ps.setString(1, dto.getName());
    ps.setString(2, dto.getDescription());
    ps.setBigDecimal(3, dto.getPrice());
    return ps;
}, keyHolder);

return new MenuResponseDTO(
    keyHolder.getKey().longValue(),
    dto.getName(),
    dto.getDescription(),
    dto.getPrice()
);
```
---

### Shorten Ver (NamedParameter + BeanProperty)

```java
KeyHolder keyHolder = new GeneratedKeyHolder();

namedJdbcTemplate.update(
    """
    INSERT INTO menu(name, description, price)
    VALUES (:name, :description, :price)
    """,
    new BeanPropertySqlParameterSource(dto),
    keyHolder
);
```

### ✨ JPA Equivalent

**Entity**

```java
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
}
```

**Repository**

```java
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
```

**Service**

```java
Menu saved = menuRepository.save(menu);
```

**Notes (JPA):**

* Short code
* ID generated automatically
* SQL is hidden (less control)

---

## 📌 SELECT

### JDBC – Find by ID

```java
jdbcTemplate.queryForObject(
    "SELECT * FROM menu WHERE id=?",
    new BeanPropertyRowMapper<>(MenuResponseDTO.class),
    id
);
```

### JPA – Find by ID

```java
menuRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Menu not found"));
```

---

## 📌 UPDATE

### JDBC

```java
int rows = jdbcTemplate.update(
    "UPDATE menu SET name=?, description=?, price=? WHERE id=?",
    dto.getName(), dto.getDescription(), dto.getPrice(), id
);

if (rows == 0) {
    throw new RuntimeException("Menu not found");
}
```

### JPA

```java
Menu menu = menuRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Menu not found"));

menu.setName(dto.getName());
menu.setDescription(dto.getDescription());
menu.setPrice(dto.getPrice());

menuRepository.save(menu);
```

---

## 📌 DELETE

### JDBC

```java
int rows = jdbcTemplate.update(
    "DELETE FROM menu WHERE id=?", id
);

if (rows == 0) {
    throw new RuntimeException("Menu not found");
}
```

### JPA

```java
menuRepository.deleteById(id);
```

---

## 🔗 MANY-TO-MANY

### JDBC Approach

**Strategy**

* Query main table (menu)
* Query junction table (`menu_categories`)
* Manual mapping in service layer

```text
Menu
 ├─ id
 ├─ name
 └─ categories (manual mapping)
```

**Notes:**

* Explicit SQL
* Best performance
* More code

---

### JPA Approach

```java
@ManyToMany
@JoinTable(
    name = "menu_categories",
    joinColumns = @JoinColumn(name = "menu_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
)
private Set<Category> categories;
```

**Notes:**

* Very little code
* Auto join handling
* Can cause N+1 queries 

---

## 🧠 BeanProperty vs Manual Mapping (JDBC)

| Aspect          | BeanProperty | Manual / Map |
| --------------- | ------------ | ------------ |
| Code length     | Short        | Long         |
| Control         | Low          | High         |
| Complex joins   | ❌            | ✅            |
| Many-to-many    | ❌            | ✅            |
| Column aliasing | ❌            | ✅            |

**Rule of Thumb:**

> Simple CRUD → BeanProperty
> Complex SQL / joins → Manual mapping

---

## ⚖️ JDBC vs JPA Summary

### JDBC

✅ Full SQL control
✅ Best performance
❌ Verbose
❌ Manual relationship handling

### JPA

✅ Very concise
✅ Automatic relationships
❌ Hidden SQL
❌ Possible performance pitfalls

---

## 📌 When to Use What

* **Simple CRUD, fast development** → JPA
* **High performance / complex queries** → JDBC
* **Large many-to-many tables** → JDBC
* **Prototype / admin system** → JPA
* **Public API with heavy traffic** → JDBC

---

