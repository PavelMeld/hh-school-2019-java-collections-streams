package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Arrays;
import java.util.stream.Collectors;


/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис (он выдает несортированный Set<Person>)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы

Ассимптотика - O(2C * n), где C - время работы методов HashMap get и put
 */
public class Task1 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = PersonService.findPersons(personIds); 
    Map<Integer,Person> idToPersonMap;

    idToPersonMap = persons.stream()
      .collect( Collectors.toMap( Person::getId, p -> p ));

    return personIds.stream()
      .map( idToPersonMap::get )
      .collect(Collectors.toList());
  }

  @Override
  public boolean check() {
    List<Integer> ids = List.of(43, 21, 34);

    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

}

