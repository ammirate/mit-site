package it.unisa.offerta_formativa.facade;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


import it.unisa.offerta_formativa.beans.ClassPartition;
import it.unisa.offerta_formativa.beans.Department;
import it.unisa.offerta_formativa.beans.Module;
import it.unisa.offerta_formativa.beans.ProfModuleClass;
import it.unisa.offerta_formativa.beans.Teaching;
import it.unisa.offerta_formativa.manager.ClassManager;
import it.unisa.offerta_formativa.manager.DegreeManager;
import it.unisa.offerta_formativa.manager.DepartmentManager;
import it.unisa.offerta_formativa.manager.ModuleManager;
import it.unisa.offerta_formativa.manager.ProfModuleClassManager;
import it.unisa.offerta_formativa.manager.TeachingManager;

public class InsertionTeachingFacade {
	
	private String department;
	private String degree;
	private String teaching;
	private int modulesNumber;
	private int profPerModuleNumber;
	
	/**
	 * Struct:
	 * 				KEY				VALUE
	 * 			|	itpm	|-> prof1, prof2, prof3
	 * 			|	itsm	|-> prof4, prof5
	 */
	private HashMap<String, ArrayList<String>> map;
	
	
	
	public InsertionTeachingFacade(){
		map = new HashMap<String, ArrayList<String>>();
	}
	
	
	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public String getTeaching() {
		return teaching;
	}


	public void setTeaching(String teaching) {
		this.teaching = teaching;
	}


	public int getModulesNumber() {
		return modulesNumber;
	}


	public void setModulesNumber(int modulesNumber) {
		this.modulesNumber = modulesNumber;
	}


	public int getprofPerModuleNumber() {
		return profPerModuleNumber;
	}


	public void setProfPerModuleNumber(int profPerModuleNumber) {
		this.profPerModuleNumber = profPerModuleNumber;
	}
	
	public void addProfessorToModule(String module, String prof){
		map.get(module).add(prof);
	}

	public void createModule(String module){
		map.put(module, new ArrayList<String>());
	}

	
	
	public String toString(){
		return map.toString();
	}

	
	public void stroreInDB(){
		/**
		 * take all managers
		 */
		TeachingManager teachingM = TeachingManager.getInstance();
		ModuleManager moduleM = ModuleManager.getInstance();
		ClassManager classM = ClassManager.getInstance();
		ProfModuleClassManager joinTable = ProfModuleClassManager.getInstance();
		
		/**
		 * create new teaching
		 */
		Teaching t = new Teaching(this.getTeaching(), this.getTeaching(), "00000", "", 1, 1, true);
		if(teachingM.createTeaching(t))
			System.out.println("Teaching inserted correctly");
		else
			System.out.println("Teaching not inserted ");

		/**
		 * creation modules
		 */
		int k=1;
		ArrayList<Module> modules = new ArrayList<Module>();
		for(String s : map.keySet()){
			Module m = new Module(t.getMatricula(), s);
			m.setIdModule(k++);
			modules.add(m);
			moduleM.createModule(m);
		}
		
		/**
		 * creation classes
		 */
		ClassPartition cp = new ClassPartition(t.getMatricula(), t.getTitle());
		cp.setIdClass(1);
		classM.createClass(cp);
		
		/**
		 * creation relationship between professors and modules
		 */
		ArrayList<ProfModuleClass> joinList = new ArrayList<ProfModuleClass>();
		for(int i=0; i<modules.size(); i++){
			ProfModuleClass pmc = new ProfModuleClass(i+1, cp.getIdClass(), modules.get(i).getIdModule());
			joinList.add(pmc);
			joinTable.create(pmc);
		}
		
		
	}
	
	/**
	 * TEST
	 * @param args
	 */
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		InsertionTeachingFacade facade = new InsertionTeachingFacade();
		
		System.out.println("Inserisci dipartimento");
		facade.setDepartment(in.next());
		
		System.out.println("Inserisci il corso di laurea");
		facade.setDegree(in.next());
		
		System.out.println("Inserisci nome insegnamento");
		facade.setTeaching(in.next());
		
		System.out.println("quanti moduli?");
		facade.setModulesNumber(in.nextInt());
		
		System.out.println("Inserisci il numero di prof associati a ogni modulo");
		facade.setProfPerModuleNumber(in.nextInt());
		
		
		for(int i=0; i<facade.getModulesNumber();i++){
			
			System.out.println("Inserisci il nome del "+ (i+1) + " modulo");
			String module = in.next();
			facade.createModule(module);
			
			for(int j=0; j<facade.profPerModuleNumber; j++){
				System.out.println((j+1) + "o professore: ");
				facade.addProfessorToModule(module, in.next());
			}
			
		}
		
		System.out.println(facade);
		
		facade.stroreInDB();
		
	}
	
	
}
