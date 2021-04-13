<!DOCTYPE html>
<html>
  <headL>
  </head>  
  <body>
  <p>
    Detalii despre tipul de joc implementat, sau alte detalii despre clase se gasesc aici <a href="https://github.com/PrEaDiVviN/PA-laboratoare/tree/main/Laboratorul7/Compulsory">Compulsory link</a>
  </p>
  <p>
    A fost adaugata la clasa <strong> Game </strong> metoda <em> public void showWinnerLongestList() </em> ce va afisa castigatorul pe idea celei mai lungi liste.
  </p>  
  <p>
    A fost creata clasa <strong> public class runningDaemonThread implements Runnable </strong> care se va ocupa timekeeping si il va afisa in secunde. Daca jocul depasteste
    30 de secunde, acesta il va opri. (timpul va fi afisat in secunde) (In <strong>Game</strong>, in metoda <em>play</em> este setat un thread ca fiind daemon)
  </p>
  <p>
    A fost creat enum-ul <strong>PlayerType</strong> pentru a se spune tipurile de player.
    <ul>
      <li>RANDOM</li>
      <li>COMPUTER</li>
      <li>HUMAN</li>
    </ul>
  </p>
  <p>
    A fost creata clasa <strong> MoveType </strong> care va incapsula metodele specifice tipurilor de mutari pe care le poate face un player.
    <ul>
      <li><em>public static void makeMoveRandom(Game game, GameBoard gameBoard)</em> ->(COMPUTER) va crea mereu o lista noua cu un nou token (compulsory for better understading of representation)</li>
      <li><em>public static void makeMoveHumanShell(Game game, GameBoard gameBoard)</em> -> ii va permite jucatorului uman sa aleaga un token de pe masa, prin indicele corespunzator.</li>
      <li><em>public static void makeMove(Game game, GameBoard gameBoard)</em> ->(COMPUTER) (Metoda mutata din Game din compulsory - see Compulsory README for details)</li>
    </ul>
  </p>  
  <p>
    Clasa Game a fost modificata 
    <ul>
      <li><strike>Player player1</strike></li>
      <li><strike>Player player2</strike></li>
      <li>Added in return <em>private List <Player> players = new ArrayList<>();</em></li>
      <li><strike>volatile Player firstToStart</strike></li>
      <li>public volatile int turn</li>
      <li>Renamed <strike>showResults()</strike> to showPlayersLists()</li>  
      <li>Added <em>public void showWinnerLongestList() </em></li>
    </ul>
  </p>
  <p>
    La clasa Player a fost adaugat atributul: <em>PlayerType type</em>
  </p>      
  <p>
    Clasa PlayGame a fost modificata
    <ul>
      <li><strike>Player player</strike></li>
      <li><strike>void makeMove()</strike></li>
      <li>Added methods <em>private void workThread 0,1,2,3()</em> -> fiecare fiind asociata unui thread, care vor folosi <strong> wait-notify </strong> pentru respectarea ordinii.</li>
    </ul>
  </p>
  <p>
    Cu ajutorul metodei <em>generateGameBoard</em> in clasa Main, metoda main, a fost generata o simulare pe o lista de dimensiune random (<100*100/2), intre cele 4 thread-uri.
  </p>
  </body>
</html>
