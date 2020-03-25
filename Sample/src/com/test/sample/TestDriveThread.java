package com.test.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Application for Test Drive management
 * @author 1022923
 *
 */
class TestDriveThread implements Runnable {

	private boolean exit;
	ArrayList<String> testDrivedCars = new ArrayList<String>() {
		{
			add("car1");
			add("car2");
			add("car3");
		}
	};
	public Map<String, ArrayList<String>> testDrivedCarStorage = new HashMap<String, ArrayList<String>>() {
		{
			put("Hari", testDrivedCars);
		}
	};

	private String carName;
	private String personName;

	public void WorkerThread(String a, String b) {
		this.carName = a;
		this.personName = b;

	}

	public void run() {
		testDriveStartThread();
		if (!exit) {
			testDriving();
			testDriveEndThread();
		}
	}

	public synchronized void testDriveStartThread() {
		ArrayList<String> droveCars = testDrivedCarStorage.get(this.personName);
		if (droveCars == null) {
			ArrayList<String> carDrove = new ArrayList<String>();
			carDrove.add(this.carName);

			testDrivedCarStorage.put(this.personName, carDrove);

		} else if (droveCars.contains(this.carName)) {
			stop();
		}

	}

	public synchronized void testDriving() {
		System.out.println("test Drving.......");
	}

	private synchronized void testDriveEndThread() {
		ArrayList<String> droveCars = testDrivedCarStorage.get(this.personName);

		droveCars.add(this.carName);
		testDrivedCarStorage.put(this.personName, droveCars);
		for (Map.Entry<String, ArrayList<String>> entry : testDrivedCarStorage.entrySet()) {
			ArrayList<String> v = entry.getValue();
			System.out.println("The Person sucessfully test drive the car");
			getUserInput();
		}

	}

	private synchronized void getUserInput() {
		Scanner sc = new Scanner(System.in); // System.in is a standard input stream
		System.out.print("Enter Person Name - ");
		String personName = sc.nextLine();
		System.out.print("Enter car Name - ");
		String carName = sc.nextLine();
		WorkerThread(carName, personName);
		run();
	}

	private void stop() {
		exit = true;
		System.out.println("The Person has already drove the car.Please select different one ");
		getUserInput();
	}

	public static void main(String args[]) {
		TestDriveThread m1 = new TestDriveThread();
		Scanner sc = new Scanner(System.in); // System.in is a standard input stream
		System.out.print("Enter Person Name - ");
		String personName = sc.nextLine();
		System.out.print("Enter car Name - ");
		String carName = sc.nextLine();
		m1.WorkerThread(carName, personName);
		Thread t1 = new Thread(m1);

		t1.start();
	}
}
