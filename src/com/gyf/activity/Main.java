package com.gyf.activity;

public class Main {

	public static void main(String[] args) {

		Execute execute = new Execute();
		execute.showOptions();
		execute.executeOption(execute.getInput());
	}

}
