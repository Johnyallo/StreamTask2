package StreamApi2;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person
                    (names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
                    ));
        }
        Stream<Person> stream = persons.stream();
        long b = stream.filter(age -> age.getAge() < 18)
                .count();
        System.out.println(b);

        Stream<Person> streamForArmy = persons.stream();
        List<String> listForArmy = streamForArmy.filter(age -> age.getAge() >= 18)
                .filter(age -> age.getAge() <= 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println(listForArmy);

        Stream<Person> streamForWorkers = persons.stream();
        List<Person> listForWorkers = streamForWorkers
                .filter(man -> man.getAge() >= 18 && man.getAge() < 60 && man.getSex() == Sex.WOMAN && man.getEducation() == Education.HIGHER ||
                //.filter(man ->
                        man.getAge() >= 18 && man.getAge() < 65 && man.getSex() == Sex.MAN && man.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person :: getFamily))
                .collect(Collectors.toList());
        System.out.println(listForWorkers);


    }
}
