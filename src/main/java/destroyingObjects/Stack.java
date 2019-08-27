package destroyingObjects;

/** Stack implementation.
 * Demonstrates how some objects cannot be deleted by the garbage collector unless
 * we properly de-reference them.
 * From Effective Java, J. Bloch */
import java.util.Arrays;
import java.util.EmptyStackException;

/** Stack implementation using a regular array */
public class Stack {

	public static final int MAX_SIZE = 20;

	private Object[] elements;
	private int size = 0; // current size of the stack

	/**
	 * Default Constructor
	 */
	public Stack() {
		elements = new Object[MAX_SIZE];
	}

	/**
	 * Pushes an object on top of the stack
	 * @param obj object to push to stack
	 */
	public void push(Object obj) {
		growStack();
		elements[size++] = obj;
	}

	/**
	 * Pops the object from the stack
	 * @return object
	 */
	public Object pop() {
		if (size == 0)
			throw new EmptyStackException();

		Object result = (Object) elements[--size];

		elements[size] = null; // Without this line, unnecessary objects will not get removed?
		return result;
	}

	/**
	 * Checks if the stack is empty.
	 * @return true if empty, fall otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Roughly doubles the size of the stack.
	 */
	private void growStack() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}

	/**
	 * The main method of the class; demonstrates how to use stack operations
	 * @param args numbers to push onto the stack
	 */
	public static void main(String[] args) {
		Stack stack = new Stack();
		for (String arg : args)
			stack.push(arg);
		while (!stack.isEmpty())
			System.out.println(stack.pop());
	}
}