#pragma once
#include "Coat.h"
#include <vector>
class Repository
{
private:
	std::vector<Coat> coats;
public:
	Repository() {}

	void substractQuantityR(int pos) { this->coats[pos].substractQuantity(); }
	/*
	Input:
		c-Coat object which need to be added
	*/
	void addCoat(const Coat& c); 

	/*
	using size and colour we can distinguish the trench coats
	Input:
		size
		colour
	Output:
		A Coat object which was found
	*/
	Coat findBySizeAndColour(const std::string& size, const std::string& colour);

	/*
	using size and colour we can distinguish the trench coats
	Input:
		size
		colour
	Output:
		The position of the Coat object
	*/
	int findPosOfCoat(const std::string& size, const std::string& colour);

	/*
	using a position we can delete the Coat object on it
	Input:
		pot - int
	*/
	void deleteCoat(int pos);

	/*
	using a position, it replaces the object on it with a new object
	Input:
		pos 
		newCoat
	*/
	void updateCoat(int pos, Coat& newCoat);

	/*
		it returns all trench coats in t
	*/
	std::vector<Coat> getCoats() const { return this->coats; }

	~Repository();
};

