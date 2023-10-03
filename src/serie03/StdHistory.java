package serie03;

import util.Contract;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Stack;

public class StdHistory<E> implements History<E> {
	private final int maxHeight;

	private final Deque<E> commandQueue;
	private final Stack<E> commandStack;
	
	
	public StdHistory(int maxHeight) {
		Contract.checkCondition( maxHeight > 0);
		this.maxHeight = maxHeight;
		this.commandQueue = new ArrayDeque<E>();
		this.commandStack = new Stack<E>();
	}

	@Override
	public int getMaxHeight() {
		// TODO Auto-generated method stub
		return this.maxHeight;
	}

	@Override
	public int getCurrentPosition() {
		// TODO Auto-generated method stub
		
		return this.commandQueue.size();
	}

	@Override
	public E getCurrentElement() {
		// TODO Auto-generated method stub
		Contract.checkCondition(getCurrentPosition() > 0);
		return this.commandQueue.getLast();
	}

	@Override
	public int getEndPosition() {
		// TODO Auto-generated method stub
		return this.commandQueue.size() + this.commandStack.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return getEndPosition() == 0;
	}
	
	

	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		Contract.checkCondition(e != null); 
		
		if (isFull()) {
			this.commandQueue.removeFirst();
		}
			
		this.commandQueue.addLast(e);
		this.commandStack.clear();
		
	}

	@Override
	public void goForward() {
		// TODO Auto-generated method stub
		Contract.checkCondition(this.getCurrentPosition() < this.getEndPosition());
		E ele = this.commandStack.pop();
		this.commandQueue.addLast(ele);
	}

	@Override
	public void goBackward() {
		// TODO Auto-generated method stub
		Contract.checkCondition(this.getCurrentPosition() > 0);
		E ele = this.commandQueue.removeLast();
		this.commandStack.push(ele);
		
	}
	
	// Outils
	private boolean isFull() {
		return getEndPosition() == this.maxHeight;
	}

}
