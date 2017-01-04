
import java.util.Random;
import java.util.Scanner;




public class ComparingSorts {
	static int min;
	static int max; 
	static int length;
	static long bbstart;
	static long instart;
	static long slcstart;
	static long heapstart;
	static long bbtime;
	static long intime;
	static long slctime;
	static long heaptime;
	static String fastest;
	static long besttime;
	static int H;

	public static int[] getList(int min, int max, int length){	//makes an array of random integers
		int list [] = new int [length];							//the range of the values and the size of the array is 
		Random r = new Random();								//determined by user

		for(int i = 0; i < length;i++){
			int result = r.nextInt(max-min) + min;
			list[i] = result;
			}			
		return list;
	}
	
	public static void HeapSort(int array[]){    //heap sorting method
			printNumbers(array);   
	    	heapstart = System.nanoTime();
	    	heapify(array);        
	        for (int i = H; i > 0; i--){
	            swap(array,0, i);
	            H = H-1;
	            maxheap(array, 0);
	        }
	        printNumbers(array);
	    }     

	public static void heapify(int array[]){
	        H = array.length-1;
	        for (int i = H/2; i >= 0; i--){
	            maxheap(array, i);        
	        }
	    }

	public static void maxheap(int array[], int i){ 
	        int left = 2*i ;
	        int right = 2*i + 1;
	        int max = i;
	        if (left <= H && array[left] > array[i]){
	            max = left;
	            }
	        if (right <= H && array[right] > array[max]){        
	            max = right;
	            }
	        if (max != i){
	            swap(array, i, max);
	            maxheap(array, max);
	        }
	    }    
	
	public static void swap(int array[], int i, int j){	//swaps values from on index to another
	        int tmp = array[i];
	        array[i] = array[j];
	        array[j] = tmp; 
	    }    
	
	public static int[] InsertionSort(int[] array){		//insertion sort method
        printNumbers(array);
        instart = System.nanoTime();
        int temp;
        for (int i = 1; i < array.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(array[j] < array[j-1]){
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
        }
        return array;
    }
	
	public static int[] SelectionSort(int[] array){		//selection sort method
         printNumbers(array);
	     slcstart = System.nanoTime();  
         for (int i = 0; i < array.length - 1; i++){
	            int index = i;
	            for (int j = i + 1; j < array.length; j++){
	                if (array[j] < array[index]){
	                    index = j;
	                }
	            }    
	            int smallerNumber = array[index]; 
	            array[index] = array[i];
	            array[i] = smallerNumber;
	        }
	        return array;
	    }
	
	
	public static void BubbleSort(int array[]) {		//bubblesort
	        printNumbers(array);
			bbstart = System.nanoTime();
	        int n = length;
	        int k;
	        for (int m = n; m >= 0; m--) {
	            for (int i = 0; i < n - 1; i++) {
	                k = i + 1;
	                if (array[i] > array[k]) {
	                	swap(array,i,k);
	                }
	            }
	            //printNumbers(array);
	        }
	        printNumbers(array);
	}
	
	  
	    public static void printNumbers(int[] array) {		//prints elements of the array
	          
	        for (int i = 0; i < array.length; i++) {
	            System.out.print(array[i] + ", ");
	        }
	        System.out.println("\n");
	    }
	
	
	
	    
	    
	    public static void main(String[] args) {
	    System.out.println("ENTER:(MIN OF RANGE,MAX OF RANGE, LENGTH OF ARRAYS)");
	    Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();

		String s = input.substring(input.lastIndexOf("(")+1, input.lastIndexOf(")"));
		String a[] = s.split(",");
		min = Integer.parseInt(a[0]);
		max = Integer.parseInt(a[1]);
		length = Integer.parseInt(a[2]);

		
		System.out.println("Bubble Sort");
		BubbleSort(getList(min,max,length));
		bbtime = System.nanoTime() - bbstart;
		System.out.println("Bubble Sort time: "+bbtime);
		fastest = "Bubble Sort";
		besttime = bbtime;
		System.out.println();

		System.out.println("Selection Sort");
		int[] arr2 = SelectionSort(getList(min,max,length));
		printNumbers(arr2);
		slctime = System.nanoTime() - slcstart;
		System.out.println("Selection Sort time: "+slctime);
		if(slctime < besttime){
			besttime = slctime;
			fastest = "Selection Sort";
		}
		System.out.println();

		System.out.println("Insert Sort");
		int[] arr3 = InsertionSort(getList(min,max,length));
		printNumbers(arr3);
		intime = System.nanoTime() - instart;
		System.out.println("Insertion Sort time: "+intime);
		if(intime < besttime){
			besttime = intime;
			fastest = "Insertion Sort";
		}
		System.out.println();

		System.out.println("Heap Sort");
		HeapSort(getList(min,max,length));
		heaptime = System.nanoTime() - heapstart;
		System.out.println("Heap Sort time: "+heaptime);
		if(heaptime < besttime){
			besttime = heaptime;
			fastest = "Heap Sort";
		}
		System.out.println();

		
		System.out.println("The fastest sorting algorithm was "+fastest+" with a time of "+besttime+" nanoseconds.");
	    }
		
}