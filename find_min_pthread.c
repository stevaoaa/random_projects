// Encontra o valor minimo em um vetor de inteiros de tamanho N

#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <limits.h>
#include <time.h>

#define N 4500	// number of elements
#define T 8 //number of threads


/*
struct to allow make call to pthread_create with fuctions with multiple args
*/
typedef struct{
	int *array;
	int start, end;
} args;


/*
Simple function to generate an array with random numbers
*/
int * generate_randon_array(int array_size, int range){
	
	int *array = (int *)malloc(sizeof(int));

	srand(0);
	int k, generated;
	for (k = 0; k < array_size; k++) {
		generated = rand() % range + 1;
		array[k] = generated;
	}

	return array;
}



/*
Given an struct [array, start, end] find the minor element of the array
*/
int search_min(void * w) {
	
	//make a cast to struct
	args *parms = (args *) w;

	//make a copy of the array from struct to the variables
	
	int start = parms->start;
	int end = parms->end;
	int *B = (int *)malloc(end + 1 * sizeof(int));
	B = parms->array;

	//set a high value to min at the begging
	int min = INT_MAX;

	//simple search
	for (int p = start;  p <= end ; p++) {
		if (B[p] < min){
			min = B[p];
		}  
	}
	printf("Find the minor value of the interval: %d\n", min);
	//return the lower value found
	return min;
}


/*
Given a big array, split it between the number of threads and find the smallest
 element of each splited part and return a new array with the results  
*/
int* split_search(int* array_target, int number_threads, int array_size){

	//store the results; the array gonna have the same number of the number of threads
	int * results = (int *)malloc(number_threads * sizeof(int)); 

	//struct to run search_min with multiple args on pthreads
	args * parametrs = (args *)malloc(sizeof(args));

	//malloc memory to the array into the struct
	parametrs->array = (int *)malloc(array_size * sizeof(int));
	parametrs->array = array_target; //copy target array to the struct

	pthread_t *ptr;  //poiter to array of threads
	ptr = malloc(sizeof(pthread_t) * number_threads); //alloc memory to an array with number of threads positions 

	
	//size of the arrays for each thread
	int chunk_size = array_size/number_threads;
	int inicio,fim; //helpers
	int cont = 0;

	//get the results from threads
	void *status;
	
	for (int i = 0; i < number_threads-1; i++) {
		inicio = i* chunk_size;
		fim = inicio + chunk_size - 1;

		printf("Thread will search the min from positions: %d, %d \n", inicio, fim);

		//set parametrs to the struct
		parametrs->start = inicio;
		parametrs->end   = fim;

		//create a thread with array interval[start:end-1]
		pthread_create(&ptr[cont], 0, (void *) search_min, (void *) parametrs);

		//syncronize threads and receive results
		pthread_join(ptr[cont], &status);
		
		//store the results into an array
		results[cont] = (int*)status;

		cont++;
	}
	
	//Last chunk with all the remaining content
	inicio = fim + 1;
	fim = array_size - 1;

	//set parametrs to the struct
	parametrs->start = inicio;
	parametrs->end   = fim;

	printf("Thread will search the min from positions: %d, %d \n", inicio, fim);
	
	pthread_create(&ptr[cont], 0, (void *) search_min, (void *) parametrs);

	//syncronize threads
	pthread_join(ptr[cont], &status);

	//store the results into an array
	results[cont] = (int*)status;;


	return results;
}




int main() {

	//parameters
	int number_threads = T;
	int size = N;  //size of the array
	int range = N/2; //range of generated numbers 

	//malloc the array and generate the random numbers
	int *my_array = (int *)malloc(size * sizeof(int));
	my_array = generate_randon_array(size,range);

	//malloc memory to struct and for the array in the struct
	args * parametros = (args *)malloc(sizeof(args));
	parametros->array = (int *)malloc(size * sizeof(int));
	
	//array to store the results of each thread
	int * results = (int *)malloc(number_threads * sizeof(int));

	//get the results
	results = split_search(my_array, number_threads, size);


	// ****** printing ********// 
	printf("Parcial results: ");
	for (int i = 0; i < number_threads; ++i)
	{
		printf("%d ", results[i]);
	}
	printf("\n");
	// ****** printing ********//


	//malloc memory to struct and for the array in the struct
	args * final = (args *)malloc(sizeof(args));
	final->array = (int *)malloc(number_threads * sizeof(int));

	final->array = results;
	final->start = 0;
	final->end = number_threads-1;

	//find the smallest value in the results
	int smallest = search_min(final);

	fflush(0);
	return -1;

	
} 