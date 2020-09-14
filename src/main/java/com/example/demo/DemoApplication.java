package com.example.demo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.bean.Book;
import com.example.demo.bean.Customer;

/**
 * @author sesi Jonnala
 *
 */
@SpringBootApplication
public class DemoApplication {
	private static final Logger logger = Logger.getLogger(DemoApplication.class.getName());
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<Customer> customers = new ArrayList<Customer>();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		DemoApplication demoApplication = new DemoApplication();

		demoApplication.addBook2Invontory(55, "Java", 33.00);
		demoApplication.addBook2Invontory(10, "Basic Java", 22.00);
		demoApplication.addBook2Invontory(20, "Advanced Java", 24.00);
		demoApplication.addBook2Invontory(30, "Java Cook book", 26.00);
		try {
			logger.log(Level.INFO, "Java:: " + demoApplication.getUnitsByBookName("Java"));
		} catch (Exception e) {
			logger.log(Level.WARNING, "" + e);
		}

		demoApplication.updateBookCount("Java", -10);

		try {
			logger.log(Level.INFO, "Java Cookook:: " + demoApplication.getUnitsByBookName("Java Cookook"));
		} catch (Exception e) {
			logger.log(Level.WARNING, "" + e);
		}

		try {
			logger.log(Level.INFO, "Advanced Java:: " + demoApplication.getUnitsByBookName("Advanced Java"));
			logger.log(Level.INFO, "Java Cook book:: " + demoApplication.getUnitsByBookName("Java Cook book"));
			logger.log(Level.INFO, "Basic Java:: " + demoApplication.getUnitsByBookName("Basic Java"));
			logger.log(Level.INFO, "Java:: " + demoApplication.getUnitsByBookName("Java"));
		} catch (Exception e) {
			logger.log(Level.WARNING, "" + e);
		}

		try {
			logger.log(Level.INFO, "Java:: " + demoApplication.getUnitsByBookName("Java"));
		} catch (Exception e) {
			logger.log(Level.WARNING, "" + e);
		}

		try {
			logger.log(Level.INFO, "Java:: " + demoApplication.getBookPriceByUnits("Java", 20));
		} catch (Exception e) {
			logger.log(Level.WARNING, "" + e);
		}

		logger.log(Level.INFO, "Java:: " + demoApplication.updateInventory("Java", "JAVA", 2.2, 2));

	}

	/**
	 * @param units
	 * @param bookName
	 * @param price
	 */
	public void addBook2Invontory(int units, String bookName, double price) {
		books.add(new Book(units, bookName, price));
	}

	/**
	 * @param bookName
	 * @return
	 */
	public int getUnitsByBookName(String bookName) throws Exception {
		int count = 0;
		for (Book num : books) {
			if (num.getTitle().contains(bookName)) {
				count = num.getUnits();
				return count;
			}
		}
		throw new IllegalArgumentException(String.format("Book not found"));
	}

	/**
	 * @param bookName
	 * @param units
	 * @return
	 */
	public double getBookPriceByUnits(String bookName, int units) {
		double price = 0;
		if (units >= 1) {
			for (Book num : books) {
				if (num.getTitle().contains(bookName)) {
					return price = num.getPrice() * units;
				}
			}
		}
		throw new IllegalArgumentException(String.format("unit should be 1 or greater"));
	}

	/**
	 * @param bookName
	 * @param unit
	 * @return
	 */
	public int updateBookCount(String bookName, int unit) {
		for (Book num : books) {
			if (num.getTitle().contains(bookName)) {
				num.setUnits(num.getUnits() + unit);
				unit = num.getUnits();
			}
		}
		return unit;
	}

	/**
	 * @param bookName
	 * @param price
	 * @return
	 */
	public double updateBookPrice(String bookName, double price) {
		for (Book num : books) {
			if (num.getTitle().contains(bookName)) {
				num.setPrice(num.getPrice() + price);
				price = num.getPrice();
			}
		}
		return price;
	}

	/**
	 * @param existingBookName
	 * @param newBookName
	 * @param price
	 * @param unit
	 * @return
	 */
	public ArrayList<Book> updateInventory(String existingBookName, String newBookName, double price, int unit) {
		if (!existingBookName.isEmpty() && !newBookName.isEmpty() && price >= 1.00 && unit >= 1) {
			for (Book num : books) {
				if (num.getTitle().contains(existingBookName)) {
					num.setTitle(newBookName);
					num.setPrice(num.getPrice() + price);
					num.setUnits(unit);
				}
				return books;
			}
		}
		throw new IllegalArgumentException(String.format("Book not found"));
	}
}
