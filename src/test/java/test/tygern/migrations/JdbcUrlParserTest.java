package test.tygern.migrations;

import com.tygern.migrations.JdbcUrlParser;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


public class JdbcUrlParserTest {

    @Test
    public void test() {
        //language=JSON
        String vcap = "{\"p-chicken-mysql\": [{\"credentials\": {\"jdbcUrl\": \"jdbc:mysql://127.0.0.1:3306/movies?user=root\"}, \"name\": \"movies-mysql\"}]}";

        Optional<String> url = JdbcUrlParser.parseUrl("p-chicken-mysql", vcap);

        assertEquals(Optional.of("jdbc:mysql://127.0.0.1:3306/movies?user=root"), url);
    }

    @Test
    public void testFailure() {
        Optional<String> url = JdbcUrlParser.parseUrl("p-chicken-mysql", "{}");

        assertEquals(Optional.empty(), url);
    }

    @Test
    public void testBadJson() {
        Optional<String> url = JdbcUrlParser.parseUrl("p-chicken-mysql", "bad json");

        assertEquals(Optional.empty(), url);
    }
}
