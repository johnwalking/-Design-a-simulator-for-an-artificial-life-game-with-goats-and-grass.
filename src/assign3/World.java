package assign3;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The World class is used to represent the world for simlation of artificial life
 * with goats and grasses. The world of fixed size (20x35) is expected to be 
 * initialized with the number of passes and the seed for the random number generator 
 * used for the simulation. The class will provide a mainLoop method with an argument 
 * about the interval for displaying the world to the standard output. 
 * The world consists of a 2D grid, in each of which, a creature (a grass or a goat) 
 * may reside. If no creature resides, the slot will be null.   
 * @author li
 *
 */

public class World {

    // TODO: put your data fields here
    
    /**
     * The constructor of the World containing two parameters:
     * @param numPasses number of passes used for the whole simulation
     * @param seed the seed to initialize the random number generator for the simulation
     */
    int Row = 20;
    int Column = 35;
    int dayPasses;
    
    // public Creatrue(int initialize, int startPreg, int EndPreg, int Dead,int seed){
    Creature[][] map = new Creature[20][35];
     
    public World(int numPasses, int seed) {
	    // TODO: put your code here
	    // initialize the world map
	    for(int i = 0 ; i < Row; i++){
	        for(int j = 0 ; j <Column; j++){
	            map[i][j] =  new Creature();
	            map[i][j].setSeed(seed);  
	        }
	    }
	    Random rand = new Random(seed);
	    for(int i= 0; i <5; i++){
	        int r = (int) (Math.random()*1000) %Row;
	        int c = (int) (Math.random()*1000) % Column;
	        if(map[r][c].det ==1){i-=1;}
	        else{
	            map[r][c] =  new Goat(20,50, 55, 70);
            }
	        // System.out.print("Goat");
	    }
	    for (int i = 0; i < 10; i++) {
            int r = (int) (Math.random() * 1000) % Row;
            int c = (int) (Math.random() * 1000) % Column;
            if((map[r][c] instanceof Goat)== false){
                map[r][c] = new Grass(3,5,6);
            }else{
                i--;
            }
            // System.out.print("Goat");
        }
        dayPasses = numPasses; 
        PrintWorld();
        // System.out.println("the days of 1");
	
    }
    public void onedayAfter(){
        for(int i=0 ; i< Row; i++){
            for(int j = 0; j <Column; j++){
                map[i][j].used = false;
            }
        }
        for(int i=0 ; i< Row; i++){
            for(int j = 0; j <Column; j++){
                if(map[i][j].used == true){continue;}
                //判斷老化;
                if((map[i][j] instanceof Goat)||(map[i][j] instanceof Grass)){
                    //老化
                    map[i][j].life +=1;
                    if(map[i][j].isDead()== true){
                        // System.out.println("die");
                        map[i][j] = new Creature();
                    }
                }
                //需要懷孕嗎
                if((map[i][j].det==1)||(map[i][j].det ==2)){
                    if(map[i][j].isPregnant()){
                        // System.out.println(i+","+j+" is pregnant");
                        int dir = map[i][j].getDirection(); 
                        int[] x_axis = { 0, 1,  0, -1 };
                        int[] y_axis = { 1, 0, -1,  0};
                        if((j+x_axis[dir])>=0&&(j+x_axis[dir]<= 34)&&(i+y_axis[dir]>=0)&&(i+y_axis[dir]<=19)){
                            if(map[i][j].det == 1){
                                //Goat born  on Creature
                                if(map[i + y_axis[dir]][j + x_axis[dir]].det == 0){ 
                                    map[i+y_axis[dir]][j+x_axis[dir]] = new Goat(20, 50, 55, 70);
                                }
                                //Goat born on Grass
                                else if(map[i + y_axis[dir]][j + x_axis[dir]].det == 1){ 
                                    map[i+y_axis[dir]][j+x_axis[dir]] = new Goat(23, 50, 55, 70);
                                }
                            }
                            
                            if(map[i][j].det == 2){
                                if (map[i + y_axis[dir]][j + x_axis[dir]].det == 0) {
                                    map[i + y_axis[dir]][j + x_axis[dir]] = new Grass(3,5,6);
                                } 
                            }
                            // System.out.println(i + "," + j + " start to born on " + (i + y_axis[dir]) + "," + (j + x_axis[dir]));
                            map[i + y_axis[dir]][j + x_axis[dir]].used = true; 
                        }
                        map[i][j].used = true;
                        
                    }else{// to do the move
                        // System.out.print(i+","+j+" start to move");
                        int dir = map[i][j].getDirection(); 
                        int[] x_axis = { 0, 1,  0, -1 };
                        int[] y_axis = { 1, 0, -1,  0};
                        if((j+x_axis[dir])>=0&&(j+x_axis[dir]<= 34)&&(i+y_axis[dir]>=0)&&(i+y_axis[dir]<=19)){
                            if(map[i][j].det == 1){//Goats
                                if(map[i + y_axis[dir]][j + x_axis[dir]].det == 2){
                                    map[i][j].food_point +=3;
                                    map[i + y_axis[dir]][j + x_axis[dir]] = new Goat(20, 50, 55, 70);
                                    map[i + y_axis[dir]][j + x_axis[dir]].food_point = map[i][j].food_point;
                                    map[i + y_axis[dir]][j + x_axis[dir]].life = map[i][j].life;
                                    map[i][j] = new Creature();
                                }
                                if (map[i + y_axis[dir]][j + x_axis[dir]].det == 0) {;
                                    map[i + y_axis[dir]][j + x_axis[dir]] = new Goat(20, 50, 55, 70);
                                    map[i + y_axis[dir]][j + x_axis[dir]].food_point = map[i][j].food_point;
                                    map[i + y_axis[dir]][j + x_axis[dir]].life = map[i][j].life;
                                    map[i][j] = new Creature();
                                }
                                // System.out.println(i + "," + j + " start to move " + (i + y_axis[dir]) + "," + (j + x_axis[dir]));
                            } 
                            
                            map[i + y_axis[dir]][j + x_axis[dir]].used = true; 
                            
                        }else{
                            map[i][j].used = true;
                        }                   
                    }
                    if (map[i][j].det == 1) {
                        map[i][j].food_point -= 1;
                    }
                }
            }
        }
    } 
    public void PrintWorld() {
        System.out.print("  ");
        for(int i=0;i<Column;i++){;
             System.out.print(i%10+" ");
        }
        System.out.println("");
        
        for(int i=0;i<Row;i++,System.out.println("")){
            // cout << i%10 << " ";
            System.out.print(i%10+" ");
            for(int j=0;j<Column;j++){
                if (map[i][j] instanceof Goat) {
                    System.out.print("X"+" ");
                    // System.out.println(map[i][j]);
                }
                else if(map[i][j] instanceof Grass){
                    System.out.print("I"+" ");     
                }
                else if(map[i][j] instanceof Creature){
                    System.out.print("  ");
                }
                
             }
        }
    
        for(int i=0;i<Column+1;i++){
            System.out.print("--");
        }
        System.out.println("");
    }

    /** the main loop use for the simulation and showing the result to the screen 
     * periodically.
     * @param displayInterval the number of passes for each display of the world
     */
    
    public void mainLoop(int displayInterval) {

	// TODO: put your code here
	    for(int i=2;i<=dayPasses;i++){
            onedayAfter();
            if( (i-1) % displayInterval == 0 ){
                PrintWorld();
                // System.out.println("the days of " + i);
            }
            // System.out.println("the days of "+i);
        }
    }
    
}
