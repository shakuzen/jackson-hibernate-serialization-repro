Serialization issue reproduction
========

The JUnit test contained in `JacksonHibernateProxyReproApplicationTests` reproduces the issue. This may be the same issue described in FasterXML/jackson-datatype-hibernate#77 or [this mentioned StackOverflow question](http://stackoverflow.com/questions/42395831/issue-serializing-lazy-manytoone).

When using Hibernate with Spring Data REST, Hibernate proxies of entities will not be serialized properly.

The `PersistentEntityJackson2Module` will wrap entities in a `org.springframework.hateoas.Resource` class.
When the `Resource`'s `content` field contains a Hibernate proxy, it is serialized even though it should be
unwrapped as it is marked with `@JsonUnwrapped`.

Expected serialization:
```json
{
  "items": [
    {
      "itemDetails": [
        {
          "extras": [
            {
              "type": "something"
            }
          ],
          "status": "reserved"
        }
      ],
      "something": "something"
    }
  ],
  "something": "something",
  "_links": {
    "self": {
      "href": "http://localhost:8080/reservations/9d0bab3c-8801-43b2-a15e-d7d6bfc08116"
    },
    "reservation": {
      "href": "http://localhost:8080/reservations/9d0bab3c-8801-43b2-a15e-d7d6bfc08116"
    }
  }
}
```

Actual serialization (notice the `content` field):
```json
{
  "items": [
    {
      "content": {
        "itemDetails": [
          {
            "extras": [
              {
                "type": "something"
              }
            ],
            "status": "reserved"
          }
        ],
        "something": "something"
      }
    }
  ],
  "something": "something",
  "_links": {
    "self": {
      "href": "http://localhost:8080/reservations/9d0bab3c-8801-43b2-a15e-d7d6bfc08116"
    },
    "reservation": {
      "href": "http://localhost:8080/reservations/9d0bab3c-8801-43b2-a15e-d7d6bfc08116"
    }
  }
}
```
