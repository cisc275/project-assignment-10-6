
public abstract class Sprite {
	public char self;
	public int xloc;
	public int yloc;
	public Lane lane;
	public char getSelf() {return self;}
	public boolean alive;
	
	Sprite(){
		this.lane = Lane.Bottom;
		this.self = 'X';
	}
	
	Sprite(Lane l, char s){
		this.lane = l;
		this.self = s;
	}
	public void setAlive(boolean a) {this.alive = a;}
	public Lane getLane() {return this.lane;}
	public String toString() {
		return String.valueOf(this.self);
	}
	public int getXloc() {
		return this.xloc;
	}
	
}
