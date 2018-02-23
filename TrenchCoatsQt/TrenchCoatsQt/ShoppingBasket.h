#pragma once
#include "Coat.h"
#include <vector>
class ShoppingBasket
{
private:
	float priceSum;
	std::vector<Coat> items;

public:
	ShoppingBasket();

	//it returns the total sum of the basket
	float getPriceSum() const { return this->priceSum; }

	/*
	it adds a an item to the basket
	Input:
		c - Coat object
	*/
	void addItem(const Coat& c);

	/*
	it returns all items from the basket
	*/
	std::vector<Coat> getItems() const { return this->items; }

	~ShoppingBasket();
};

