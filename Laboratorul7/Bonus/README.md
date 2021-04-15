<!DOCTYPE html>
<html>
  <headL>
  </head>  
  <body>
  <p>
    Detalii despre partea de optional se gasesc aici <a href="https://github.com/PrEaDiVviN/PA-laboratoare/tree/main/Laboratorul7/Optional">Optional link</a>
  </p>
  <p>
    Clasa <strong> Game </strong> are acum doua noi modalitati de realizare a scorului
    <ul>
      <li><em>showWinnerLongestList()</em>   -> Adaugata la optional</li>
      <li><em> showWinnerMostEven()</em>   -> NEW: Castigatorul este player-ul cu cele mai multe numere pare din tokenele luate(doar pereche -> See <a href="https://github.com/PrEaDiVviN/PA-laboratoare/tree/main/Laboratorul7/Compulsory">Compulsory</a> for details about Token).</li>
      <li><em> showWinnerSmallestDifference()</em>   -> NEW: Castigatorul este player-ul care are suma diferentelor in valoarea absoluta a valorilor perechii tokenilor.</li>
    </ul>
  </p>  
  <p>
    Clasa Game se va ocupa de crearea interfetei pentru jucator.
    <ul>
      <li>S-a modificat declaratia: public class Game <strong>extends Application</strong> implements Playable</li>
      <li>Metoda noua adaugata: <em> @Override
    public void start(Stage primaryStage) </em></li>
      <li>In metoda <em>play()</em>, dupa crearea celor 4 thread-uri responsabile pentru jucatori, a fost lansata interfata folosind metoda <em>launch</em></li>
    </ul>
  </p>
  <p>
    La enum-ul <strong>PlayerType</strong> au fost adaugate inca 3 tipuri de player
    <ul>
      <li>RANDOM</li>
      <li>COMPUTER</li>
      <li>HUMAN</li>
      <li>SMALLESTDIFFERENCECOMPUTER -> <strong>NEW</strong></li>
      <li>EVENGAMECOMPUTER -> <strong>NEW</strong></li>
      <li>INTERFACEHUMAN -> <strong>NEW</strong></li>
    </ul>
  </p>
  <p>
    A fost adaugata clasa <strong>LongestSequenceBacktracking</strong><br>
        -> Aceasta, dandui-se un token de start si o lista de tokene, afla cea mai lunga secventa de tokene aplicand un algorithm de tip backtracking. Initial, a fost
    creata pentru a fi folosita pentru crearea unui smartplayer, dar folosirea lui peste secvente de tokene mai mari ingreuneaza extrem de mult rularea programului.
    Deci, din aceasta cauza a fost abandonata.
  </p>
  <p>
    La clasa moveType au fost adaugate noi elemente.
    <ul>
      <li>public static Game game -> <strong>NEW</strong></li>
      <li>public static GameBoard gameBoard -> <strong>NEW</strong></li>
      <li><em>public static void makeMoveRandom(Game game, GameBoard gameBoard)</em> ->(COMPUTER) (<strong>Smartplayer -> Longest Sequence</strong>) </li>
      <li><em>public static void makeMoveMostEven (Game game, GameBoard gameBoard)</em> ->(EVENGAMECOMPUTER) (<strong>Smartplayer -> Most even numbers</strong>) -> <strong>NEW</strong>  </li>
      <li><em>public static void makeMoveSmallestDifference (Game game, GameBoard gameBoard))</em> ->(SMALLESTDIFFERENCECOMPUTER) (<strong>Smartplayer -> Smallest difference in each token values</strong>) -> <strong>NEW</strong>  </li>
      <li><em>public static void makeMoveSmallestDifference (Game game, GameBoard gameBoard))</em> ->(SMALLESTDIFFERENCECOMPUTER) (<em>Folosit pt conectarea dintre JavaFX thread cu thread-3 in PlayGame. Thread-ul 3 este responsabil doar pt pastrarea ordinii de selectie a tokenilor, in timp ce thread-ul JavaFX se ocupa de extragerea tokenului. </em>) -> <strong>NEW</strong>  </li>
    </ul>
  </p>  
  <p>
    Fotografii referitoare la interfata se gasesc aici  <a href="https://github.com/PrEaDiVviN/PA-laboratoare/tree/main/Laboratorul7/Bonus/Photos">Photos</a>
  </p>
  </body>
</html>
