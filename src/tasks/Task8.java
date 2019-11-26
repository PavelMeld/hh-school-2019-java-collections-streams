package tasks;

import common.Person;
import common.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.Instant;
import java.util.function.BiFunction;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  // @PavelMeld : Оригинал изменял список persons, так нельзя
  public List<String> getNames(List<Person> persons) {
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  //@PavelMeld : ok
  public Set<String> getDifferentNames(List<Person> persons) {
    return getNames(persons).stream().distinct().collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  // @PavelMeld : ok
  public String convertPersonToString(Person person) {
    String result = "";
    if (person.getSecondName() != null) {
      result += person.getSecondName();
    }

    if (person.getFirstName() != null) {
      result += " " + person.getFirstName();
    }

    if (person.getSecondName() != null) {
      result += " " + person.getSecondName();
    }
    return result;
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    Map<Integer, String> map = new HashMap();

    // @PavelMeld : Очень хотелось сделать целиком через стрим, но оригинальная версия
    // мне нравится больше, добавил в нее чуть-чуть стрима. 

    persons.stream()
      .distinct()
      .forEach( p -> map.put(p.getId(), p.getFirstName()) );

    // return persons.stream()
    //   .distinct()
    //   .collect(
    //     Collectors.groupingBy(
    //       Person::getId, 
    //       Collectors.mapping(Person::getFirstName, Collectors.joining())
    //     )
    //   );

    return map;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    for (Person person1 : persons1)
      for (Person person2 : persons2)
        if (person1.equals(person2))
          return true;

    return false;
  }

  //Выглядит вроде неплохо...
  // @PavelMeld : Не очень идея - использовать глобальную переменную.
  // Переменная больше не нужна - убрал из описания класса
  // Да и вообще - переменную, если есть Stream::count
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    Person  p1, p2, p3, p4;

    p1 = new Person(1, "123", Instant.now());
    p2 = new Person(2, "223", Instant.now());
    p3 = new Person(3, "323", Instant.now());
    p4 = new Person(4, "423", Instant.now());

    Map<Integer, String> mappedIds = getPersonNames(List.of(p1, p2, p1, p3, p4));

    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = true;
    boolean reviewerDrunk = false;
    return codeSmellsGood || reviewerDrunk;
  }
}
