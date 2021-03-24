<!DOCTYPE html>
<html>
  <headL>
  </head>  
  <body>
  <p>
    Detalii despre cele 4 clase ce extind clasa Item se gasesc aici: <a href="https://github.com/PrEaDiVviN/PA-laboratoare/tree/main/Laboratorul5/Compulsory">Compulsory link</a>
  </p>
  <p>
    Au fost create urmatoarele clase ce extind clasa <strong> Exception </strong> pentru a se ilustra erorile:
    <ul>
      <li><strong>InvalidCatalogItemException</strong> -> Item ce nu este de tipul celor 4 clase cunoscute.</li>
      <li><strong>InvalidItemIdException</strong> -> Id-ul item-ului se afla deja in lista sau este de lungime 0.</li>
      <li><strong>InvalidItemPathException</strong> -> Path-ul are lungimea 0.</li>
      <li><strong>InvalidCatalogException</strong> -> Catalogul nu exista sau nu se pot face operatii IO pe el.</li>
      <li><strong>EmptyCatalogException</strong> -> Catalogul nu are iteme.</li>
      <li><strong>KeyboardShellCommandException</strong> -> Eroare in shell (dezvaluie si comanda ce a dat eroare).</li>
    </ul>  
  </p>  
  <p>
    A fost creata <strong>clasa abstracta Command </strong> pentru a descrie o comanda generica.<br>
    --><em>abstract void applyTo  (Catalog modify) throws InvalidCatalogItemException, InvalidItemIdException, InvalidItemPathException ,InvalidCatalogException, EmptyCatalogException, KeyboardShellCommandException;</em>
    <br>Clasele ce extind clasa Command sunt urmatoarele:
    <ul>
      <li><strong>AddCommand</strong>
        <ul>
          <li>applyTo(Catalog modify) throws InvalidItemIdException </li> 
          <li>AddCommand(Item item) throws InvalidCatalogItemException, InvalidItemPathException</li>
        </ul> 
      </li>
      <li><strong>ListCommand</strong>
        <ul>
          <li>applyTo(Catalog target) throws EmptyCatalogException</li> 
        </ul> 
      </li>
      <li><strong>PlayCommand</strong>
        <ul>
          <li>applyTo(Catalog target) throws InvalidItemIdException</li> 
          <li>PlayCommand(String id)</li>
        </ul> 
      </li>
      <li><strong>SaveCommand</strong>
        <ul>
          <li>applyTo(Catalog target) throws InvalidCatalogException</li> 
        </ul>       
      </li>
      <li><strong>LoadCommand</strong>
        <ul>
          <li>applyTo(Catalog target) throws InvalidCatalogException</li> 
          <li>LoadCommand(String path)</li>
        </ul>          
      </li>
      <li><strong>KeyboardShellCommand</strong>
        <ul>
          <li>applyTo(Catalog modify) throws KeyboardShellCommandException</li> 
        </ul>      
      </li>
      <li><strong>CreateReportCommand</strong>
        <ul>
          <li>applyTo(Catalog modify) throws InvalidCatalogException</li> 
        </ul>    
      </li>
   </ul>
  </p>
  <p>
    Arhiva jar finala a aplicatiei se numeste <strong>Laboratorul5.jar</strong> si se gaseste in folderul Laboratorul5_jar. <br>
  <Strong>Lansarea aplicatiei de la consola: </strong><em>java -jar C:\Users\PrEaD\Desktop\Anul2_semestrul2\PA\Laboratorul5\com\company\Laboratorul5.jar</em>
  </p>
  <p>
  Shell-ul ce permite citirea comenzilor de la tastura este dat prin clasa <strong>KeyboardShellCommand</strong>.
  </p>
  <p>
  Crearea raportului HTML a fost realizata prin clasa <strong>CreateReportCommand</strong>.<br>
  Template-ul pentru report se gaseste in folder-ul <em>Templates</em> si are numele <strong>Report.ftl</strong>.<br>
  Report-ul HTML create se gaseste tot in folderul <em>Templates</em> si are numele <strong>report.html</strong>.<br>
  Folder-ul Documentation contine documentatia lui FreeMarker.
  </p>
  </body>
</html>
