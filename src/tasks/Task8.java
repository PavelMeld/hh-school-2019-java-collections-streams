package tasks;

import common.Person;
import common.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
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
  //@PavelMeld : убрал стрим
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  //@PavelMeld : Stream-style join
  public String convertPersonToString(Person person) {

    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
      .filter(text -> text != null)
      .collect(Collectors.joining(" "));

  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {

    return persons.stream()
            .collect(
				Collectors.toMap(Person::getId, Person::getFirstName, (a,b) -> a)
			);

  }

  // есть ли совпадающие в двух коллекциях персоны?
  // @PavelMeld: Заменил Collection::contains на HashSet::contains
  // c постоянным временем доступа
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
	
    return persons1.stream()
            .anyMatch(new HashSet<>(persons2)::contains);
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
    Person  p1, p2, p3, p4, p5;

    p1 = new Person(1, "123", "bgf", Instant.now());
    p2 = new Person(2, "223", Instant.now());
    p3 = new Person(3, "323", Instant.now());
    p4 = new Person(4, "423", Instant.now());
    p5 = new Person(4, "423", Instant.now());

    System.out.println("<"+convertPersonToString(p2)+">");

    getPersonNames(List.of(p1, p2, p1, p3)).forEach(
      (k, v) -> System.out.println(k+" "+v)
    );

    System.out.println(hasSamePersons(List.of(p1,p2), List.of(p3,p1)));
    System.out.println(hasSamePersons(List.of(p1,p2), List.of(p3,p4)));
	System.out.println("Count even " + countEven(Stream.of(0)));

    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = true;
    boolean reviewerDrunk = false;
    return codeSmellsGood || reviewerDrunk;
  }
}
