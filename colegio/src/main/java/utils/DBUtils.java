package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBUtils {
	
	//ENTITYMANAGERFACTTORY - JPA
	private static EntityManagerFactory entityManagerFactory; // Las variables estáticas son las que pertenecen a la clase y no al objeto.
	
	// Implementamos el patrón singleton, lo cual nos garantiza que solo haya un objeto creado de SessionFactory en nuestra app
	public static EntityManagerFactory creadorEntityManager() {
	    if (entityManagerFactory == null) {
	        try {
	            entityManagerFactory = Persistence.createEntityManagerFactory("colegio"); // Será el nombre de nuestra unidad de persistencia en el archivo persistence.xml
	        } catch (Throwable ex) {
	            System.err.println("Error al crear el objeto SessionFactory" + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	    return entityManagerFactory;
	}
	

	
	//SESSIONFACTORY - HIBERNATE
    private static SessionFactory sessionFactory; 
    // Las variables estáticas son las que pertenecen a la clase y no al objeto.
    // Es decir, esta variable es compartida por todos los objetos que
    // se creen de esta clase

    // Implementamos el patrón singleton, lo cual nos garantiza que solo haya un objeto creado de SessionFactory en nuestra app

    public static SessionFactory creadorSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Error al crear el objeto SessionFactory" + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }
	
	

	public static Connection conexion() {
		Connection connection = null;
		Context ctx = null;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/miBBDD");
			connection = ds.getConnection();
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return connection;

	}

}
