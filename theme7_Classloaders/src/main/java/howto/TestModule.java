package howto;

public class TestModule {
    private static int counter = 0; //WTF Почему при динамической загрузке счетчик не сбрасывается? Ведь

    public String toString() {
        return "howto.TestModule, version 1! " + (counter++);
    }
}