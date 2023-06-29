import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Random;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;




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
	static long mergetime;
	static String fastest;
	static long besttime;
	static long radixstart;
	static long timtime;
	static long quicktime;
	static long shelltime;
	static long buckettime;
	static long temp;
	static int H;

	public ComparingSorts(){

	}

	//RadixSort
	public static void countSort(int arr[], int n, int exp)
    {
        int output[] = new int[n]; 
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);
         for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    public static long RadixSort(int arr[])
    {
		printNumbers(arr);

		radixstart = System.nanoTime();
        int m = getMax(arr, arr.length);
        for (int exp = 1; m / exp > 0; exp *= 10){
            countSort(arr, arr.length, exp);
		}
		printNumbers(arr);

		return System.nanoTime() - radixstart;

    }

	public static int getMax(int arr[], int n)
    {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }


	//HeapSort
	public static long HeapSort(int array[]){    
			printNumbers(array);   
	    	heapstart = System.nanoTime();
	    	heapify(array);        
	        for (int i = H; i > 0; i--){
	            swap(array,0, i);
	            H = H-1;
	            maxheap(array, 0);
	        }
			printNumbers(array);

		    return System.nanoTime() - heapstart;
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
	
  
	//InsertionSort
	public static long InsertionSort(int[] array){		
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
		printNumbers(array);
		return System.nanoTime() - instart;
    }
	
	//SelectionSort
	public static long SelectionSort(int[] array){		
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
			printNumbers(array);

		    return System.nanoTime() - slcstart;
	    }
	
	//BubbleSort
	public static long BubbleSort(int array[]) {		
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
	        }
	       printNumbers(array);
		    return System.nanoTime() - bbstart;
	}

	//MergeSort
	public static void merge(int[] a, int[] l, int[] r, int left, int right) {
 
    int i = 0, j = 0, k = 0;
    while (i < left && j < right) {
        if (l[i] <= r[j]) {
            a[k++] = l[i++];
        }
        else {
            a[k++] = r[j++];
        }
    }
    while (i < left) {
        a[k++] = l[i++];
    }
    while (j < right) {
        a[k++] = r[j++];
    }
}

	public static void mergeSort(int[] a, int n) {
		if (n < 2) {
			return;
		}
		int mid = n / 2;
		int[] l = new int[mid];
		int[] r = new int[n - mid];

		for (int i = 0; i < mid; i++) {
			l[i] = a[i];
		}
		for (int i = mid; i < n; i++) {
			r[i - mid] = a[i];
		}
		mergeSort(l, mid);
		mergeSort(r, n - mid);

		merge(a, l, r, mid, n - mid);
	}

	public static long MergeSortTime(int array[]) {		
	
         printNumbers(array);
	     mergetime = System.nanoTime();  
		mergeSort(array,array.length);
		
		 printNumbers(array);

		return System.nanoTime() - mergetime;	

	}
	
	//TimSort
   public static int MIN_MERGE = 32;

    public static int minRunLength(int n)
    {
        assert n >= 0;
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    public static void insertionSortTim(int[] arr, int left,
                                     int right)
    {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    public static void mergeTim(int[] arr, int l, int m, int r)
    {
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (int x = 0; x < len1; x++) {
            left[x] = arr[l + x];
        }
        for (int x = 0; x < len2; x++) {
            right[x] = arr[m + 1 + x];
        }

        int i = 0;
        int j = 0;
        int k = l;

  
        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            arr[k] = left[i];
            k++;
            i++;
        }

        while (j < len2) {
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    public static void timSort(int[] arr, int n){
        int minRun = minRunLength(MIN_MERGE);

        for (int i = 0; i < n; i += minRun) {
            insertionSortTim(
                arr, i,
                Math.min((i + MIN_MERGE - 1), (n - 1)));
        }
        for (int size = minRun; size < n; size = 2 * size) {

            for (int left = 0; left < n; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1),
                                     (n - 1));


                if (mid < right)
                    mergeTim(arr, left, mid, right);
            }
        }
    }

	public static long timSortTime(int array[]) {	
	
         printNumbers(array);
	     timtime = System.nanoTime();  
		timSort(array,array.length);
		
		 printNumbers(array);

		return System.nanoTime() - timtime;	

	}
	
	//QuickSort
	public static int partition(int[] arr, int low, int high){
        int pivot = arr[high];

        int i = (low - 1);
 
        for (int j = low; j <= high - 1; j++) {
 
            if (arr[j] < pivot) {
                 i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }
 

    public static void quickSort(int[] arr, int low, int high)
    {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

	public static long quickSortTime(int array[]) {		
	
         printNumbers(array);
	     quicktime = System.nanoTime();  
		quickSort(array,0,array.length-1);
		
		 printNumbers(array);

		return System.nanoTime() - quicktime;	

	}	

	//ShellSort
	public static void shellSort(int arr[]){
        int n = arr.length;
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < n; i += 1)
            {
                int temp = arr[i];

                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
  

                arr[j] = temp;
            }
        }
    }

	public static long shellSortTime(int array[]) {		
	
        printNumbers(array);
	    shelltime = System.nanoTime();  
		shellSort(array);
		
		 printNumbers(array);

		return System.nanoTime() - shelltime;	

	}

	//BucketSort
	public static void bucketSort(int[] arr) {
    int max = max(arr);
    int min = min(arr);
    int numberOfBuckets = max - min + 1;
    List<List<Integer>> buckets = new ArrayList<>(numberOfBuckets);
    for (int i = 0; i < numberOfBuckets; ++i) {
      buckets.add(new ArrayList<>());
    }
    for (int value : arr) {
      int hash = hash(value, min, numberOfBuckets);
      buckets.get(hash).add(value);
    }
    for (List<Integer> bucket : buckets) {
      Collections.sort(bucket);
    }
    int index = 0;
    for (List<Integer> bucket : buckets) {
      for (int value : bucket) {
        arr[index++] = value;
      }
    }
  }	  
  
  public static int min(int[] arr) {
    int min = arr[0];
    for (int value : arr) {
      if (value < min) {
        min = value;
      }
    }
    return min;
  }
  
    public static int max(int[] arr) {
    int max = arr[0];
    for (int value : arr) {
      if (value > max) {
        max = value;
      }
    }
    return max;
  }
    public static int hash(int elem, int min, int numberOfBucket) {
    return (elem - min) / numberOfBucket;
  }


  	public static long bucketSortTime(int array[]) {	
	
        printNumbers(array);
	    buckettime = System.nanoTime();  
		bucketSort(array);
		
		printNumbers(array);

		return System.nanoTime() - buckettime;	

	}

	//Supporting Methods
	public static void swap(int array[], int i, int j){	
	        int tmp = array[i];
	        array[i] = array[j];
	        array[j] = tmp; 
	}  
	
	public static void printNumbers(int[] array) {		
	          
	        for (int i = 0; i < array.length; i++) {
	            System.out.print(array[i] + ", ");
	        }
	        System.out.println("\n");
	    }
	
	public static void copyArray(int[] initArray, int[] tmpArray){
		for (int i = 0; i < initArray.length;i++){
			tmpArray[i] = initArray[i];
		}
	}


	public static int[] getbestscenarioArray(int length){	//makes an array of sorted integers
		int list [] = new int [length];	
		for(int i = 0; i < length;i++){
			int result = i*2;
			list[i] = result;
			}						
		return list;
	}

	public static int[] getworstscenarioArray(int length){	//makes an array of unsorted(worse case) integers
		int list [] = new int [length];
		int result =  length;	
		for(int i = 0; i < length;i++){
			
			list[i] = result;
			result--;
			}						
		return list;
	}	

	public static int[] getList(int min, int max, int length){	//makes an array of random integers
		int list [] = new int [length];							
		Random r = new Random();								
		for(int i = 0; i < length;i++){
			int result = r.nextInt(max-min) + min;
			list[i] = result;
			}			
		return list;
	}


static <K,V extends Comparable<? super V>>
SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
        new Comparator<Map.Entry<K,V>>() {
            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                int res = e1.getValue().compareTo(e2.getValue());
                return res != 0 ? res : 1;
            }
        }
    );
    sortedEntries.addAll(map.entrySet());
    return sortedEntries;
}	
	    




	    public static void main(String[] args) {
	    System.out.println("This program compares the speed of various sorting algorithms.");
	    System.out.println("Compares each algo in random, best case and worst case scenarios.");
	    System.out.println("Enter a length for an Array of Integers to be sorted");
	    System.out.println("Enter an integer between 1-5000: ");

	    Scanner scanner = new Scanner(System.in);
		int input =0;
		if(scanner.hasNextInt()){
			input = scanner.nextInt();
		if(input>0 && input <= 5000){
		length = input;
		}else{
		length = 0;
		System.out.println("Please Enter Positive Integer Less than Max");
		}

		}else{
			System.out.println("Please Enter Valid Integer");
		}
		scanner.close();

		min = 0;

		max = length*10;
		if(length > 0){
		int[] init = getList(min,max,length);

		System.out.println("Initial Arrays");
		System.out.println();
		int[] arr = init;
		int[] arrbest = getbestscenarioArray(length);
		int[] arrworst = getworstscenarioArray(length);
		System.out.println();
		System.out.println();
		
		System.out.println("Random");
		printNumbers(arr);
		System.out.println("Best Case");
		printNumbers(arrbest);
		System.out.println("Worst Case");
		printNumbers(arrworst);
		System.out.println();
		System.out.println();
		int[] tempArr = new int[length];
		int[] tempBestArr = new int[length];
		int[] tempWorstArr = new int[length];
		copyArray(init,tempArr);
		copyArray(arrbest,tempBestArr);
		copyArray(arrworst,tempWorstArr);

		SortedMap<String,Long> randommap = new TreeMap<String,Long>();
		SortedMap<String,Long> bestmap = new TreeMap<String,Long>();
		SortedMap<String,Long> worstmap = new TreeMap<String,Long>();


//BubbleSort
		System.out.println("Bubble Sort");
		System.out.println();
		System.out.println("Random case:");
		temp = BubbleSort(tempArr);
		randommap.put("BubbleSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Best case:");
		temp = BubbleSort(tempBestArr);
		bestmap.put("BubbleSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Worst case: ");
		temp=BubbleSort(tempWorstArr);
		worstmap.put("BubbleSort",temp);		
		System.out.println(temp);
		System.out.println();
		System.out.println();

		copyArray(init,tempArr);
		copyArray(arrbest,tempBestArr);
		copyArray(arrworst,tempWorstArr);

//SelectionSorts
		System.out.println("Selection Sort");
		System.out.println();
		System.out.println("Random case: ");
		temp = SelectionSort(tempArr);
		randommap.put("SelectionSort",temp);
		System.out.println(temp);
		
		System.out.println();
		System.out.println("Best case: ");
		temp = SelectionSort(tempBestArr);
		bestmap.put("SelectionSort",temp);
		System.out.println(temp);
		
		System.out.println();
		System.out.println("Worst case: ");		
		temp = SelectionSort(tempWorstArr);
		worstmap.put("SelectionSort",temp);
		System.out.println(temp);
		System.out.println();
		System.out.println();

		copyArray(init,tempArr);
		copyArray(arrbest,tempBestArr);
		copyArray(arrworst,tempWorstArr);		


//InsertionSort
		System.out.println("Insertion Sort");
		System.out.println();
		System.out.println("Random case: ");
		temp = InsertionSort(tempArr);
		randommap.put("InsertionSort",temp);
		System.out.println(temp);
		
		System.out.println();
		System.out.println("Best case: ");
		temp = InsertionSort(tempBestArr);
		bestmap.put("InsertionSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Worst case: ");		
		temp = InsertionSort(tempWorstArr);
		worstmap.put("InsertionSort",temp);
		System.out.println(temp);
		System.out.println();
		System.out.println();

		copyArray(init,tempArr);
		copyArray(arrbest,tempBestArr);
		copyArray(arrworst,tempWorstArr);
//HeapSort
		System.out.println("HeapSort Sort");
		System.out.println();
		System.out.println("Random case: ");
		temp = HeapSort(tempArr);
		randommap.put("HeapSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Best case: ");
		temp = HeapSort(tempBestArr);
		bestmap.put("HeapSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Worst case: ");
		temp = HeapSort(tempWorstArr);
		worstmap.put("HeapSort",temp);
		System.out.println(temp);
		System.out.println();
		System.out.println();

		copyArray(init,tempArr);
		copyArray(arrbest,tempBestArr);
		copyArray(arrworst,tempWorstArr);		
//RadixSort		
		System.out.println("RadixSort Sort");
		System.out.println();
		System.out.println("Random case: ");
		temp = RadixSort(tempArr);
		randommap.put("RadixSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Best case: ");
		temp = RadixSort(tempBestArr);
		bestmap.put("RadixSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Worst case: ");
		temp = RadixSort(tempWorstArr);
		worstmap.put("RadixSort",temp);
		System.out.println(temp);
		System.out.println();
		System.out.println();

		copyArray(init,tempArr);
		copyArray(arrbest,tempBestArr);
		copyArray(arrworst,tempWorstArr);	

	//MergeSortTime
		System.out.println("Merge Sort");
		System.out.println();
		System.out.println("Random case: ");
		temp = MergeSortTime(tempArr);
		randommap.put("MergeSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Best case: ");
		temp = MergeSortTime(tempBestArr);
		bestmap.put("MergeSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Worst case: ");
		temp = MergeSortTime(tempWorstArr);
		worstmap.put("MergeSort",temp);
		System.out.println(temp);
		System.out.println();
		System.out.println();

		copyArray(init,tempArr);
		copyArray(arrbest,tempBestArr);
		copyArray(arrworst,tempWorstArr);

		//timSortTime
		System.out.println("TimSort");
		System.out.println();
		System.out.println("Random case: ");
		temp = timSortTime(tempArr);
		randommap.put("TimSort"+"\t",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Best case: ");
		temp = timSortTime(tempBestArr);
		bestmap.put("TimSort"+"\t",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Worst case: ");
		temp = timSortTime(tempWorstArr);
		worstmap.put("TimSort"+"\t",temp);
		System.out.println(temp);
		System.out.println();
		System.out.println();
        

		copyArray(init,tempArr);
		copyArray(arrbest,tempBestArr);
		copyArray(arrworst,tempWorstArr);

		//quickSortTime
		System.out.println("QuickSort");
		System.out.println();
		System.out.println("Random case: ");
		temp = quickSortTime(tempArr);
		randommap.put("QuickSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Best case: ");
		temp = quickSortTime(tempBestArr);
		bestmap.put("QuickSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Worst case: ");
		temp = quickSortTime(tempWorstArr);
		worstmap.put("QuickSort",temp);
		System.out.println(temp);
		System.out.println();
		System.out.println();		

		copyArray(init,tempArr);
		copyArray(arrbest,tempBestArr);
		copyArray(arrworst,tempWorstArr);

		//shellSortTime
		System.out.println("ShellSort");
		System.out.println();
		System.out.println("Random case: ");
		temp = shellSortTime(tempArr);
		randommap.put("ShellSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Best case: ");
		temp = shellSortTime(tempBestArr);
		bestmap.put("ShellSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Worst case: ");
		temp = shellSortTime(tempWorstArr);
		worstmap.put("ShellSort",temp);
		System.out.println(temp);
		System.out.println();
		System.out.println();


//bucketSortTime		
		copyArray(init,tempArr);
		copyArray(arrbest,tempBestArr);
		copyArray(arrworst,tempWorstArr);

	
		System.out.println("BucketSort");
		System.out.println();
		System.out.println("Random case: ");
		temp = bucketSortTime(tempArr);
		randommap.put("BucketSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Best case: ");
		temp = bucketSortTime(tempBestArr);
		bestmap.put("BucketSort",temp);
		System.out.println(temp);

		System.out.println();
		System.out.println("Worst case: ");
		temp = bucketSortTime(tempWorstArr);
		worstmap.put("BucketSort",temp);
		System.out.println(temp);
		System.out.println();
		System.out.println();
		
		NumberFormat myFormat = NumberFormat.getInstance();
		myFormat.setGroupingUsed(true);
		System.out.println();		
		System.out.println("Random");		

		Iterator<Map.Entry<String,Long>> i1 = entriesSortedByValues(randommap).iterator();
		while(i1.hasNext()){
			Map.Entry<String,Long> m = (Map.Entry<String,Long>)i1.next();
			String key = (String)m.getKey();
			Long value = (Long)m.getValue();
			System.out.println(key+"\t"+myFormat.format(value));
		}	
		System.out.println();		
		System.out.println("Best Case");		
		Iterator<Map.Entry<String,Long>> i2 = entriesSortedByValues(bestmap).iterator();
		while(i2.hasNext()){
			Map.Entry<String,Long> m = (Map.Entry<String,Long>)i2.next();
			String key = (String)m.getKey();
			Long value = (Long)m.getValue();
			System.out.println(key+"\t"+myFormat.format(value));
		}	
		System.out.println();		
		System.out.println("Worst Case");		

		Iterator<Map.Entry<String,Long>> i3 = entriesSortedByValues(worstmap).iterator();
		while(i3.hasNext()){
			Map.Entry<String,Long> m = (Map.Entry<String,Long>)i3.next();
			String key = (String)m.getKey();
			Long value = (Long)m.getValue();
			System.out.println(key+"\t"+myFormat.format(value));
		}	

	}
		
}}