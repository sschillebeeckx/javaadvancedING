package be.abis.exercise.test;

import be.abis.exercise.exception.PersonShouldBeAdultException;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestMain {

    public static void main(String[] args) {
        try {
            PersonRepository pr = FilePersonRepository.getInstance();
            List<Person> persons = pr.getAllPersons();

            System.out.println("All persons in file");
            persons.forEach(System.out::println);

            System.out.println("\n--------------------- Exercise A -------------------");
            persons.stream()
                    .filter(p -> p.getLastName().startsWith("S"))
                    .sorted(Comparator.comparing(Person::getFirstName))
                    .forEach(System.out::println);

            System.out.println("\n--------------------- Exercise B -------------------");
            persons.stream()
                    .filter(p -> p.getCompany() != null)
                    .map(p -> p.getCompany().getName())
                    .distinct()
                    .forEach(System.out::println);
              // based on equals in Company
            persons.stream()
                    .filter(p -> p.getCompany() != null)
                    .map(p -> p.getCompany())
                    .distinct()
                    .forEach(System.out::println);

            System.out.println("\n--------------------- Exercise C -------------------");
            long count = persons.stream()
                    .filter(p -> p.getCompany() != null)
                    .filter(p -> p.getCompany().getAddress().getTown().equalsIgnoreCase("Leuven"))
                    .count();
            System.out.println("There are " + count + " persons working in Leuven");

            System.out.println("\n--------------------- Exercise D -------------------");
            Person youngest = persons.stream()
                    .min((p1, p2) -> {
                        try {
                            return (int) (p1.calculateAge() - p2.calculateAge());
                        } catch (PersonShouldBeAdultException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    // .min(Comparator.comparing(Person::calculateAge))
                    .orElse(null);
            ;
            System.out.println(youngest);

            System.out.println("\n--------------------- Exercise E -------------------");
            Map<String, List<Person>> personsPerCompany = persons.stream()
                    .filter(p -> p.getCompany() != null)
                    .collect(Collectors.groupingBy(p -> p.getCompany().getName()));
            System.out.println(personsPerCompany + "\n");
            for (String cName : personsPerCompany.keySet()) {
                System.out.println(cName + ": ");
                for (Person p : personsPerCompany.get(cName)) {
                    System.out.println(p);
                }
                System.out.println("");
            }

            System.out.println("\n--------------------- Exercise F -------------------");
            Map<String, Long> numberOfPersonsPerCompany =
                    persons.stream()
                            .filter(p -> p.getCompany() != null)
                            .collect(Collectors.groupingBy(p -> p.getCompany().getName(), Collectors.counting()));
            for (String cName : numberOfPersonsPerCompany.keySet()) {
                System.out.println(cName + ": " + numberOfPersonsPerCompany.get(cName));
            }

            System.out.println("\n--------------------- Exercise G -------------------");
            Double averageAge= persons.stream()
                    .mapToLong(p-> {
                        try {
                            return p.calculateAge();
                        } catch (PersonShouldBeAdultException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .average()
                    .orElse(0);
            System.out.println("The average age is " + averageAge);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
