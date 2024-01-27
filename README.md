# Parallel Processes Project 1
## Prerequesites
A computer with Java installed

## Instalation Process
1. Download the FindPrimes.java file
2. Open Command prompt <br>
   a) For Windows: press the Windows Key and search for Command Prompt. <br>
   b) For Mac: click the Launchpad icon in the Dock then search for Terminal.
3. Navigate to the Downloads folder by typing the command `cd Downloads`
4. Compile the program by typing the command `javac FindPrimes.java`
5. Run the program by typing the command `java FindPrimes`
6. Navigate to your downloads folder to find the text file `primes.txt`
7. The text file is formatted in the following way <br>
<[elapsed runtime in seconds]> <[number of prime numbers found]> <[sum of all prime numbers found]> <br>
<[largest 10 prime numbers found, separated by commas]>

## Report
The program uses an incredibly efficient algorithm called the Sieve of Eratosthenes in order to quickly calculate prime numbers, computing even 10^8 primes in under 5 seconds during testing. This is sped up even faster by using multithreaded processing to have the algorithm run on multiple separate blocks of numbers at the same time, in which it averaged out to about 3 seconds when tested.
