#pragma once
#include "Repository.h"
#include "ShoppingBasket.h"
#include <string>
#include <vector>
class Controller
{
private:
	Repository repo;
	ShoppingBasket basket;

public:
	Controller(const Repository& r, const ShoppingBasket& sp) : repo(r), basket(sp) {}

	//returns the Repository object
	Repository getRepo() const { return repo; }
	//returns the ShoppingBasket object
	ShoppingBasket getBasket() const { return basket; }

	/*
		Input:
			size - string that shows the size of the coat (s,m,l,xl,xxl)
			colour
			price
			quantity
			link - the link to the trench coat image
		Output:
			1 - if the product was added successfully
			0 - if the product already exists
	*/
	int addCoatToRepoC(const std::string size, const std::string colour, float price, int quantity,float length, const std::string link);
	
	/*
		It finds the trench coat using size and colour
		Input:
			size
			colour
		Output:
			1 - if the coat was removed successfully
			0 - if the coat doesn't exist
	*/
	int deleteCoatC(const std::string size, const std::string colour);

	/*
		it finds the coat using oldSize and oldColour, then it replaces with a new coat
		Input:
			oldSize
			oldColour
			size - string that shows the size of the coat (s,m,l,xl,xxl)
			colour
			price
			quantity
			link - the link to the trench coat image
		Output:
			1 - if the product was updated successfully
			0 - if the product doesn't exist
	*/
	int updateCoatC(const std::string oldSize, const std::string oldColour, const std::string size, const std::string colour, float price, int quantity,float length, const std::string link);
	
	void substractQuantityC(int pos) { this->repo.substractQuantityR(pos); }
	/*
		it shearches to trench coats which have the same size as the input size,the returns a DynamicArray with them
		Input:
			size
		output:
			1- if the coats were selected successfully
	*/
	std::vector<Coat> getSuitableCoats(const std::string size);

	//adds a 
	int addCoatToBasket(const Coat& c);

	~Controller();
};

