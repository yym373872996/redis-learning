package person.rulo.redis.learning.lettuce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import person.rulo.redis.learning.common.domain.User;

import java.io.Serializable;


@SpringBootTest
class LettuceApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Test
    void contextLoads() {

    }

    @Test
    public void testStr() {
        stringRedisTemplate.opsForValue().set("chengji", "26");
        Assertions.assertEquals("26", stringRedisTemplate.opsForValue().get("chengji"));
    }

    @Test
    public void testSerializable() {
        User user = new User("superman", 20);
        serializableRedisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("batman", 30);
        serializableRedisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("spiderman", 40);
        serializableRedisTemplate.opsForValue().set(user.getUsername(), user);


        user = (User)serializableRedisTemplate.opsForValue().get("superman");
        Assertions.assertEquals(20, user.getAge());
        user = (User)serializableRedisTemplate.opsForValue().get("batman");
        Assertions.assertEquals(30, user.getAge());
        user = (User)serializableRedisTemplate.opsForValue().get("spiderman");
        Assertions.assertEquals(40, user.getAge());
    }

}
