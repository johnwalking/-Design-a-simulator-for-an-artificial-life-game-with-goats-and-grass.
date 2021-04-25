package assign3;
public class Goat extends Creature{ 
    public Goat(int initialize, int startPregnant, int endPregnant, int Dead){
        food_point = initialize; // 起始生命
        stPreg = startPregnant; // 可生育
        endPreg = endPregnant; // 不能生育
        dead  = Dead; // 死
        det = 1; // 判斷用
        life =0 ; // 起始生命
    }
    
}
