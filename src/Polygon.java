
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
    
    public boolean inside(int x, int y){
        boolean up=false;
        boolean down=false;
        boolean right=false;
        boolean left=false;
        for(Wall wall:walls){
            if(x>wall.getX()&&x<wall.getX()+wall.getWidth()){
            	if(!(y>wall.getY()&&y<wall.getY()+wall.getLength())){
            		if(y<wall.getY()){
            			up=true;
            		}
            		else{
            			down=true;
            		}
            	}
            }
            else if(y>wall.getY()&&y<wall.getY()+wall.getLength()){
            	if(!(x>wall.getX()&&x<wall.getX()+wall.getWidth())){
            		if(x<wall.getX()){
            			right=true;
            		}
            		else{
            			left=true;
            		}
            	}
            }
        }
        if(up&&down&&right&&left){
        	return true;
        }
        else{
        	return false;
        }
        
    }
    
    public void split(int x1,int y1,int x2,int y2){
    	Polygon p1=new Polygon();
    	Polygon p2=new Polygon();
    	for(Wall wall: walls){
    		
    	}
    }
    
    public void expand(){
    	
    }


}
