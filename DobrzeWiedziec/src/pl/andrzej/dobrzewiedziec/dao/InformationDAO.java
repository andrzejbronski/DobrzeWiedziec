package pl.andrzej.dobrzewiedziec.dao;

import java.util.List;
import pl.andrzej.dobrzewiedziec.model.Information;

public interface InformationDAO extends GenericDAO<Information, Long>{

    List<Information> getAll();

}
