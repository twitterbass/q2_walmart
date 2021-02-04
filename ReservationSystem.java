package movie_theater;  
import java.io.*; 
import java.util.*; 

public class ReservationSystem {
  private class Node {
    String id; 
    int num; 

    public Node(String id, int num) {
      this.id = id; 
      this.num = num; 
    }
  }
  private final String[] symbols = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
  private final int ROWS = 10; 
  private final int COLS = 21; 
  private char[][] seats;

  public ReservationSystem() {
    seats = new char[ROWS][COLS];
    for (int i = 0; i < ROWS; ++i) {
      for (int j = 1; j < COLS; ++j) {
        seats[i][j] = '_';
      }
    } 
  }

  private Queue<Node> handleInput(String inputPath) {
    Queue<Node> q = new LinkedList<>(); 

    try {
      File myFile = new File(inputPath); 
      Scanner reader = new Scanner(myFile); 
      while (reader.hasNextLine()) {
        String[] data = reader.nextLine().split(" "); 
        String id = data[0]; 
        int num = Integer.parseInt(data[1]); 
        q.add(new Node(id, num)); 
      }
      reader.close(); 
    } catch (FileNotFoundException e) {
      System.out.println("Error when reading file."); 
      e.printStackTrace(); 
    }

    return q; 
  }

  private String writeOutput(List<String> result) {
    String outPath = System.getProperty("user.dir") + "/output.txt"; 
    
    try {
      File outFile = new File(outPath); 
      FileWriter writer = new FileWriter(outPath); 
      for (String line : result) {
        writer.write(line); 
      }
      writer.close(); 
    } catch (IOException e) {
      System.out.println("Error when writing file.");
      e.printStackTrace(); 
    }

    return outPath; 
  }

  public String findSeats(int num) {
    for (int i = 0; i < ROWS; ++i) {
        int count = 0; 
        for (int j = 1; j < COLS; ++j) {
          if (seats[i][j] == '_') {
            ++count; 
            if (count == num) {
              String result = ""; 
              int start = j - count + 1; 
              for (int k = start; k <= j; ++k) {
                seats[i][k] = 'X'; 
                result += (symbols[i] + k + " ");

                //safety rule 
                if (i + 1 < ROWS) seats[i+1][k] = '*';
              }

              // safety rule
              for (int k = 1; k <= 3; ++k) {
                if (k + j < COLS) seats[i][k+j] = '*';
                if (i + 1 < ROWS && k + j < COLS) seats[i+1][k+j] = '*';
              }

              return result; 
            }
          } else {
            count = 0; 
          } 
        }
      }

      return ""; 
  }

  public void print() {
    for (int i = 0; i < ROWS; ++i) {
      for (int j = 1; j < COLS; ++j) {
        System.out.print(seats[i][j] + " "); 
      }
      System.out.println(); 
    }
  }

  public String processReservations(String inputPath) {
    Queue<Node> q = handleInput(inputPath); 

    List<String> result = new ArrayList<>(); 
    while (!q.isEmpty()) {
      Node cur = q.poll(); 
      int num = cur.num; 

      String sub = cur.id + " " + findSeats(num) + "\n"; 
      result.add(sub);  
    }

    String outputPath = writeOutput(result); 

    return outputPath; 
  }
}