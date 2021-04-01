<!DOCTYPE html>
<html>
  <headL>
  </head>  
  <body>
  <p>
  Clasele folosite pentru modelarea problemei sunt: 
   <ol>
     <li><strong>Board</strong> -> clasa abstracta
       <ul>
         <li><em>public abstract void showBoard()</em></li>
       </ul>
      </li>
      <li><strong>Playable</strong> -> interfata
          <ul>
            <li><em>public void play();</em></li>
          </ul>
      </li>
     <li><strong>Pair</strong>
           <ul>
             <li>int first</li>
             <li>int second</li>
             <li><em> @Override public String toString()</em></li>
           </ul>
      </li>
     <li><strong>Token</strong>
        <ul>
            <li>Pair pair</li>
            <li>int value</li>
            <li><em> @Override public boolean equals(Object obj)</em> </li>
            <li><em> @Override public String toString()</em> </li>
        </ul>
      </li>
       <li><strong>Player</strong>
        <ul>
            <li>String name</li>
            <li>float rating</li>
            <li>Color color</li>
            <li>List(List(Token)) status = Collections.synchronizedList(new ArrayList<>())</li>
            <li><em> @Override public boolean equals(Object obj)</em> </li>
            <li><em> @Override public String toString()</em> </li>
        </ul>
      </li>
     </li>
       <li><strong>GameBoard extends Board</strong>
        <ul>
            <li>List<Token> representationBoard = Collections.synchronizedList(new ArrayList<>());</li>
            <li><em> public List<Token> generateGameBoard(int limit)</em> -> Lista generata intr-un mod random</li>
        </ul>
      </li>
      <li><strong>GameBoard extends Board</strong>
        <ul>
            <li>List<Token> representationBoard = Collections.synchronizedList(new ArrayList<>());</li>
            <li><em> public List(Token) generateGameBoard(int limit)</em> -> Lista generata intr-un mod random</li>
        </ul>
      </li>
      <li><strong>Game implements Playable</strong>
        <ul>
            <li>Player player1</li>
            <li>Player player2</li>
            <li>volatile Player firstToStart</li>
            <li>GameBoard gameBoard</li>
            <li><em>void showResults()</em></li>
            <li><em>@Override public void play()</em> -> <strong>metoda in care vor fi create thread-urile</strong></li>
        </ul>
      </li>
      <li><strong>PlayGame implements Runnable</strong>
        <ul>
            <li>Player player</li>
            <li>Game game</li>
            <li>GameBoard gameBoard</li>
            <li><em> @Overridepublic void run()</em></li>
            <li><em> void makeMove()</em> -> <strong> metoda in care se extrag jetoanele</strong></li>
        </ul>
      </li>
    </ol>
  </p>
  <p>
    <em>Ideea jocului este usor modificata: </em> fiecare player extrage cate un jeton unul dupa altul pana cand tabla este goala astfel incat sa formeze cel mai lung, dar
    nu este obligatoriu ca drumul sa se termine in acelasi loc. Adica drumurile de genul (2,1) (1,2) (2,3) sunt permise.<br>
    -> Lista userului<strong> status</strong> este o Lista de Liste intrucat, algoritmul din spate a fost gandit in felul urmator: construieste o lista incercand sa adaugi un nou token 
    la inceputul ei sau la finalul ei. Daca nu exista exista cum sa se adauge la aceasta lista, atunci adauga o alta lista la listele userului cu un token random si tura viitoare verifica la amandoua daca poti adauga, daca nu fa alta.<br>
    <em>Algoritmul se gaseste in PlayGame -> MakeMove()</em>
  </p>  
  </body>
</html>
