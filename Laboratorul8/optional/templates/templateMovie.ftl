<html>
    <head>
        <title>Movies Report</title>
    </head>
    <body>
        <h1>Report for Movies</h1>
        <ul>
            <#list movie as movies>
                <h3 style="color: purple;">Angajat</h3>
                <li> Id: ${movies.getId()} </li>
                <li> Titlu: ${movies.getTitle()} </li>
                <li> Data lansarii: ${movies.getRelease_date()} </li>
                <li> Durata: ${movies.getDuration()} </li>
                <li> Scorul: ${movies.getScore()} </li>
            </#list>
        </ul>
    </body>
</html>