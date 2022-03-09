package config.easteregg;

public class TestParent {
    @Override
    public String toString() {
        return "TestParent{" +
                "num=" + num +
                '}';
    }

    public int num = 11;

    public TestParent() {
        System.out.println("this = " + this.toString());
        System.out.println("this = " + this.num);
        System.out.println("super = " + super.toString());

    }
}
