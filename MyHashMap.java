package test;

import java.util.Arrays;

public class MyHashMap<K, V> {
/**
 * A hashtable implementation of map,demonstration purpose, generic type is provided.
 * 
 * supported operations:
 * size();
 * isEmpty();
 * clear();
 * put(K key, V value);
 * get(K key);
 * containsKey(K key);
 * containsValue(V value)
 * remove(K key)
 */
	public static class Node<K, V> {
		final K key;
		V value;
		Node<K, V> next;
		public Node (K key, V value) {
			this.key = key;
			this.value = value;			
		}
		public V getValue() {
			return value;
		}
		public K getKey() {
			return key;
		}
		public void setValue(V value) {
			this.value = value;
		}
	}
	private int size;
	private float loadFactor;
	private Node<K, V>[] array;
	public static final int DEFAULT_CAPACITY = 16;
	public static final float DEFAULT_LOAD_FACTOR = 0.75f;
	public MyHashMap(){
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	public MyHashMap(int cap, float lf) {
		if (cap <= 0) {
			throw new IllegalArgumentException("cap should be greater than 0");
		}
		array = (Node<K, V>[]) (new Node[cap]);
		size = 0;
		loadFactor = lf;
 	}
	public boolean isEmpty() {
		return size == 0;
	}
	public int size() {
		return size;
	}
	public void clear() {
		Arrays.fill(this.array, null);
		size = 0;
	}
	private int hash(K key){
		if (key == null) {
			return 0;
		}
		return key.hashCode() & 0X7FFFFFFF;
	}
	private int getIndex(K key) {
		return hash(key) % array.length;
	}
	public V put (K key, V value) {
		//Node<K, V> newNode = new Node(key, value);
		int index = getIndex(key);
		Node<K, V> cur = array[index];
		while (cur != null) {
			if (equalsKey(cur.key,key)) {
				V oldValue = cur.getValue();
				cur.setValue(value);
				return oldValue;
			}
			cur = cur.next;
		}
		Node<K, V> newHead = new Node(key, value);
		cur = array[index];
		array[index] = newHead;
		newHead.next = cur;
		size++;
		if (needRehashing()) {
			rehashing();
		}
		return null;
	}
	private boolean equalsKey(K k1, K k2) {
		if (k1 == null && k2 == null) {
			return true;
		}
		if (k1 == null || k2 == null) {
			return false;
		}
		return k1.equals(k2);
	}
	private boolean equalsValue(V v1, V v2) {
		if (v1 == null && v2 == null) {
			return true;
		}
		if (v1 == null || v2 == null) {
			return false;
		}
		return v1.equals(v2);
	}
	private boolean needRehashing() {
		float ratio = ((float) size)/array.length;
		return ratio > loadFactor; 
	}
	private void rehashing() {
		int oldsize = size;
		Node<K, V>[] oldarray = array;
		array = (Node<K, V>[]) (new Node[array.length * 2]);
		for (Node<K, V> node : oldarray) {
			while (node != null) {
				put(node.getKey(), node.getValue());
				node = node.next;
			}
		}
		size = oldsize;
	}
	public V get(K key) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				return node.getValue();
			}
			node = node.next;
		}
		return null;
	}
	public boolean containsKey(K key) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}
	public boolean containsValue(V value) {
		if (isEmpty()) {
			return false;
		}
		for (Node<K, V> node: array) {
			while (node != null) {
				if (equalsValue(node.getValue(), value)) {
					return true;
				}
				node = node.next;
			}
		}
		return false;
	}
	public V remove(K key) {
		int index = getIndex(key);
		Node<K, V> cur = array[index];
		Node<K, V> dummy = new Node(null, null);
		dummy.next = cur;
		Node<K, V> prev = dummy;
		while (cur != null) {
			if (equalsKey(cur.key, key)) {
				prev.next = cur.next;
				size--;
				array[index] = dummy.next;
				return cur.getValue();
			}
		}
		return null;
	}
}
