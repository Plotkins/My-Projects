#include "Controller.h"
#include "Coat.h"
#include <iostream>
#include <vector>

int Controller::addCoatToRepoC(const std::string size, const std::string colour, float price, int quantity, float length, const std::string link)
{
	Coat c = this->repo.findBySizeAndColour(size, colour);
	if ( c.getColour()==colour && c.getSize()==size)
		return 0;

	Coat coat{ size,colour,price,quantity,length,link };
	this->repo.addCoat(coat);

	return 1;
}

int Controller::deleteCoatC(const std::string size,const std::string colour)
{
	Coat c = this->repo.findBySizeAndColour(size, colour);
	if (c.getColour() == "" && c.getSize() == "")
		return 0;

	int pos = this->repo.findPosOfCoat(size, colour);
	this->repo.deleteCoat(pos);

	return 1;
}

int Controller::updateCoatC(const std::string oldSize, const std::string oldColour, const std::string size, const std::string colour, float price, int quantity,float length, const std::string link)
{
	Coat c = this->repo.findBySizeAndColour(oldSize, oldColour);
	if (c.getColour() == "" && c.getSize() == "")
		return 0;
	Coat test = this->repo.findBySizeAndColour(size, colour);
	if (test.getSize() == size && test.getColour() == colour)
		return 0;

	Coat newCoat{ size,colour,price,quantity,length,link };
	int pos = this->repo.findPosOfCoat(oldSize, oldColour);
	this->repo.updateCoat(pos, newCoat);

	return 1;
}

std::vector<Coat> Controller::getSuitableCoats(const std::string size)
{	
	std::vector<Coat> suitableCoats;
	std::vector<Coat> v = this->repo.getCoats();
	if (size == "")
		for (auto c:this->repo.getCoats())
			suitableCoats.push_back(c);
	else
		for (auto c : this->repo.getCoats())
			if (c.getSize() == size)
				suitableCoats.push_back(c);

	return suitableCoats;
}

int Controller::addCoatToBasket(const Coat & c)
{
	this->basket.addItem(c);
	return 1;
}

Controller::~Controller()
{
}
