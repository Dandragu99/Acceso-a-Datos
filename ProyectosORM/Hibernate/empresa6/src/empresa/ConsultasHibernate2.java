/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import org.hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Damiansu
 */
public class ConsultasHibernate2 {

    public static void main(String[] args) {
        SessionFactory session = HibernateUtil.getSessionFactory();
        Session sesion = session.openSession();

        String hql = "from Departamentos";
        Query q = sesion.createQuery("from Departamentos");

        ArrayList<Departamentos> depar
                = (ArrayList<Departamentos>) q.list();

        //ESTOY EN JAVA... ME da igual HIbernate
        for (Departamentos d : depar) {
            System.out.println(d.getDnombre());
        }
        System.out.println("Oficio de empleados");
        String hql2 = "from Empleados";
        Query q2 = sesion.createQuery(hql2);

        q2.setFetchSize(10);
        Iterator iter = q2.iterate();
        while (iter.hasNext()) {
            Empleados e = (Empleados) iter.next();
            System.out.println(e.getOficio());
        }
        
        System.out.println("Objects de clases principales (Existen)");
        String hql3 = "from Empleados e, Departamentos d where"
                + " e.departamentos.deptNo = d.deptNo order by Apellido";
        Query q3 = sesion.createQuery(hql3);
        List lista = q3.list();
        Iterator iter3 = lista.iterator();
        while (iter3.hasNext()) {
            Object[] partes = (Object[]) iter3.next();
            Empleados e = (Empleados) partes[0];
            Departamentos d = (Departamentos) partes[1];
            System.out.println(e.getApellido() + " " + d.getLoc());

        }

        System.out.println("Objects de clases no existen... que los coja Object");
        String hql4 = "select e.departamentos.deptNo, avg(salario), count(empNo) "
                + "from Empleados e group by e.departamentos.deptNo";
        Query q4 = sesion.createQuery(hql4);
        iter = q4.iterate();

        while (iter.hasNext()) {
            Object[] partes = (Object[]) iter.next();
            Byte d = (Byte) partes[0];
            Double media = (Double) partes[1];
            Long cuenta = (Long) partes[2];
            System.out.println(d + " " + media + " " + cuenta);

        }

        String hql5 = "select "
                + "new empresa.Totales2(d.deptNo, count(e.empNo), "
                + "coalesce(avg(e.salario),0), d.dnombre)"
                + " from Empleados"
                + " as e right join e.departamentos"
                + " as d group by d.deptNo, d.dnombre";
        System.out.println("Creo una clase y lo meto");
        Query q5 = sesion.createQuery(hql5);
        iter = q5.iterate();

        while (iter.hasNext()) {
            Totales2 t = (Totales2) iter.next();

            System.out.println(t.getCuenta() + " " + t.getMedia() + " " + t.getNumero());

        }

        System.out.println("Damián me ha dicho que solo"
                + " hay un registro en la salida");
        String hql6 = "select avg(em.salario) "
                + "from Empleados as em";
        Query q6 = sesion.createQuery(hql6);

        Double media = (Double) q6.uniqueResult();
        System.out.println("La media de salario es: " + media);

        System.out.println("Consulta parametizada");
        String hql7 = "from Empleados as e where "
                + "e.departamentos.deptNo = :ndep and "
                + "e.oficio = :ofi";
        Query q7 = sesion.createQuery(hql7);
        //pedimos los datos al usuario
        //tratamos los datos para que no afecten a mi aplicación
        //Oficio DevOPs

        //
        q7.setParameter("ndep", (byte) 30);
        q7.setParameter("ofi", "VENDEDOR");

        List<Empleados> lista7 = q7.list();
        Iterator<Empleados> iter7 = lista7.iterator();
        while (iter7.hasNext()) {
            Empleados e = (Empleados) iter7.next();
            System.out.println(e.getApellido());
        }

        sesion.close();
        session.close();

    }

}
