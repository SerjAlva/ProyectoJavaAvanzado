package modelo;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class modelo extends database{
    int[] cliId, factId, vehId, factCost, vehFactId;
    String[] cliName, cliDir, vehPlaca, vehMod, vehMarca;
    public void leerClientes(Document clientes){
        try{
            File archivo = new File("C:\\Users\\LeSerge\\Documents\\NetBeansProjects\\ProyectoJavaAvanzado\\xmljava_avanz\\Clientes.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            System.out.println("Elemento Raiz: " + document.getDocumentElement().getNodeName());
            NodeList listaClientes = document.getElementsByTagName("cliente");
            cliId = new int[listaClientes.getLength()];
            cliName = new String[listaClientes.getLength()];
            cliDir = new String[listaClientes.getLength()];
            for(int i = 0; i < listaClientes.getLength(); i++){
                Node nodo = listaClientes.item(i);
                System.out.println("Elemento: " + nodo.getNodeName());
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element)nodo;
                    cliId[i] = Integer.parseInt(element.getAttribute("id"));
                    cliName[i] = element.getElementsByTagName("nombre").item(0).getTextContent();
                    cliDir[i] = element.getElementsByTagName("direccion").item(0).getTextContent();
//                    System.out.println(cliId[i]);
//                    System.out.println(cliName[i]);
//                    System.out.println(cliDir[i]);
//                    System.out.println("Id: " + element.getAttribute("id"));
//                    System.out.println("Nombre: " + element.getElementsByTagName("nombre").item(0).getTextContent());
//                    System.out.println("Direccion: " + element.getElementsByTagName("direccion").item(0).getTextContent());
                }
            }
        }catch(Exception ex){}
    }
    public void leerFacturas(Document facturas){
            try{
            File archivo = new File("C:\\Users\\LeSerge\\Documents\\NetBeansProjects\\ProyectoJavaAvanzado\\xmljava_avanz\\Facturas.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            System.out.println("Elemento Raiz: " + document.getDocumentElement().getNodeName());
            NodeList listaFacturas = document.getElementsByTagName("factura");
            factId = new int[listaFacturas.getLength()];
            factCost = new int[listaFacturas.getLength()];
            for(int i = 0; i < listaFacturas.getLength(); i++){
                Node nodo = listaFacturas.item(i);
                System.out.println("Elemento: " + nodo.getNodeName());
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element)nodo;
                    factId[i] = Integer.parseInt(element.getAttribute("id"));
                    factCost[i] = Integer.parseInt(element.getElementsByTagName("costo_total").item(0).getTextContent());
//                    System.out.println("Id: " + element.getAttribute("id"));
//                    System.out.println("Costo Total: " + element.getElementsByTagName("costo_total").item(0).getTextContent());
                }
            }
        }catch(Exception ex){}
    }
    public void leerVehiculos(Document vehiculos){
        try{
            File archivo = new File("C:\\Users\\LeSerge\\Documents\\NetBeansProjects\\ProyectoJavaAvanzado\\xmljava_avanz\\Vehiculos.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            System.out.println("Elemento Raiz: " + document.getDocumentElement().getNodeName());
            NodeList listaFacturas = document.getElementsByTagName("vehiculo");
            vehId = new int[listaFacturas.getLength()];
            vehPlaca = new String[listaFacturas.getLength()];
            vehMarca = new String[listaFacturas.getLength()];
            vehMod = new String[listaFacturas.getLength()];
            vehFactId = new int[listaFacturas.getLength()];
            for(int i = 0; i < listaFacturas.getLength(); i++){
                Node nodo = listaFacturas.item(i);
                System.out.println("Elemento: " + nodo.getNodeName());
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element)nodo;
                    vehId[i] = Integer.parseInt(element.getAttribute("id"));
                    vehPlaca[i] = element.getElementsByTagName("placas").item(0).getTextContent();
                    vehMarca[i] = element.getElementsByTagName("marca").item(0).getTextContent();
                    vehMod[i] = element.getElementsByTagName("modelo").item(0).getTextContent();
                    vehFactId[i] = Integer.parseInt(element.getElementsByTagName("id_factura").item(0).getTextContent());
//                    System.out.println("Id: " + element.getAttribute("id"));
//                    System.out.println("Placas: " + element.getElementsByTagName("placas").item(0).getTextContent());
//                    System.out.println("Marca: " + element.getElementsByTagName("marca").item(0).getTextContent());
//                    System.out.println("Modelo: " + element.getElementsByTagName("modelo").item(0).getTextContent());
//                    System.out.println("id factura: " + element.getAttribute("id"));
                }
            }
        }catch(Exception ex){}       
    }
    public void cargaClientes(){
        String q;
        for(int i = 0; i < cliId.length; i++){
            q = "INSERT TO CLIENTE (IDCLIENTE, NOMBRE, DIRECCION) VALUES (" + cliId[i] + ",'" + cliName[i] + "','" + cliDir[i] + "')";
            try{
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
            }catch(Exception e){}
        }
    }
    public void cargaFacturas(){
        String q;
        for(int i = 0; i < cliId.length; i++){
            q = "INSERT TO FACTURA (FACTURAID,MONTO) VALUES (" + factId[i] + "," + factCost[i] + ")";
            try{
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
            }catch(Exception e){}
        }
    }
    public void cargaVehiculos(){
        String q;
        for(int i = 0; i < cliId.length; i++){
            q = "INSERT TO VEHICULO (VEHICULOID, PLACA, MODELO, FACTURAID) VALUES (" + vehId[i] + ",'" + vehPlaca[i] + "','" + vehMarca[i] + "','"+ vehMod[i] + "'," + vehFactId[i] + ")";
            try{
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
            }catch(Exception e){}
        }
    }
    public void generarPoliza(){
        String[] polAper = new String[cliId.length];
        String[] polVenc = new String[cliId.length];
        double[] polCost = new double[cliId.length];
        double[] polPrim = new double[cliId.length];
        int[] polId = new int[cliId.length];
        int[] polCliId = new int[cliId.length];
        int[] polVehId = new int[cliId.length];
        
        for(int i = 0; i < cliId.length; i++){
            polId[i] = i;
            polCliId[i] = cliId[i];
            polVehId[i] = vehId[i];
            polCost[i] = (factCost[i]*6.67)/1200;
            polPrim[i] = (factCost[i]*0.85);
            polAper[i] = "2018-" + i + "-30";
            polVenc[i] = "2019-" + i + "-30";
        }
        String q;
        for(int i = 0; i < polId.length; i++){
            q = "INSERT TO POLIZA (POLIZAID, COSTO, PRIMA, FECHA_AP, FECHA_VEN, IDCLIENTE, VEHICULOID) VALUES (" + polId[i] + "," + polCost[i] + ",'" + polAper[i] + "','" + polVenc[i] + "'," + polCliId[i] + "," + polVehId[i] + ")";
            try{
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
            }catch(Exception e){}
        }
    }
    
    public void consClientsAll(JTable tablota){
        String q = "SELECT * FROM CLIENTE";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("DIRECCIÓN");
        tablota.setModel(modelo);
        String[] datos = new String[3];
        try{
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                modelo.addRow(datos);
            }
        }catch(Exception e){}
    }
    public void conFactsAll(JTable tablota){
        String q = "SELECT * FROM FACTURA";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("CLIENTES");
        tablota.setModel(modelo);
        String[] datos = new String[2];
        try{
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                modelo.addRow(datos);
            }
        }catch(Exception e){}
    }
    public void conClientCarCost(JTable tablota){
        String q = "SELECT NOMBRE, V.PLACAS, V.MODELO, F.MONTO FROM CLIENTE C JOIN POLIZA ON C.IDCLIENTE = POLIZA.IDCLIENTE JOIN VEHICULO V ON POLIZA.VEHICULOID = V.VEHICULOID JOIN FACTURA F ON V.FACTURAID = F.FACTURAID;";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PLACAS");
        modelo.addColumn("MODELO");
        modelo.addColumn("MONTO");
        tablota.setModel(modelo);
        String[] datos = new String[4];
        try{
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            }
        }catch(Exception e){}
    }
    public void conClientCar(JTable tablota, int idcliente){
        int i = 0;
        String q = "SELECT C.NOMBRE, C.DIRECCION, V.PLACAS FROM CLIENTE C JOIN CLIENTE C JOIN POLIZA P ON C.IDCLIENTE = P.IDCLIENTE JOIN VEHICULO V ON P.VEHICULOID = V.VEHICULOID WHERE C.IDCLIENTE = " + idcliente + ";";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("NOMBRE");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("PLACAS");
        tablota.setModel(modelo);
        String[] datos = new String[3];
        try{
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                modelo.addRow(datos);
            }
        }catch(Exception e){}
    }
    public void conClientPol(JTable tablota, int idcliente){
        String q = "SELECT C.NOMBRE, V.PLACAS, P.COSTO, P.PRIMA FROM cliente C JOIN POLIZA P ON C.IDCLIENTE = P.IDCLIENTE JOIN VEHICULO V ON P.VEHICULOID = V.VEHICULOID WHERE C.IDCLIENTE = " + idcliente + ";";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PLACAS");
        modelo.addColumn("COSTP");
        modelo.addColumn("PRIMA");
        tablota.setModel(modelo);
        String[] datos = new String[4];
        try{
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            }
        }catch(Exception e){}
    }
    public void conFechasPol(JTable tablota){
        String q = "SELECT P.FECHA_AP, P.FECHA_VEN FROM POLIZA P;";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FECHA APERTURA");
        modelo.addColumn("FECHA VENCIMIENTO");
        tablota.setModel(modelo);
        String[] datos = new String[2];
        try{
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                modelo.addRow(datos);
            }
        }catch(Exception e){}   
    }
    public void elMacho(JTable tablota){
        String q = "SELECT C.NOMBRE, V.PLACAS, F.MONTO, P.COSTO FROM CLIENTE C JOIN POLOZA P ON C.IDCLIENTE = P.IDCLIENTE JOIN VEHICULO V ON P.VEHICULOID = V.VEHICULOID JOIN FACTURA F ON V.FACTURAID = F.FACTURAID WHERE P.COSTO = (SELECT MAX(COSTO) FROM poliza);";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PLACAS");
        modelo.addColumn("MONTO");
        modelo.addColumn("COSTO");
        tablota.setModel(modelo);
        String[] datos = new String[4];
        try{
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            }
        }catch(Exception e){}        
    }
}

