/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utng.bda.alumno;
import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/**
 *
 * @author benru
 */
public class Conexion {
   // objectContainer db = Db4o.openFile();
    private ObjectContainer OC;
    
    
    private void open(){
    	try {
    		this.OC = Db4oEmbedded.openFile("db_school.yap");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public boolean insertar(Alumno objeto){
        try{
            this.open();
            OC.store(objeto);
            this.OC.close();
            return true;
        }catch(Exception e){
            System.out.println("crudalumno.insertAlumno(): " + e);
            return false;
        }
    }
	public boolean actualizar(Alumno original, Alumno changed){
    	 boolean result = false;
     	try{
         	this.open();
         	System.out.println("Objeto: "+original);
     		System.out.println("Alumno: "+changed);
     		ObjectSet query = this.OC.queryByExample(original);
         	System.out.println(query);
         	if (query.hasNext()) {
         		original = (Alumno) query.next();
         		original.setNombreAlum(changed.getNombreAlum());
         		original.setApPaterno(changed.getApPaterno());
         		original.setApMaterno(changed.getApMaterno());
         		original.setFecNacimiento(changed.getFecNacimiento());
         		original.setCalif1(changed.getCalif1());
         		original.setCalif2(changed.getCalif2());
         		original.setCalif3(changed.getCalif3());
         		original.setCalif4(changed.getCalif4());
         		original.setCalif5(changed.getCalif5());
         		original.setCuatrimestre(changed.getCuatrimestre());
         		original.setCarrera(changed.getCarrera());
         		this.OC.store(original);
         		result = true;
         	}else {
         		JOptionPane.showMessageDialog(null, "Registro no encontrado");
         	}
         }catch(Exception e){
            System.out.println("crudalumno.ActualizarAlumno(): " + e);
            return result;
        }finally {
        	this.OC.close();
        	return result;
        }
    }
    public boolean eliminar(Alumno objeto){
        boolean result = false;
    	try{
        	this.open();
    		System.out.println(objeto);
        	ObjectSet<Alumno> query = this.OC.queryByExample(objeto);
        	if (query.hasNext()) {
        		objeto = query.next();
        		this.OC.delete(objeto);
        		result = true;
        	}else {
        		JOptionPane.showMessageDialog(null, "Registro no encontrado");
        	}
        }catch(Exception e){
        	e.printStackTrace();
            System.out.println("crudalumno.EliminarAlumno(): " + e);
        }finally {
        	this.OC.close();
        	return result;
        }
    }
    
    
    
        public Alumno[] Consultar() {
        	Alumno[] alumnos = null;
        	try {
            //CONSULTAMOS LOS OBJETOS ALMACENADOS EN LA BASE DE DATOS Y LOS RETORNAMOS EN UN ARREGLO DE TIPO Persona
            
            this.open();
            ObjectSet<Alumno> resultados = this.OC.query(Alumno.class);
            int i = 0;
            if (resultados.hasNext()) {
                alumnos = new Alumno[resultados.size()];
                while (resultados.hasNext()) {
                    alumnos[i] = (Alumno) resultados.next();
                    i++;
                }
            }
            this.OC.close();
            return alumnos;
        } catch (Exception e) {
            System.out.println("bdoo.Controlador.l() : " + e);
            return alumnos;
        }
    }
}
