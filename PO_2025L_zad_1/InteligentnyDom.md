Świetnie, oto kolejna propozycja zadania, tym razem w kontekście inteligentnego domu, wraz z uproszczonym diagramem UML w PlantUML.

---

**Zadanie Praktyczne: System Zarządzania Urządzeniami w Inteligentnym Domu**

**Cel:**
Zadanie ma na celu ocenę umiejętności studentów w zakresie projektowania i implementacji obiektowego modelu systemu zarządzania urządzeniami w inteligentnym domu. Sprawdzone zostaną: tworzenie hierarchii klas (dziedziczenie), definiowanie i implementacja interfejsów, stosowanie enkapsulacji do ochrony stanu obiektów oraz wykorzystanie polimorfizmu do elastycznego zarządzania różnymi typami urządzeń.

**Kontekst:**
Projektujesz oprogramowanie dla centralnej jednostki sterującej inteligentnego domu "Inżynierski Zakątek". System ma pozwalać na monitorowanie i kontrolowanie różnych urządzeń zainstalowanych w domu.

**Wymagania:**

1.  **Klasy Podstawowe:**
    *   **`Urzadzenie` (Device):**
        *   Abstrakcyjna klasa bazowa dla wszystkich inteligentnych urządzeń.
        *   **Właściwości (enkapsulacja!):** `idUrzadzenia` (string/int), `nazwaPrzyjazna` (string), `lokalizacja` (string, np. "Salon", "Kuchnia"), `status` (np. enum `StatusUrzadzenia`: `WLACZONE`, `WYLACZONE`, `AWARIA`, `NIEZNANY`). Dostęp do statusu powinien być kontrolowany (np. tylko getter, zmiana przez dedykowane metody).
        *   **Metody:**
            *   Konstruktor inicjujący podstawowe właściwości.
            *   `pobierzStatus()`: Zwraca aktualny `status` urządzenia.
            *   (Abstrakcyjna) `pobierzSzczegolowyOpis()`: Zwraca string z bardziej szczegółowymi informacjami o stanie urządzenia (do implementacji w klasach pochodnych).
            *   (Potencjalnie wirtualna/chroniona) `zmienStatus(StatusUrzadzenia nowyStatus)`: Metoda do wewnętrznej zmiany statusu.

    *   **`InteligentnyDom` (SmartHome):**
        *   Główna klasa zarządzająca.
        *   **Właściwości:** `nazwaDomu` (string), `listaUrzadzen` (lista/tablica obiektów typu `Urzadzenie`).
        *   **Metody:**
            *   `dodajUrzadzenie(Urzadzenie u)`
            *   `usunUrzadzenie(string idUrzadzenia)`
            *   `znajdzUrzadzenie(string idUrzadzenia)`: Zwraca obiekt `Urzadzenie` lub `null`.
            *   `wyswietlStatusWszystkichUrzadzen()`: Iteruje po liście i wywołuje `pobierzSzczegolowyOpis()` dla każdego urządzenia (polimorfizm).
            *   `zarzadzajUrzadzeniem(string idUrzadzenia, ...)`: Metoda do interakcji z konkretnym urządzeniem (szczegóły zostaną doprecyzowane przez interfejsy).

2.  **Dziedziczenie (Hierarchia Urządzeń):**
    *   Stwórz co najmniej **trzy** konkretne klasy dziedziczące po `Urzadzenie`, np.:
        *   **`Lampa` (Light):**
            *   Dodatkowe właściwości: `poziomJasnosci` (int, 0-100), `kolor` (string, np. "biały", "czerwony" - uproszczone).
            *   Nadpisz `pobierzSzczegolowyOpis()`, aby zawierał informacje o jasności i kolorze (jeśli dotyczy) oraz statusie WŁ/WYŁ.
            *   Implementuje interfejs `IPrzelaczalne` (patrz niżej).
        *   **`Termostat` (Thermostat):**
            *   Dodatkowe właściwości: `temperaturaDocelowa` (double), `aktualnaTemperatura` (double, może być tylko do odczytu z zewnątrz).
            *   Nadpisz `pobierzSzczegolowyOpis()`, pokazując obie temperatury i status.
            *   Implementuje interfejs `IPrzelaczalne` (można włączyć/wyłączyć ogrzewanie/chłodzenie) oraz `IRegulowalne` (patrz niżej).
        *   **`CzujnikRuchu` (MotionSensor):**
            *   Dodatkowe właściwości: `czyWykrytoRuch` (boolean, tylko do odczytu), `czulosc` (int).
            *   Nadpisz `pobierzSzczegolowyOpis()`, informując o ostatnim stanie detekcji.
            *   Implementuje interfejs `IRaportujace` (patrz niżej).

3.  **Interfejsy (Definiowanie Zdolności):**
    *   Zdefiniuj co najmniej **dwa** interfejsy reprezentujące różne zdolności urządzeń:
        *   **`IPrzelaczalne` (ISwitchable):**
            *   Metody: `wlacz()`, `wylacz()`. Implementacja w klasach (`Lampa`, `Termostat`) powinna odpowiednio zmieniać ich wewnętrzny `status` (np. na `WLACZONE`/`WYLACZONE`).
        *   **`IRegulowalne` (IAdjustable):**
            *   Metoda: `ustawPoziom(int poziom)` lub `ustawWartosc(double wartosc)`. Np. dla `Lampa` mogłoby to ustawiać `poziomJasnosci`, a dla `Termostat` - `temperatureDocelowa`.
        *   **`IRaportujace` (IReporting):**
            *   Metoda: `pobierzOstatnieZdarzenie()` lub `pobierzDane()`: Zwraca specyficzne dane (np. dla `CzujnikRuchu` mogłoby zwrócić informację o ostatniej detekcji).

4.  **Polimorfizm:**
    *   W klasie `InteligentnyDom`:
        *   Metoda `wyswietlStatusWszystkichUrzadzen()` demonstruje polimorfizm przez wywoływanie `pobierzSzczegolowyOpis()` na obiektach różnych klas pochodnych przechowywanych jako `Urzadzenie`.
        *   Dodaj metodę np. `wlaczWszystkiePrzelaczalne()`: Iteruje po liście urządzeń, sprawdza, czy obiekt implementuje `IPrzelaczalne` (używając operatora `instanceof` lub `is`/`as` w zależności od języka) i jeśli tak, rzutuje i wywołuje metodę `wlacz()`.
        *   Dodaj metodę np. `raportujZdarzeniaZCzujnikow()`: Podobnie, znajduje urządzenia implementujące `IRaportujace` i wywołuje ich specyficzną metodę raportowania.

5.  **Demonstracja:**
    *   W metodzie `main` (lub odpowiedniku):
        *   Stwórz obiekt `InteligentnyDom`.
        *   Stwórz kilka różnych obiektów urządzeń (`Lampa`, `Termostat`, `CzujnikRuchu`) z różnymi ID, nazwami i lokalizacjami. Dodaj je do domu.
        *   Wyświetl status wszystkich urządzeń.
        *   Spróbuj włączyć i wyłączyć niektóre urządzenia (korzystając z metody zarządzającej w `InteligentnyDom`, która użyje interfejsu `IPrzelaczalne`).
        *   Spróbuj dostosować poziom/wartość dla urządzeń implementujących `IRegulowalne`.
        *   Wyświetl status ponownie, aby zobaczyć zmiany.
        *   Wywołaj metody demonstrujące polimorfizm przez interfejsy (np. `wlaczWszystkiePrzelaczalne`, `raportujZdarzeniaZCzujnikow`).

**Kryteria Oceny:**

*   Poprawność modelu obiektowego (klasy, atrybuty, metody).
*   Prawidłowe zastosowanie enkapsulacji (ukrywanie stanu, metody dostępowe).
*   Poprawne użycie dziedziczenia i nadpisywania metod (w tym metod abstrakcyjnych).
*   Poprawne zdefiniowanie i implementacja interfejsów do modelowania zdolności.
*   Demonstracja polimorfizmu (zarówno przez hierarchię klas, jak i przez interfejsy).
*   Logiczna spójność działania systemu.
*   Czytelność kodu, zgodność z konwencjami nazewnictwa.
*   Kompletność funkcjonalności demonstracyjnej.

**Czas realizacji:** Maksymalnie 3 godziny.

