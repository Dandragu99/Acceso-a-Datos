/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import org.hibernate.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Damiansu
 */
public class ConsultasHibernate {

    public static void main(String[] args) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        String hql = "from Departamentos";
        Query q = session.createQuery(hql);
        //Lo recorremos cargando todo en memoria de Java
        //Hacemos una  única petición a la BBDD
        System.out.println("list()");
        List<Departamentos> list = q.list();
        Iterator<Departamentos> iter = list.iterator();

        while (iter.hasNext()) {
            Departamentos d = (Departamentos) iter.next();
            System.out.println(d.getDnombre());
        }
        //Hacemos múltiples peticiones
        System.out.println("iterate()");
        String hql2 = "from Departamentos";
        Query q2 = session.createQuery(hql2);
        Iterator iter2 = q2.iterate();
        while (iter2.hasNext()) {
            Departamentos d = (Departamentos) iter2.next();
            System.out.println(d.getDnombre());
        }

        //Fijarse bien dónde lo metemos
        //Lo metemos en empleados
        System.out.println("Empleados del departamento 20");
        String hql3 = "from Empleados as e where e.departamentos.deptNo=20";
        Query q3 = session.createQuery(hql3);
        Iterator iter3 = q3.iterate();
        while (iter3.hasNext()) {
            Empleados e = (Empleados) iter3.next();
            System.out.println(e.getApellido());
        }

        //Meter un inner join en mis dos objetos
        System.out.println("JOIN");
        String hql4 = "from Empleados e, Departamentos d where"
                + " e.departamentos.deptNo = d.deptNo order by Apellido";
        Query q4 = session.createQuery(hql4);
        Iterator iter4 = q4.iterate();
        while (iter4.hasNext()) {
            Object[] partes = (Object[]) iter4.next();
            Empleados e = (Empleados) partes[0];
            Departamentos de = (Departamentos) partes[1];
            System.out.println(e.getApellido() + " " + de.getDnombre());
        }

        //Consulta con una clase creada para trabajar con ese objeto
        String hql5 = "select new empresa.Totales(d.deptNo, count(e.empNo), "
                + "coalesce(avg(e.salario),0), d.dnombre)"
                + " from Empleados as e"
                + " right join e.departamentos as d"
                + " group by d.deptNo, d.dnombre";
        System.out.println("Creo una clase y lo meto");

        Query q5 = session.createQuery(hql5);
        Iterator iter5 = q5.iterate();

        while (iter5.hasNext()) {
            Totales2 t = (Totales2) iter5.next();
            System.out.println(t.getCuenta() + " " + t.getMedia() + " ");;
        }
                    
        session.close();
        sesion.close();
    }
}
