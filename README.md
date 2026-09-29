# Wiz-Mart

Willkommen bei **Wiz-Mart**, dem Shop für Zauberer.

In diesem Projekt entwickeln Sie eine bestehende JavaFX-Anwendung für einen kleinen Shop weiter. 
Der Shop funktioniert bereits, enthält aber noch einige Lücken und Stellen, die verbessert werden können.

Sie werden in diesem Projekt folgende Konzepte kennenlernen:

* Enums
* Generics
* Lambda Expressions
* Design Pattern: Observer

## Projektstruktur

```text
src/
└── main/
    ├── java/
    │   └── com/
    |       └── cramer/
    │           └── _2551_wiz_mart/
    │               ├── Application.java
    │               ├── Launcher.java
    │               │
    │               ├── controller/
    │               │   └── ShopController.java
    │               │
    │               ├── model/
    │               │   ├── Product.java
    │               │   ├── Potion.java
    │               │   ├── Cloak.java
    │               │   ├── Wand.java
    │               │   ├── Spellbook.java
    │               │   ├── CartItem.java
    │               │   └── ShoppingCart.java
    │               │
    │               ├── service/
    │               │   └── ShopService.java
    │               │
    │               └── data/
    │                   └── ProductData.java
    │
    └── resources/
        └── com/
            └── cramer/
                └── _2551_wiz_mart/
                    └── shop-view.fxml
```

## Ausgangssituation

Der Shop kann bereits:

* Produkte anzeigen
* Produkte in den Warenkorb legen
* Produkte aus dem Warenkorb entfernen
* den Warenkorb leeren
* die Anzahl der Artikel anzeigen
* den Gesamtpreis berechnen

Einige Funktionen sind jedoch noch nicht implementiert oder sind z.Zt. schlecht umgesetzt.
Ihre Aufgabe ist es, die Anwendung Schritt für Schritt weiterzuentwickeln.

---

## Aufgabe 1 – Dynamische Produktsuche

Aktuell muss die Suche manuell ausgelöst werden, indem man nach einer Eingabe `Enter` drückt.

Die Suche soll stattdessen automatisch reagieren, sobald sich der Inhalt des Suchfeldes verändert.

**Machen Sie sich dazu zunächst mit der Klasse `ShopController` vertraut und vollziehen Sie nach, 
welche Aufgaben die einzelnen Methoden haben.**

### Anforderungen

* Wenn der Benutzer Text eingibt, soll sich die Produktliste automatisch aktualisieren.
* Der Benutzer soll nicht mehr Enter drücken müssen.
* Die bestehende Methode `displayProducts()` soll weiterhin für die Anzeige und Filterung verwendet werden.

### Tipps
Die Tipps geben Ihnen in zunehmend explizite Hilfestellung. 
Lesen Sie sich schrittweise immer nur einen Tipp durch.

1. Schauen Sie sich `ShopController` das `TextField` `searchField` genauer an.
JavaFX stellt für viele UI-Elemente **Properties** zur Verfügung. 
Welche Property könnte für uns interessant sein?
2. Für den Text des Suchfeldes benötigen Sie einen `Listener`, der Änderungen im Suchfeld "beobachtet."
In diesem Fall soll folgende Property beobachtet werden:
`searchField.textProperty()`.
Einen Listener können wir mit der Methode `addListener()` hinzufügen.
3. Ihr Code muss in der Methode `initialize()` ergänzt werden.
Hier wird diese Aufgabe bereits für das Feld `categoryComboBox` gelöst.

---
## Aufgabe 2 – Kategorien mit Enums

Aktuell werden die Kategorien der Produkte mit dem Datentyp `String` gespeichert.
Dadurch können leicht fehlerhafte Schreibweisen entstehen, die Java als unterschiedliche Werte behandelt, 
zum Beispiel `"Potion"`, `"potion"` oder `"Potions"`.

Die Kategorien sollen deshalb durch ein **Enum** dargestellt werden.

Ein Enum legen Sie in IntelliJ genau wie eine Klasse oder Interface als eigene Datei an.
Es wird mit dem Schlüsselwort `enum` definiert.
Die einzelnen möglichen Werte werden innerhalb der geschweiften Klammern angegeben.

Ein einfaches Enum sieht beispielsweise so aus:
```java
public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}
```
Das Enum können Sie in anderen Klassen dann genau wie einen Datentyp verwenden. 
Auf einen konkreten Enum-Wert kann dann beispielsweise so zugegriffen werden:
```java
Direction dir = Direction.NORTH;
```

### Anforderungen

* Erstellen Sie ein Enum `Category`. 

* Das Enum soll die vier Produktkategorien des Shops enthalten.
* Ersetzen Sie den bisherigen `String`-Datentyp für die Kategorie durch das neue Enum.
* Passen Sie alle Stellen an, an denen bisher mit den Kategorien als `String` gearbeitet wird (außer die `categoryComboBox` im Controller)
* Die Funktionalität der Anwendung soll für den Benutzer unverändert bleiben.

### Tipps

Die Tipps geben Ihnen in zunehmend explizite Hilfestellung.
Lesen Sie sich schrittweise immer nur einen Tipp durch.

1. Ihr Enum soll die vier Produktkategorien enthalten. Diese können Sie relativ bequem im Projektbaum ablesen.
2. Sie sehen, dass das Attribut `category` bereits an vielen Stellen im Code verwendet wird. 
Hier kann Ihnen die IntelliJ-Funktion `Refactor -> Type Migration` helfen, um keine Stelle zu übersehen.

---
## Aufgabe 3 - Kategorie-Enums nutzen
In der Methode `setupCategories()` in `ShopController` werden die Kategorien z.Zt. einzeln hinzugefügt. 
Fügen wir eine Kategorie hinzu, muss der Code hier manuell angepasst werden.
Dies lässt sich durch die Nutzung von Enums nun optimieren.

### Anforderungen
* Finden Sie einen Weg, die definierten Shop-Kategorien als Einträge im Dropdown dynamisch anzulegen. Ändern Sie dabei nicht den Typ `ComboBox<String>`!
* Behalten Sie den Default-Eintrag `"All"`, bei dem nach keiner Kategorie gefiltert wird.
* Passen Sie ggf. die Filterlogik in `displayProducts()` an Ihre neue Implementierung an.

### Tipp
Lesen Sie sich den Tipp nur bei Bedarf durch:

Überprüfen Sie die Methoden, die Ihnen für das Enum `Category` zur Verfügung stehen. Die Methode `.values()` gibt Ihnen eine iterierbare Datenstruktur zurück.

---
## Aufgabe 4 - Repository ergänzen

Wiz-Mart hat aktuell zwar keine Datenbank-Anbindung, könnte aber eine haben.
Diese potenzielle Änderung soll aber möglichst wenig Auswirkungen auf unseren Code haben. 
Insbesondere `ShopService`, in der sich die Domänenlogik befindet, soll unabhängig von der Datenquelle arbeiten können.

Eine Lösung für dieses Problem ist eine **Abstraktion des Datenzugriffs** durch ein `Repository`, die den Datenzugriff kapselt. 
Die Reihenfolge der Schichten ist dann: 

Controller => Service => Repository => Datenquelle

Ob das Repository die Produkte aus einer Datenbank, einer Datei oder dem Programm selbst bereitstellt, ist dem restlichen Programm dann egal.

Auf diese Weise können auch später mal Kunden oder Bestellungen von einem Repository verwaltet werden. Immer sind die Aufgaben ungefähr gleich und ähneln CRUD:
Produkte/Kunden/Bestellungen suchen, auslesen, löschen, etc.

Das könnte man durch einzelne `ProductRepository`, `CustomerRepository`, `OrderRepository`, etc. lösen. 
Die Implementierungen wären allerdings sehr ähnlich, weil die grundlegenden Funktionalitäten unabhängig vom Datentyp sind.

Alternativ arbeitet man mit Generics:

`Repository<T>`

`T` ist hierbei ein Platzhalter für den konkreten Datentyp wie `Product`, `Customer` oder `Order`.

Entwickeln Sie deshalb eine **generische Repository-Klasse**, die unterschiedliche Arten von Objekten verwalten kann.

### Anforderungen

* Legen Sie ein Package `repository` an. Erstellen Sie darin eine Klasse `Repository<T>`.

* Das Repository soll unsere Datenbasis verwalten, die für unsere Produkte z.Zt. vom Typ `List<Product>` ist.

* Das Repository soll mindestens folgende Funktionen zur Verwaltung der Liste anbieten:

  * Ein Objekt hinzufügen: `add()`
  * Ein Objekt entfernen: `remove()`
  * Alle Objekte zurückgeben: `findAll()`

* Es soll keine Rolle spielen, von welchem Datentyp die Objekte sind.
* Passen Sie anschließend den `ShopService` so an, dass er anstatt von `List<Product>` nun `Repository<Product>` verwendet.
    * Der Aufruf von `ProductData.createProducts()` darf im Service enthalten bleiben, auch wenn die Schichtentrennung dadurch nicht hundertprozentig erfolgt.

### Tipps

Die Tipps geben Ihnen in zunehmend explizite Hilfestellung.
Lesen Sie sich schrittweise immer nur einen Tipp durch.

1. Das Repository soll eine Liste mit beliebigen Objekten verwalten können. Diese Liste sollten Sie als Attribut der Klasse verfügbar machen.
2. Der Typ der verwalteten Objekte soll nicht fest in der Klasse stehen. Ihr Attribut könnte daher z.B. folgendermaßen aussehen:
   `private List<T> items;`
3. Unten finden Sie die Methode zum Hinzufügen von Objekten zur Liste `items`. Fügen Sie diese Methode der Klasse hinzu. 
Orientieren Sie sich bei der Implementierung von Löschen und Zurückgeben an dieser Implementierung.
```java
public void add(T item) {
    items.add(item);
}
```
---
## Aufgabe 5 – Ein Enum für Sortierungen

Im Shop gibt es bereits eine Auswahlmöglichkeit für die Sortierung der Produkte.
Aktuell hat diese Auswahl jedoch noch keine Funktion.

Auch hier soll die Sortierung mit einem Enum gelöst werden, das ein paar mehr Eigenschaften hat.

Ein Enum kann genau wie eine Klasse Attribute und Konstruktoren haben.
Hier sehen Sie die Definition eines Enums `Priority`, das einen Konstruktor und ein Attribut `label` zur "hübscheren" Darstellung hat:

```java
public enum Priority {

    LOW("Niedrig"),
    MEDIUM("Mittel"),
    HIGH("Hoch");

    private final String label;

    Priority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
```

### Anforderungen

* Entsprechend dem Beispiel oben soll es ein Enum `SortOption` geben, das folgende Werte hat:
  * NAME
  * PRICE_ASCENDING
  * PRICE_DESCENDING
* Die "hübschen" Labels der Werte können Sie aus der Klasse `ShopController` übernehmen.
* Befüllen Sie das Dropdown in der Controller-Methode `setupSorting()` nun dynamisch mit den Werten aus `SortOption` - im Grunde so, wie Sie es in Aufgabe 3 in der Methode `setupCategories()` gelöst haben.

---
## Aufgabe 6 - Sortier-Enum verwenden
Das angelegte Enum soll nun im Controller verwendet werden, um die Sortierfunktionalität zu ergänzen.
Hierfür benutzen wir einen **Lambda-Ausdruck**. Ein Lambda-Ausdruck beschreibt im Grunde eine Funktion, der wir keinen Namen geben.
Ein sehr simpler Lambda-Ausdruck ist:
```java
(x) -> x * 2
```
Dieser lässt sich lesen als "Für `x` führe `x * 2` aus und gib mir das Ergebnis."

Folgender Code zeigt, wie eine Liste von Personen nach Ihrem Alter mithilfe einer Lamba-Expression sortiert werden:

```java
List<Person> people = new ArrayList<>();

people.sort((person1, person2) ->
        Integer.compare(person1.getAge(), person2.getAge())
);
```
In diesem Code wird mithilfe der Methode `sort()` die Liste `people` sortiert. Die Methode übergibt dem Lambda-Ausdruck dabei jeweils zwei Elemente `person1` und `person2`.
Der Lambda-Ausdruck vergleicht dann deren Alter und gibt das Vergleichsergebnis an die Sortiermethode zurück.

Entsprechend diesem Beispiel soll je nach ausgewählter Sortierung nach Name oder Preis (auf/absteigend) sortiert werden.

### Anforderungen
* Finden Sie die richtige Methode im Code, wo Sie die Sortierung ergänzen müssen
* Ergänzen Sie Überprüfungen, welche der drei Sortierung vom Nutzer ausgewählt wurde
* Je nach Nutzer-Auswahl soll die passende Sortierung entsprechend dem oberen Code-Beispiel mithilfe von Lambda-Ausdrücken erfolgen

### Tipps
Die Tipps geben Ihnen in zunehmend explizite Hilfestellung.
Lesen Sie sich schrittweise immer nur einen Tipp durch.

* Im Code befindet sich bereits ein `TODO` bei der Methode, wo die Sortierung ergänzt werden muss: `displayProducts()`
* In den if-Abfragen zur Ermittlung der ausgewählten Sortierung vergleichen Sie zwei Objekte vom Typ `SortOption`. 
Den Vergleich von Enums können Sie mit `==` anstellen.
* Überprüfen Sie für die Lambda-Ausdrücke, welchen Datentyp die Elemente haben, nach denen sortiert werden soll. 
Beim Preis handelt es sich dabei um einen `double`, beim Namen um einen `String`. Der Beispiel-Lambda-Ausdruck arbeitet mit `int`.
* Zum Vergleichen von zwei Strings steht Ihnen die Methode `.compareTo()` zur Verfügung, z.B: `"Aaron".compareTo("Bert")`
---