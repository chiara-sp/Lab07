package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<Nerc> nercList;
	private List<PowerOutages> solution;
	private int maxAffected;
	List<PowerOutages> eventi;
		
	public Model() {
		podao = new PowerOutageDAO();
		this.nercList=podao.getNercList();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	public List<PowerOutages> getPowerOutagesByNerc(Nerc nerc){
		return podao.getPowerOutagesByNerc(nerc);
	}
	public List<PowerOutages> getWorstCase(int maxYears, int maxOutage, Nerc nerc){
		solution= new ArrayList<>();
		this.maxAffected=0;
		
		eventi= podao.getPowerOutagesByNerc(nerc);
		
		List<PowerOutages> parziale = new LinkedList<>();
		
		cerca(parziale,maxYears, maxOutage);
		
		return solution;
	}

	private void cerca(List<PowerOutages> parziale, int maxYears, int maxOutage) {
		
		//Condizione di terminazione
		if(sumAffectedPeople(parziale)>this.maxAffected) {
			this.maxAffected= sumAffectedPeople(parziale);
			solution= new ArrayList<>(parziale);
			
		}
		for(PowerOutages p: eventi) {
			if(!parziale.contains(p)) {
				parziale.add(p);
				if(checkYear(parziale,maxYears) && checkOutages(parziale, maxOutage)) {
					cerca(parziale,maxYears,maxOutage);
				}
				parziale.remove(p);
			}
		}
		
	}

	private boolean checkOutages(List<PowerOutages> parziale, int maxOutage) {
		int sum=0;
		for(PowerOutages p: parziale) {
			sum+=p.getDuration();
		}
		if(sum<=maxOutage)
			return true;
		return false;
	}

	private boolean checkYear(List<PowerOutages> parziale, int maxYears) {
		int diff=0;
		int annoMax=0;
		int annoMin=3000;
		for(PowerOutages p: parziale) {
			if(p.getYear()>annoMax)
				annoMax=p.getYear();
			if(p.getYear()<annoMin)
				annoMin=p.getYear();
			
		}
		diff=annoMax-annoMin;
		if(diff<=maxYears)
			return true;
		return false;
	}

	public int sumAffectedPeople(List<PowerOutages> parziale) {
		int sum=0;
		for(PowerOutages p: parziale) {
			sum+= p.getCustomer_affected();
		}
		return sum;
	}
	

}
