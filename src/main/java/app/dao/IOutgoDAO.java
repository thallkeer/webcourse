package app.dao;

import app.entities.Outgo;

import java.sql.SQLException;
import java.util.List;

public interface IOutgoDAO {
    public void addOutgo(Outgo outgo);
    public List<Outgo> getOutgoesByEmpId(Integer emp_id) throws SQLException;
}
