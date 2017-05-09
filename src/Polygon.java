
import java.util.ArrayList;

public class Polygon {
        
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    
    public Polygon(){
        
    }
    
    public void add(Wall wall){
        walls.add(wall);
    }
    
    public ArrayList<Wall> walls(){
        return walls;
    }
    
   /* public boolean inside(int x, int y){
        boolean up=false;
        boolean down=false;
        boolean right=false;
        boolean left=false;
        for(Wall wall:walls){
            if(x>wall.getX()&&x<wall.getX()+wall.getWidth()){
            }
        }
        
    }*/


}
