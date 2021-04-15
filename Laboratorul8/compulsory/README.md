<!DOCTYPE html>
<html>
  <headL>
  </head>  
  <body>
  <p>
    Clasele folosite pentru modelare se afla in pachetul <strong>model</strong>: 
   <ol>
     <li><strong>Movie</strong>
       <ul>
         <li><em>int id</em></li>
         <li><em>String title</em></li>
         <li><em>Date release_date</em></li>
         <li><em>Time duration</em></li>
         <li><em>int score</em></li>
       </ul>
      </li>
      <li><strong>Genre</strong> 
          <ul>
            <li><em>int id</em></li>
            <li><em>String name</em></li>
          </ul>
      </li>
   </ol>  
  </p>
  <p>
    Clasa folosita pentru crearea conexiuni ceruta de tip singleton este in pachetul <strong>connection</strong> si se
    numeste <strong>ConnectionSingleton</strong>.
  </p> 
  <p>
    Scriptul sql se gaseste in pachetul <strong>sqlscript</strong> si are numele <strong>databases.sql</strong>.
  </p>  
  <p>
    Au fost create doua exceptii in pachetul <strong>exceptions</strong>
    <ul> 
      <li><em>NoGenreException</em></li>
      <li><em>NoMovieException</em></li>
    </ul>  
  </p>  
  <p>
    Clasele DAO se afla in pachetul <strong>dao</strong>: 
   <ol>
     <li><strong>Genres</strong>
       <ul>
         <li><em>PreparedStatement insertStmt</em></li>
         <li><em>PreparedStatement getStmtById</em></li>
         <li><em>PreparedStatement getStmtByName</em></li>
         <li><em>void InsertGenre(Genre genre) throws NoGenreException</em></li>
         <li><em>Genre getGenreById(int id) throws  NoGenreException</em></li>
         <li><em>Genre getGenreByTitle(String title) throws  NoGenreException</em></li>
       </ul>
      </li>
      <li><strong>Movies</strong> 
          <ul>
             <li><em>PreparedStatement insertStmt</em></li>
             <li><em>PreparedStatement getStmtById</em></li>
             <li><em>PreparedStatement getStmtByName</em></li>
             <li><em>void InsertMovie(Movie movie) throws NoMovieException</em></li>
             <li><em>Movie getMovieById(int id) throws  NoMovieException</em></li>
             <li><em>Movie getMovieByTitle(String title) throws  NoMovieException</em></li>
          </ul>
      </li>
   </ol>  
  </p>
  <p>
  Fiecare functionalitate a claselor DAO a fost testata in clasa <strong>Main</strong> in metoda <em>main</em>.
  </p>
  </body>
</html>
