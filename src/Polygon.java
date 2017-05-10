
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
    
    public void split(int x1,int y1,int x2,int y2, int ballX1, int ballX2){
    	Wall wall1 = null;
    	Wall wall2 = null;
    	Polygon p1=new Polygon();
    	Polygon p2=new Polygon();
    	for(Wall wall: walls){
    		if(wall.inside(x1, y1))
    			wall1=wall;
    		else if(wall.inside(x2, y2))
    			wall2=wall;

    	}
    	if(wall1==null||wall2==null)
    		return;
    	if(x1==x2){
    		
    	}
    	else if(y1==y2){
    		
    	}
    	else if((x1>x2&&y1>y2)||(x2>x1&&y2>y1)){
    		
    	}
    	else{
    		
    	}
    	
    }
    
    public void expand(){
    	
    }


}
