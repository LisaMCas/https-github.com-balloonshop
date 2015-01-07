package mk.ukim.finki.emk.balloonshop.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mk.ukim.finki.emk.balloonshop.model.Category;
import mk.ukim.finki.emk.balloonshop.model.Product;
import mk.ukim.finki.emk.balloonshop.model.User;
import mk.ukim.finki.emk.balloonshop.service.CategoryService;
import mk.ukim.finki.emk.balloonshop.service.ProductService;
import mk.ukim.finki.emk.balloonshop.service.UserService;
import mk.ukim.finki.emk.balloonshop.utils.AdminModelAndView;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Secured("ROLE_ADMIN")
@RequestMapping(value = "/admin")
@Controller
public class AdminController {

	FileItem small;
	FileItem large;
	public static final String FILEPATH = "C:\\Users\\Dejan\\Desktop\\FINKI\\eclipse proekti\\balloonshop\\src\\main\\webapp\\images\\";

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView view = new AdminModelAndView("admin");
		return view;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView usersGet() {
		ModelAndView view = new AdminModelAndView("users");
		view.addObject("users", userService.getAllUsers());
		view.addObject("user", new User());
		return view;
	}

	@RequestMapping(value = "/delete/user", method = RequestMethod.GET)
	public ModelAndView deleteUser(@RequestParam int id) {
		userService.deleteUser(id);
		ModelAndView view = new ModelAndView("redirect:/admin/users");
		view.addObject("users", userService.getAllUsers());
		return view;
	}

	@RequestMapping(value = "/add/user", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User user) {
		userService.addOrUpdateUser(user);
		ModelAndView view = new ModelAndView("redirect:/admin/users");
		return view;
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView productsGet() {
		ModelAndView view = new AdminModelAndView("products");
		view.addObject("products", productService.getAllProducts());
		return view;
	}

	@RequestMapping(value = "/delete/product", method = RequestMethod.GET)
	public ModelAndView deleteProduct(@RequestParam int id) {
		productService.deleteProduct(id);
		ModelAndView view = new ModelAndView("redirect:/admin/products");
		view.addObject("products", productService.getAllProducts());
		return view;
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView addProductGet() {
		ModelAndView view = new AdminModelAndView("add_product");
		view.addObject("product", new Product());
		return view;
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String addProductPost(@RequestParam String name,
			@RequestParam String description, @RequestParam double price,
			@RequestParam boolean onPromotion, Product p)
			throws ParseException, IOException {
		
		small = p.getFileSmallImage().getFileItem();
		large = p.getFileLargeImage().getFileItem();

		FileOutputStream smallImageOutputStream = null;
		FileOutputStream largeImageOutputStream = null;

		if (small.getSize() > 0) {
			smallImageOutputStream = new FileOutputStream(new File(FILEPATH+"t"+small.getName()));
			smallImageOutputStream.write(small.get());
			smallImageOutputStream.close();
		}

		if (large.getSize() > 0) {
			largeImageOutputStream = new FileOutputStream(new File(FILEPATH+large.getName()));
			largeImageOutputStream.write(large.get());
			largeImageOutputStream.close();
		}

		if (name != "" && description != "" && price == (double) price
				&& onPromotion == true || onPromotion == false) {
			Product product = new Product();

			product.setName(name);
			product.setDescription(description);
			product.setPrice(price);
			product.setOnPromotion(onPromotion);
			product.setSmallImage(small.getName().toString());
			product.setLargeImage(large.getName().toString());
			productService.addOrUpdateProduct(product);
			return "redirect:/admin/products";
		} else {
			return "redirect:/admin/product";
		}
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ModelAndView categoriesGet() {
		ModelAndView view = new AdminModelAndView("categories");
		view.addObject("categories", categoryService.getAllCategories());
		view.addObject("category", new Category());
		return view;
	}

	@RequestMapping(value = "/delete/category", method = RequestMethod.GET)
	public ModelAndView deleteCategory(@RequestParam int id) {
		categoryService.deleteCategory(id);
		ModelAndView view = new ModelAndView("redirect:/admin/categories");
		view.addObject("categories", categoryService.getAllCategories());
		return view;
	}

	@RequestMapping(value = "/add/category", method = RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute Category category) {

		categoryService.addOrUpdateCategory(category);
		ModelAndView view = new ModelAndView("redirect:/admin/categories");

		return view;
	}

	@RequestMapping(value = "/edit/categoryform/{categoryId}", method = RequestMethod.GET)
	public ModelAndView editCategoryForm(@PathVariable int categoryId) {
		ModelAndView view = new ModelAndView("/admin-pages/category_form");
		view.addObject("category", categoryService.getCategory(categoryId));
		view.addObject("edit", true);
		return view;
	}

	@RequestMapping(value = "/edit/userform/{userId}", method = RequestMethod.GET)
	public ModelAndView editUserForm(@PathVariable int userId) {
		ModelAndView view = new ModelAndView("/admin-pages/user_form");
		view.addObject("user", userService.getUser(userId));
		view.addObject("editUser", true);
		return view;
	}

}
