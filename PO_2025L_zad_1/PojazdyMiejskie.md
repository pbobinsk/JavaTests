Okej, oto propozycja zadania dotyczącego Systemu Zarządzania Flotą Pojazdów Miejskich.

---

**Zadanie Praktyczne: System Zarządzania Flotą Pojazdów Miejskich**

**Cel:**
Zadanie ma na celu weryfikację umiejętności studentów w zakresie modelowania i implementacji systemu zarządzania flotą pojazdów miejskich z wykorzystaniem paradygmatu obiektowego. Studenci powinni zademonstrować zrozumienie i zastosowanie klas, obiektów, dziedziczenia, interfejsów, enkapsulacji oraz polimorfizmu.

**Kontekst:**
Pracujesz dla miejskiego przedsiębiorstwa komunikacyjnego "Mobilne Miasto", które potrzebuje nowego systemu do zarządzania swoją zróżnicowaną flotą pojazdów – od autobusów po hulajnogi elektryczne. System ma umożliwić śledzenie statusu, lokalizacji oraz podstawowych parametrów eksploatacyjnych pojazdów.

**Wymagania:**

1.  **Klasy Podstawowe:**
    *   **`Pojazd` (Vehicle):**
        *   Abstrakcyjna klasa bazowa dla wszystkich pojazdów we flocie.
        *   **Właściwości (enkapsulacja!):** `idRejestracyjny` (string), `rokProdukcji` (int), `aktualnyStatus` (np. enum `StatusPojazdu`: `OPERACYJNY`, `W_SERWISIE`, `AWARIA`, `POZA_UZYCIEM`), `lokalizacja` (string, np. "Baza" lub współrzędne jako prosty string). Dostęp do statusu i lokalizacji powinien być kontrolowany.
        *   **Metody:**
            *   Konstruktor inicjujący podstawowe dane.
            *   `pobierzStatus()`: Zwraca `aktualnyStatus`.
            *   `aktualizujStatus(StatusPojazdu nowyStatus)`: Zmienia status pojazdu.
            *   `aktualizujLokalizacje(string nowaLokalizacja)`: Zmienia lokalizację.
            *   (Abstrakcyjna) `wyswietlSzczegoly()`: Zwraca string z podsumowaniem informacji o pojeździe (do implementacji w klasach pochodnych).

    *   **`SystemZarzadzaniaFlota` (FleetManagementSystem):**
        *   Klasa centralna zarządzająca flotą.
        *   **Właściwości:** `nazwaSystemu` (string), `listaPojazdow` (lista/tablica obiektów typu `Pojazd`).
        *   **Metody:**
            *   `dodajPojazd(Pojazd p)`
            *   `usunPojazd(string idRejestracyjny)`
            *   `znajdzPojazd(string idRejestracyjny)`: Zwraca obiekt `Pojazd` lub `null`.
            *   `wyswietlStatusFloty()`: Iteruje po liście i wywołuje `wyswietlSzczegoly()` dla każdego pojazdu (polimorfizm).
            *   `raportujPojazdyWSerwisie()`: Wyszukuje i wyświetla pojazdy ze statusem `W_SERWISIE`.

2.  **Dziedziczenie (Hierarchia Pojazdów):**
    *   Stwórz co najmniej **trzy** konkretne klasy dziedziczące po `Pojazd`, np.:
        *   **`Autobus` (Bus):**
            *   Dodatkowe właściwości: `numerLinii` (string), `pojemnoscPasazerow` (int), `poziomPaliwa` (double, w procentach lub litrach).
            *   Nadpisz `wyswietlSzczegoly()`, aby uwzględnić dodatkowe informacje.
            *   Implementuje interfejs `IMozliwyDoNamierzenia` i `IWymagajacySerwisu` (patrz niżej).
        *   **`Tramwaj` (Tram):**
            *   Dodatkowe właściwości: `numerLinii` (string), `liczbaWagonow` (int). (Tramwaje zazwyczaj nie mają "paliwa" w tradycyjnym sensie).
            *   Nadpisz `wyswietlSzczegoly()`.
            *   Implementuje interfejs `IMozliwyDoNamierzenia` i `IWymagajacySerwisu`.
        *   **`HulajnogaElektryczna` (ElectricScooter):**
            *   Dodatkowe właściwości: `poziomNaladowaniaBaterii` (int, 0-100).
            *   Nadpisz `wyswietlSzczegoly()`.
            *   Implementuje interfejs `IMozliwyDoNamierzenia`. (Serwis może być prostszy, ale też wymagany).

3.  **Interfejsy (Definiowanie Zdolności):**
    *   Zdefiniuj co najmniej **dwa** interfejsy reprezentujące specyficzne zdolności lub wymagania:
        *   **`IMozliwyDoNamierzenia` (ITrackable):**
            *   Metoda: `pobierzAktualnaLokalizacje()`: Zwraca string lub bardziej złożony obiekt lokalizacji (dla uproszczenia może zwracać wartość pola `lokalizacja`).
        *   **`IWymagajacySerwisu` (IServiceable):**
            *   Metoda: `sprawdzStanTechniczny()`: Zwraca boolean (czy wymaga serwisu) lub string z opisem stanu.
            *   Metoda: `zaplanujSerwis()`: Zmienia status pojazdu na `W_SERWISIE` i zwraca np. datę planowanego serwisu (może być uproszczone do zmiany statusu i zwrócenia `true`).

4.  **Polimorfizm:**
    *   W klasie `SystemZarzadzaniaFlota`:
        *   Metoda `wyswietlStatusFloty()` demonstruje polimorfizm przez wywoływanie `wyswietlSzczegoly()` na różnych typach pojazdów przechowywanych w jednej kolekcji.
        *   Dodaj metodę np. `zlokalizujWszystkiePojazdy()`: Iteruje po flocie, sprawdza, czy pojazd implementuje `IMozliwyDoNamierzenia` i jeśli tak, wywołuje `pobierzAktualnaLokalizacje()` i wyświetla wynik.
        *   Dodaj metodę np. `przeprowadzPrzegladSerwisowy()`: Iteruje po flocie, znajduje pojazdy implementujące `IWymagajacySerwisu`, wywołuje `sprawdzStanTechniczny()` i jeśli pojazd wymaga serwisu, wywołuje `zaplanujSerwis()`.

5.  **Demonstracja:**
    *   W metodzie `main` (lub odpowiedniku):
        *   Stwórz obiekt `SystemZarzadzaniaFlota`.
        *   Stwórz kilka obiektów różnych pojazdów (`Autobus`, `Tramwaj`, `HulajnogaElektryczna`) z unikalnymi ID i różnymi danymi początkowymi (status, lokalizacja, paliwo/bateria). Dodaj je do systemu.
        *   Wyświetl status całej floty.
        *   Zaktualizuj status i lokalizację kilku pojazdów.
        *   Wyświetl status floty ponownie, aby zobaczyć zmiany.
        *   Wywołaj metody demonstrujące polimorfizm przez interfejsy (np. `zlokalizujWszystkiePojazdy`, `przeprowadzPrzegladSerwisowy`).
        *   Wyświetl pojazdy znajdujące się w serwisie.

**Kryteria Oceny:**

*   Poprawność zaprojektowanej hierarchii klas i ich implementacji.
*   Prawidłowe zastosowanie enkapsulacji (hermetyzacja danych).
*   Poprawne użycie dziedziczenia i nadpisywania metod (w tym metod abstrakcyjnych).
*   Poprawne zdefiniowanie i implementacja interfejsów.
*   Demonstracja polimorfizmu (zarówno przez dziedziczenie, jak i przez interfejsy).
*   Logiczna spójność działania systemu.
*   Czytelność i jakość kodu (nazewnictwo, formatowanie, komentarze w uzasadnionych miejscach).
*   Kompletność i poprawność działania funkcjonalności demonstracyjnej.

**Czas realizacji:** Maksymalnie 3 godziny.

