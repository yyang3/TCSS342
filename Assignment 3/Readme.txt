/******************************************************************************
 *  Name: Yicong Yang      
 
******************************************************************************/


/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 *****************************************************************************/
I used Hash map because it is easy to find the synset with the unique ID of each item. Thus we could search each key in O(1).


/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 *****************************************************************************/
I used hash map because of the uniqueness of starting ID. Thus we could search each key in O(1).




/******************************************************************************
 *  Describe concisely your algorithm to compute the shortest common
 *  ancestor in ShortestCommonAncestor. What is the order of growth of
 *  the running time of your methods as a function of the number of
 *  vertices V and the number of edges E in the digraph? What is the
 *  order of growth of the best-case running time?
 *
 *  
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't
 *  forget to count the time needed to initialize the marked[],
 *  edgeTo[], and distTo[] arrays.
 *****************************************************************************/

Description: 1. Use depth first to find all the ancestors of nounA (O(n) n = ancestors of nounA)
		  2. Use depth first to find all the ancestors of nounB (O(m) m = ancestors of nounB)
		  3. Loop through List of ancestors of nounA and nounB to find the common ancestors (O(m * n))
		  4. Loop throught the common ancestors list to find the shortest common ancestor (O(p) * O(l) p = number of common ancestors, l = time complexity of finding length between 2 vertices)
	Time complexity of length:
		  1. Use breadth first to find all the ancestors for the starting vertice until the queue for bread first is empty (not found) or found the destination vertice (O(n) n = total ancestors of starting vertice)

                                              running time
method                               best case            worst case
------------------------------------------------------------------------
length(int v, int w)                O(1)			  O(n)

ancestor(int v, int w)              O(n)                 O(n^2)



/******************************************************************************
Output:
Print your output here !
You output should contain the following:
1. List of all the edges of DAG
in test.txt
2. SAP ancestor and length between two noun ID
1750s 1840s
4, 1530s
1820s 1850s
3, 1770s
1790s 15_May_Organization
4, 'hood
1530s 1780s
-1, -1
3. Sematic Relatedness between two nouns passed as console input.
 *****************************************************************************/

