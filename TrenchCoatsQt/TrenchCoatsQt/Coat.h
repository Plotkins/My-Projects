#pragma once
#include <iostream>

class Coat
{
private:
	std::string size;
	std::string colour;
	float price;
	int quantity;
	float length;
	std::string link;

public:
	//default construtor
	Coat();

	//constructor with parameters
	Coat(const std::string& size,const std::string& colour, const float& price, const int& quantity,const float& length, const std::string& link);
	bool operator==(const Coat& c);
	bool operator<(const float length);
	//getters
	std::string getSize() const { return this->size; }
	std::string getColour() const { return this->colour; }
	float getPrice() const { return this->price; }
	int getQuantity() const { return this->quantity;}
	float getLength() const { return this->length; }
	std::string getLink() const { return this->link; }
	void substractQuantity() { this->quantity--; }
	void addQuantity() {this->quantity++;}
	void quantityOne() {	this->quantity = 1;	}

	//function that opens an image using a link
	void openImg();
	~Coat();
};

