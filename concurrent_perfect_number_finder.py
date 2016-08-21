import multiprocessing
import time
import sys


def main():
    if len(sys.argv) < 2:
        print "Must enter a number"
        return
    n = 0    
    try:
        n = int(sys.argv[1])    
    except ValueError:
        print "Invalid input"
        return    
    start_time = time.time()
    find_perfect_number(n)
    end_time = time.time()
    print "Running find_perfect_number() in {} seconds".format(end_time-start_time)
    
    start_time = time.time()
    find_perfect_number_in_parallel(n)
    end_time = time.time()
    print "Running find_perfect_number_in_parallel() in {} seconds".format(end_time-start_time)
       

def find_perfect_number(limit):
    print "Perfect numbers from 1 to {}:".format(limit)
    for x in xrange(1, limit+1):
        if is_perfect_number(x):
            print x
            
            
def find_perfect_number_in_parallel(limit): 
    print "Perfect numbers from 1 to {}:".format(limit)           
    pool_size = multiprocessing.cpu_count() * 2
    process_pool = multiprocessing.Pool(processes=pool_size)
    check_perfect_number_results = process_pool.map(is_perfect_number, range(1, limit+1))
    for i, result in enumerate(check_perfect_number_results):
        if result:
            print i+1
    
    
def is_perfect_number(number):
    sum = 0
    for x in xrange(1, number):
        if number % x == 0:
            sum += x
        if sum > number:
            return False
    return sum == number 
   

if __name__=='__main__':
    main()


   
                               