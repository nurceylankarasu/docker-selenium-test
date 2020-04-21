import org.testng.annotations.Test;

public class DockerTest extends BaseTest{

    @Test
    public void dockerTest() {
        driver.get("https://www.google.com/");
        System.out.println("Hello docker!");
    }
}