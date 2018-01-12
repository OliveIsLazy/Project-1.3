public class TestContainer{

	public static void main(String[] args){
	
	Container box = new Container(8, 5, 33);
	//Parcel A = new Parcel("A");
	//Parcel B = new Parcel("B");
	//Parcel C = new Parcel("C");
	
	if(box.relevant(new Parcel(random()))){
	System.out.print("it works!");
	}
	
	
	}
	
	public boolean fit(Parcel[] p){
	for(int i = 0; i< p.length; i++){
	if(box.relevant(p[i]) == false)
		return false; 
	}
	return true;
	}
	
	public order(
	
	public static String random(){
	int i = (int)(3*Math.random());
	if( i == 0)
		return "A";
	else if( i == 1)
		return "B";
	else if(i == 2)
		return "C";
	return "";
	}




}