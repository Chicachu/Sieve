import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

//***************************************
// * Programmer: Erica Putsche 
// * Date: 10/17/13
// *
//    create a queue and fill it with the consecutive integers 2 through n inclusive.
//    create an empty queue to store primes.
//    do {
//        obtain the next prime p by removing the first value in the queue of numbers.
//        put p into the queue of primes.
//        go through the queue of numbers, eliminating numbers divisible by p.
//    } while (p < sqrt(n))
//    all remaining values in numbers queue are prime, so transfer them to primes queue
//
public class Sieve {
    private Queue<Integer> nums;// = new LinkedList<Integer>();
    private Queue<Integer> primes;// = new LinkedList<Integer>();
    private int lastInt,primeCount;
    private boolean computeToCalled;
    final int firstPrime = 2;
    Sieve(){  //constructor
    	computeToCalled = false;
    }
    
//    public static void main(String[] args) {
//        System.out.println("This program computes all prime numbers up to a");
//        System.out.println("maximum using the Sieve of Eratosthenes.");
//        System.out.println();
//        Scanner console = new Scanner(System.in);
//        Sieve s = new Sieve();
//        for(;;) {
//            System.out.print("Maximum n to compute (0 to quit)? ");
//            int max = console.nextInt();
//            if (max == 0)
//                break;
//            System.out.println();
//            s.computeTo(max);
//            s.reportResults();
//            int percent = s.getCount() * 100 / s.getMax();
//            System.out.println("% of primes = " + percent);
//            System.out.println();
//        }
//    }

   //***********************************************************
   // computeTo:: This is the method that should implement the 
   // sieve algorithm.
   //***********************************************************
   // All prime computations must be implemented using this algorithm.
   // The method should compute all primes up to and including n.
   // It should throw an IllegalArgumentException if n is less than 2.
   //***********************************************************
   // Lats updated 10/17/13 Erica Putsche 
   //***********************************************************
    public void computeTo(int n)throws IllegalArgumentException{
       if(n<firstPrime) throw new IllegalArgumentException();
        lastInt = n;
        primeCount = 0; // reset count
        nums = new LinkedList<Integer>();
        primes = new LinkedList<Integer>();
        
        
        // initialize list of numbers from 2 - n.
        for (int i = firstPrime; i <= lastInt; i++) {
        	nums.offer(i);
        }
        
        do {
        	// grab first number from list of numbers and give it to primes
        	int divisor = nums.poll();
        	primes.offer(divisor);
        	primeCount++;
        	int subject; 
        	do {
        		subject = nums.poll();
        		// if prime, put to back of list of numbers
        		// otherwise, it just disappears!
        		if (subject % divisor != 0) {
        			nums.offer(subject);
        		}
        	} while (subject < nums.peek());
        } while (nums.peek() <= Math.sqrt(lastInt));
        
        // once all of the non-primes are removed, finish placing
        	// primes into primes list.
        do {
        	primes.offer(nums.poll());
        	primeCount++;
        } while (nums.peek() != null);
        
        // other methods are safe to call now
        computeToCalled = true;
    }
    
    /**
     * Reports all prime numbers up to the specified number, and reports
     * the percentage of primes found up to that number.
     * 
     * @throws IllegalStateException
     */
    public void reportResults() throws IllegalStateException {
    	if (!computeToCalled) {
    		throw new IllegalStateException();
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("Primes up to " + lastInt + " are as follows:\n");
    	int first = primes.peek();
    	do {
    		// take number from front of list
    		int num = primes.poll();
    		sb.append(num + " ");
    		// put it to the back again
    		primes.offer(num);
    	} while (primes.peek() > first);
    	sb.append("\n");
 
    	System.out.print(sb.toString());
    }
    
    /**
     * Gets the last count of the primes found.
     * @return The last count of primes found.
     * @throws IllegalStateException
     */
    public int getCount() throws IllegalStateException {
    	if (!computeToCalled) {
    		throw new IllegalStateException();
    	}
    	return primeCount;
    }
    
    /**
     * Gets the last user inputed number used in calculating primes.
     * @return The last user input value for calculating primes.
     * @throws IllegalStateException
     */
    public int getMax() throws IllegalStateException {
    	if (!computeToCalled) {
    		throw new IllegalStateException();
    	}
    	return lastInt;
    }
}
