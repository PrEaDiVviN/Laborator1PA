<html>
  <head>
  </head>
  <body>
    <p>Au fost create cele 2 clase Solution si Problem. Pe langa, a fost creata <strong> clasa abstracta Algorithm </strong> care are metoda abstracta <em>Solution solve(Problem P)</em>. <strong> Clasa greedyAlgorithm </strong> extinde clasa precedenta si rezolva problema printr-un algoritm greedy. Aceasta prezinta metoda <em>long getExecutionTime()</em> in plus si doua variabile de tip long: <em>start</em>,<em>end</em>.</p> 
    <p><strong>java-faker</strong> a fost introdus prin intermediul jar-urilor. </p>
    <p><strong>query-urile</strong> se pot vedea in clasa Main in partea de jos a metodei main.</p>
    <p>Testarea algoritmului greedy a fost realiza in clasa <strong>greedyAlgorithmTest</strong> care are doua metode de testare:
      <ul>
        <li><em>void testFirst()</em> -> care verifica daca un test scris personal si rezolvat, obtine solutia asteptata de mine folosind <em>assertEquals()</em>.</li>
        <li><em>void testSpeed()</em> -> care pe o macheta de 100 de studenti si 100 de scoli, genereaza random niste preferinta ale acestora si aplica algoritmul greedy. Finalmente, verifica daca viteza de executie a algoritmului este mai mica de 3 secunde folosind <em>assertTrue()</em>.</li>
      </ul>  
    </p>
    <p>
      In clasa <strong>Student</strong> au fost adaugate 2 atribute in plus, <em>int varsta</em> si <em>Sex sex</em>.
    </p>
    <p>
      <strong>Sex</strong> este un <strong>enum</strong> care are doua valori:<em>MALE</em> si <em>FEMALE</em>.
    </p>
  </body>
</html>
