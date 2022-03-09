package config.easteregg;

public class TestChild extends TestParent {
    private int num = 10;

    public TestChild() {
        System.out.println("this = " + this);
        System.out.println("this = " + this.num);


        System.out.println("super = " + super.toString());
        System.out.println("super = " + super.num);
    }

    @Override
    public String toString() {
        return "TestChild{" +
                "num=" + num +
                '}';
    }
}
