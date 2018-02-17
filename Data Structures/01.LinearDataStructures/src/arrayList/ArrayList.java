2

public class ArrayList<T>{
	private T[] arr;
	private int currInd;
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity){

		this.arr = (T[])new Object[capacity];
		this.currInd = 0;
	}
	@SuppressWarnings("unchecked")
	public ArrayList(){
		this.arr = (T[])new Object[1];
		this.currInd = 0;
	}

	
	public int getCount() {
		return this.currInd;
	}


	public T get(int index) {
		if(index<0|| index>=this.arr.length){
			throw new IllegalArgumentException();
		}
		return this.arr[index];
	}


	public void add(T element) {
		if(this.currInd == this.arr.length){
			this.resize();
		}
		this.arr[currInd] = element;
		this.currInd++;
	}

	public T removeAt(int index) {
		if(index<0 || index>=this.arr.length){
			throw new IllegalArgumentException();
		}
		T removedItem = this.arr[index];

		this.arr[index] = null;
		shiftLeft(index);
		this.currInd--;
		if(this.currInd <this.arr.length/3){
			Shrink();
		}
		return removedItem;
	}

	public void set(int i, T item) {
		if(i <0 || i>this.arr.length){
			throw new IllegalArgumentException();
		}
		this.arr[i] = item;
	}


	private void shiftLeft(int index) {
		System.arraycopy(this.arr, index + 1, this.arr, index, this.arr.length - 1 - index);
		this.arr[this.arr.length-1] = null;
	}

	@SuppressWarnings("unchecked")
	private void resize(){
	T[] newArr =(T[])new Object[this.arr.length*2];
		System.arraycopy(this.arr, 0, newArr, 0, arr.length);
		this.arr = newArr;

	}
	@SuppressWarnings("unchecked")

	private void Shrink(){
		T[] newArr = (T[])new Object[this.arr.length/2];
		System.arraycopy(this.arr, 0, newArr, 0, arr.length);
		this.arr = newArr;
	}
}
