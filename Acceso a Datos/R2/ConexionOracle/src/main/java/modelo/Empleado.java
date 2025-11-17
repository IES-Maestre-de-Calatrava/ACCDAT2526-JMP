/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import com.mycompany.conexionoracle.Conexionoracle;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 *
 * @author Javier Molina
 * Created on 21 oct 2025
 */
public class Empleado {
    
    private int emp_no;
    private String apellido;
    private String oficio;
    private int dir;
    private Date fecha_alt;
    private double salario;
    private double comision;
    private int dept_no;
    
    private final static String CONSULTA_SELECT_ALL = "SELECT e.* FROM Empleados e";
    private final static String CONSULTA_SELECT_BY_ID = "SELECT e.* FROM Empleados e WHERE e.emp_no = ?";
    
    private final static String CONSULTA_SELECT_BY_DEPARTAMENTO = "SELECT e.*, d.dnombre "
                                                                + "FROM Empleados e "
                                                                + "JOIN Departamentos d ON e.dept_no = d.dept_no "
                                                                + "WHERE e.dept_no = ?";
    
    private final static String CONSULTA_SELECT_ORDER_BY_DEPT = "SELECT e.* FROM Empleados e ORDER BY e.dept_no";
    
    private final static String INSERT = "insert into Empleados values (?,?,?,?,?,?,?,?)";
    private final static String INSERTAUTO = "insert into Empleados (emp_no, apellido, oficio) values (?,?,?)";
    private final static String UPDATE = "update Empleados set apellido=?, oficio=? where dept_no=?";
    private final static String DELETE = "delete from Empleados where dept_no=?";

    
    
    public Empleado(){
        
    }
    
    public Empleado(int emp_no, String apellido, String oficio) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
    }
    
    public Empleado(int emp_no, String apellido, String oficio, int dir, String fecha_alt, double salario, double comision, int dept_no) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;      
        this.salario = salario;
        this.comision = comision;
        this.dept_no = dept_no;
        
        this.fecha_alt = convertirFecha(fecha_alt);
    }
    
    public int getEmp_no() {
        return emp_no;
    }

    public String getApellido() {
        return apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public int getDir() {
        return dir;
    }

    public Date getFecha_alt() {
        return fecha_alt;
    }

    public double getSalario() {
        return salario;
    }

    public double getComision() {
        return comision;
    }

    public int getDept_no() {
        return dept_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setFecha_alt(String fecha_alt) {
        //this.fecha_alt = OperacionesBBDD.convertirFecha(fecha_alt);
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }
    
    @Override
    public String toString() {
        return "Empleado{" + "emp_no=" + emp_no + ", apellido=" + apellido + ", oficio=" + oficio + ", dir=" + dir + ", fecha_alt=" + fecha_alt + ", salario=" + salario + ", comision=" + comision + ", dept_no=" + dept_no + '}';
    }    
    
    // FUNCIONA
    private Date convertirFecha(String fecha) {
        try {
        // 1. Definir el formateador para "DD/MM/YYYY"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // 2. Parsear el String a un objeto de solo fecha (LocalDate)
        LocalDate localDate = LocalDate.parse(fecha, formatter);
        
        // 3. Convertir el LocalDate a java.sql.Date.
        // ESTO ES CLAVE: Date.valueOf(LocalDate) crea un sql.Date sin problemas de hora/zona horaria.
        return java.sql.Date.valueOf(localDate); 
        
        } catch (java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException("Error de formato de fecha: " + fecha, e);
        }
    }
    
    
    
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        Optional<ResultSet> rs = Optional.empty();
        
        try {
            rs = bbdd.select(CONSULTA_SELECT_ALL);
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return rs;
    }
    
    public void SelectById(OperacionesBBDD bbdd, int emp_no){
        Optional<ResultSet> rs = null;
        
        try {
            double salario = 0.0;
            int contadorEmpleados =0;
            
            rs = bbdd.select(CONSULTA_SELECT_BY_ID, emp_no); 
            if(rs.isPresent()){
                while (rs.get().next()) {
                    this.setEmp_no(rs.get().getInt("emp_no"));
                    this.setApellido(rs.get().getString("apellido"));
                    this.setOficio(rs.get().getString("oficio"));
                    this.setDir(rs.get().getInt("dir"));
                    this.setFecha_alt(rs.get().getString("fecha_alt"));
                    this.setSalario(rs.get().getDouble("salario"));
                    this.setComision(rs.get().getDouble("comision"));
                    this.setDept_no(rs.get().getInt("salario"));

                }
            }
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    
    
    public void SelectByDepartamento(OperacionesBBDD bbdd, int dept_no){
        Optional<ResultSet> rs = null;
        
        try {
            double salario = 0.0;
            
            rs = bbdd.select(CONSULTA_SELECT_BY_DEPARTAMENTO, dept_no); 
            if(rs.isPresent()){
                while (rs.get().next()) {
                    String apellido = rs.get().getString("apellido");
                    String oficio = rs.get().getString("oficio");
                    double salarioEmp = rs.get().getDouble("salario");
                    String ndepartamento = rs.get().getString("dnombre");
                    System.out.println("Apellido: "+ apellido+", Oficio: "+oficio+", Salario: "+salarioEmp+", ndepart: "+ndepartamento);
                    
                    salario += salarioEmp;
                }
                
                int empleados = OperacionesBBDD.numeroFilasResultSet(rs.get());
                System.out.println("Total empleados: "+empleados);

                
                if(empleados > 0){
                    System.out.println("Salario medio: "+(salario/empleados)+", Numero total de empleados: "+empleados);
                }else{
                    System.out.println("El departamento con ID: "+dept_no+" no existe");
                }
            }
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    public void SelectEmpleadosYSalarioByDepartamento(OperacionesBBDD bbdd){
        Optional<ResultSet> rs = null;
        
        int ndepartAnterior = -1;
        int ndepart = 0;
        
        double salarioDept = 0.0;
        int empleadosDept = 0;
        
        double totalSalarioGeneral = 0.0;
        int totalEmpleadosGeneral = 0;
        
        try {
            rs = bbdd.select(CONSULTA_SELECT_ORDER_BY_DEPT); 
            
            if(rs.isPresent()){
                
                while (rs.get().next()) {
                    
                    String apellido = rs.get().getString("apellido");
                    String oficio = rs.get().getString("oficio");
                    double salarioEmp = rs.get().getDouble("salario");
                    ndepart = rs.get().getInt("dept_no");
                    
                     
                    if(ndepart != ndepartAnterior){
                        if(empleadosDept > 0){
                            System.out.println("Salario medio: "+(salarioDept/empleadosDept)+", Numero total de empleados: "+empleadosDept);
                            System.out.println();
                        }else{
                            System.out.println("El departamento con ID: "+dept_no+" no existe");
                        }
                        
                        totalEmpleadosGeneral += empleadosDept;
                        totalSalarioGeneral += salarioDept;
                        
                        salarioDept = 0;
                        empleadosDept = 0;
                    }
                    
                    
                    System.out.println("Apellido: "+ apellido+", Oficio: "+oficio+", Salario: "+salarioEmp+", Departamento: "+ndepart);
                    
                    salarioDept += salarioEmp;
                    empleadosDept++;
                    
                    ndepartAnterior = ndepart;

                } 
                
                // el ultimo departamento hay que hacerlo fuera del bucle pq la condicion de que sea distinto a otro no existe porque es el ULTIMO
                System.out.println("Salario medio: "+(salarioDept/empleadosDept)+", Numero total de empleados: "+empleadosDept);
                System.out.println();
                
                
                totalEmpleadosGeneral += empleadosDept;
                totalSalarioGeneral += salarioDept;
                System.out.println("Total empleados: "+ totalEmpleadosGeneral+" Total Salario Medio: "+ (totalSalarioGeneral/totalEmpleadosGeneral));
                
            }
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    public static void mostrarResultado(Optional<ResultSet> rsOptional){
        
        if(!rsOptional.isPresent()){
            System.out.println("No se obtuvieron resultados o hubo un error en la BBDD");
            return;
        }
        
        // Desempaquetar solo la UNA vez
        ResultSet rs = rsOptional.get();
        try {
            while (rs.next()){
                    System.out.println("Numero empleado: " + rs.getInt("emp_no") +
                        ". Apellido: " + rs.getString("apellido") + 
                        ". Oficio: " + rs.getString("oficio") + 
                        ". Dir: " + rs.getInt("dir") + 
                        ". Fecha_alt: " + rs.getString("fecha_alt") + 
                        ". Salario: " + rs.getDouble("salario") + 
                        ". Comision: " + rs.getDouble("comision") + 
                        ". Numero de departamento: " + rs.getInt("dept_no"));
                }
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    public static void motrarApellidoDirComisionSalario(OperacionesBBDD bbdd, Optional<ResultSet> rsOptional){
        
        if(!rsOptional.isPresent()){
            System.out.println("No se obtuvieron resultados o hubo un error en la BBDD");
            return;
        }
        
        // Desempaquetar solo la UNA vez
        ResultSet rs = rsOptional.get();
        try {
            while (rs.next()){
                    System.out.println(
                        "Apellido: " + rs.getString("apellido") + 
                        ". Salario: " + rs.getDouble("salario") + 
                        ". Comision: " + rs.getDouble("comision")+ 
                        ". Nomina tras aplicar irpf 20%: " + fNomina(bbdd, rs.getDouble("salario"), rs.getDouble("comision"), 20));
                }
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public String insertar(OperacionesBBDD bbdd){
        StringBuilder errores = new StringBuilder();
        
        if (this.salario < 0) {
            errores.append("ERROR: El salario debe ser una cantidad positiva (mayor que cero)\n");
        }
        
        if(this.apellido == null || this.oficio == null){
            errores.append("ERROR: El apellido u oficio no pueden ser nulos\n");
        }
        
        java.util.Date utilDate = new java.util.Date(this.fecha_alt.getTime());                

        LocalDate fechaAltaLocalDate = utilDate.toInstant() 
                                       .atZone(ZoneId.systemDefault()) // Esto usa tu zona horaria (CET)
                                       .toLocalDate();
        
        LocalDate hoy = LocalDate.now();
        
        if(!fechaAltaLocalDate.isEqual(hoy)){
            errores.append("ERROR: la fecha de alta debe ser la fecha de hoy\n");
        }
        
        if (errores.length() > 0) {
        return "Errores:\n" + errores.toString();
    }
        
        try {
            bbdd.insert(INSERT, this.emp_no, this.apellido, this.oficio, this.dir, this.fecha_alt, this.salario, this.comision, this.dept_no);            
            return "Insercion completa correctamente: Numero Empleado: "+this.emp_no+", Apellido: "+this.apellido+", Oficio: "+this.oficio+", Dir: "+this.dir+", Fecha actual: "+this.fecha_alt+", Salario: "+this.salario+", Comision: "+this.comision+", Numero de departamento: "+dept_no;
        
        } catch (SQLException ex) {
            return comprobarExcepcion(ex);
        }
    }
    
    public String insertarAuto(OperacionesBBDD bbdd){
        String rowid = null;
        
        try {
            Optional<ResultSet> rs = bbdd.insert(INSERTAUTO, this.emp_no, this.apellido, this.oficio);
            
            if(rs.isPresent()){
                rs.get().next();
                rowid = rs.get().getString(1);
                
            }
            
            return rowid;
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return rowid;
    }
    
    public String update(OperacionesBBDD bbdd){
        int i;
        String mensaje="";
        try{
            i = bbdd.updateDeleteQuery(UPDATE, this.apellido, this.oficio, this.dir, this.fecha_alt, this.salario, this.comision, this.dept_no, this.emp_no);
                       
        }catch (SQLException ex) {
            return comprobarExcepcion(ex);
        }
       
        if (i == 0){
           mensaje =  "No existe el empleado";
        }
        else{
            mensaje = "Empleado modificado correctamente";
        }      
        return mensaje;
    }
    
    public String delete(OperacionesBBDD bbdd){
        int i = 0;
        String mensaje = "";
        try{
            i = bbdd.updateDeleteQuery(DELETE, this.dept_no);
        }catch(SQLException ex){
            return comprobarExcepcion(ex);
        }
        
        if (i == 0){
           mensaje =  "No existe el empleado";
        }
        else{
            mensaje = "Empleado modificado correctamente";
        }      
        return mensaje;
    }
    
    
    public static void pSubidaSalario(OperacionesBBDD bbdd, int dept_no, int cantidad){
        CallableStatement llamada;
        String sql = "{call p_subida_sal (?,?)}";
        
        try {
            llamada = bbdd.getConexion().prepareCall(sql);
            
            llamada.setInt(1, dept_no); // en ese primer paramatro va el dept_no
            
            llamada.setInt(2, cantidad); // el segundo parametro es la cantidad
            
            llamada.executeUpdate();  // Lo ejecuto
            
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    
    public static int fEmpleadosDepartamento(OperacionesBBDD bbdd, int dept_no){
        CallableStatement llamada;
        int numEmpleados = 0;
        String sql = "{? = call f_n_empleado (?)}";
        
        try {
            llamada = bbdd.getConexion().prepareCall(sql);
            
            llamada.registerOutParameter(1, Types.INTEGER); // el primer parametro es de salida
            
            llamada.setInt(2, dept_no); // en ese segundo paramatro va el dept_no, parametro entrada
                       
            llamada.executeUpdate();  // Lo ejecuto
            
            numEmpleados = llamada.getInt(1); // recupero el nombre de la salida con getString()
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return numEmpleados;
    }
    
    
    public static double fNomina(OperacionesBBDD bbdd, double salario, double comision, int irpf){
        CallableStatement llamada;
        double salarioFinal = 0;
        String sql = "{? = call f_nomina (?,?,?)}";
        
        try {
            llamada = bbdd.getConexion().prepareCall(sql);
            
            llamada.registerOutParameter(1, Types.DOUBLE); // el primer parametro es de salida
            
            llamada.setDouble(2, salario); 
                       
            llamada.setDouble(3, comision);
            
            llamada.setInt(4, irpf);
            
            llamada.executeUpdate();  // Lo ejecuto
            
            salarioFinal = llamada.getDouble(1); // recupero el nombre de la salida con get[tipo_dato]()
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return salarioFinal;
    }
    
    public String comprobarExcepcion(SQLException ex){
        String mensaje = "";

            if(ex.getErrorCode() == 2291){
                
                if(ex.getMessage().contains("FK_DEP")){
                    mensaje ="El departamento no existe";
                } else if (ex.getMessage().contains("FK_DIR_EMPLEADOS")){
                    mensaje = "El director no existe";
                }
            }else if(ex.getErrorCode() == 2292){
                mensaje= "Error: Violación de integridad referencial (Clave Foránea)";
            
            }else if(ex.getErrorCode() == 1){
                mensaje = "ERROR: El número de empleado ya existe en la base de datos (Clave duplicada)";
            }
            return mensaje;
    }
    
    public void subirSalarioPorDepartamento(OperacionesBBDD bbdd, double salario, int dept_no) throws SQLException{
        try{
            Empleado em  = new Empleado();
            Optional<ResultSet> rs = em.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL UPDATE================================");
            em.mostrarResultado(rs);
                        
            rs.get().beforeFirst();
            while(rs.get().next()){
                if(rs.get().getInt(8) == dept_no){
                    
                    double salarioActual = rs.get().getDouble("salario");
                
                    double nuevoSalario = salarioActual + salario;
                    
                    rs.get().updateDouble("salario", nuevoSalario);
                    rs.get().updateRow();
                }
            }
        
            System.out.println("\n=========================MOSTRAR DESPUES DEL UPDATE==================================");
            rs.get().beforeFirst();
            em.mostrarResultado(rs);
        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }
    
    
    public void subirPorcentajeDeSalarioPorDepartamento(OperacionesBBDD bbdd, int porcentaje, int dept_no) throws SQLException{
        try{
            Empleado em  = new Empleado();
            Optional<ResultSet> rs = em.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL UPDATE================================");
            em.mostrarResultado(rs);
                        
            rs.get().beforeFirst();
            while(rs.get().next()){
                if(rs.get().getInt(8) == dept_no){
                    
                    double salarioActual = rs.get().getDouble("salario");
                
                    double nuevoSalario = salarioActual + (salarioActual*(porcentaje/100.00)); // divide entre double que si no da 0
                    
                    rs.get().updateDouble("salario", nuevoSalario);
                    rs.get().updateRow();
                }
            }
        
            System.out.println("\n=========================MOSTRAR DESPUES DEL UPDATE==================================");
            rs.get().beforeFirst();
            em.mostrarResultado(rs);
        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }
    
    public static void insertSobreResultSet(OperacionesBBDD bbdd, Empleado empleado) throws SQLException, InterruptedException{
        try{
            
            Optional<ResultSet> rs = empleado.selectAll(bbdd);
            
            System.out.println("======================MOSTRAR ANTES DEL INSERT================================");
            //departamento.mostrarResultado(rs);
            empleado.mostrarResultado(rs);
    
            // cojo la fecha de hoy
            LocalDate fechaLocalDate = LocalDate.now();
        
            // convertir LocalDate a java.sql.Date
            Date fechaSql = Date.valueOf(fechaLocalDate);
            
            int numFila = rs.get().getRow();
            rs.get().moveToInsertRow();
            numFila = rs.get().getRow();
            
            rs.get().updateInt(1, 9999);
            rs.get().updateString(2, "MOLINA");
            rs.get().updateString(3, "EMPLEADO");
            rs.get().updateInt(4, 7566);
            rs.get().updateDate(5, fechaSql);
            rs.get().updateDouble(6, 1800.56);
            rs.get().updateDouble(7, 2.00);
            rs.get().updateInt(8, 15);
            
            rs.get().insertRow();
            rs.get().moveToCurrentRow();
            numFila = rs.get().getRow();

            System.out.println("\n=========================MOSTRAR DESPUES DEL INSERT==================================");
            rs.get().beforeFirst();
            empleado.mostrarResultado(rs);
            System.out.println("NumFilas: "+numFila);
        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }
    
    public static void insertSobreResultSet2(OperacionesBBDD bbdd, Empleado empleado) throws SQLException, InterruptedException{
        try{
            
            Optional<ResultSet> rs = empleado.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL INSERT================================");
            empleado.mostrarResultado(rs);
                              
            int numFila = rs.get().getRow();
            rs.get().moveToInsertRow();
            numFila = rs.get().getRow();
            
            rs.get().updateInt(1, empleado.getEmp_no());
            rs.get().updateString(2, empleado.getApellido());
            rs.get().updateString(3, empleado.getOficio());
            rs.get().updateInt(4, empleado.getDir());
            rs.get().updateDate(5, empleado.getFecha_alt());
            rs.get().updateDouble(6, empleado.getSalario());
            rs.get().updateDouble(7, empleado.getComision());
            rs.get().updateInt(8, empleado.getDept_no());
            
            rs.get().insertRow();
            rs.get().moveToCurrentRow();
            numFila = rs.get().getRow();

            System.out.println("\n=========================MOSTRAR DESPUES DEL INSERT==================================");
            rs.get().beforeFirst();
            empleado.mostrarResultado(rs);
            System.out.println("NumFilas: "+numFila);
        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }
    
    public static void deleteSobreResultSetPorCampo(OperacionesBBDD bbdd, int dept_no)throws SQLException, InterruptedException{
        try{
            int deptNoAEliminar = dept_no;
            Empleado empleado  = new Empleado();
            Optional<ResultSet> rs = empleado.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL DELETE================================");
            empleado.mostrarResultado(rs);
            
            rs.get().beforeFirst();
            boolean encontrado = false;
        
            while (rs.get().next() && encontrado == false) {
                if (rs.get().getInt(8) == deptNoAEliminar) {
                    rs.get().deleteRow(); 
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró el empleado");
            }
        
            System.out.println("\n=========================MOSTRAR DESPUES DEL DELETE==================================");
            rs.get().beforeFirst();
            empleado.mostrarResultado(rs);
            System.out.println("Empleados con DEPT_NO " + deptNoAEliminar + " eliminados correctamente");

        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }
    
    public static void deleteSobreResultSetPorObjeto(OperacionesBBDD bbdd, Empleado empleado)throws SQLException, InterruptedException{
        try{
            int emp_no = empleado.getEmp_no();
            String apellido = empleado.getApellido();
            String oficio = empleado.getOficio();
            int dir = empleado.getDir();
            Date fecha = empleado.getFecha_alt();
            Double salario = empleado.getSalario();
            Double comision = empleado.getComision();
            int dept_no = empleado.getDept_no();
            
            Optional<ResultSet> rs = empleado.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL DELETE================================");
            empleado.mostrarResultado(rs);
            
            rs.get().beforeFirst();
            boolean encontrado = false;
        
            while (rs.get().next() && encontrado == false) {
                if ((rs.get().getInt(1) == emp_no) && (rs.get().getString(2).equals(apellido)) && (rs.get().getString(3).equals(oficio)) 
                     && (rs.get().getInt(4) == dir) && (rs.get().getDate(5).equals(fecha)) && (rs.get().getDouble(6) == salario) 
                     && (rs.get().getDouble(7) == comision)&& (rs.get().getInt(8) == dept_no)) {
                        rs.get().deleteRow(); 
                        encontrado = true;   
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró el empleado");
            }
        
            System.out.println("\n=========================MOSTRAR DESPUES DEL DELETE==================================");
            rs.get().beforeFirst();
            empleado.mostrarResultado(rs);
            System.out.println("Empleado eliminado correctamente");

        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }

    public static void updateSobreResultSetPorCampo(OperacionesBBDD bbdd, String oficio) throws SQLException, InterruptedException{
        try{
            Empleado em = new Empleado();
            Optional<ResultSet> rs = em.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL UPDATE================================");
            em.mostrarResultado(rs);
            
            
            rs.get().beforeFirst();
            while(rs.get().next()){
                rs.get().updateString("oficio", oficio);
                rs.get().updateRow();
            }
        
            System.out.println("\n=========================MOSTRAR DESPUES DEL UPDATE==================================");
            rs.get().beforeFirst();
            em.mostrarResultado(rs);
        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }
    
    /*
    public static void updateSobreResultSetPorObjeto(OperacionesBBDD bbdd, Empleado em) throws SQLException, InterruptedException{
        try{
            Optional<ResultSet> rs = em.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL UPDATE================================");
            em.mostrarResultado(rs);
            
            
            rs.get().beforeFirst();
            while(rs.get().next()){
                rs.get().updateInt("emp_no", em.getEmp_no());
                rs.get().updateString("apellido", em.getApellido());
                rs.get().updateString("oficio", em.getOficio());
                rs.get().updateInt("dir", em.getDir());
                rs.get().updateDate("fecha_alt", em.getFecha_alt());
                rs.get().updateDouble("salario", em.getSalario());
                rs.get().updateDouble("comision", em.getComision());
                rs.get().updateInt("dept_no", em.getDept_no());
                rs.get().updateRow();
            }
        
            System.out.println("\n=========================MOSTRAR DESPUES DEL UPDATE==================================");
            rs.get().beforeFirst();
            em.mostrarResultado(rs);
        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }
*/
}
