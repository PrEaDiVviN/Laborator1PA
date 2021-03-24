<html>
<head>
  <title>Catalog Report</title>
</head>
<body>
  <h1>Catalog name: ${title}</h1>
  <p>Catalog path: ${path}</p>
  <ul>
    <h2>Songs:</h2>
    <#list song as songs>
      <li> ${songs_index + 1}. Nume: ${songs.getName()} Id: ${songs.getId()} Locatie: ${songs.getLocation()} Artist: ${songs.getMadeByArtist()} Release date: ${songs.getReleaseDate()} Duration: ${songs.getSongDuration()}</li>
    </#list>
    <h2>Books:</h2>
    <#list book as books>
      <li> ${books_index + 1}. Nume: ${books.getName()} Id: ${books.getId()} Locatie: ${books.getLocation()} Author: ${books.getAuthor()} Release date: ${books.getReleaseDate()} Page number: ${books.getPageNumber()} Rating: ${books.getRating()}</li>
    </#list>
    <h2>Photos:</h2>
    <#list image as images>
      <li> ${images_index + 1}. Nume: ${images.getName()} Id: ${images.getId()} Locatie: ${images.getLocation()} Author: ${images.getAuthor()} Datestamp: ${images.getDateStamp()} Description: ${images.getDescription()} width: ${images.getWidth()} height: ${images.getHeight()}</li>
    </#list>
    <h2>Melodii:</h2>
    <#list movie as movies>
      <li> ${movies_index + 1}. Nume: ${movies.getName()} Id: ${movies.getId()} movies: ${movies.getLocation()} BoxOfficeValue: ${movies.getBoxOffice()} Release date: ${movies.getReleaseDate()} Duration: ${movies.getMovieDuration()} Rating: ${movies.getRating()}</li>
    </#list>
  </ul>

</body>
</html>