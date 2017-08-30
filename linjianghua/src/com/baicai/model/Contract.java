package com.baicai.model;

import java.util.List;

import com.baicai.model.base.BaseContract;

@SuppressWarnings("serial")
public class Contract extends BaseContract<Contract>{
	public static final Contract me = new Contract();
	
	public List<Contract> findContract() {
		return find("select * from contract_pic order by picId asc");
	}
	
	public List<Contract> findContractByOrder(int orderId) {
		return find("select * from contract_pic where orderId = '"+orderId+"' order by picId asc");
	}
	
	public boolean findContractEmpty(int orderId) {
		return find("select * from contract_pic where orderId = '"+orderId+"' order by picId asc").isEmpty();
	}
}
