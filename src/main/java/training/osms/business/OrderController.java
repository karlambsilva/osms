package training.osms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.osms.persistence.OrderDao;

@Component
public class OrderController {
	
	private @Autowired OrderDao dao;
	
	public OrderDao getDao() {
		return dao;
	}
	
	public void setDao(OrderDao dao) {
		this.dao = dao;
	}
	
	@Transactional
	public void saveOrder(Order order) {
		dao.insertOrder(order);		
	}

	public List<Order> searchOrder(OrderSearchOptions options) {
		return dao.searchOrder(options);
	}
	
	@Transactional
	public void deleteOrder(Order order) {
		throw new BusinessException("Ops! You cannot delete orders.");		
	}
	
	@Transactional
	public void updateOrder(Order order) {
		dao.updateOrder(order);
	}

	public Integer searchOrderCount(OrderSearchOptions options) {
		return dao.searchOrderCount(options);
	}

}
