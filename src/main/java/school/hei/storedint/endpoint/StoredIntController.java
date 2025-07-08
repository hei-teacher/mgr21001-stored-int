package school.hei.storedint.endpoint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoredIntController {

  private static final Path FILE_PATH = Paths.get("/tmp/stored-int.txt");

  @GetMapping("/stored-int")
  public String getStoredInt() throws InterruptedException, IOException {
    for (long i = 0; i < 1L * Integer.MAX_VALUE; i++)
      ;
    if (Files.exists(FILE_PATH)) {
      String content = Files.readString(FILE_PATH).trim();
      return "Stored value: " + content;
    } else {
      int randomInt = new Random().nextInt(1000); // [0,999]
      Files.writeString(
          FILE_PATH,
          Integer.toString(randomInt),
          StandardOpenOption.CREATE,
          StandardOpenOption.WRITE);
      return "Generated and stored: " + randomInt;
    }
  }
}
