<!DOCTYPE html>
<html>
  <headL>
    <style>
      ol {
      counter-reset: item
      }
      li {
        display: block
      }
      li:before {
        content: counters(item, ".") " ";
        counter-increment: item
      }
    </style>
  </head>  
  <body>
  <p>
    Am implementat toate cele 4 clase care extind clasa Item.<br>
    <strong>Item -></strong><br>
    <ol>
      <li>Image
          <ol>
            <li>String description</li>
            <li>String author</li>
            <li>Date dateStamp</li>
            <li>int height</li>
            <li>int height</li>
          </ol>
      </li>
      <li>Song</li>
      <li>Book</li>
      <li>Movie</li>
    </ol>
  </p>
  </body>
</html>
