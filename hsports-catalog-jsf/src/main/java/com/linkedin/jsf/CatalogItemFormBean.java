package com.linkedin.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class CatalogItemFormBean implements Serializable{
	private CatalogItem item = new CatalogItem();
	private List<CatalogItem> items = new ArrayList<>();
	
	public String addItem() {
		long itemId = this.items.size() + 1;
		
		this.items.add(new CatalogItem(itemId, this.item.getName(), this.item.getManufacturer(), this.item.getDescription(), this.item.getAvailableDate()));
		
		// To see what's in the collection
		this.items.stream().forEach(item -> {
			System.out.println(item.toString());
		});
		
		// Redirection
		return "list?faces-redirect=true"; // The name of the facelet we navigate to once this method's logic is executed 
	}
	
	public CatalogItem getItem() {
		return item;
	}
	public void setItem(CatalogItem item) {
		this.item = item;
	}
	public List<CatalogItem> getItems() {
		return items;
	}
	public void setItems(List<CatalogItem> items) {
		this.items = items;
	}
}
