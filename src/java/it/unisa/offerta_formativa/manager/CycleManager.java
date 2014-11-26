package it.unisa.offerta_formativa.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unisa.offerta_formativa.beans.Cycle;

public class CycleManager {
	
	private static CycleManager instance = null;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private static String TABLE="cycle";
	private static String PKEY ="id";
	
	private CycleManager(){
		conn = DBConnector.getConnection();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean createCycle(Cycle c){
		try {
			if(stmt.executeUpdate("INSERT INTO "+TABLE+"(id,title) VALUES("+c.getInsertQuery()+")")==1)return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Cycle readCycle(int c){
		return null;
	}
	
	public boolean updateCycle(Cycle c){
		return false;
	}
	
	public boolean deleteCycle(int c){
		return false;
	}
	
	public ArrayList<Cycle> getAllCycles(){
		ArrayList<Cycle> toReturn = new ArrayList<Cycle>();
		try {
			System.out.println("SELECT * FROM "+TABLE);
			rs = stmt.executeQuery("SELECT * FROM "+TABLE);
			while(rs.next()){
				toReturn.add(new Cycle(rs.getInt("id"),rs.getString("title")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public static CycleManager getInstance(){
		if(instance==null)
			instance=new CycleManager();
		return instance;
	} 
	
	
}
