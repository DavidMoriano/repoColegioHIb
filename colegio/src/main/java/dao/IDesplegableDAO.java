package dao;

import java.util.ArrayList;
import dto.DesplegableDTO;

public interface IDesplegableDAO {
	ArrayList<DesplegableDTO> desplegableMunicipios();

	ArrayList<DesplegableDTO> desplegableAlumnos();

	ArrayList<DesplegableDTO> desplegableAsignaturas();
}
