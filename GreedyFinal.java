public class GreedyFinal
{	
	private static String parPent;
	private static String[] order;
	private static Container2 box = new Container2(); 
	private static int[] counter = new int[3];
	//private static ArrayList<Parcel> list
	
	private static double totalVolume = 0;
	private static double totalValue = 0;
	
	public GreedyFinal(String type, String whichorder){
	parPent = type;
	order = orderType(whichorder);
	checkFit(0);
	}
	
	public static void main(String[] args){
	long time = System.nanoTime();	
		//Method which orders parcels based on maximum value/volume/density
			
	GreedyFinal greedy = new GreedyFinal("parcel" , "value");
		
	for(int i = 0; i<3; i++){
		System.out.println( order[i] + ": " + counter[i]);
		
	}
	
	System.out.println("Volume: " + totalVolume);
	System.out.println("Value: " + totalValue);
	
	
	System.out.println(System.nanoTime() - time);
	//box.printList();
	 }
	 
	 
	//method which checks if most optimal parcel fits and puts it into container
	public static void checkFit(int i){
		if(i >= 3){
		return;
		}
		else{
			Parcel p = new Parcel(order[i]);
			if(box.relevant(p)){
			counter[i]++;
			totalVolume += p.getVolume();
			totalValue += p.getValue();
			checkFit(i);
		}
		else
			checkFit(i+1);
	}
	}
	
	
	//method which gets the values relevant to what we want to maximize
	public static String[] orderType(String type){
    	
    	double[] criteria = new double[3];
    	
    	if(type == "density"){
    		if(parPent == "parcel"){
    		criteria[0] = (new Parcel("A")).getRate();
    		criteria[1] = (new Parcel("B")).getRate();
    		criteria[2] = (new Parcel("C")).getRate();	
    	}
    		if(parPent == "pentomino"){
    		criteria[0] = (new Parcel("L")).getRate();
    		criteria[1] = (new Parcel("P")).getRate();
    		criteria[2] = (new Parcel("T")).getRate();	
    	
    	}}
    	
    	if(type == "value"){
    		if(parPent == "parcel"){
    		criteria[0] = (new Parcel("A")).getValue();
    		criteria[1] = (new Parcel("B")).getValue();
    		criteria[2] = (new Parcel("C")).getValue();
    		}
    		if(parPent == "pentomino"){
    		criteria[0] = (new Parcel("L")).getValue();
    		criteria[1] = (new Parcel("P")).getValue();
    		criteria[2] = (new Parcel("T")).getValue();
    	}}
    	
    	if(type == "volume"){
    		if(parPent == "parcel"){
    		criteria[0] = (new Parcel("A")).getVolume();
    		criteria[1] = (new Parcel("B")).getVolume();
    		criteria[2] = (new Parcel("C")).getVolume();
    		}
    		if(parPent == "pentomino"){
    		criteria[0] = (new Parcel("L")).getVolume();
    		criteria[1] = (new Parcel("P")).getVolume();
    		criteria[2] = (new Parcel("T")).getVolume();
    	}}
    	String[] parcelType = new String[3];
    	
    	if(parPent == "parcel"){
    		parcelType[0] = "A" ;
    		parcelType[1] = "B" ;
    		parcelType[2] = "C" ;
    	}
    	if(parPent == "pentomino"){
    		parcelType[0] = "L" ;
    		parcelType[1] = "P" ;
    		parcelType[2] = "T" ;
    	}
    		
    		String[] order = parcelOrder(criteria, parcelType);
    		for(int i = 0; i<3; i++){
    			System.out.println( (i+1) + ". " + order[i]);
    		}
    		return order;
    
    }
    
    //places parcels in order 
    public static String[] parcelOrder(double[] array, String[] type){
    		
    	double min = 100;
    	double max = 0;
    	double mid = 0;
    	
    	String typeMax = null;
    	String typeMin = null;
    	String typeMid = null;
    		
    	for(int i = 0; i<3; i++){
    		if( array[i] >= max){
    			if( array[i] > max){
    				max = array[i];
    				typeMax = type[i];
    			}
    			else{
    				mid = array[i];
    				typeMid = type[i];
    		}}
    		if( array[i] <= min){
    			if( array[i] < min){
    			min = array[i];
    			typeMin = type[i];
    			}
    			else{
    				mid = array[i];
    				typeMid = type[i];
    		}
    	}}
    	
    	if(max == min){
    		typeMax = type[0];
    		typeMid = type[1];
    		typeMin = type[2];
    		
    		}
    		
    		for(int i = 0; i<3; i++){	
    	if( array[i] < max && array[i] > min){
    			System.out.println("Mid: " + array[i]);
    			mid = array[i];
    			typeMid = type[i];
    	}}
    		
    	String[] order = { typeMax, typeMid, typeMin};	
    	
    	return order;
    		
    }
    
    public static int getFirstCoordinate(int i, int j){
    	
    	int coord = box.getCoordinates(i, j);
    	return coord;
    
    }
    
    public static Parcel getParcelList(int i){
    	Parcel list = box.getList(i);
    	return list;
    
    }





}