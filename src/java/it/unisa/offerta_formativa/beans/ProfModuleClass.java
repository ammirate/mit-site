package it.unisa.offerta_formativa.beans;

/**
 * 
 * @author Alessandro
 *
 */
public class ProfModuleClass {
	private int idProf;
	private int idClass;
	private int idModule;
	
	public ProfModuleClass(){
		idProf=-1;
		idClass=-1;
		idModule=-1;
	}
	
	public ProfModuleClass(int idProf, int idClass, int idModule){
		System.out.println("id profModuleClass");
		System.out.println(idProf);
		System.out.println(idClass);
		System.out.println(idModule);
		if(idProf>=0 && idClass>=0 && idModule>=0){
			this.idProf = idProf;
			this.idClass = idClass;
			this.idModule=idModule;
		}else{throw new IllegalArgumentException("parameters cannot be negative");}
	}
	
	
	
	public int getIdProf() {
		return idProf;
	}

	public void setIdProf(int idProf) {
		if(idProf>0)this.idProf = idProf;
		else throw new IllegalArgumentException("id prof cannot be negative");
	}

	public int getIdClass() {
		return idClass;
	}

	public void setIdClass(int idClass) {
		if(idClass>0)this.idClass = idClass;
		else throw new IllegalArgumentException("id class cannot be negative");
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		if(idModule>0)this.idModule = idModule;
		else throw new IllegalArgumentException("id module cannot be negative");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "idProfessor="+idProf+", idClass="+idClass+", idModule="+idModule;
	}
	
	public String toStringInsertQuery(){
		return idProf+","+idClass+","+idModule;
	}
	
	
}
