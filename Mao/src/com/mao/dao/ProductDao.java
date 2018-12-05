package com.mao.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mao.domain.CategoryBean;
import com.mao.domain.Order;
import com.mao.domain.OrderItem;
import com.mao.domain.Product;
import com.mao.utils.DataSourceUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ProductDao {
	
	public List<Product> findTmallMarket() throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select id, image, title, money from product limit ?,?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class), 0, 8);
	}

	public List<Product> findTmallHK() throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select id, image, title, money from product limit ? offset ?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class), 8, 8);
	}

	public List<CategoryBean> findCategory() throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select cid, cateName from category";
		return runner.query(sql, new BeanListHandler<CategoryBean>(CategoryBean.class));
	}

	public Product findProductById(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select id, image, title, money, productImg from product where id = ?";
		return runner.query(sql, new BeanHandler<Product>(Product.class), id);
	}

	public void insetToOrders(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		runner.update(DataSourceUtils.getConnection(), sql, order.getOid(), order.getDate(), order.getTotal(),
				order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());
	}

	public void insertToOrderItem(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "insert into orderitem values(?,?,?,?,?)";
		List<OrderItem> orderList = order.getOrderItemList();
		for (OrderItem orderItem : orderList) {
			runner.update(DataSourceUtils.getConnection(), sql, orderItem.getItemId(), orderItem.getCount(),
					orderItem.getSubtotal(), orderItem.getProduct().getId(), orderItem.getOrder().getOid());
		}
	}

	public void saveOrderInfo(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "update orders set address =?,name = ?,telephone=? where oid = ?";
		runner.update(sql, order.getAddress(), order.getName(), order.getTelephone(), order.getOid());
	}

	public void pay(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "update orders set state = 1 where oid = ?";
		runner.update(sql, oid);
	}

	public List<Order> findOrderList(String uid) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select * from orders where uid = ?";
		return runner.query(sql, new BeanListHandler<Order>(Order.class), uid);
	}

	public List<Map<String, Object>> findOrderItemByOid(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select * from orderitem oi, product p where oi.pid=p.id and oid = ?";
		return runner.query(sql, new MapListHandler(), oid);
	}

	public void savePeoduct(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "insert into product values(null, ?, ?, ?, ?, ?) ";
		runner.update(sql, product.getImage(), product.getTitle(), product.getMoney(), product.getCategory().getCid(), product.getProductImg());
	}
	
	//所有获取商品
	public List<Product> findAllProduct(int limit, int offset) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select id, image, title, money from product limit ? offset ?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class), limit, offset*limit);
	}
	//获取所有商品总数
	public int findAllProductTotal() throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select count(*) from product";
		Long count = (Long) runner.query(sql, new ScalarHandler());
		return count.intValue();
	}
	
	
	public List<Order> findAllOrder(int offset) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select * from orders limit 10 offset ?";
		return runner.query(sql, new BeanListHandler<Order>(Order.class), offset);
	}

}
