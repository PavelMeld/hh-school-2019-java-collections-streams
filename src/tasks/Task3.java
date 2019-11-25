package tasks;

import common.Person;
import common.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, затем по имени, а потом по дате создания
 */
public class Task3 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<Person> sort(Collection<Person> persons) {
    List<Person> sorted;

    sorted =  persons.stream()
      .sorted(
        (a, b) -> {
          int vs;

          vs = a.getSecondName().compareTo(b.getSecondName());
          if (vs != 0) 
            return vs;

          vs = a.getFirstName().compareTo(b.getFirstName());
          if (vs != 0) 
            return vs;

          return new Long(a.getCreatedAt().toEpochMilli()).compareTo(b.getCreatedAt().toEpochMilli());
        }
      )
      .collect(Collectors.toList());

     return sorted;
  }

  @Override
  public boolean check() {
    Instant time = Instant.now();
    List<Person> persons = List.of(
        new Person(1, "Oleg", "Ivanov", time),
        new Person(2, "Vasya", "Petrov", time),
        new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
        new Person(4, "Oleg", "Ivanov", time.plusSeconds(1))
    );
    List<Person> sortedPersons = List.of(
        new Person(1, "Oleg", "Ivanov", time),
        new Person(4, "Oleg", "Ivanov", time.plusSeconds(1)),
        new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
        new Person(2, "Vasya", "Petrov", time)
    );
    return sortedPersons.equals(sort(persons));
  }
}
