import movie_theater.ReservationSystem; 

public class Solution {
  
  public static void main(String[] args) {

    ReservationSystem r = new ReservationSystem(); 
    String result = r.processReservations("input.txt");
    System.out.println("Output: " + result); 
    System.out.println("After handling reservation: "); 
    r.print();

  }
}