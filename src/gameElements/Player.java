package gameElements;

/**
 * Created by simon on 17/02/17.
 */
public class Player {
	
	private int number;
	private String colorMode;
	
	public Player(int number){
		this.number = number;
		this.colorMode = "bw";
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getColorMode() {
		return colorMode;
	}

	public void setColorMode(String colorMode) {
		this.colorMode = colorMode;
	}
	
	public String toString(){
		
		if(this.number == 0){
			if(this.colorMode == "bw"){
				return "white";
			}
			else{
				return "green";
			}
		}
		//colorMode == 1
		else{
			if(this.colorMode == "bw"){
				return "black";
			}
			else{
				return "orange";
			}
		}
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return number == player.number;

    }

    @Override
    public int hashCode() {
        return number;
    }
}
