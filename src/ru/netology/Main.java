package ru.netology;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }

        long count = persons.stream().filter(age -> age.getAge() < 18).count();
        System.out.println("Несовершеннолетних : " + count);

        System.out.println();
        System.out.println("Список призывников :");
        List<String> filteredPersons = persons.stream()
                .filter(s -> (s.getAge() > 18) && (s.getAge() < 27) && (s.getSex() == Sex.MAN))
                .map(Person::getFamily)
                .limit(10)
                .collect(Collectors.toList());
        filteredPersons.forEach(System.out::println);

        System.out.println();
        List<Person> filteredPersons1 =
                persons.stream()
                .filter(s -> (s.getEducation() == Education.HIGHER))
                .filter(s -> ((s.getAge() > 18) && (s.getAge() < 65) && (s.getSex() == Sex.MAN)) ||
                        (s.getAge() > 18) && (s.getAge() < 60) && (s.getSex() == Sex.WOMAN))
                .sorted(Comparator.comparing(Person::getFamily))
                 .limit(10)
                .collect(Collectors.toList());
        System.out.println("Список работоспособных людей с высшим образованием :");
        filteredPersons1.forEach(System.out::println);

        }
}


