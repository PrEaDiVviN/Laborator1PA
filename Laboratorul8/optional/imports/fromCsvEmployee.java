package compulsory.imports;

import com.opencsv.CSVReader;
import compulsory.model.Employee;

import javax.sound.midi.Patch;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fromCsvEmployee {

    public static List<Employee> readEmployeesFromCSV(String fileName) {
        List<Employee> employees = new ArrayList<>();
        Employee toAddEmployee;
        String name, birthName, birthDate;
        int height;
        int index = 0;

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> read = reader.readAll();
            for (String [] line: read) {
                if (index != 0) {
                    name = line[1];
                    birthName = line[2];
                    if (line[3].length() > 0)
                        height = Integer.parseUnsignedInt(line[3]);
                    else
                        height = -1;
                    if (line[6].length() > 0)
                        birthDate = line[6];
                    else
                        birthDate = "####";
                    toAddEmployee = new Employee(index,name,birthName,height,birthDate,"unknown");
                    employees.add(toAddEmployee);
                }
                index++;
                if(index == 100)
                    break;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

}
