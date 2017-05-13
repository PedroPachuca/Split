
import java.util.ArrayList;

public class Polygon {
        
    private static ArrayList<Wall> walls = new ArrayList<Wall>();
    
    public Polygon(){
        
    }
    
    public void add(Wall wall){
        walls.add(wall);
    }
    
    public ArrayList<Wall> walls(){
        return walls;
    }
    
    public static boolean inside(int x, int y){
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
    
    public Polygon split(int x1,int y1,int x2,int y2, int ballX1, int ballX2, Wall divider1, Wall divider2){
    	Wall wall1 = null;
    	Wall wall2 = null;
    	Wall wall1a = null;
    	Wall wall1b = null;
    	Wall wall2a = null;
    	Wall wall2b = null;
    	Polygon p1=new Polygon();
    	Polygon p2=new Polygon();
    	for(Wall wall: walls){
    		if(wall.inside(x1, y1))
    			wall1=wall;
    		else if(wall.inside(x2, y2)){
    			wall2=wall;
    		}

    	}
    	if(wall1==null||wall2==null){
    		return null;
    	}
    	if(wall1.getWidth()==10){
    		wall1a = new Wall(wall1.getX(),wall1.getY(), 10,y1-wall1.getY()+10);
    		wall1b = new Wall(wall1.getX(),y1,20,wall1.getY()+wall1.getLength()-y1);
    	}
    	else if(wall1.getLength()==10){
    		wall1a = new Wall(wall1.getX(),wall1.getY(), x1-wall1.getX()+10,10);
    		wall1b = new Wall(x1,wall1.getY(),wall1.getX()+wall1.getWidth()-x1,10);
    	}
    	if(wall2.getWidth()==10){
    		wall2a = new Wall(wall2.getX(),wall2.getY(), 10,y2-wall2.getY()+10);
    		wall2b = new Wall(wall2.getX(),y2,10,wall2.getY()+wall2.getLength()-y2);
    	}
    	else if(wall2.getLength()==10){
    		wall2a = new Wall(wall2.getX(),wall2.getY(), x2-wall2.getX()+10,10);
    		wall2b = new Wall(x2,wall2.getY(),wall2.getX()+wall2.getWidth()-x2,10);
    		System.out.println(wall2a.getWidth());
    	}
    	if(x1==x2){
    		for(Wall wall: walls){
    			if(wall.getX()!=wall1.getX()||wall.getY()!=wall1.getY()||wall.getLength()!=wall1.getLength()||wall.getWidth()!=wall1.getWidth()){
    				if(wall.getX()!=wall2.getX()||wall.getY()!=wall2.getY()||wall.getLength()!=wall2.getLength()||wall.getWidth()!=wall2.getWidth()){
    					if(wall.getX()<=x1){
    						p1.add(wall);
    					}
    					else{
    						p2.add(wall);
    					}
    				}
    			}
    		}
    		p1.add(wall1a);
    		p1.add(wall2a);
    		p2.add(wall1b);
    		p2.add(wall2b);
    		p1.add(divider1);
    		p2.add(divider1);
    		if(divider2!=null){
    			p1.add(divider2);
    			p2.add(divider2);
    		}
    		if(p1.inside(ballX1,ballX2)){
    			return p1;
    		}
    		else{
    			return p2;
    		}
    	}
    	else if(y1==y2){
    		for(Wall wall: walls){
    			
    		}
    	}
    	else if((x1>x2&&y1>y2)||(x2>x1&&y2>y1)){
    		
    	}
    	else{
    		
    	}
    	return null;
    	
    }
    
    public void expand(){
    	
    }


}
