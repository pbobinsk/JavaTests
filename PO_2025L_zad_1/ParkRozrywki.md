Jasne, oto propozycja zadania praktycznego skupiająca się na kluczowych koncepcjach OOP, osadzona w nietypowym kontekście i możliwa do zrealizowania w około 3 godziny.

---

**Zadanie Praktyczne: System Zarządzania Parkiem Rozrywki**

**Cel:**
Celem zadania jest zaprojektowanie i implementacja prostego systemu do zarządzania atrakcjami i gośćmi w parku rozrywki. Zadanie ma na celu sprawdzenie umiejętności w zakresie modelowania problemu przy użyciu klas i obiektów, stosowania dziedziczenia do tworzenia hierarchii bytów, wykorzystania interfejsów do definiowania kontraktów, implementacji enkapsulacji oraz demonstracji polimorfizmu.

**Kontekst:**
Wyobraź sobie, że tworzysz oprogramowanie dla nowego parku rozrywki "Inżynieria Przygody". Park potrzebuje systemu do śledzenia swoich atrakcji, zarządzania dostępem gości i sprawdzania podstawowych wymagań.

**Wymagania:**

1.  **Klasy Podstawowe:**
    *   **`Atrakcja` (Attraction):**
        *   Powinna być to klasa bazowa (potencjalnie abstrakcyjna) dla wszystkich atrakcji w parku.
        *   **Właściwości (enkapsulacja!):** `nazwa` (string), `pojemnoscMaksymalna` (int), `aktualnaLiczbaOsob` (int, prywatna, dostęp przez metody).
        *   **Metody:**
            *   `wejdz(int liczbaOsob)`: Zwiększa `aktualnaLiczbaOsob`, ale nie może przekroczyć `pojemnoscMaksymalna`. Zwraca `true` jeśli wejście się powiodło, `false` w przeciwnym razie.
            *   `wyjdz(int liczbaOsob)`: Zmniejsza `aktualnaLiczbaOsob` (nie może spaść poniżej 0).
            *   `pobierzStatus()`: Zwraca string opisujący status atrakcji (np. nazwę, obłożenie).
            *   (Potencjalnie abstrakcyjna) `czyMozeWejsc(Gosc gosc)`: Sprawdza, czy dany gość spełnia wymagania wstępu (zostanie zaimplementowana w klasach pochodnych).

    *   **`Gosc` (Visitor):**
        *   **Właściwości:** `imie` (string), `wiek` (int), `wzrost` (int, w cm), `posiadanyBilet` (obiekt typu `Bilet`).
        *   Proste metody dostępowe (gettery).

    *   **`Bilet` (Ticket):**
        *   **Właściwości:** `idBiletu` (string/int), `typBiletu` (np. enum: `NORMALNY`, `ULGOWY`, `VIP`), `czyWazny` (boolean).
        *   Proste metody dostępowe.

2.  **Dziedziczenie (Hierarchia Atrakcji):**
    *   Stwórz co najmniej **dwie** klasy dziedziczące po `Atrakcja`, reprezentujące różne typy atrakcji, np.:
        *   **`KolejkaGorska` (RollerCoaster):**
            *   Dodatkowe właściwości: `minimalnyWzrost` (int), `maksymalnaPredkosc` (int).
            *   Nadpisz metodę `czyMozeWejsc(Gosc gosc)`: Sprawdza, czy gość ma ważny bilet ORAZ czy ma wystarczający wzrost.
            *   Nadpisz `pobierzStatus()`, aby zawierał dodatkowe informacje (np. min. wzrost).
        *   **`Karuzela` (Carousel):**
            *   Dodatkowe właściwości: `czyDlaDzieci` (boolean).
            *   Nadpisz metodę `czyMozeWejsc(Gosc gosc)`: Sprawdza tylko ważność biletu. Jeśli `czyDlaDzieci` jest `true`, może dodatkowo sprawdzać wiek (np. poniżej 12 lat).
            *   Nadpisz `pobierzStatus()`.
        *   **`Pokaz` (Show):**
            *   Dodatkowe właściwości: `godzinaRozpoczecia` (string/DateTime), `czasTrwania` (int, w minutach).
            *   Nadpisz metodę `czyMozeWejsc(Gosc gosc)`: Sprawdza tylko ważność biletu.
            *   Nadpisz `pobierzStatus()`.

3.  **Interfejsy:**
    *   Zdefiniuj interfejs `IWymaganiaBezpieczenstwa` (ISafetyRequirements) z metodą `sprawdzWymagania()`.
    *   Niech klasy `KolejkaGorska` i potencjalnie inne (według uznania) implementują ten interfejs. Implementacja metody `sprawdzWymagania()` może np. wypisywać na konsolę komunikat o konieczności sprawdzenia pasów bezpieczeństwa lub innych specyficznych wymagań.

4.  **Polimorfizm:**
    *   Stwórz klasę `ParkRozrywki` (ThemePark).
    *   **Właściwości:** `nazwaParku` (string), `listaAtrakcji` (lista/tablica obiektów typu `Atrakcja`).
    *   **Metody:**
        *   `dodajAtrakcje(Atrakcja a)`: Dodaje atrakcję do listy.
        *   `usunAtrakcje(string nazwaAtrakcji)`: Usuwa atrakcję z listy.
        *   `symulujWejscieGoscia(Gosc g, string nazwaAtrakcji)`: Znajduje atrakcję po nazwie. Jeśli istnieje i gość może wejść (użyj polimorficznej metody `czyMozeWejsc`), próbuje dodać gościa do atrakcji (użyj `wejdz(1)`). Wypisz odpowiedni komunikat o powodzeniu lub niepowodzeniu.
        *   `wyswietlStatusWszystkichAtrakcji()`: Iteruje po `listaAtrakcji` i dla każdego obiektu wywołuje jego metodę `pobierzStatus()`, demonstrując polimorfizm. Wypisuje wyniki na konsolę.
        *   `przeprowadzInspekcjeBezpieczenstwa()`: Iteruje po `listaAtrakcji`. Dla tych atrakcji, które implementują interfejs `IWymaganiaBezpieczenstwa`, wywołuje metodę `sprawdzWymagania()`.

5.  **Demonstracja:**
    *   W metodzie `main` (lub dedykowanej klasie testowej):
        *   Stwórz obiekt `ParkRozrywki`.
        *   Stwórz kilka różnych obiektów atrakcji (`KolejkaGorska`, `Karuzela`, `Pokaz`) i dodaj je do parku.
        *   Stwórz kilka obiektów `Gosc` z różnymi biletami, wiekiem i wzrostem.
        *   Zasymuluj próby wejścia różnych gości na różne atrakcje (niektóre powinny się powieść, inne nie).
        *   Wyświetl status wszystkich atrakcji.
        *   Przeprowadź inspekcję bezpieczeństwa.

**Kryteria Oceny:**

*   Poprawność implementacji klas zgodnie z zasadami OOP (konstruktory, metody, właściwości).
*   Prawidłowe zastosowanie enkapsulacji (ukrywanie danych).
*   Poprawne użycie dziedziczenia i nadpisywania metod.
*   Poprawne zdefiniowanie i implementacja interfejsu.
*   Demonstracja polimorfizmu (zarówno przez dziedziczenie - wspólna lista `Atrakcja`, jak i przez interfejs - `IWymaganiaBezpieczenstwa`).
*   Logika działania programu zgodna z wymaganiami.
*   Czytelność i przejrzystość kodu (nazewnictwo, formatowanie).
*   Spełnienie funkcjonalności demonstracyjnej w `main`.

**Czas realizacji:** Maksymalnie 3 godziny.

---

**Dlaczego ten temat jest dobry:**

*   **Niestandardowy:** Odchodzi od typowych przykładów.
*   **Relatywnie prosty do zrozumienia:** Koncept parku rozrywki jest powszechnie znany.
*   **Dobrze mapuje się na OOP:** Naturalnie pojawiają się hierarchie (atrakcje), różne byty (goście, bilety, atrakcje), wspólne interfejsy (wymagania) i potrzeba polimorfizmu (zarządzanie różnymi atrakcjami w jednolity sposób).
*   **Skalowalny:** Można łatwo dodać więcej typów atrakcji, biletów, czy wprowadzić personel, jeśli czas pozwoli lub jako zadanie dodatkowe.
*   **Wymaga wszystkich kluczowych konceptów:** Zadanie wymusza użycie klas, obiektów, dziedziczenia, interfejsów, enkapsulacji i polimorfizmu w sensowny sposób.
*   **Realistyczny czas:** Zakres zadania jest odpowiedni na około 3 godziny pracy dla studenta znającego podstawy OOP.