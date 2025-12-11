package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.IDesplegableDAO;
import dto.DesplegableDTO;
import utils.DBUtils;

public class DesplegableDAOImp implements IDesplegableDAO {

    @Override
    public ArrayList<DesplegableDTO> desplegableMunicipios() {
        String sql = "SELECT * FROM municipios ORDER BY nombre";
        ArrayList<DesplegableDTO> listaMunicipios = new ArrayList<>();

        try {
            Connection connection = DBUtils.conexion();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DesplegableDTO a = new DesplegableDTO(rs.getInt(1), rs.getString(5));
                listaMunicipios.add(a);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listaMunicipios;
    }

    @Override
    public ArrayList<DesplegableDTO> desplegableAlumnos() {
        String sql = "SELECT id, nombre FROM alumnos WHERE activo = 1 ORDER BY nombre";
        ArrayList<DesplegableDTO> listaAlumnos = new ArrayList<>();

        try {
            Connection connection = DBUtils.conexion();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Formato: "id - nombreAlumno"
                DesplegableDTO a = new DesplegableDTO(rs.getInt(1), rs.getInt(1) + " - " + rs.getString(2));
                listaAlumnos.add(a);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAlumnos;
    }

    @Override
    public ArrayList<DesplegableDTO> desplegableAsignaturas() {
        String sql = "SELECT id, nombre FROM asignaturas WHERE activo = 1 ORDER BY nombre";
        ArrayList<DesplegableDTO> listaAsignaturas = new ArrayList<>();

        try {
            Connection connection = DBUtils.conexion();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DesplegableDTO a = new DesplegableDTO(rs.getInt(1), rs.getString(2));
                listaAsignaturas.add(a);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAsignaturas;
    }

}
