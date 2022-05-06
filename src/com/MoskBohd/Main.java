package com.MoskBohd;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {


        //----------------------------- SCANNERS
        LocalDateTime start = LocalDateTime.now();

        Scanner scanner = new Scanner(new File("logs.txt"));
        String out = "";
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            if(line.contains("2019-10-13")){
                out += line + System.lineSeparator();
            }
        }
        Files.writeString(Path.of("outSCANNERS.txt"), out);
        System.out.println(ChronoUnit.MILLIS.between(start, LocalDateTime.now()));


        //1. Parse the file logs.txt (see the attachment).
        // Extract to a separate file all the logs.
        //-------------------------- STREAMS

        StringBuilder stringBuilder = new StringBuilder("");

        start = LocalDateTime.now();
        Files.lines(Paths.get("logs.txt"))
                .filter(line->line.contains("\\d\\d\\d\\d-\\d\\d-\\d\\d"))
                .forEach(line->stringBuilder.append(line + System.lineSeparator()));

        Path path1 = Paths.get("outSTREAMS" + ".log");
        Files.write(path1, Collections.singleton(stringBuilder));
        System.out.println(ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
        
        //2. Calculate the total number of logs (lines).
        int logsAmount = (int) Files.lines(Paths.get("logs.txt"))
                .filter(line->line.contains("2019-10-13"))
                .count();
        System.out.println(logsAmount);

        //3. Calculate the total  number of  ERROR logs. Use previous skills - String.split

        //--------------------------------OUT OF MEMORIES
        /*String longAsString = new String(Files.readAllBytes(Paths.get("logs.txt")));
        String[] lines = longAsString.split(System.lineSeparator());
        List<String> strings = Arrays.stream(lines)
                .filter(line->line.contains("2819-10-13"))
                .collect(Collectors.toList());
        String outs = "";
        for (int i = 0; i < strings.size(); i++){
            outs += strings.get(i) + System.lineSeparator();
        }
        Path path = Paths.get("outSTRINGS.txt");
        Files.write(path, outs.getBytes(StandardCharsets.UTF_8));

        System.out.println(ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
        */

        //4.  Calculate the total number of ERROR logs. Use Files.lines.
        int errorsAmount = (int)Files.lines(Paths.get("logs.txt"))
                .filter(line->line.contains("2019-10-13"))
                .filter(line -> line.contains("ERROR"))
                .count();
        System.out.println(errorsAmount);

        //5. Compare two results.
    }
}
