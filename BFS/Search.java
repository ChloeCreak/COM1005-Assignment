package BFS;


/**
*	Search.java -
*   abstract class specialising to JugsSearch etc
*   includes depth-first and breadth-first strategies
*   reconstructs solution path
*   Phil Green 2013 version
*   Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

import java.util.*;

public abstract class Search {

	protected SearchNode initNode; // initial node
	protected SearchNode currentNode; // current node

	protected ArrayList<SearchNode> open; // open - list of SearchNodes
	protected ArrayList<SearchNode> closed; // closed - .......
	protected ArrayList<SearchNode> successorNodes; // used in expand & vetSuccessors

	/**
	 * ------------------------------------
	 * 			RUN THE SEARCH
	 * ------------------------------------
	 * 
	 * @param initState initial state
	 * @param strat     - String specifying strategy
	 * @return indication of success or failure
	 */
	public String runSearch(SearchState initState, String strategy) {

		initNode = new SearchNode(initState); // create the first node at the top of three

		System.out.println("Starting " + strategy + " Search"); // outputs strategy

		open = new ArrayList<SearchNode>(); // creates the arrayList of 'open' nodes (ones yet to be explored)
		open.add(initNode); // adds the first node (initNode) to this list

		closed = new ArrayList<SearchNode>(); // creates the arrayList of 'closed' node (the ones unexplored)

		int iterationCount = 1; // counter

		while (!open.isEmpty()) { // implents the search - checking in a loop if the current node is the wanted node
			// as well as implenting the search strategy everytime it goes to a new node

			// print contents of open aka the nodes left to search (and the counter)
			System.out.println("iteration no " + iterationCount);
			System.out.println("open is");
			for (SearchNode nn : open) { // FOR loop to output the contents of the arrayList open
				String nodestr = nn.toString(); // making list into a string to output
				System.out.println(nodestr);
			}

			selectNode(strategy); // selects next node given the strategy
			// and makes it the currentNode & removes it from open
			System.out.println("Current node " + currentNode.toString());

			if (currentNode.goalPredicate(this)) // checking if current node is the wanted node
				return reportSuccess();

			expand(); // current node is not the wanted node so the process is repeated

			closed.add(currentNode); // add current node to the closed arrayList

			iterationCount = iterationCount + 1;
		}
		;

		return "Search Fails"; // out of the while loop (failure)

	}

	// ------------------------------------
	//			EXPAND CURRENT NODE
	// ------------------------------------

	private void expand() {

		// get all successor nodes
		successorNodes = currentNode.getSuccessors(this); // pass search instance
		// getSuccessors is from SearchNode.java

		vetSuccessors(); // Dynamic Programming check

		for (SearchNode snode : successorNodes) {
			open.add(snode); // add surviving nodes to open arrayList
			snode.setParent(currentNode); // set their parents to currentNode
		}
	}

	// ------------------------------------
	//		REJECT OPEN/CLOSED STATES
	// ------------------------------------
	// vet the successors - reject any states are on the open or closed arrayLists

	private void vetSuccessors() {

		ArrayList<SearchNode> vslis = new ArrayList();

		for (SearchNode snode : successorNodes) {
			if (!(onClosed(snode)) && !(onOpen(snode)))
				vslis.add(snode);
		}
		;
		successorNodes = vslis;
	}

	// ------------------------------------
	//		IS CURRENT NODE CLOSED?
	// ------------------------------------
	// onClosed - is the state for a node the same as one on closed?

	private boolean onClosed(SearchNode newNode) {
		boolean ans = false;
		for (SearchNode closedNode : closed) {
			if (newNode.sameState(closedNode))
				ans = true;
		}
		return ans;
	}

	// ------------------------------------
	//		IS CURRENT NODE OPEN?
	// ------------------------------------
	// onOpen - is the state for a node the same as one on closed?

	private boolean onOpen(SearchNode newNode) {
		boolean ans = false;
		for (SearchNode openNode : open) {
			if (newNode.sameState(openNode))
				ans = true;
		}
		return ans;
	}

	// ------------------------------------
	//			SELECTS NEXT NODE
	// ------------------------------------
	// select the next node - change from search1
	// call depthFirst or breadthFirst dependent on strat
	// defaults to breadthFirst

	private void selectNode(String strat) {
		if (strat == "depthFirst")
			depthFirst();
		else
			breadthFirst();
	}

	// ------------------------------------
	//			DEPTH-FIRST
	// ------------------------------------

	private void depthFirst() {
		int osize = open.size();
		currentNode = (SearchNode) open.get(osize - 1); // last node added to open
		open.remove(osize - 1); // remove it
	}

	// ------------------------------------
	//			BREADTH-FIRST
	// ------------------------------------

	private void breadthFirst() {
		currentNode = (SearchNode) open.get(0); // first node on open
		open.remove(0);
	}

	// ------------------------------------
	//		RUNS WHEN NODE IS FOUND
	// ------------------------------------
	// change from search1
	// report success - reconstruct path, convert to string & return

	private String reportSuccess() {

		SearchNode n = currentNode;
		StringBuffer buf = new StringBuffer(n.toString());
		int plen = 1;

		while (n.getParent() != null) {
			buf.insert(0, "\n");
			n = n.getParent();
			buf.insert(0, n.toString());
			plen = plen + 1;
		}

		System.out.println("=========================== \n");
		System.out.println("Search Succeeds");

		System.out.println("Efficiency " + ((float) plen / (closed.size() + 1)));
		System.out.println("Solution Path\n");
		return buf.toString();
	}

}
