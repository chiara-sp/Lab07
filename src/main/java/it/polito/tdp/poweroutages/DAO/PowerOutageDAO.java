package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutages> getPowerOutagesByNerc(Nerc nerc){
		String sql="select p.id, nerc_id, customers_affected, date_event_began, date_event_finished "
				+ "from poweroutages p "
				+ "where nerc_id=?";
		
		List<PowerOutages> result= new LinkedList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutages p= new PowerOutages(res.getInt("id"),nerc,res.getInt("customers_affected"), res.getTimestamp("date_event_began").toLocalDateTime(),
						res.getTimestamp("date_event_finished").toLocalDateTime());
				result.add(p);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

}
