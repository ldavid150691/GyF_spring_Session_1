package com.gyf.activity;

import java.util.ArrayList;
import java.util.Scanner;

import com.gyf.activity.model.ToDoModel;

public class Execute {

	private ArrayList<ToDoModel> toDoList;

	public Execute() {
		toDoList = new ArrayList<>();
	}

	public void showOptions() {

		String options[] = {

				"a. Agregar nuevas tareas",

				"b. Listar todas las tareas",

				"c. Eliminar tareas por Id",

				"d. Cambiar el estado de la tarea",

				"e. Salir"

		};

		System.out.println(" Funcionalidades To Do List: \n");

		for (String itemMenu : options) {

			System.out.println(itemMenu);

		}

		System.out.println(" Por favor introduzca la funcionalidad a realizar por teclado: (a - b - c - d) \n");
	}

	public String getInput() {
		Scanner scaner = new Scanner(System.in);

		return scaner.nextLine();
	}

	public void executeOption(String option) {

		switch (option.toLowerCase()) {
		case "a":
			System.out.println("Escriba la tarea a guardar:");
			createTask(getInput());
			showOptions();
			executeOption(getInput());
			break;
		case "b":
			showAllToDoList();
			showOptions();
			executeOption(getInput());
			break;
		case "c":
			System.out.println("Escriba el código de la tarea a eliminar: \n ");
			try {
				deleteById(Integer.parseInt(getInput()));
				showOptions();
				executeOption(getInput());
			} catch (Exception e) {
				System.out.println("El código es un número: \n ");
				executeOption("c");
			}
			break;
		case "d":
			taskForUpdate();
			showOptions();
			executeOption(getInput());
			break;
		case "e":
			System.out.println("Chao!");
			break;
		default:
			System.out.println("Opcion invalida, elija nuevamente una opcion vália. \n");
			executeOption(getInput());
			break;
		}

	}

	private void createTask(String text) {

		ToDoModel model = new ToDoModel();
		model.setDescription(text);
		model.setState("TO_DO");
		toDoList.add(model);

	}

	public void showAllToDoList() {

		for (ToDoModel toDoModel : toDoList) {
			System.out.println("- Código: " + toDoModel.hashCode() + "  Descripción de la tarea: "
					+ toDoModel.getDescription() + " , Estado: " + toDoModel.getState() + "\n");
		}
	}

	private void deleteById(int code) {

		String message = toDoList.removeIf(item -> item.hashCode() == code) ? "Tarea eliminada \n"
				: "Tarea no se pudo eliminar \n";
		System.out.println(message);
	}

	private int getIndexFromCode(int code) {
		// ToDoModel model = toDoList.stream().filter(item -> code ==
		// item.hashCode()).findAny().orElse(null);
		int result = -1;
		for (int i = 0; i < toDoList.size(); i++) {
			if (toDoList.get(i).hashCode() == code) {
				result = i;
			}
		}
		return result;
	}

	private void taskForUpdate() {
		int task = 0;
		System.out.println("Escriba el código de la tarea a actualizar: \n ");
		try {
			task = getIndexFromCode(Integer.parseInt(getInput()));

			if (task == -1) {
				System.out.println("Tarea no encontrada.\n");
			} else {
				updateTask(task);
			}
		} catch (Exception e) {
			System.out.println("El código debe ser un número. \n");
			taskForUpdate();
		}

	}

	private void updateTask(int index) {
		String options[] = { "a. Por hacer", "b. Terminada" };
		System.out.println(" Elija el estado: \n");
		for (String itemMenu : options) {
			System.out.println(itemMenu);
		}
		ToDoModel doModel = toDoList.get(index);
		switch (getInput().toLowerCase()) {
		case "a":
			doModel.setState("TO_DO");
			toDoList.set(index, doModel);

			break;
		case "b":
			doModel.setState("DONE");
			toDoList.set(index, doModel);
			break;
		default:
			System.out.println("Opcion invalida. \n");
			updateTask(index);
			break;
		}

	}

}
