package training.osms.persistence;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.osms.business.Order;
import training.osms.business.OrderSearchOptions;

@Component
public class OrderDao {
	
	private @PersistenceContext EntityManager manager;
	
	public void insertOrder(Order order) {
		manager.persist(order); 	
	}
	
	public void updateOrder(Order order) {
		manager.merge(order); 
	}

	public void deleteOrder(Order order) {
		Order managedOrder = manager.find(Order.class, order.getId()); 
		manager.remove(managedOrder); // o manager s? consegue deletar os items q ele conhece.. por isso q preciso procurar pela categoria	
	}	
	
	public List<Order> searchOrder(OrderSearchOptions options) {

		StringBuilder predicate = toPredicate(options);

		if(options.getOrder() != null){
			predicate.append(" order by ord.");
			predicate.append(options.getOrder().getValue());
			if(options.isDesc()){
				predicate.append(" desc");
			}
		}		
		
		TypedQuery<Order> query = manager.createQuery(
				"SELECT ord FROM training.osms.business.Order ord where " + predicate, 
				Order.class);		

		setParameters(options, query);

		if (options.getStartPosition() != null){
			query.setFirstResult(options.getStartPosition());
		}			
		if (options.getMaxResults() != null){
			query.setMaxResults(options.getMaxResults());
		}			
		List<Order> result = query.getResultList();

		for (Order o: result){
			System.out.println(o.getOrderDate());
		}
		
		return result;		
	}

	public int searchOrderCount(OrderSearchOptions options) {

		StringBuilder predicate = toPredicate(options);			
		
		TypedQuery<Long> query = manager.createQuery(
				"SELECT count(ord) FROM training.osms.business.Order ord where " + predicate, 
				Long.class);		

		setParameters(options, query);

		Long result = query.getSingleResult();
System.out.println(result.intValue());
		return result.intValue();		

	}	


	private StringBuilder toPredicate(OrderSearchOptions options) {
		StringBuilder predicate = new StringBuilder("1 = 1");

		if (options.getId() != null){
			predicate.append(" and ord.id = :orderId");
		}
		
		if (options.getOrderDate() != null && options.getOrderDate().length() > 0){
			predicate.append(" and ord.orderDate BETWEEN :orderDate and :nextDay");
		}
		
		return predicate;
	}

	private void setParameters(OrderSearchOptions options, TypedQuery<?> query) {
		if (options.getId() != null){
			query.setParameter("orderId", options.getId());
		}

		if (options.getOrderDate() != null && options.getOrderDate().length() > 0){
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = formatter.parse(options.getOrderDate());
				
				Calendar c = Calendar.getInstance(); 
				c.setTime(date); 
				c.add(Calendar.DATE, 1);
				Date nextDay = c.getTime();				
				
				query.setParameter("orderDate", date);
				query.setParameter("nextDay", nextDay);
				
				System.out.println(date + " betweeen " + nextDay);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
