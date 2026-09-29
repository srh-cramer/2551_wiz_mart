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

Erstellen Sie eine Klasse `Repository<T>`.

Das Repository soll unsere Datenbasis verwalten, die für unsere Produkte z.Zt. vom Typ `List<Product>` ist.

Das Repository soll mindestens folgende Funktionen zur Verwaltung der Liste anbieten:

* Ein Objekt hinzufügen: `add()`
* Ein Objekt entfernen: `remove()`
* Alle Objekte zurückgeben: `findAll()`

Dabei soll es keine Rolle spielen, von welchem Datentyp die Objekte sind.

Passen Sie anschließend den `ShopService` so an, dass er anstatt von `List<Product>` nun `Repository<Product>` verwendet.

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

