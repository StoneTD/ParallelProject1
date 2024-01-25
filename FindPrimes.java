import java.util.*;


public class FindPrimes 
{
    private final static int threadsUsed = 8;  // The number of threads we're allowed to use
    private final static int maximum = 100000000;  // The limit upon which we find primes until
    private boolean[] primes;  // The boolean array to use for the Sieve 
    private int blockSize;  // The size of the blocks allocated to each thread
    private List<Thread> threads;  // Thread list   

    public FindPrimes()
    {
        this.primes = new boolean[maximum + 1];
        // Calculates the size of the block that each thread will work on
        // This way all threads work on the same amount of numbers
        this.blockSize = (maximum + threadsUsed - 1) / threadsUsed;
        this.threads = new ArrayList<>();

        for (int i = 2; i <= maximum; i++)
            primes[i] = true;   // Boolean arrays auto innitalize to false in Java    
    }

    public List<Integer> sievinTime() throws InterruptedException
    {
        // Make the threads
        for (int i = 0; i < threadsUsed; i++)
        {
            int start = i * blockSize + 2;  // Where each thread starts
            int stop = Math.min((i + 1) * blockSize + 1, maximum);  // Where each thread stops

            Thread thread = new Thread(() -> 
            {
                // It's Sievin Time
                for (int j = 2; j * j < stop; j++)
                {
                    if (primes[j])
                    {
                        // We want to either use j^2 or the smallest multiple of j that is >= start
                        // We don't want to be below the starting point nor j^2
                        int multStarter = Math.max(j * j, (start / j) * j);
                        
                        // A third for loop has hit the algorithm
                        for (int k = multStarter; k < stop; k += j)
                            primes[k] = false;  // marks the multiples as not prime
                    }
                }

            });

            threads.add(thread);
            thread.start();
        }
        
        // Wait till the threads are all done
        for (int i = 0; i < threadsUsed; i ++)
            threads.get(i).join();

        // Form the list of primes
        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i < maximum; i++)
            if (primes[i])
                primeList.add(i);

        return primeList;
    }

    // Store top 10 primes in a double linked list
    // Head is smallest, Tail is largest
    // Probably make it ahead of time, initializing all the nodes to -1
    // When inserting new value (x), check value of Head
        // If Head == -1 Head.data = x
        // If Head != -1 compare value below
    // If x is smaller than Head, check value of Tail
        // If Tail == -1 Temp = Tail.prev, Tail.prev.next = null, Tail = Temp
        // Temp = null, If Head.prev != null Temp = Head.prev
        // Head.prev = new Node(x), Head.prev.next = Head, Head.prev.prev = Temp;

        // If Tail != -1 go next
    // If x is bigger than head, repeat check with Head.next
    // So we want a function to check where x should go and be able to pass it Head.next
    // Static Node insertValue (int x, Node Head, Node Tail), Returns whatever the Head is now

    // That was silly I can just count backwards from the final list
    // I'm keeping it just cause I think it's funny
    // Can you believe I was gonna implement a doubly linked list
    // that updates in real time while my algorithm was running?
    // That would've been so slow

    public static void main(String [] args) throws InterruptedException
    {
        // For testing
        // long totalTime = 0;
        // for (int j = 0; j < 10; j++)
        // {
            long startTime = System.currentTimeMillis();

            long sum = 0;

            FindPrimes greekLover = new FindPrimes();
            List<Integer> primes = greekLover.sievinTime();

            List<Integer> lastPrimes = new ArrayList<>();
            int num = primes.size();
            for (int i = 10; i > 0; i--)
                lastPrimes.add(primes.get(num - i));

            for (int i = 0; i < num; i++)
                sum += primes.get(i);

            long endTime = System.currentTimeMillis();
            // totalTime += (endTime - startTime);
            
            System.out.print("<" + (endTime - startTime) / 1000.00 + " s> ");
            System.out.print("<" + num + "> ");
            System.out.println("<" + sum + "> ");
            
            System.out.print("<");
            for (int i = 0; i < 10; i++)
                System.out.print(lastPrimes.get(i) + (i == 9 ? ">\n" : ", "));
        // }

        // long averageTime = totalTime / 10;
        // System.out.println("Average time: <" + averageTime / 1000.00 + " s>");
    }
}
