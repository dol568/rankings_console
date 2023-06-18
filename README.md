# rankings_console

Program wyliczajacy rankingi godzinowe na podstawie wczytanych arkuszy kalkulacyjnych.\
Program obsluguje pliki w formacie .xls.\
Pliki powinny miec nazwe wg wzoru: "nazwisko"_"imie".xls.\
Pliki nie spelnijace tego wzoru zostaja pominiete.\
Aby program dzialal poprawnie arkusze kalkulacyjne
powinny byc sformatowane wg wzoru podanego w przykladowych danych.\
Program tworzy rankingi

a) pracownikow wg przepracowanych godzin we wszystkich projektach

b) miesiecy (np. 1. styczen 2015 - 43 godzin, 2. luty 2014 - 42 godziny) wg przepracowanych godzin we wszystkich projektach przez wszystkich pracownikow

c) 10-ciu najbardziej\najmniej pracowitych dni (np. 1. 5 wrzesien 2015 - 43 godziny, 2. 4 wrzesien - 42 godziny) wg przepracowanych godzin we wszystkich projektach przez wszystkich pracownikow. Jesli kilka ostatnich pozycji bedzie mialo rowna liczbe godzin, a liczba pozycji w rankingu bedzie przekraczac limit to zostaja one wyswietlone

### Obsluga programu
W terminalu nalezy wpisac ponizsza komende, ktora wczyta pliki z podanego katalogu i podkatalogow
```
java -jar <plikjar> [-f lub --files] <katalog>
```
Nastepnie aby wystwielic ranking pracownikow wg przepracowanych godzin we wszystkich projektach nalezy wpisac
```
java -jar <plikjar> [-e lub --employeesRanking] [ASC/DESC] <limit>
```
np. java -jar *.jar -e DESC 5\
wyswietli w terminalu ranking 5-ciu pracownikow z najwieksza iloscia przepracowanych godzin od najwiekszej do najmniejszej

Inne dostepne komendy

```
java -jar <plikjar> [-e lub --employeesRanking] [ASC/DESC] <limit> CHART
```
np. java -jar *.jar -e DESC 5 CHART\
wyswietli ranking w formie wykresu
```
java -jar <plikjar> [-e lub --employeesRanking] [ASC/DESC] <limit> CHART PDF
```
np. java -jar *.jar -e DESC 5 CHART PDF\
wyswietli ranking w formie wykresu i zapisze go do pliku PDF w miejscu z ktorego uruchamiana jest komenda wczytujaca dane. Ponowne wpisanie tej komendy z innymi argumentami powoduje nadpisanie pliku
```
java -jar <plikjar> [-e lub --employeesRanking] [ASC/DESC] <limit> XLS <nazwapliku>
```
np. java -jar *.jar -e DESC 5 XLS ranking\
zapisze ranking do pliku ranking.xls w miejscu z ktorego uruchamiana jest komenda wczytujaca dane.
```
java -jar <plikjar> [-e lub --employeesRanking] [ASC/DESC] <limit> PDF <nazwapliku>
```
np. java -jar *.jar -e DESC 5 PDF ranking\
zapisze ranking do pliku ranking.pdf w miejscu z ktorego uruchamiana jest komenda wczytujaca dane.

Analogiczne komendy obowiazuja jesli chcemy wystwietlic ranking miesiecy lub dni.\
Wystarczy zamiast [-e lub --employeesRanking] wpisac [-m, --monthsRanking] lub [-d, --daysRanking]\
Przyklady:
```
java -jar <plikjar> [-m, --monthsRanking] [ASC/DESC] <limit>
```
```
java -jar <plikjar> [-d, --daysRanking] [ASC/DESC] <limit>
```
Ostatnia dostepna komenda jest
```
java -jar <plikjar> [-h, --help]
```
ktora wyswietla w terminalu dostepne komendy i spodob ich uzycia

Program tworzy w miejscu z ktorego uruchamiana jest komenda wczytujaca dane plik spreadsheet.ser, ktory mozna usunac po zakonczeniu korzystania z programu

Plik [JAR](https://github.com/dol568/rankings_console/blob/main/target/rankings_console-1.0-SNAPSHOT-shaded.jar)
