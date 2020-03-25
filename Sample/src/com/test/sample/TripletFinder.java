
package com.test.sample;
/**
 *  Program to find out all the possible set of 3 numbers from array where its sum is 10
 * @author 1022923
 *
 */
class TripletFinder {
	static void getTriplet(int[] IntegerArray) {
		int n = IntegerArray.length;
		int sum = 10;

		for (int i = 0; i < (n - 1); i++) {
			int forWardControll = i + 1;
			int backWardControll = n - 1;
			int firstLoopControll = IntegerArray[i];
			while (forWardControll < backWardControll) {
				if (firstLoopControll + IntegerArray[forWardControll] + IntegerArray[backWardControll] == sum) {

					System.out.println(firstLoopControll + "," + IntegerArray[forWardControll] + ","
							+ IntegerArray[backWardControll]);
					forWardControll++;
					backWardControll--;
				} else {
					backWardControll--;
				}
			}
		}
	}

	public static void main(String args[]) {
		int[] Integerarray = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		getTriplet(Integerarray);
	}

}