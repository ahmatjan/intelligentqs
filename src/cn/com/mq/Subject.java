package cn.com.mq;

import java.util.List;
import java.util.ArrayList;

public abstract class Subject {
	
	private List<Observe> list = new ArrayList<Observe>();
	
	public void attach(Observe observe){
		list.add(observe);
	}
	
	public void remove(Observe observe){
		list.remove(observe);
	}
	
	public void info(int id, String str){
		System.out.print("change");
		for(Observe observe : list){
			observe.update(id, str);
		}
	}
}
