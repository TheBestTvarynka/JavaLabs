package kpi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DirectoryScanner {
    String dir;
    ExecutorService executor = Executors.newFixedThreadPool(30);

    DirectoryScanner(String dir) {
        this.dir = dir;
    }

    Runnable countForOperators(File file) {
        return () -> {
            try {
                int fors = Files.lines(Paths.get(file.getPath()))
                        .mapToInt(str -> {
                            int forCount = 0;
                            int startIndex = str.indexOf("for");
                            while (startIndex != -1) {
                                forCount++;
                                startIndex = str.indexOf("for", startIndex + 3);
                            }
                            return forCount;
                        })
                        .sum();
                System.out.println(file.getPath() + " -> " + fors);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    Runnable scanSubdir(String dir) {
        return () -> {
            try {
                System.out.println("Start looking in " + dir);
                Files.list(Paths.get(dir))
                      .map(Path::toFile)
                      .forEach(file -> {
                          if (file.isDirectory()) {
                              executor.submit(scanSubdir(file.getAbsolutePath()));
                          } else {
                              String fileName = file.getName();
                              if (fileName.endsWith(".c") || fileName.endsWith(".h")) {
                                  executor.submit(countForOperators(file));
                              }
                          }
                      });
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        };
    }

    public void scan() {
        executor.submit(scanSubdir(dir));
//        executor.shutdown();
    }
}
