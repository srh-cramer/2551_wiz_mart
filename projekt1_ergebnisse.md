# Projekt 1 Ergebnisse
## Enums
### Wichtigste Punkte
- Ein enum beschreibt eine feste Menge unveränderlicher Werte (= Konstanten)
- Die Werte sind typsicher: Statt beliebiger Strings wie "Potion" oder "potion" gibt es nur definierte Werte wie `Category.POTION`
- Die Konstanten in einen Enum werden per Konvention GROSS geschrieben
- Enums eignen sich besonders für Zustände, Kategorien, Optionen oder andere klar begrenzte Auswahlmöglichkeiten
- Ein Enum kann zusätzlich Attribute, Konstruktoren und Methoden besitzen
- Enum-Werte kann man mit `==` vergleichen

### Minimalbeispiel
#### Definition
```java
public enum Category {
    POTION,
    CLOAK,
    WAND,
    SPELLBOOK
}
```
#### Verwendung
```java
Category category = Category.POTION;

if (category == Category.POTION) {
    System.out.println("Magischer Trank");
}
```

## Generics
### Wichtigste Punkte
- Generics ermöglichen es, Klassen und Methoden unabhängig vom konkreten Datentyp zu schreiben.
- Bei der Definition des Generics wird statt des konkreten Datentyps ein Platzhalter (häufig `T`) verwendet
- Der konkrete Typ wird bei der Verwendung festgelegt, z.B. `Repository<Product>` oder `Repository<String>`.
- Dadurch kann dieselbe Klasse für verschiedene Datentypen verwendet werden.
- Generics sorgen für Typsicherheit zur Compile-Zeit.
- Besonders häufig findet man Generics bei Collections wie `List<T>`, `Map<K,V>` oder bei eigenen Container-/Repository-Klassen.

### Minimalbeispiel
#### Definition
```java
public class Box<T> {

    private T content;

    public void set(T content) {
        this.content = content;
    }

    public T get() {
        return content;
    }
}
```
#### Verwendung
```java
Box<String> box = new Box<>();
box.set("Zaubertrank");

String text = box.get();
```

## Lambda-Ausdrücke
### Wichtigste Punkte
* Ein Lambda-Ausdruck ist eine Funktion, der wir keinen Namen geben
* Die allgemeine Schreibweise ist: `Parameter -> Anweisung/Ergebnis`
* Lambdas werden häufig für den Aufruf funktionaler Interfaces verwendet 
(= Interface mit genau einer abstrakten Methode)
* Verwendung häufig im Zusammenhang mit Sortieren, Filtern, Iterieren und Callbacks/Event-Handling
* Ein Lambda-Ausdruck beschreibt meistens, *was* passieren soll, während der aufrufende Code bestimmt *wann* es passiert

### Minimalbeispiel
Beim Sortieren:
```java
List<String> names = new ArrayList<>();
names.sort((a,b) -> a.compareTo(b));
```
Als Callback:
```java
Runnable action = () -> System.out.println("Hallo");
action.run();
```