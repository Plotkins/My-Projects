#include "ShoppingBasket.h"

ShoppingBasket::ShoppingBasket()
{
	this->priceSum = 0;
}

void ShoppingBasket::addItem(const Coat & c)
{
	int ok = 0;
	for (unsigned i=0;i<this->items.size();i++)
		if (this->items[i] == c)
			this->items[i].addQuantity(), ok = 1;

	if (ok == 0)
	{
		this->items.push_back(c);
		this->items.back().quantityOne();
	}
		

	this->priceSum += c.getPrice();
	
}

ShoppingBasket::~ShoppingBasket()
{
}
