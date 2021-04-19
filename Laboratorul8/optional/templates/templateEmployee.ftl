<html>
    <head>
        <title>Employee Report</title>
    </head>
    <body>
        <h1>Report for Employees</h1>
        <ul>
            <#list employee as employees>
                <h3 style="color: blue;">Angajat</h3>
                <li> Id: ${employees.getId()} </li>
                <li> Nume: ${employees.getName()} </li>
                <li> Numele dat la nastere: ${employees.getBirthName()} </li>
                <li> Inaltime: ${employees.getHeight()} </li>
                <li> Data nasterii: ${employees.getBirthDate()} </li>
            </#list>
        </ul>
    </body>
</html>