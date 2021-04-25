package assign3;
import java.util.Random;

public class Creature{
    int food_point = 10000000; // 起始食物點數
    int stPreg = 0; // 可生育
    int endPreg = 0; // 不能生育
    int dead = 10000; // 死亡
    int life = 0 ; // 起始生命
    int det;
    boolean used;
    Random random;

    public Creature(){
        det = 0; // 判斷用
    }
    public void setSeed(int seed){
        random = new Random(seed);
        // System.out.println(seed);
    } 
    public int getDirection() {
        int ret = (int) (Math.random()*1000)%4;
        // System.out.println("random"+ret);
        return ret;
    }
    public boolean isDead(){
        if(life >= dead || food_point <=0 ){
            return true;
        }else{
            return false;
        }
    }
    public boolean isPregnant(){
        if(life<=endPreg&& life >= stPreg){
            return true; 
        }
        return false;
    }
}
