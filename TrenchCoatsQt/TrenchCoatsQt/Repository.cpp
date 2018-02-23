#include "Repository.h"
#include <iostream>
#include <algorithm> 
using namespace std;

void Repository::addCoat(const Coat & c)
{
	this->coats.push_back(c);
}

Coat Repository::findBySizeAndColour(const std::string & size, const std::string & colour)
{
	if (this->coats.size() == 0)
		return Coat{};
	Coat sample{ size,colour,0,0,0,"" };
	for (auto c:this->coats)
		if (c==sample)
			return c;
	return Coat{};
}

int Repository::findPosOfCoat(const std::string & size, const std::string & colour)
{
	if (this->coats.size()==0)
		return -1;
	Coat sample{ size,colour,0,0,0,"" };
	for (unsigned int i = 0; i < this->coats.size(); i++)
	{
		Coat c = coats[i];
		if (c==sample)
			return i;
	}
	return -1;
}

void Repository::deleteCoat(int pos)
{
	this->coats.erase(this->coats.begin()+pos);
}

void Repository::updateCoat(int pos, Coat & newCoat)
{
	std::replace(this->coats.begin(), this->coats.end(), this->coats[pos], newCoat);
}
		
Repository::~Repository()
{
}
