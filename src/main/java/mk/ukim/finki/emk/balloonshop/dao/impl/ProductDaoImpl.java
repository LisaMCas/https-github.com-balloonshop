package mk.ukim.finki.emk.balloonshop.dao.impl;

import java.util.List;

import mk.ukim.finki.emk.balloonshop.dao.ProductDao;
import mk.ukim.finki.emk.balloonshop.model.Product;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addProduct(Product p) {
		getCurrentSession().save(p);
	}

	@Override
	public void deleteProduct(int id) {
		Product p = getProduct(id);
		if (p != null) {
			getCurrentSession().delete(p);
		}
	}

	@Override
	public void updateProduct(Product p) {
		Product product = getProduct(p.getId());
		if (product != null) {
			product.setCartProducts(p.getCartProducts());
			product.setCategories(p.getCategories());
			product.setDescription(p.getDescription());
			product.setLargeImage(p.getLargeImage()); // treba da se napravi za
														// upload
			product.setName(p.getName());
			product.setOnPromotion(p.isOnPromotion());
			product.setPrice(p.getPrice());
			product.setPurchaseDetails(p.getPurchaseDetails());
			product.setSmallImage(p.getSmallImage()); // isto za upload
			getCurrentSession().update(product);
		}
	}

	@Override
	public Product getProduct(int id) {
		return (Product) getCurrentSession().get(Product.class, id);
	}

	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) getCurrentSession().createQuery("from Product")
				.list();
	}

	@Override
	public List<Product> search(String keyword) {
		return (List<Product>) getCurrentSession()
				.createQuery("from Product where name LIKE :keyword")
				.setParameter("keyword", "%" + keyword + "%").list();
	}

	@Override
	public List<Product> getProductsInRange(int from, int max, String keyword) {
		Criteria query = getCurrentSession().createCriteria(Product.class).add(
				Restrictions.like("name", "%" + keyword + "%"));
		// createQuery(
		// "from Product where name LIKE :keyword").setParameter(
		// "keyword", "%" + keyword + "%");
		return query.setFirstResult(from).setMaxResults(max).list();
	}

	@Override
	public int getProductsCount(String keyword) {
		String count = getCurrentSession()
				.createQuery(
						"select count(*) from Product where name LIKE :keyword")
				.setParameter("keyword", "%" + keyword + "%").uniqueResult()
				.toString();

		return Integer.parseInt(count);
	}
}
