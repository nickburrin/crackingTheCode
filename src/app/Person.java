package app;

class Person {
    int birth;
    int death;

    public Person(int b, int d) {
        this.birth = b;
        this.death = d;
    }

    public static Person create(int minYear, int maxYear) {
        int diff = maxYear - minYear;
        int birth = (int) (minYear + (Math.random() * diff)/2);
        int death = (int) (birth + (Math.random() * diff)/2);
        return new Person(birth, death);
    }
}