<!DOCTYPE html>
<html>
  <headL>
  </head>  
  <body>
  <p>
    Detaliile implementate la partea de Optional se gasesc aici : <a href="https://github.com/PrEaDiVviN/PA-laboratoare/edit/main/Laboratorul5/Compulsory/README.md">Optional -> Link</a>
  </p>
  <p>
    Graful reprezentativ al itemilor unui catalog este dar prin clasa <strong>CatalogRepresentationGraph</strong>.<br> Reprezentarea este data prin hasmap-ul urmator: <em> Map( Item, List(Item)) representation</em>.
  </p>  
  <p>
    A fost creata o clasa <strong> astracta Algorithm </strong> cu metoda abstracta <em>public Solution solve(CatalogRepresentationGraph graph)</em>. <br>
    A fost creata o clasa <strong> Solution </strong> care tine minte detaliile unei solutii.<br>
    Clasa <strong> greedyAlgoritm </strong> extinde clasa Algorithm si rezolva problema oferita printr-un algorithm greedy prin apelarea metodei <em>solve</em>.
  </p>  
  <p>
    Pentru testarea algoritmului a fost creata clasa <strong>GreedyAlgorithmTest</strong>.<br>
    Generarea unor grafuri mari se realizeaza prin metoda <em>CatalogRepresentationGraph generateRandom(int bound)</em> ce se afla in clasa GreedyAlgorithmTest. 
    Testele se pot vedea in clasa main.
  </p>
  <p>
    Am incercat sa implementez clasa <strong> InfoCommand </strong> care nu extrage informatii la momentul actual despre metadate, ci ar trebui sa afiseze pe ecran textul
    unui fisier. <strong> Nu functioneaza </strong> -> necesita dependinte care nu au fost adaugate(sqlite-jdbc..)!
  </p>  
  </body>
</html>
